package ch.bbw.webshop.security;

import ch.bbw.webshop.entity.DBManager;
import ch.bbw.webshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version 12.01.2020
 * webshop - WebSecurityConfig
 */

@Service
public class UserServiceSecurity implements UserDetailsService {

    @Autowired
    private DBManager DBMANAGER;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User searchUser = DBMANAGER.findUser(email);
        if(searchUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(searchUser.getEmail(), searchUser.getPassword(), new ArrayList<>());
    }
}
