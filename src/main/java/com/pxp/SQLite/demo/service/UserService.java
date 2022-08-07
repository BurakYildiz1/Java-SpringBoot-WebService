package com.pxp.SQLite.demo.service;

import com.pxp.SQLite.demo.entity.Login;
import com.pxp.SQLite.demo.entity.User;
import com.pxp.SQLite.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /*public Boolean tryLogin(Login user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            try {
                List<User> users = userRepository.findByEmail(user.getEmail());
                users.stream().forEach(s -> {
                    userRepository.findByEmail(user.getEmail());
                });
                return "User record deleted successfully.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "User does not exist";
        }

        return true;
    }*/

    @Transactional
    public String createUser(User user){
        try {
            if (!userRepository.existsByeMail(user.geteMail())){
                user.setId(null == userRepository.findMaxId()? 0 : userRepository.findMaxId() + 1);
                userRepository.save(user);
                return "User record created successfully.";
            }else {
                return "User already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<User> readUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public String updateUser(User user){
        if (userRepository.existsByeMail(user.geteMail())){
            try {
                List<User> users = userRepository.findByeMail(user.geteMail());
                users.stream().forEach(s -> {
                    User userToBeUpdate = userRepository.findById(s.getId()).get();
                    userToBeUpdate.setFullName(user.getFullName());
                    userToBeUpdate.seteMail(user.geteMail());
                    userRepository.save(userToBeUpdate);
                });
                return "User record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "User does not exists in the database.";
        }
    }

    @Transactional
    public String deleteUser(User user){
        if (userRepository.existsByeMail(user.geteMail())){
            try {
                List<User> users = userRepository.findByeMail(user.geteMail());
                users.stream().forEach(s -> {
                    userRepository.delete(s);
                });
                return "User record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "User does not exist";
        }
    }

}
