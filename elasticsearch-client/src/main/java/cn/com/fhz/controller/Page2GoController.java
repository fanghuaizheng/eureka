package cn.com.fhz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by woni on 18/2/1.
 */
@Controller
public class Page2GoController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("info")
    public void returnAPI(HttpServletResponse respoinse){
        try {
            respoinse.sendRedirect("swagger-ui.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
