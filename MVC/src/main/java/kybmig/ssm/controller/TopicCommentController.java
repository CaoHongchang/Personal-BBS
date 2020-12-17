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
import java.util.List;

@Controller
public class TopicCommentController {

    private TopicCommentService service;

    private UserService userService;

    public TopicCommentController(TopicCommentService TopicService, UserService userService) {

        this.service = TopicService;
        this.userService = userService;
    }


    @PostMapping("/topicComment/add")
    public ModelAndView add(Integer topicId, String content, HttpServletRequest request) {

        //使用mybatis方式
        UserModel currentUser = userService.currentUser(request);

        TopicCommentModel comment = service.add(currentUser.getId(), topicId, content);


        Utility.log("comment add id %s", comment.getId());
        return new ModelAndView("redirect:/topic/detail/"+ topicId);
    }

    @GetMapping("/topicComment/delete")
    public ModelAndView deleteMapper(int id) {;
        service.deleteById(id);
        return new ModelAndView("redirect:/topic");
    }

    @GetMapping("/topicComment/edit")
    public ModelAndView edit(int id, int topicId) {
        Utility.log("edit.topicId: %s", topicId);
        TopicCommentModel comment = service.findCommentById(id);

        ModelAndView m = new ModelAndView("topic/topicComment_edit");
        m.addObject("comment", comment);
        m.addObject("topicId", topicId);
        return m;
    }

    @PostMapping("/topicComment/update")
    public ModelAndView updateMapper(int id, String content, int topicId) {
        Utility.log("update.topicId: %s", topicId);
        service.update(id, content);
        return new ModelAndView("redirect:/topic/detail/" + topicId);
    }
}
