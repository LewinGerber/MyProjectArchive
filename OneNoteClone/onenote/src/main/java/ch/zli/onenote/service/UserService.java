package ch.zli.onenote.service;

import ch.zli.onenote.entities.User;
import ch.zli.onenote.entities.UserRole;

import java.util.List;

public interface UserService {
    // get user from DB by id
    public User getUser(int id);

    // get all users from db
    public List<User> getAllUsers();

    // register a new user
    public User registerUser(User user);

    // remove user from db
    public boolean removeUser(int id);

    // edit a user
    public User editUser(User user);

    // get user by email
    public User getUser(String email);

    // get all roles
    public List<UserRole> getUserRoles();
}
