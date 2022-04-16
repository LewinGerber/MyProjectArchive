package ch.zli.onenote.service;

import ch.zli.onenote.entities.Content;
import ch.zli.onenote.entities.User;
import ch.zli.onenote.entities.UserRole;
import ch.zli.onenote.entities.repositories.ContentRepository;
import ch.zli.onenote.entities.repositories.UserRepository;
import ch.zli.onenote.entities.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public User getUser(int id) {
       Optional<User> user = userRepository.findById(id);
       if (user.isPresent())
           return user.get();
       return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List) userRepository.findAll();
        return users;
    }

    @Override
    public User registerUser(User user) {
        // get all possible roles - and set default role
        UserRole role = getUserRoles().get(0);
        user.setUserRole(role);
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public boolean removeUser(int id) {
        // get user with id
        Optional<User> userOpt = userRepository.findById(id);
        // check if user exists
        if (!userOpt.isPresent() || userOpt == null)
            return false;
        User user = userOpt.get();
        List<Content> contentList = contentRepository.findByUserId(id);
        /* */
        Content content = contentList.get(0);
        contentRepository.delete(content);

        /*// delete content manually
        Content content = user.getContent();
        content.setUser(null);
        contentRepository.save(content);
        // remove user role
        user.setUserRole(null);
        // delete user*/
        userRepository.delete(user);
        return true;
    }

    @Override
    public User editUser(User user) {
        User editedUser = userRepository.save(user);
        return editedUser;
    }

    @Override
    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<UserRole> getUserRoles() {
        return (List) userRoleRepository.findAll();
    }

    /*public void createUsers() {
        // USER 1
        User user1 = new User();
        user1.setEmail("mail@example.com");
        user1.setPassword("$2a$12$92ZX3usZr7t2MCL.PDe8vOR6rVWT8PExPkcLzVMggNk21EcbRi24K");
        user1.setContent(new Content());

        // USER 2
        User user2 = new User();
        user2.setEmail("mail2@example.com");
        user2.setPassword("$2a$12$92ZX3usZr7t2MCL.PDe8vOR6rVWT8PExPkcLzVMggNk21EcbRi24K");
        user2.setContent(new Content());

        // USER 3
        User user3 = new User();
        user3.setEmail("mail3@example.com");
        user3.setPassword("$2a$12$92ZX3usZr7t2MCL.PDe8vOR6rVWT8PExPkcLzVMggNk21EcbRi24K");
        user3.setContent(new Content());

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }*/
}
