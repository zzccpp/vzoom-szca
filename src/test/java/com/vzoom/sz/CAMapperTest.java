package com.vzoom.sz;

import com.vzoom.sz.bean.SZCAInfo;
import com.vzoom.sz.mapper.SZCAMapper;
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
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationMain.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CAMapperTest {

    @Autowired(required = false)
    private SZCAMapper shenZhenCAMapper;

    @Test
    public void getCa(){
        SZCAInfo info = shenZhenCAMapper.selectCAInfoById(1);
        System.out.println(info);
    }

    @Test
    public void addCa(){
        SZCAInfo info = new SZCAInfo();
        info.setNsrsbh("nsh2");
        info.setPublicKey("puk2");
        info.setSign("sig2");
        shenZhenCAMapper.insertCAInfo(info);
        System.out.println("添加完成...");
    }

}
*/
