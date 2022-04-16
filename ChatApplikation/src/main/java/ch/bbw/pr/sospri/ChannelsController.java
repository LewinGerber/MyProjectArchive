package ch.bbw.pr.sospri;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ch.bbw.pr.sospri.member.Member;
import ch.bbw.pr.sospri.member.MemberService;
import ch.bbw.pr.sospri.message.Message;
import ch.bbw.pr.sospri.message.MessageService;

/**
 * ChannelsController
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Controller
public class ChannelsController {
	@Autowired
	MessageService messageservice;
	@Autowired
	MemberService memberservice;

	@GetMapping("/get-channel")
	public String getRequestChannel(Model model) {
		System.out.println("getRequestChannel");
		model.addAttribute("messages", messageservice.getAll());
		
		Message message = new Message();
		message.setContent("");
		System.out.println("message: " + message);
		model.addAttribute("message", message);
		return "channel";
	}

	//ResponseBody
	//@RequestMapping(value = "/add-message", method = RequestMethod.POST)
	@PostMapping("/add-message")
	public String postRequestChannel(Model model, Authentication auth, @ModelAttribute @Valid Message message, BindingResult bindingResult) {
		System.out.println("postRequestChannel(): message: " + message.toString());
		if(bindingResult.hasErrors()) {
			System.out.println("postRequestChannel(): has Error(s): " + bindingResult.getErrorCount());
			model.addAttribute("messages", messageservice.getAll());
			return "channel";
		}

		if(auth.isAuthenticated()) {
			Member member = memberservice.getByUserName(auth.getName());
			message.setAuthor(member.getPrename() + " " + member.getLastname());
			message.setOrigin(new Date());
			System.out.println("message accepted: " + message);
			messageservice.add(message);
		} else {
			System.out.println("message rejected (not authenticated): " + message);
		}
		
		return "redirect:/get-channel";
	}
}
