package ch.bbw.pr.sospri.member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * To regist a new Member
 * @author peter.rutschmann
 * @version 27.04.2020
 */
public class RegisterMember {
	@NotEmpty(message = "prename may not be empty" )
	@Size(min=2, max=25, message="Die Länge des Vornamens muss 2 bis 25 Zeichen sein.")
	private String prename;

	@NotEmpty (message = "lastname may not be empty" )
	@Size(min=2, max=25, message="Die Länge des Nachnamens 2 bis 25 Zeichen sein.")
	private String lastname;

	@NotEmpty(message = "password may not be empty" )
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-._;:]).{8,}$", message="Das Passwort sollte Klein- und Grossbuchstaben sowie Sonderzeichen erhalten. Eine Länge von 8 Zeichen ist das Minimum.")
	private String password;

	private String confirmation;
	private String message;
	private int challengeNumber = 123;
	
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public int getChallengeNumber() {
		return challengeNumber;
	}

	public void setChallengeNumber(int challengeNumber) {
		this.challengeNumber = challengeNumber;
	}

	@Override
	public String toString() {
		return "RegisterMember [prename=" + prename + ", lastname=" + lastname + ", password=" + password
				+ ", confirmation=" + confirmation + ", challengeNumber=" + challengeNumber +"]";
	}
}
