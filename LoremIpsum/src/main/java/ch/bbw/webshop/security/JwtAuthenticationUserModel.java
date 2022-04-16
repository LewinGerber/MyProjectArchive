package ch.bbw.webshop.security;

/**
 * @version 12.01.2020
 * webshop - AuthenticationUserModel
 * Responsible for temporarily managing user credentials (=> secure against XSS)
 */

public class JwtAuthenticationUserModel {
    private String email;
    private String password;

    public JwtAuthenticationUserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}