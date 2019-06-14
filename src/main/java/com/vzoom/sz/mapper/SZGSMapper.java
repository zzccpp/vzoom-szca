package com.vzoom.sz.mapper;

import com.vzoom.sz.bean.SZGSInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 20:13
 * @describe vzoom-szca <描述>
 */
@Mapper
public interface SZGSMapper {

    /**
     * 查询为获取签名数据
     * @return
     */
    List<SZGSInfo> selectSZGSInfo();

    /**
     * 更新签名
     */
    void updateSZGSInfo(SZGSInfo szgsInfo);

}
