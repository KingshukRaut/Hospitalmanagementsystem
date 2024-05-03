package com.Prcato.Controller;

import com.Prcato.Entity.User;
import com.Prcato.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Autowired the PasswordEncoder

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the login view (login.html)
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            // Create an authentication token and authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Redirect to the home page after successful login
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password. Please try again.");
            return "login"; // Return the login view with an error message
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the session and clear the security context
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/login"; // Redirect to the login page
    }
}
