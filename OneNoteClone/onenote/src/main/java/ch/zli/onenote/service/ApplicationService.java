package ch.zli.onenote.service;

import ch.zli.onenote.entities.User;

public interface ApplicationService {
    // check if user with these creds exists
    public User validateCredentials(String email ,String password);

    // create new JWT Token
    public String generateJWTToken(User user);
}
