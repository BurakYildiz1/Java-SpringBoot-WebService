package com.pxp.SQLite.demo.controller;

import com.pxp.SQLite.demo.entity.Login;
import com.pxp.SQLite.demo.entity.User;
import com.pxp.SQLite.demo.repository.UserRepository;
import com.pxp.SQLite.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //@RequestMapping(value = "info",method = RequestMethod.GET)
    @PostMapping("/login")
    public boolean tryLogin(@RequestBody Login user){

        User usr= userRepository.findAll().stream().filter(x-> Objects.equals(x.geteMail(),user.geteMail() )&& Objects.equals(x.getPassword(),user.getPassword())).findFirst().get();

        if(usr!=null)
            return true;

        return false;
    }


    @GetMapping("/info")
    public String Info(){
        return "The application is up!";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    public List<User> getUserList(){
        return userService.readUsers();
    }

    @RequestMapping(value = "/updateuser",method = RequestMethod.PUT)
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


    @RequestMapping(value = "/deleteuser",method = RequestMethod.DELETE)
    public String deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }



}
