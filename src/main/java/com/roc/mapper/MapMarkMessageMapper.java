package com.roc.mapper;

import com.roc.pojo.MapMarkMessage;
import com.roc.vo.MarkVo;

import java.util.List;

/**
 * @author p
 */
public interface MapMarkMessageMapper {

    int insertPojo(MapMarkMessage mapMarkMessage);

    MapMarkMessage getDetailMessage(int markId);

    List<MarkVo> getAllList();

    List<MapMarkMessage> getListByStatus(int status);//通过状态获取相应标注信息

    List<MapMarkMessage> getListByUserId(int userId);//获取用户所有标注信息

    List<MapMarkMessage> getListByUserIdAndStatus(int userId,int status);//根据状态和用户id获取标注信息

    List<MapMarkMessage> getListByMatchTitle(String title);

    int updatePojo(int markId,int status);

    int deletePojo(int markId);

}
