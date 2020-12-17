package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class ResetController {
    private UserService userService;
    private HashMap<String, Integer> tokenMap;
    private MailController controller;

    public ResetController(UserService userService, MailController controller) {
        this.userService = userService;
        tokenMap = new HashMap<>();
        this.controller = controller;
    }



    @PostMapping("/reset/send")
    public ModelAndView send(String username) {
        UserModel user = userService.findByUsername(username);

        if(user == null) {
            return  new ModelAndView("redirect:/login");
        }

        String token = UUID.randomUUID().toString();

        tokenMap.put(token, user.getId());
        Utility.log("token: %s, id: %s", token, user.getId());

        String title = "修改密码";
        String content = "http://localhost:9000/reset/view?token=" +token;

        controller.send("1289741303@qq.com", title, content);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/reset/view")
    public ModelAndView view(String token) {
        Integer id = tokenMap.get(token);
        if (id == 0) {
           return new ModelAndView("redirect:/login");
        }

        UserModel user = userService.findById(id);
        Utility.log("user: %s", user);
        ModelAndView model = new ModelAndView("user/updatePassword");
        model.addObject("token", token);

        return model;

    }

    @PostMapping("/reset/update")
    public ModelAndView updatePassword(String token, String password) {
        Utility.log("新密码: %s", password);
        Integer id = tokenMap.get(token);
        if (id == 0) {
            return new ModelAndView("redirect:/login");
        }

        UserModel user = userService.findById(id);
        Utility.log("user: %s", user);

        user.setPassword(password);
        userService.update(id, user.username, password);

        return new ModelAndView("redirect:/login");
    }


}
