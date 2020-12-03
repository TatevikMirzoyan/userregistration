package com.registration.userregistration.service;

import com.registration.userregistration.model.User;
import com.registration.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tatevik Mirzoyan
 * Created on 03-Dec-20
 */
@Service
public class UserService {
    @Autowired
    UserRepository repo;

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id " + id));
    }

    public void addUser(User user) {
        repo.save(user);
    }

    public void deleteUser(User user) {
        repo.delete(user);
    }

    public void updateUser(long id, User user) {
        user.setId(id);
        repo.save(user);
    }
}
