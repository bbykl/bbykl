package top.bbykl.crm.workbench.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bbykl.crm.commons.contants.contants;
import top.bbykl.crm.commons.domain.returnObject;
import top.bbykl.crm.commons.utils.dateUtils;
import top.bbykl.crm.commons.utils.uuidUtils;
import top.bbykl.crm.setting.model.User;
import top.bbykl.crm.setting.service.userService;
import top.bbykl.crm.workbench.model.Activity;
import top.bbykl.crm.workbench.service.activityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @description: 市场活动
 */
@Controller
@Api("市场活动")
public class activityController {
    //自动装配业务类
    @Autowired
    top.bbykl.crm.setting.service.userService userService;
    @Autowired
    top.bbykl.crm.workbench.service.activityService activityService;
    @ApiOperation("市场活动首页")
    @RequestMapping("/workbench/activity/index")
    public String index(HttpServletRequest request, Model model){
        //需要获得一些动态信息此处指表单里任务拥有者
        List<User> users = userService.queryAllUser();
        //可以写在作用域，也可以通过modelview来完成
        request.setAttribute("userList",users);
        model.addAttribute("userList",users);
        return "workbench/activity/index";
    }
    @ApiOperation("创建市场活动")
    @ResponseBody
    @RequestMapping("/workbench/activity/toadd")
    public Object create(Activity activity, HttpSession session){
        System.out.println("接收到的activity："+activity);
        //uuid设置id
        activity.setId(uuidUtils.getUid());
        //这里不设置name,是因为name用户可以进行修改。
        activity.setCreateBy(((User)session.getAttribute(contants.USER_SESSION)).getId());
        activity.setCreateTime(dateUtils.dateFormatDateTime(new Date()));
        System.out.println("完整activity: "+activity);
        //创建市场活动
        //封装返回状态信息
        returnObject returnObject = new returnObject();
        try {
            int i = activityService.addActivity(activity);
            if (i>0){
                //创建成功
                returnObject.setCode(contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("创建成功");
            }
            else {
                //创建失败
                returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试...");
        }
        return returnObject;
    }
    @ApiOperation("分页查询市场活动列表")
    @ResponseBody
    @PostMapping("/workbench/activity/queryActivityByConditionForPage")
    public Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        int activityCounts = activityService.queryAllActivityCounts(map);
        List<Activity> list = activityService.queryActivityByConditionForPage(map);
        HashMap<String, Object> returnObject = new HashMap<>();
        returnObject.put("activityList",list);
        returnObject.put("allCounts",activityCounts);
        System.out.println("输出结果："+activityCounts+" "+list);
        return returnObject;
    }
    @ApiOperation("删除市场活动")
    @ResponseBody
    @RequestMapping("/workbench/activity/deleteActivityByIds")
    public Object deleteActivityByIds(@ApiParam("市场活动id数组") String[] id){
        System.out.println("前端传来数据："+ Arrays.toString(id));
        returnObject returnObject = new returnObject();
        try{
            int i = activityService.deleteActivityByIds(id);
            if (i>0){
                returnObject.setCode(contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试");
        }
        return returnObject;
    }
    @ApiOperation("修改市场活动-回显信息")
    @RequestMapping("/workbench/activity/queryActivityById")
    public @ResponseBody Object queryActivityById(@ApiParam("唯一市场活动id") String id){
        //System.out.println("接受id: "+id);
        Activity activity = activityService.queryActivityById(id);
       //System.out.println(activity);
        return activity;
    }
    @ApiOperation("修改市场活动-修改信息")
    @PostMapping("/workbench/activity/updateActivity")
    public@ResponseBody Object updateActivity(@ApiParam("修改后的市场活动") Activity activity,HttpSession session){
        System.out.println("进入修改："+activity);
        returnObject returnObject = new returnObject();
        //完善修改信息
        //从session里获取登录信息
        activity.setEditBy(((User)session.getAttribute(contants.USER_SESSION)).getId());
        activity.setEditTime(dateUtils.dateFormatDate(new Date()));
        System.out.println("完善信息修改："+activity);
        try {
            int i = activityService.updateActivity(activity);
            if (i>0){
                returnObject.setCode(contants.RETURN_OBJECT_CODE_SUCCESS);
                return returnObject;
            }
            returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试");
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试");
        }
        return returnObject;
    }
}
