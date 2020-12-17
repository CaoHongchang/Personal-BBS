package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.BoardModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.BoardService;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.springframework.beans.factory.xml.UtilNamespaceHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PublicController {
    private UserService service;
    private BoardService boardService;
    private TopicService topicService;
    private UserService userService;

    public PublicController(UserService service, BoardService boardService, TopicService topicService, UserService userService) {
        this.service = service;
        this.boardService = boardService;
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request, String tab) {
        UserModel current = service.currentUser(request);

        if (tab == null) {
           tab = "B1";
        }
        Utility.log("tab: %s", tab);
        //此处是将写死的'游客'改为当前登录用户的用户名
        Integer userId = (Integer)request.getSession().getAttribute("user_id");
        String username = "游客";

        if(userId !=null) {
            UserModel user = service.findById(userId);
            Utility.log("当前用户: %s", user);
            username = user.getUsername();

        }

        BoardModel boardModel = boardService.selectBoardByName(tab);
        Utility.log("BoardModel: %s", boardModel);

        List<BoardModel> boards = boardService.all();
        Utility.log("BoardModels: %s", boards);

        List<TopicModel> topicModels = new ArrayList<>();

        List<TopicModel> topics = topicService.selectAlltopicByBoardId(boardModel.getId());
        Utility.log("topics: %s", topics);

        for (TopicModel topicModel : topics) {
            Utility.log("index.topicModel: %s", topicModel);
            TopicModel oneTopic = topicService.selectOneWithCommentsByTopicId(topicModel.getId());

            Utility.log("index.oneTopic: %s", oneTopic);
            Utility.log("index.oneTopic.userId: %s", oneTopic.getUserId());
            String uname = userService.selectUsernameByTopicId(oneTopic.getUserId());

            oneTopic.getUser().setUsername(uname);

            topicModels.add(oneTopic);
        }

        Utility.log("index.topicModels: %s", topicModels);

        // model 说的是给模板引擎的 model
        // view 说的是模板名字，没有后缀
        // view 可以自动补全，也可以直接跳转
        ModelAndView mv = new ModelAndView("index");


        mv.addObject("username", username);
        mv.addObject("boards",boards);
        mv.addObject("tab", tab);
        mv.addObject("topics", topics);
        mv.addObject("topicModels", topicModels);
        return mv;
    }

}
