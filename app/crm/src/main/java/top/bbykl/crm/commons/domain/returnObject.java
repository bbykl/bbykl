package top.bbykl.crm.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: 公用实体类，用来作为返回前端的信息标识
 * @date 2022/11/9 14:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class returnObject {
    private String code;
    private String message;
    private Object others;
}
