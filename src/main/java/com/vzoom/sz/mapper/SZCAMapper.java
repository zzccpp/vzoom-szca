package com.vzoom.sz.mapper;

import com.vzoom.sz.bean.SZCAInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 16:44
 * @describe vzoom-szca 处理深圳CA表
 */
@Mapper
public interface SZCAMapper {

    /**
     * 根据ID查下证书签名
     * @param id
     * @return
     */
    SZCAInfo selectCAInfoById(int id);

    /**
     * 添加证书签名信息
     * @param caInfo
     */
    void insertCAInfo(SZCAInfo caInfo);

}
