package top.bbykl.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: 格式化date的工具类·
 * @date 2022/11/11 20:35
 */
public class dateUtils {

    

    /**
     *对指定的date进行格式化 yyyy-MM-dd HH:mm:ss
     * @param:
     * @return:
     */
    public static String dateFormatDateTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     *对指定date进行格式化 yyyy-MM-dd
     * @param:
     * @return:
     */
    public static String dateFormatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}
