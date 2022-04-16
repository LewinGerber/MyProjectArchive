package ch.bbw.pr.sospri;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lewin Gerber
 * @version 14.04.2021
 * 50_02_SpringBootChatAppSoSpriV01
 */

@Controller
public class ApplicationErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorMapping(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if(statusCode == 404) return "404_error_page";

        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
