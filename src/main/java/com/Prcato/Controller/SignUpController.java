package com.Prcato.Controller;

import com.Prcato.Entity.User;
import com.Prcato.Payload.UserDto;
import com.Prcato.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showSignUpForm() {
        return "signup"; // Return the signup view (signup.html)
    }

    @PostMapping
    public String signUp(@ModelAttribute UserDto userDto , Model model) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            model.addAttribute("signupError", "Username already exists." +
                    " Please choose a different one.");
            return "signup";
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        userRepository.save(user);
        return "redirect:/login"; // Redirect to login page after successful signup
    }
}
