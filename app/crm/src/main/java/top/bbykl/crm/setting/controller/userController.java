package top.bbykl.crm.setting.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bbykl.crm.commons.contants.contants;
import top.bbykl.crm.commons.domain.returnObject;
import top.bbykl.crm.commons.utils.dateUtils;
import top.bbykl.crm.setting.model.User;
import top.bbykl.crm.setting.service.userService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @description: 用户控制层
 * @author 盛祥进
 * @date 2022/11/9 12:42
 * @version 1.0
 */
@Controller
public class userController {
    @Autowired
    top.bbykl.crm.setting.service.userService userService;
    @GetMapping("/settings/qx/user/login.do")
    @ApiOperation(value = "登录接口")
    public String toLogin(HttpServletRequest request){
        //如果用户session存在，则直接重定向首页
        if (request.getSession().getAttribute(contants.USER_SESSION)!=null){
            return "redirect:/workbench/index";
        }
        return "settings/qx/user/login";
    }

    /**
     * @description:
     * @param: loginAct,loginPwd,isRemember
     * @param： 用户账号，用户密码，是否记录密码（10天免登录）
     * @return: Object
     * @return  请求状态信息（returnObject自定义状态类）
     * @author 盛祥进
     * @date: 2022/11/9 14:29
     */

    /**
     *
     * @param: request,session(返回给视图层,用于存储数据的容器)
     * @return:
     */
    @ApiOperation("登陆验证")
    @ResponseBody
    @RequestMapping("/settings/qx/user/login")
    public Object login(String loginAct, String loginPwd, String isRemember, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        //返回状态标识
      //  System.out.println(dateUtils.dateFormatDateTime(new Date())+"进入测试");
        returnObject returnObject = new returnObject();
        HashMap<String , Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.selectUserByPassword(map);
        if (user==null){
            //1.验证是否存在
            //登陆失败，没有找到用户
            returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户不存在");
            return returnObject;
        }
        else{
            //2.验证是否过期
            //第一钟比较方式，通过时间戳进行比较
            String expireTime = user.getExpireTime();
            System.out.println(expireTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse=null;
            try {
                 parse= simpleDateFormat.parse(expireTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long time = parse.getTime();
            System.out.println(time+"-----"+new Date().getTime());
            if (time<new Date().getTime()){
//                登录失败，用户凭证已过期
                System.out.println("进入判断");
                returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户凭证已过期，请重新登录");
                return returnObject;
            }

            //3.验证是否账号锁定
            if ("0".equals(user.getLockState())){
                //登陆失败，账号锁定
                returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号锁定，联系管理员解锁");
                return returnObject;
            }

            //4.验证ip是否受限
            String remoteAddr = request.getRemoteAddr();
            System.out.println("远程ip: "+remoteAddr);
            if (!user.getAllowIps().contains(remoteAddr)){
                //登录失败，ip受限
                returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户ip禁止访问");
                return returnObject;
            }
        }
        returnObject.setCode(contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("登录成功");
        session.setAttribute(contants.USER_SESSION,user);
        System.out.println("session存值"+session.getAttribute(contants.USER_SESSION));

//        实现十天免登录，判断是否记录密码
        System.out.println("isRemember:"+isRemember);
        if ("true".equals(isRemember)){
//            保存账号密码在cookie中,不安全可以通过加密或其他方式实现
            Cookie cookieAct = new Cookie("loginAct",user.getLoginAct());
            Cookie cookiePwd = new Cookie("loginPwd",user.getLoginPwd());
            //单位：秒
            cookieAct.setMaxAge(10*24*60*60);
            cookiePwd.setMaxAge(10*24*60*60);
//            写到浏览器返回客户端
            response.addCookie(cookieAct);
            response.addCookie(cookiePwd);
            System.out.println("进入cookie添加");
        }
        else {
//            不记住密码,则删除cookie,用于协议原因不能删除客户机上东西，故通过cookie的生命周期来实现删除。
            Cookie cookieAct = new Cookie("loginAct","0");
            Cookie cookiePwd = new Cookie("loginPwd","0");
            cookieAct.setMaxAge(0);
            cookiePwd.setMaxAge(0);
            response.addCookie(cookieAct);
            response.addCookie(cookiePwd);
        }
        return returnObject;
    }
    @ApiOperation("安全退出")
    @RequestMapping("/settings/qx/user/logout")
    public String logout(HttpSession httpSession,HttpServletResponse response){
//        移除session
        httpSession.removeAttribute(contants.USER_SESSION);
//        移除cookie
        Cookie cookieAct = new Cookie("loginAct","0");
        Cookie cookiePwd = new Cookie("loginPwd","0");
        cookieAct.setMaxAge(0);
        cookiePwd.setMaxAge(0);
        response.addCookie(cookieAct);
        response.addCookie(cookiePwd);
        //返回首页
        return "redirect:/";
    }
}
