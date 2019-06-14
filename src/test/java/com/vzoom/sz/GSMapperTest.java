package com.vzoom.sz;

import com.vzoom.sz.bean.SZCAInfo;
import com.vzoom.sz.bean.SZGSInfo;
import com.vzoom.sz.mapper.SZCAMapper;
import com.vzoom.sz.mapper.SZGSMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 18:07
 * @describe vzoom-szca
 *
 * 证书签名Dao测试
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationMain.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class GSMapperTest {
//
//    @Autowired(required = false)
//    private SZGSMapper szgsMapper;*/
//
//    /*@Test
//    public void getGs(){
//        List<SZGSInfo> infos = szgsMapper.selectSZGSInfo();
//        for (SZGSInfo info : infos) {
//            System.out.println("未签名纳税号:"+info);
//        }
//    }
//
//    @Test
//    public void updateGs(){
//        SZGSInfo info = new SZGSInfo();
//        info.setNsrsbh("124403004557537467");
//        info.setContent("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//        szgsMapper.updateSZGSInfo(info);
//        System.out.println("更新完成...");
//    }
//
//}
