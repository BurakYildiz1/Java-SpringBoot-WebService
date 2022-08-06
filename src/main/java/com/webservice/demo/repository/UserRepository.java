package com.webservice.demo.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webservice.demo.model.LoginModel;
import com.webservice.demo.model.UserModel;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRepository {
    public static List<UserModel> getUsers(){
        List<UserModel> users = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/static/Usersdata.json"));

            // convert JSON array to list of users
            users = gson.fromJson(reader, new TypeToken<List<UserModel>>() {}.getType());

            // return users

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users.stream().filter(p->p.isActive()).collect(Collectors.toList());
    }
    public static UserModel tryLogin(LoginModel loginModel){
        try {
            return  getUsers().stream().filter(x-> Objects.equals(x.geteMail(), loginModel.geteMail()) && Objects.equals(x.getPassword(), loginModel.getPassword())).findFirst().get();
        }
        catch (Exception e){
            return null;
        }

    }
}
