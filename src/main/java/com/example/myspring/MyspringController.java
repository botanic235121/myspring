package com.example.myspring;

import com.example.myspring.domain.Users;
import com.example.myspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MyspringController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "MYTEST") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("getusers")
    public String getUsers(Map<String, Object> model) {
        Iterable<Users> users = userRepository.findAll();
        model.put("users", users);
        return "index";
    }

    /*@GetMapping("/body")
    public @ResponseBody
    Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }*/

    @PostMapping
    public String addUser(
            @RequestParam String name, @RequestParam Integer age,
            @RequestParam String email, Map<String, Object> model) {
        Users user = new Users(name, age, email);
        userRepository.save(user);
        Iterable<Users> users = userRepository.findAll();
        model.put("users", users);
        return "index";
    }

    @PostMapping("/filter")
    public String filter (@RequestParam String filter, Map<String,Object> model){
        Iterable<Users> users;

        if (filter != null && !filter.isEmpty()){
            users = userRepository.findByName(filter);
        }else{
            users = userRepository.findAll();
        }
        model.put("users",users);
        return "index";
    }

}
