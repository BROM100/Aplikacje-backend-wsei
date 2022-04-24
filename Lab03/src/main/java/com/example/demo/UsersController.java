package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
public class UsersController {

    private static final int PAGE_NUMBER = 1;
    private static final int PAGE_SIZE = 2;

    private final List<UserEntity> users = new LinkedList<>();

    //Create default users map
    @PostConstruct
    private void Create() {
        users.add(new UserEntity(1L, "Romek", "romek@romek.pl"));
        users.add(new UserEntity(2L, "Tomek", "tomek@tomek.pl"));
        users.add(new UserEntity(3L, "Atomek", "atomek@atomek.pl"));
    }


    @RequestMapping("/users/{id}/get")
    public UserEntity GetUserById(
            @PathVariable Long id
    ) {
        return users.stream()
                .filter(userEntity -> userEntity.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Not found user for given id " + id));
    }


    @GetMapping("/users")
    public UsersResults GetUsers(@RequestParam(name = "page-number", required = false) Integer pageNumberParam,
                           @RequestParam(name = "page-size", required = false) Integer pageSizeParam) {
        int pageNumber = pageNumberParam != null ? pageNumberParam : PAGE_NUMBER;
        int pageSize = pageSizeParam != null ? pageSizeParam : PAGE_SIZE;
        int offset = (pageNumber - 1) * pageSize;
        return new UsersResults(pageNumber, users.size() / pageSize, pageSize, users.size(),
                subList(users, offset, pageSize));
    }

    public static <T> List<T> subList(List<T> it, int offset, int limit) {
        if (it == null) {
            return it;
        }
        if (it.size() <= offset) {
            return Collections.emptyList();
        }
        if (limit < 0) {
            limit = it.size();
        }
        if (offset < 0) {
            offset = 0;
        }
        limit = offset + limit > it.size() ? it.size() - offset : limit;
        return (limit == it.size() && offset == 0) ? it : it.subList(offset, offset + limit);
    }


    @GetMapping("/users/{id}/remove")
    public Boolean RemoveUserById(
            @PathVariable Long id
    ) {
        UserEntity userEntity = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Not found user for given id " + id));
        return users.remove(userEntity);
    }

}