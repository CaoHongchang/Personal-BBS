package kybmig.ssm.controller;
import kybmig.ssm.Utility;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class TodoController {

    private TodoService service;

    public TodoController(TodoService todoService) {

        this.service = todoService;
    }


//    @PostMapping("/todo/add")
//    public String add(String content) {
//        TodoModel todo = todoService.add(content);
//        Utility.log("todo add id %s", todo.getId());
//        return "redirect:/todo";
//    }

    @PostMapping("/todo/add")
    public ModelAndView add(String content) {
//        TodoModel todo = service.add(content);
//        TodoModel todo = service.addBySQL(content);

        //使用mybatis方式
        TodoModel todo = service.add(content);


        Utility.log("todo add id %s", todo.getId());
        return new ModelAndView("redirect:/todo");
    }

    @GetMapping("/todo/delete")
    public ModelAndView deleteMapper(int id) {
//        service.deleteById(id);
        service.deleteById(id);
        return new ModelAndView("redirect:/todo");
    }


    @GetMapping("/todo/edit")
    public ModelAndView edit(int id) {
        TodoModel todo = service.findById(id);

        ModelAndView m = new ModelAndView("todo_edit");
        m.addObject("todo", todo);
        return m;
    }

    @PostMapping("/todo/update")
    public ModelAndView updateMapper(int id, String content) {
        service.update(id, content);
        return new ModelAndView("redirect:/todo");
    }

    @GetMapping("/todo")
    public ModelAndView index() {

//        List<TodoModel> todos = service.all();
        List<TodoModel> todos = service.all();

        ModelAndView m = new ModelAndView("todo_index");
        m.addObject("todos", todos);
        return m;
    }
}
