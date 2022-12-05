package top.bbykl.crm.testController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
    @RequestMapping("/test")

    public String test01(int a){
        return "index";
    }
}
