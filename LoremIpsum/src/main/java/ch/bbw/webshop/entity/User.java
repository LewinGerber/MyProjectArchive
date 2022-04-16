package ch.bbw.webshop.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Lewin Gerber
 * @version 12.01.2021
 * webshop
 */

@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    //email of the user
    private String email;
    //password of the user
    private String password;
    //string with items in cart (code -> productID#Quantity-)
    @Column(name = "cart_code")
    private String cartCode;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //GETTER & SETTER
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
        this.cartCode = cartCode;
    }
}
