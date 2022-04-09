package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.Map;

@Controller
public class UsersController {

    private final Map<Long, UserEntity> users = new HashMap<>();

    //Create default users map
    @PostConstruct
    private final void Create() {

        users.put(1L, new UserEntity(1L, "Romek", "romek@romek.pl"));
        users.put(2L, new UserEntity(2L, "Tomek", "tomek@tomek.pl"));
        users.put(3L, new UserEntity(3L, "Atomek", "atomek@atomek.pl"));
    }


    @RequestMapping("/users/{id}/get")
    @ResponseBody

    public Object GetUserById(
            @PathVariable Long id
    ) {
        return users.get(id);
    }


    @RequestMapping("/users")
    @ResponseBody

    public Object GetUsers(

    ) {
        return users;
    }


    @RequestMapping("/users/{id}/remove")
    @ResponseBody

    public Object RemoveUserById(
            @PathVariable Long id
    ) {
        return users.remove(id);
    }


//    @RequestMapping("/users/add")
//    @ResponseBody
//    public Object AddUser(
//            @RequestParam Long id,
//            @RequestParam String name
//    ) {
//        users.put(id, new UserEntity(id, name));
//        return "added user: " + name;
//    }
}