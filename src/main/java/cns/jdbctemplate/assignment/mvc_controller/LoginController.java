package cns.jdbctemplate.assignment.mvc_controller;

import cns.jdbctemplate.assignment.model.User;
import cns.jdbctemplate.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.text.ParseException;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUserPage")
    public String addUserPage(Model model) {
        model.addAttribute("loginPage", true);
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Register User");
        model.addAttribute("formAction", "/add-user");

        model.addAttribute("switchLoginSignUpAction", "/loginUserPage");
        model.addAttribute("switchLoginSignUpLabel", "Go to Login Page");

        model.addAttribute("message", "Student Added Successfully");

        return "signup";
    }


    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) throws ParseException {

        userService.create(user);

        String message = "Record with id is save successfully";
        model.addAttribute("message", message);
        return "redirect:addUserPage";
    }


    @GetMapping("/loginUserPage")
    public String loginUserPage(Model model) {
        model.addAttribute("loginPage", false);
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Login ");
        model.addAttribute("formAction", "/login");

        model.addAttribute("switchLoginSignUpAction", "/addUserPage");
        model.addAttribute("switchLoginSignUpLabel", "Go to Signup Page");

        model.addAttribute("message", "User Added Successfully");
        return "signup";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String username = user.getUsername();
        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();

        if (password.equals(confirmPassword)) {
            try {
                Optional<User> data = userService.findByUsernameAndPassword(username, password);
                if (data.isPresent()) {
                    request.getSession().setAttribute("userName", user.getUsername());
                    request.getSession().setAttribute("id", user.getId());
                    return "redirect:list";
                } else {
                    redirectAttributes.addFlashAttribute("passwordNotMatch", "Wrong username or password!!");
                    return "redirect:loginUserPage";
                }
            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("passwordNotMatch", "Password and Confirm Password is not matched!!");
                return "redirect:loginUserPage";
            }
        } else {
            redirectAttributes.addFlashAttribute("passwordNotMatch", "Password and Confirm Password is not matched!!");
            return "redirect:loginUserPage";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginUserPage";

    }
}
