package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService {

    void save(User user);
    void delete(long id);
    List<User> findAll();
    User findById(long id);

    void update(long id, User user);

}
