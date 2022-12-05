package top.bbykl.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.bbykl.crm.setting.mapper.UserMapper;

import javax.annotation.Resource;

@SpringBootTest
class CrmApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {

    }

}
