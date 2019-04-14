package ch.practice.controller;

import ch.practice.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("json")
//声明素有的方法都用json济宁响应
@RestController
public class JsonController {

    @RequestMapping("demo01")
    public List<User> test01(){

        List<User> list = new ArrayList<User>();
        for(int i = 0;i< 20;i++) {
            User user = new User();
            user.setId(i+1L);
            user.setUsername("zhangsan"+i);
            user.setName("张三"+i);
            user.setAge(18);
            list.add(user);
        }
        return list;


    }
}
