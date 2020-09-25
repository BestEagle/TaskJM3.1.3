package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    public User findByEmail(String email);

    User save(User user);

    User findById(int id);

    Iterable<User> findAll();

    void deleteById(int id);


}
