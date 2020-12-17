package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TodoModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;


@Controller
public class RedisController {
    private RedisTemplate<String, String> templateNormal;
    private RedisTemplate<Integer, TodoModel> templateTodo;

    private HashMap<String, String> localredis = new HashMap<>();

    // RedisTemplate 都是被注入的对象，来自 RedisConfig.java
    public RedisController(RedisTemplate<String, String> templateNormal, RedisTemplate<Integer, TodoModel> templateTodo) {
        this.templateNormal = templateNormal;
        this.templateTodo = templateTodo;
    }

    @GetMapping("/redis/index")
    public ModelAndView index() {
        Utility.log("执行 redis/index 路由");
        TodoModel m = new TodoModel();
        m.setId(1);
        m.setContent("testRedis");
        templateTodo.opsForValue().set(m.getId(), m);
        TodoModel mGet = templateTodo.opsForValue().get(m.getId());
        Utility.log("mGet %s", mGet);

        var value = templateNormal.opsForValue().get("testkey");
//         String value = localredis.get("testkey");
        if (value == null) {
            value = "redis 中没有 testkey 的值";
        }
        var mv = new ModelAndView("redis/index");
        mv.addObject("key", value);
        return mv;
    }

    @GetMapping("/redis/set")
    public String set(String value) {
        if (value == null) {
            value = "gua";
        }
        // 往 redis 里面存一个值
        templateNormal.opsForValue().set("testkey", value);
        // 往 messgeQueue 频道发布消息
        templateNormal.convertAndSend("messageQueue", value);
//         localredis.put("testkey", value);
        return "redirect:index";
    }
}


