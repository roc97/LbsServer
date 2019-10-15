package com.roc.mapper;

import com.roc.pojo.Experience;

import java.util.List;

public interface ExperienceMapper extends BaseMapper<Experience> {

    List<Experience> getListByUserId(int userId);//获取用户所有心得

    List<Experience> getListByTitle(String title);//根据标题模糊搜索

    List<Experience> getAllList();
}
