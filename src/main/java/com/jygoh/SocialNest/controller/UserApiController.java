package com.jygoh.SocialNest.controller;

import com.jygoh.SocialNest.dto.AddUserRequest;
import com.jygoh.SocialNest.repository.UserRepository;
import com.jygoh.SocialNest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user")
    public String signup(AddUserRequest request, Model model) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            model.addAttribute("emailExistsError", "이미 존재하는 이메일입니다.");
            return "signup()";
        }
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}
