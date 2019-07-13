package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 萧一旬
 * @Description:
 * @Date: Create in 13:59 2019/4/10
 */
@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("{id}")
    @ResponseBody
    public String hello(@PathVariable int id){
        /*try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return id+"";
    }
}
