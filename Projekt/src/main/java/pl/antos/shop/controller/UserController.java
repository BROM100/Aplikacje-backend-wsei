package pl.antos.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.antos.shop.model.User;
import pl.antos.shop.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userRepository.save(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User _user = optional.get();
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setGender(user.getGender());
            _user.setEmail(user.getEmail());
            _user.setPhoneNumber(user.getPhoneNumber());
            // TODO Hash password
            _user.setPassword(user.getPassword());
            _user.setStreet(user.getStreet());
            _user.setStreetNumber(user.getStreetNumber());
            _user.setCity(user.getCity());
            _user.setPostalCode(user.getPostalCode());
            _user.setCountryCode(user.getCountryCode());
            _user.setActive(user.isActive());
            _user.setConfirmed(user.isConfirmed());

            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") UUID id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/users/enable/{id}")
    public ResponseEntity<User> enableUser(@PathVariable("id") UUID id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User _user = optional.get();
            _user.setActive(true);
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/disable/{id}")
    public ResponseEntity<User> disableUser(@PathVariable("id") UUID id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User _user = optional.get();
            _user.setActive(false);
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/confirm/{id}")
    public ResponseEntity<User> confirmUser(@PathVariable("id") UUID id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User _user = optional.get();
            _user.setConfirmed(true);
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}