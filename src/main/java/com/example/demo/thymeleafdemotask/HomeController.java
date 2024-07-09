package com.example.demo.thymeleafdemotask;

import com.example.demo.thymeleafdemotask.model.User;
import com.example.demo.thymeleafdemotask.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    EmailService emailservice;
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute User user, Model model) throws MessagingException {
        model.addAttribute("user", user);
        emailservice.sendmailAttachment(user.getToemail(),user.getCc(),user.getSubject(),user.getBody(),user.getFilepath());
        model.addAttribute("message","email send Success...");
        return "result";
    }
}
