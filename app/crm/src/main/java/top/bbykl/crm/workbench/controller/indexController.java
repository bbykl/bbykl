package top.bbykl.crm.workbench.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.bbykl.crm.commons.contants.contants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: 首页控制层
 * @date 2022/11/12 0:04
 */
@Controller
@Api("业务首页控制层")
public class indexController {
    @RequestMapping("/workbench/index")
    @ApiOperation("首页接口")
    public String index(Model model, HttpSession session, HttpServletRequest request){
//        cookie只存在相应controller
//        System.out.println("cookie输出：");
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie.getName()+" "+cookie.getValue());
//        }
        System.out.println("session存值为:"+session.getAttribute(contants.USER_SESSION)+" "+session);
        //通过model返回给视图层
        model.addAttribute(contants.USER_SESSION,session.getAttribute(contants.USER_SESSION));
        return "workbench/index";
    }
}
