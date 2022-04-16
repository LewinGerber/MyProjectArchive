package ch.bbw.webshop;

import ch.bbw.webshop.entity.DBManager;
import ch.bbw.webshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Lewin Gerber
 * @version 28.11.2020
 * webshop
 */

@Service
public class WebService {

    @Autowired
    private DBManager DBMANAGER;

    /*
        update the cart code
    */
    public void saveCartCode(String email, String cartCode) {
        User user = DBMANAGER.findUser(email);
        user.setCartCode(cartCode);
        DBMANAGER.saveUser(user);
    }

    /*
        get the cart code
    */
    public String getCartCode(String email) {
        return DBMANAGER.findUser(email).getCartCode();
    }

}
