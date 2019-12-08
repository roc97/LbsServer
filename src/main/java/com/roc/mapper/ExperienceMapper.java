package com.roc.mapper;

import com.roc.pojo.Experience;
import com.roc.vo.ExperienceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author p
 */
public interface ExperienceMapper extends BaseMapper<Experience> {

    List<ExperienceVo> getListByUserId(int userId);//获取用户所有心得

    List<Experience> getListByTitle(String title);//根据标题模糊搜索

    List<ExperienceVo> getAllList();

    int updateLikeNumAndUnlikeNum(int experienceId,int likeNum,int unlikeNum);//更新点赞和踩

    @Override
    int deletePojo(int id);

    List<Map> countTimeSlotRecords(@Param("startTime")String startTime,
                                   @Param("endTime")String endTime);
}
