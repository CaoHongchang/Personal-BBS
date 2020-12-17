package kybmig.ssm.controller;

import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView m = new ModelAndView("user/login");
        return m;
    }

    @PostMapping("user/login")
    public ModelAndView login(String username, String password, HttpServletRequest request) {
        if (service.validLogin(username,password)) {
            UserModel current = service.findByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("user_id", current.getId());

            ModelAndView m = new ModelAndView("redirect:/");
            return m;
        } else {
            ModelAndView m = new ModelAndView("redirect:/login");
            return m;
        }
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
        return new ModelAndView("user/register");
    }

    @PostMapping("/user/add")
    public ModelAndView register(String username, String password, String email) {
        service.add(username, password, email);

        ModelAndView m = new ModelAndView("redirect:/login");
        return m;
    }


}
