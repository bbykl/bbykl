package top.bbykl.crm.workbench.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bbykl.crm.workbench.mapper.ActivityMapper;
import top.bbykl.crm.workbench.model.Activity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description:市场活动业务接口
 */
public interface activityService {
    public int addActivity(Activity activity);
    public List<Activity> queryActivityByConditionForPage(Map<String,Object> map);
    public int queryAllActivityCounts(Map<String,Object> map);
    int deleteActivityByIds(String[] ids);
    Activity queryActivityById(String id);
    int updateActivity(Activity activity);
    List<Activity> queryAllActivity();
    List<Activity> queryAllActivityByIds(String[] id);
    int importActivitys(List<Activity> activityList);
}
