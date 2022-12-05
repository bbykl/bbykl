package top.bbykl.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bbykl.crm.workbench.mapper.ActivityMapper;
import top.bbykl.crm.workbench.model.Activity;
import top.bbykl.crm.workbench.service.activityService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description: 市场活动业务接口
 */
@Service("activityService")
public class activityImpl implements activityService {
    @Resource
    ActivityMapper activityMapper;
    @Override
    public int addActivity(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryAllActivityCounts(Map<String, Object> map) {
        return activityMapper.selectAllActivityCounts(map);
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public List<Activity> queryAllActivity() {
        return activityMapper.selectAllActivity();
    }

    @Override
    public List<Activity> queryAllActivityByIds(String[] id) {
        return activityMapper.selectAllActivityByIds(id);
    }

    @Override
    public int importActivitys(List<Activity> activityList) {
        return activityMapper.insertAcyivitys(activityList);
    }

}
