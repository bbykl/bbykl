package top.bbykl.crm.setting.service;

import org.springframework.stereotype.Service;
import top.bbykl.crm.setting.model.User;

import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @author 盛祥进
 * @date 2022/11/9 12:41
 * @version 1.0
 */
public interface userService {
    User selectUserByPassword(Map<String,Object> map);
    List<User> queryAllUser();
}
