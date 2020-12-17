package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.WeiboModel;
import kybmig.ssm.service.TodoService;
import kybmig.ssm.service.WeiboService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WeiboController {

    private WeiboService service;

    public WeiboController(WeiboService service) {

        this.service = service;
    }

    @PostMapping("/weibo/add")
    public ModelAndView add(String content) {
        WeiboModel weibo = service.add(content);
        return new ModelAndView("redirect:/weibo");
    }

    @GetMapping("/weibo")
    public ModelAndView index() {

        List<WeiboModel> weibos = service.all();
        ModelAndView m = new ModelAndView("weibo/weibo_index");
        m.addObject("weibos", weibos);
        return m;
    }

    @GetMapping("/weibo/edit")
    public ModelAndView edit(Integer id, String content) {
        WeiboModel w = service.findById(id);

        ModelAndView m = new ModelAndView("/weibo/weibo_edit");
        m.addObject("weibo", w);
        return m;
    }

    @PostMapping("/weibo/update")
    public ModelAndView update(Integer id, String content) {
        Utility.log("update方法中的id = %s", id);
        Utility.log("update方法中的content = %s", content);
        service.update(id, content);
        return new ModelAndView("redirect:/weibo");
    }

    @GetMapping("/weibo/delete")
    public ModelAndView delete(Integer id) {
        service.deleteById(id);
        return new ModelAndView("redirect:/weibo");
    }

}
