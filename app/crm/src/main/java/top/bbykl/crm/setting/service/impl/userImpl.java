package top.bbykl.crm.setting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bbykl.crm.setting.mapper.UserMapper;
import top.bbykl.crm.setting.model.User;
import top.bbykl.crm.setting.service.userService;

import java.util.List;
import java.util.Map;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: TODO
 * @date 2022/11/9 12:33
 */
@Service("userService")
public class userImpl implements userService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUserByPassword(Map<String, Object> map) {
        return userMapper.selectUserByPassword(map);
    }

    @Override
    public List<User> queryAllUser() {
        //调用dao层
        return userMapper.selectAllUser();
    }
}
