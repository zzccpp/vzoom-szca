package com.vzoom.sz.conroller;

import com.vzoom.sz.bean.SZCAInfo;
import com.vzoom.sz.mapper.SZCAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 16:40
 * @describe vzoom-szca <描述>
 */
@RestController
public class HelloController {

    @Autowired(required = false)
    private SZCAMapper shenZhenCAMapper;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/getca")
    public void getCA(){
        /*List<SZCAInfo> infos = shenZhenCAMapper.selectCAInfoById(1);
        for (SZCAInfo info : infos) {
            System.out.println(info);
        }*/
    }
}
