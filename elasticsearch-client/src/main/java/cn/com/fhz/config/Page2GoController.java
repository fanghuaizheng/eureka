package cn.com.fhz.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzfang on 2018/1/31.
 */
@Controller
public class Page2GoController {

    @RequestMapping("info")
    public void returnAPI(HttpServletResponse respoinse){
        try {
            respoinse.sendRedirect("swagger-ui.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
