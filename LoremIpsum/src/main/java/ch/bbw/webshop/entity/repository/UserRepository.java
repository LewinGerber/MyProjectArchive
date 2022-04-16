package ch.bbw.webshop.entity.repository;

import ch.bbw.webshop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Lewin Gerber
 * @version 12.01.2021
 * webshop
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
