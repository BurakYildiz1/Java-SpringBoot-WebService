package com.webservice.demo.controller;
import com.webservice.demo.model.LoginModel;
import com.webservice.demo.model.UserModel;
import com.webservice.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String Index() {
        return "hello world";
    }


    @PostMapping("/login")
    public UserModel Index(@RequestBody LoginModel loginModel){
        return UserRepository.tryLogin(loginModel);
    }

    @GetMapping("/getUsers")
    public List<UserModel> GetAll(){
        return UserRepository.getUsers();
    }
}