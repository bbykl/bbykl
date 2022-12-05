package top.bbykl.crm.commons.utils;

import java.util.UUID;

/**
 * @version 1.0
 * @description: uuid工具类封装
 */
public class uuidUtils {
    public static String getUid(){
        //格式化输出，去掉"-"
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
