package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserTopicController {
    private HttpServletRequest request;

    private TopicService topicService;
    private UserService userService;
    private TopicCommentService topicCommentService;

    public UserTopicController(HttpServletRequest request, TopicService topicService, UserService userService, TopicCommentService topicCommentService) {
        this.request = request;
        this.topicService = topicService;
        this.userService = userService;
        this.topicCommentService = topicCommentService;
    }


    @GetMapping("/personal/{username}")
    public ModelAndView myself(@PathVariable String username) {
        Utility.log("username: %s", username);
//        UserModel currentUser = userService.currentUser(request);
        UserModel currentUser = userService.findByUsername(username);
        Utility.log("currentUser: %s", currentUser);

        List<TopicModel> topicModels = new ArrayList<>();
        List<Integer> topicIds = topicService.selectTopicIdByUserId(currentUser.id);

        for (Integer topicId : topicIds) {
            TopicModel oneTopic = topicService.selectOneWithCommentsByTopicId(topicId);
            Utility.log("oneTopic: %s", oneTopic);
            topicModels.add(oneTopic);
        }


        Utility.log("myself.topicModels: %s", topicModels);
        List<TopicCommentModel> topicCommentModels = topicCommentService.selectAllCommentsWithUserId(currentUser.id);
        Utility.log("topicCommentModels: %s", topicCommentModels);



        ModelAndView m = new ModelAndView("user/personal");
        m.addObject("currentUser", currentUser);
        m.addObject("topicModels", topicModels);
        m.addObject("topicCommentModels", topicCommentModels);
        return m;
    }

}
