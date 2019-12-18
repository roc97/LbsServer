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

    Experience getPojo(int operateObject,int id);

    List<ExperienceVo> getListByUserId(int userId);//获取用户所有心得

    List<Experience> getListByTitle(String title);//根据标题模糊搜索

    /**
     * 获取所有心得
     * @param operateObject 用户id
     * @return
     */
    List<ExperienceVo> getAllList(int operateObject);

    @Override
    int deletePojo(int id);

    List<Map> countTimeSlotRecords(@Param("startTime")String startTime,
                                   @Param("endTime")String endTime);

    /**
     * 更新点赞（踩）数
     * @param id 心得id
     * @param type 0代表赞 1代表踩
     * @param total 点赞总数
     * @return
     */
    int updateLikeNumOrHateNum(int id,int type,int total);
}
