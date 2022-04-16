package ch.bbw.pr.sospri;

import ch.bbw.pr.sospri.member.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * RegisterController
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Controller
public class RegisterController {
	@Autowired
	MemberService memberservice;
	@Autowired
	UrlService urlService;
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());

	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		System.out.println("getRequestRegisteredMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}

	public String createMember(RegisterMember registerMember) {
		//create a new member object
		Member newMember = new Member();
		//username check
		String username = registerMember.getPrename().toLowerCase() + '.' + registerMember.getLastname().toLowerCase();
		for (Member m : memberservice.getAll()){
			if(m.getUsername().equals(username)) {
				registerMember.setMessage("A user with this username already exists");
				return "register";
			}
		}
		newMember.setUsername(
				registerMember.getPrename().toLowerCase()
						+ '.' + registerMember.getLastname().toLowerCase());
		newMember.setPrename(registerMember.getPrename());
		newMember.setLastname(registerMember.getLastname());

		// date
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		newMember.setDate(sqlDate);

		//challenge number
		newMember.setChallengeNumber(registerMember.getChallengeNumber());

		//validate password entry
		if (registerMember.getPassword().equals(registerMember.getConfirmation())) {
			newMember.setPassword(bCryptPasswordEncoder.encode(registerMember.getPassword()));
		} else {
			registerMember.setMessage("The passwords don't match");
			return "register";
		}
		newMember.setAuthority("member");

		//save to db
		memberservice.add(newMember);
		return "registerconfirmed";
	}
	
	@PostMapping("/get-register")
	public String postRequestRegistMembers(@Valid RegisterMember registerMember, BindingResult bindingResult) {
		System.out.println("postRequestRegistMembers: registerMember");
		if(bindingResult.hasErrors()) return "register";
		System.out.println(registerMember);
		return createMember(registerMember);
	}

	// reset process in registration controller because of password encoder

	@GetMapping("/reset-password")
	public String resetPassword(@RequestParam String url, Model model) {
		urlService.setRequestedUrl(url);
		model.addAttribute("passwordReset", new PasswordReset());
		return "passwordReset";
	}

	@GetMapping("/reset-password-challenge")
	public String resetPasswordRequest(Model model) {
		model.addAttribute("passwordReset", new PasswordReset());
		return "passwordResetChallenge";
	}

	@PostMapping("/reset-password-challenge")
	public String resetPassword(@Valid PasswordReset passwordReset, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) return "passwordResetChallenge";

		// check if the password match
		if(!passwordReset.getPassword().equals(passwordReset.getPasswordConfirmation())) {
			passwordReset.setMessage("The new passwords don't match.");
			return "passwordResetChallenge";
		}

		if(passwordReset.getUsername() != null) {
			Member member = memberservice.getByUserName(passwordReset.getUsername());
			if(passwordReset.getChallengeNumber() == member.getChallengeNumber()) {
				member.setPassword(bCryptPasswordEncoder.encode(passwordReset.getPassword()));

				// date
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				member.setDate(sqlDate);

				memberservice.add(member);
				return "redirect:/";
			}
			passwordReset.setMessage("The credentials aren't correct");
			return "passwordResetChallenge";
		}
		return "redirect:/";
	}

	@PostMapping("/reset-password")
	public String resetPassword(
			@Valid PasswordReset passwordReset,
			String requestedUrl,
			Authentication auth,
			BindingResult bindingResult,
			Model model) throws IOException {
		if(bindingResult.hasErrors()) return "passwordReset";

		// check if the password match
		if(!passwordReset.getPassword().equals(passwordReset.getPasswordConfirmation())) {
			passwordReset.setMessage("The new passwords don't match.");
			return "passwordReset";
		}

		if(auth.isAuthenticated()) {
			Member member = memberservice.getByUserName(auth.getName());
			if(bCryptPasswordEncoder.matches(passwordReset.getOldPassword(), member.getPassword())){
				System.out.println("Password reset");
				member.setPassword(bCryptPasswordEncoder.encode(passwordReset.getPassword()));

				// date
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				member.setDate(sqlDate);

				memberservice.add(member);
			} else {
				passwordReset.setMessage("Password reset aborted. The old password isn't correct.");
				return "passwordReset";
			}
		}
		if(!urlService.getRequestedUrl().equals(""))
			return "redirect:" + urlService.getRequestedUrl();
		return "redirect:/";
	}
}