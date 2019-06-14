package com.vzoom.sz.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vzoom.sz.bean.SZGSInfo;
import com.vzoom.sz.mapper.SZGSMapper;
import com.vzoom.sz.utils.JsonUtils;
import com.vzoom.utils.HttpClientUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 20:40
 * @describe vzoom-szca
 *
 * 更新深圳签名证书任务
 *
 */
@Service
public class UpdateSignTask {

    private static Logger logger = Logger.getLogger(UpdateSignTask.class);

    @Value("${sz.cert.url}")
    private String certUrl;
    @Value("${threadNum}")
    private int threadNum;
    ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Autowired(required = false)
    private SZGSMapper szgsMapper;

    @Scheduled(fixedRate=5000) //上一次开始执行时间点之后1秒再执行
    public void doTask(){
        logger.info("开始任务执行");
        //memory();
        List<SZGSInfo> szgsInfos = szgsMapper.selectSZGSInfo();
        for (SZGSInfo info : szgsInfos)
            executorService.execute(() -> {
                try {
                    logger.info("准备更新数据:" + info);
                    initSign(info);//获取签名&初始化content
                    szgsMapper.updateSZGSInfo(info);
                    logger.info("准备更新数据:" + info.getNsrsbh()+",更新完成!");
                } catch (Exception e) {
                    logger.error("处理数据异常<<<<<<<:" + info + ">>>>>>" + e);
                }
            });
        if (szgsInfos.size() < 1) {
            logger.info("数据已经更新完!");
        }
    }

    public void memory(){
        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        logger.info("JVM内存已用/空闲/总内存/最大内存,"+vmUse+"/"+vmFree+"/"+vmTotal+"/"+vmMax+"MB");
    }

    /**
     * 获取签名及公钥
     * @param info
     */
    private void initSign(SZGSInfo info) {
        Map<String,String> paramsMap = new HashMap<>();
        info.setAreacode(StringUtils.defaultIfBlank(info.getAreacode(),"").trim());
        paramsMap.put("userName",info.getNsrsbh()+"_"+info.getAreacode());
        paramsMap.put("nsrsbh",info.getNsrsbh());
        paramsMap.put("signInfo","e3bd7d9cb5fd249a2174bcde7dee6918");
        String jsonStr = JSON.toJSONString(paramsMap);
        logger.info("深圳获取签名及公钥请求参数:"+ JsonUtils.prettyPrinter(jsonStr));
        String result = HttpClientUtil.doPostJson(certUrl,null,jsonStr);
        logger.info("深圳获取签名及公钥请求返回:"+JsonUtils.prettyPrinter(result));
        JSONObject json = JSON.parseObject(result);
        //拼装请求征信XML格式
        String publicKey = StringUtils.defaultIfBlank(json.getString("publicKey"),"");
        String sign = StringUtils.defaultIfBlank(json.getString("signInfo"),"");
        // 协议内容
        Map<String, String> caMap = new HashMap<>();
        caMap.put("sign", sign);
        caMap.put("protocolContent", "e3bd7d9cb5fd249a2174bcde7dee6918");//协议内容为空
        caMap.put("signCert", publicKey);
        caMap.put("certFlag", "0");
        caMap.put("authorityTime", info.getSqrq());//授权日期
        caMap.put("validTime", info.getYxqz());//有效日期
        caMap.put("authorityTarget",info.getAreacode());
        String nsrbm = JSONObject.toJSONString(caMap);
        logger.info("深圳请求征信nsrbm信息为:"+JsonUtils.prettyPrinter(nsrbm));
        Base64 base64 = new Base64();
        nsrbm = new String(base64.encode(nsrbm.getBytes()));
        logger.info("Base64编码后值:"+nsrbm);
        info.setContent(nsrbm);
    }
}
