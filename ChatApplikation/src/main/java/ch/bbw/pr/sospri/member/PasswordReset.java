package ch.bbw.pr.sospri.member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Lewin Gerber
 * @version 06.04.2021
 * 50_02_SpringBootChatAppSoSpriV01
 */
public class PasswordReset {
    private String oldPassword;

    @NotEmpty (message = "new password may not be empty" )
    @Size(min=2, max=25, message="Die Länge des Nachnamens 2 bis 25 Zeichen sein.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-._;:]).{8,}$", message="Das Passwort sollte Klein- und Grossbuchstaben sowie Sonderzeichen erhalten. Eine Länge von 8 Zeichen ist das Minimum.")
    private String password;

    @NotEmpty(message = "password confirmation may not be empty" )
    private String passwordConfirmation;

    private String username;

    private String message;

    private String requestedUrl;

    private int challengeNumber;

    //GETTER & SETTER
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public int getChallengeNumber() {
        return challengeNumber;
    }

    public void setChallengeNumber(int challengeNumber) {
        this.challengeNumber = challengeNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
