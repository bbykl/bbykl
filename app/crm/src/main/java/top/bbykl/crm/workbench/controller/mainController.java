package top.bbykl.crm.workbench.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: TODO
 * @date 2022/11/18 11:04
 */
@Controller
public class mainController {

    @ApiOperation("局部main")
    @RequestMapping("/workbench/main/index")
    public String main(){
        return "workbench/main/index";
    }
}
