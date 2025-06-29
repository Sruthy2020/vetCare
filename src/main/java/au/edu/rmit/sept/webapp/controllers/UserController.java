package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;

@Controller
@SessionAttributes({"loggedInUser", "userType"})
public class UserController {
    private final UserService userService;
    private final PetInformationService petInfoService;

    @Autowired
    public UserController(UserService userService, PetInformationService petInfoService) {
        this.userService = userService;
        this.petInfoService = petInfoService;
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("errorMessage", "Email already exists! Please use a different email.");
            System.out.println("Email already exists! Please use a DIFFERENT email!");
            return "SignUp";
        }else{
            userService.createUser(user);
            return "redirect:/";
        }

    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        boolean isValidUser = userService.verifyUser(email, password);
        if (isValidUser) {
            User user = userService.findByEmail(email);
            // storing user info in the session to maintain login state
            request.getSession().setAttribute("loggedInUser", user);
            request.getSession().setAttribute("userEmail", email);
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("userType", user.getUserType());

            // added logged-in user to the model to pass data to the view
            model.addAttribute("loggedInUser", user);
            model.addAttribute("userType", user.getUserType());
            model.addAttribute("successMessage", "Logged in sucessfully");
            System.out.println("Logged in successfully!!!!");
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "Login";
        }
    }

    @GetMapping("/account")
    public String getUserProfile(Model model, @ModelAttribute("loggedInUser") User loggedInUser) {
        model.addAttribute("user", loggedInUser);
        return "Account";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        // terminating the session
        request.getSession().invalidate();
        System.out.println("Logged out!!!");
        // for proper navbar after logging out
        model.addAttribute("loggedInUser", null);
        model.addAttribute("userType", null);
        return "redirect:/";
    }

    @PostMapping("/account/pet-register")
    public String registerPet(@ModelAttribute PetInformation petInformation, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            petInformation.setOwnerId(loggedInUser.getId());
            petInfoService.createPetInformation(petInformation);
            System.out.println("Pet registered successfully!!!!");
        }
        return "redirect:/";
    }

    @PostMapping("/account/edit")
    public String editUser(@ModelAttribute User user, HttpServletRequest request, Model model) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            user.setId(loggedInUser.getId());
            User u = userService.updateUser(user);
            request.getSession().setAttribute("loggedInUser", u);
            // added updated user to the model to pass updated data to the view
            model.addAttribute("loggedInUser", u);
            System.out.println("Account updated successfully!!!!");
            return "redirect:/";
        }
        return "redirect:/account";
    }



    @PostMapping("/account/delete")
    public String deleteUser(HttpServletRequest request, Model model) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            userService.deleteUser(loggedInUser.getEmail());
            request.getSession().invalidate();
            System.out.println("Account deleted successfully!!!!");
            model.addAttribute("loggedInUser", null);
            model.addAttribute("userType", null);

            return "redirect:/";
        }
        return "redirect:/account";
    }


}