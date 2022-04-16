package ch.bbw.webshop.entity;

import ch.bbw.webshop.entity.repository.ProductRepository;
import ch.bbw.webshop.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Lewin Gerber
 * @version 12.01.2021
 * webshop
 */

@Service
public class DBManager {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    /*
        USER - METHODS
    */
    public User findUser(int id) {
        Optional opt = userRepository.findById(id);
        return opt.isPresent() ? (User) opt.get() : null;
    }

    public User findUser(User u) {
        Optional opt = userRepository.findById(u.getId());
        return opt.isPresent() ? (User) opt.get() : null;
    }

    public User findUser(String email) {
        Optional opt = userRepository.findByEmail(email);
        return opt.isPresent() ? (User) opt.get() : null;
    }

    public void saveUser(User u) {
        userRepository.save(u);
    }

    /*
        PRODUCT - METHODS
    */
    public List<Product> findAllProducts() {
        return (List) productRepository.findAll();
    }
}
