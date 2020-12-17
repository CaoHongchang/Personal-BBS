package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.BoardModel;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.BoardService;
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
public class TopicController {

    private TopicService service;

    private UserService userService;

    private BoardService boardService;

    private TopicCommentService topicCommentService;

    public TopicController(TopicService TopicService, UserService userService, BoardService boardService, TopicCommentService topicCommentService) {

        this.service = TopicService;
        this.userService = userService;
        this.boardService = boardService;
        this.topicCommentService = topicCommentService;
    }


////    @PostMapping("/topic/add")
////    public String add(String content) {
////        TopicModel Topic = TopicService.add(content);
////        Utility.log("Topic add id %s", Topic.getId());
////        return "redirect:/topic";
////    }
//
    @PostMapping("/topic/add")
    public ModelAndView add(String title, String content, String tab, HttpServletRequest request) {
//        TopicModel Topic = service.add(content);
//        TopicModel Topic = service.addBySQL(content);
        Utility.log("tab: %s", tab);
        //使用mybatis方式
        UserModel currentUser = userService.currentUser(request);

        BoardModel boardModel = boardService.selectBoardByName(tab);
        Utility.log("add.boardModel: %s", boardModel);
        Utility.log("add.boardModel.id: %s", boardModel.getId());
        TopicModel topic = service.add(currentUser.getId(), title, content, boardModel, boardModel.getId());

        topicCommentService.add(1, topic.getId(), "欢迎各位评论");

        Utility.log("Topic add id %s", topic.getId());
        return new ModelAndView("redirect:/?tab=" + tab);
    }
//
    @GetMapping("/topic/delete")
    public ModelAndView deleteMapper(int id) {
//        service.deleteById(id);
        service.deleteById(id);
        return new ModelAndView("redirect:/topic");
    }
//
//
    @GetMapping("/topic/edit")
    public ModelAndView edit(int id) {
        TopicModel topic = service.findById(id);

        ModelAndView m = new ModelAndView("topic/topic_edit");
        m.addObject("topic", topic);
        return m;
    }
//
    @PostMapping("/topic/update")
    public ModelAndView updateMapper(int id, String title, String content) {
        service.update(id, title, content);
        return new ModelAndView("redirect:/topic");
    }

    @GetMapping("/topic/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id) {
        TopicModel topicWithComments = service.findByIdWithCommentsAndUser(id);

        UserModel user = userService.findById(topicWithComments.getUserId());

        Utility.log("detail.topic: %s", topicWithComments);
        ModelAndView m = new ModelAndView("topic/detail_1");
        m.addObject("topicWithComments", topicWithComments);
        m.addObject("topicId", id);
        m.addObject("user", user);
        return m;
    }


    @GetMapping("/topic")
    public ModelAndView index() {

        List<TopicModel> Topics = service.all();
        List<TopicModel> topics = service.all();

        ModelAndView m = new ModelAndView("topic/topic_index");
        m.addObject("topics", topics);
        return m;
    }

    @GetMapping("/topic/{tab}/{username}")
    public ModelAndView index(@PathVariable String tab, @PathVariable String username) {
        UserModel currentUser = userService.findByUsername(username);

        BoardModel boardModel = boardService.selectBoardByName(tab);
        Utility.log("boardModel: %s", boardModel);

        List<TopicModel> topics = service.selectAlltopicByBoardId(boardModel.getId());
        Utility.log("topics: %s", topics);

        ModelAndView m = new ModelAndView("topic/topic_index_1");
        m.addObject("topics", topics);
        m.addObject("tab", tab);
        m.addObject("currentUser", currentUser);
        return m;
    }
}
