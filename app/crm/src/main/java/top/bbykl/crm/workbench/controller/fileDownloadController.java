package top.bbykl.crm.workbench.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.bbykl.crm.commons.contants.contants;
import top.bbykl.crm.commons.domain.returnObject;
import top.bbykl.crm.commons.utils.dateUtils;
import top.bbykl.crm.commons.utils.uuidUtils;
import top.bbykl.crm.setting.model.User;
import top.bbykl.crm.workbench.model.Activity;
import top.bbykl.crm.workbench.service.activityService;
import top.bbykl.crm.workbench.service.impl.activityImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @description: 市场列表下载与上传
 */
@Controller
public class fileDownloadController {
    @Autowired
    activityImpl activity;
    @ApiOperation("导出市场活动列表")
    @RequestMapping("/workbench/activity/fileDownloadActivityList")
    public void fileDownloadActivityList(HttpServletRequest request, HttpServletResponse response){
        List<Activity> activities = activity.queryAllActivity();
        System.out.println("返回数据为： "+activities);
        //创建文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建页
        HSSFSheet sheet = workbook.createSheet("市场活动列表");
        //创建表头
        HSSFRow header = sheet.createRow(0);
        //创建表头介绍
        header.createCell(0).setCellValue("名称");
        header.createCell(1).setCellValue("拥有者");
        header.createCell(2).setCellValue("开始时间");
        header.createCell(3).setCellValue("结束时间");
        header.createCell(4).setCellValue("描述");
        header.createCell(5).setCellValue("创建人");
        //数据录入
        for (int i=1;i<=activities.size();i++){
            HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(activities.get(i-1).getName());
                row.createCell(1).setCellValue(activities.get(i-1).getOwner());
                row.createCell(2).setCellValue(activities.get(i-1).getStartDate());
                row.createCell(3).setCellValue(activities.get(i-1).getEndDate());
                row.createCell(4).setCellValue(activities.get(i-1).getDescription());
                row.createCell(5).setCellValue(activities.get(i-1).getCreateBy());
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        String filename="";
        try {
            filename=URLEncoder.encode("市场活动列表.xls","utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        try {
            //获取字节输出流，因为文件类型为字节流

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation("导出市场活动列表（选择）")
    @RequestMapping("/workbench/activity/fileDownloadActivityListByIds")
    public void fileDownloadActivityListByIds(HttpServletRequest request, HttpServletResponse response,@ApiParam("市场活动id数组") String[] id){
        List<Activity> activities = activity.queryAllActivityByIds(id);
        System.out.println("返回数据为： "+activities);
        //创建文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建页
        HSSFSheet sheet = workbook.createSheet("市场活动列表");
        //创建表头
        HSSFRow header = sheet.createRow(0);
        //创建表头介绍
        header.createCell(0).setCellValue("名称");
        header.createCell(1).setCellValue("拥有者");
        header.createCell(2).setCellValue("开始时间");
        header.createCell(3).setCellValue("结束时间");
        header.createCell(4).setCellValue("描述");
        header.createCell(5).setCellValue("创建人");
        //数据录入
        for (int i=1;i<=activities.size();i++){
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(activities.get(i-1).getName());
            row.createCell(1).setCellValue(activities.get(i-1).getOwner());
            row.createCell(2).setCellValue(activities.get(i-1).getStartDate());
            row.createCell(3).setCellValue(activities.get(i-1).getEndDate());
            row.createCell(4).setCellValue(activities.get(i-1).getDescription());
            row.createCell(5).setCellValue(activities.get(i-1).getCreateBy());
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        String filename="";
        try {
            filename=URLEncoder.encode("市场活动列表.xls","utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        try {
            //获取字节输出流，因为文件类型为字节流

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation("导入市场活动")
    @RequestMapping("/workbench/activity/importActivitys")
    @ResponseBody
    public Object importActivitys(@ApiParam("前端文件对象") MultipartFile activityFile, String testParamter, HttpSession session){
        //自己封装的返回状态标识
        returnObject returnObject = new returnObject();
        //调试记录
        System.out.println(testParamter);
        System.out.println("进入后端导入市场活动接口");
        System.out.println(activityFile);
        //文件名
        System.out.println(activityFile.getOriginalFilename());
        //参数名
        System.out.println(activityFile.getName());
        //文件类型
        System.out.println(activityFile.getContentType());
        //解析文件对象封装实体类
        ArrayList<Activity> activities = new ArrayList<>();
        Activity activity1;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(activityFile.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i=1;i<sheet.getLastRowNum()+1;i++){
                activity1=new Activity();
                activity1.setId(uuidUtils.getUid());
                //从session中获取当前登录者id(默认谁导入文件，创建人就是谁)
                activity1.setCreateBy(((User)session.getAttribute(contants.USER_SESSION)).getId());
                activity1.setCreateTime(dateUtils.dateFormatDate(new Date()));
                HSSFRow row = sheet.getRow(i);
                for (int j=0;j<row.getLastCellNum();j++){
                    HSSFCell cell = row.getCell(j);
                    String value=getCellValue(cell);
                    System.out.print(value+" ");
                    switch (j){
                        case 0:activity1.setName(value);break;
                        case 1:activity1.setOwner(((User)session.getAttribute(contants.USER_SESSION)).getId());break;
                        case 2:activity1.setStartDate(value);break;
                        case 3:activity1.setEndDate(value);break;
                        case 4:activity1.setDescription(value);break;
                    }
                    //在编写代码之前确定xls表的每列具体代表什么，简化编程实现
                }
                activities.add(activity1);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用业务,dml语言，需要捕获异常，可能操作失败
        try {
            System.out.println("读取列表："+activities);
            int i = activity.importActivitys(activities);
            System.out.println("插入成功"+i);
            returnObject.setCode(contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setOthers(i);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试..");
        }
        return returnObject;
    }
    //封装获取列值函数
    private String getCellValue(HSSFCell cell){
        String value="";
        switch (cell.getCellType()){
            case 0:
                value = cell.getNumericCellValue()+"";
                break;
            case 1:
                value = cell.getStringCellValue();
                break;
            case 2:
                value = cell.getCellFormula();
                break;
        }
        return value;
    }
    @Test
    public void test(){

        System.out.println(uuidUtils.getUid());
    }
}
