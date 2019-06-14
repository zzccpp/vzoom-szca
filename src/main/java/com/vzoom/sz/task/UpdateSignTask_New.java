/*
package com.vzoom.sz.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.vzoom.sz.bean.SZGSInfo;
import com.vzoom.sz.mapper.SZGSMapper;
import com.vzoom.sz.utils.JsonUtils;
import com.vzoom.utils.HttpClientUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

*/
/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 20:40
 * @describe vzoom-szca
 *
 * 更新深圳签名证书任务
 *
 *//*

//@Service
public class UpdateSignTask_New {

    private static Logger logger = Logger.getLogger(UpdateSignTask_New.class);
    private  ExecutorService pool =  Executors.newFixedThreadPool(50);
    private  ListeningExecutorService executorService = MoreExecutors.listeningDecorator(pool);
    private  List<ListenableFuture<Integer>> futures = Lists.newArrayList();

    @Value("${sz.cert.url}")
    private String certUrl;//博客作者
    @Value("${threadNum}")
    private int threadNum;

    @Autowired(required = false)
    private SZGSMapper szgsMapper;

    private boolean isInit = false;

    @Scheduled(fixedRate=5000) //上一次开始执行时间点之后5秒再执行
    public void doTask(){
        */
/*if(!isInit){
            logger.info("初始化线程池：threadNum="+threadNum);
            pool =  Executors.newFixedThreadPool(threadNum);
            isInit = true;
        }*//*

        for(int i = 0; i < 100;  i ++){
            List<SZGSInfo> szgsInfos = szgsMapper.selectSZGSInfo();
            for (SZGSInfo info : szgsInfos){
                ListenableFuture listenableFuture = executorService.submit(new ClientTask(info));
                Futures.addCallback(listenableFuture, new FutureCallbackImpl());
                futures.add(listenableFuture);
            }
        }
        Future<List<Integer>> future = Futures.allAsList(futures);
        try {
            future.get();
            logger.info("已完成所有任务！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class FutureCallbackImpl implements FutureCallback<SZGSInfo> {

        @Override
        public void onSuccess(SZGSInfo o) {
            logger.info(String.format("任务成功!%s", o.getNsrsbh()));
        }

        @Override
        public void onFailure(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    class ClientTask implements Callable<SZGSInfo> {
        private SZGSInfo info;
        public ClientTask(SZGSInfo info){
            this.info = info;
        }
        @Override
        public SZGSInfo call() throws Exception {
            logger.info("准备更新数据:" + info);
            initSign(info);//获取签名&初始化content
            szgsMapper.updateSZGSInfo(info);
            logger.info("准备更新数据:" + info.getNsrsbh()+",更新完成!");
            return info;
        }
    }

    */
/**
     * 获取签名及公钥
     * @param info
     *//*

    private void initSign(SZGSInfo info) {
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("userName",info.getNsrsbh()+"_"+StringUtils.defaultIfBlank(info.getAreacode(),""));
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
        caMap.put("authorityTarget",StringUtils.defaultIfBlank(info.getAreacode(),""));
        String nsrbm = JSONObject.toJSONString(caMap);
        logger.info("深圳请求征信nsrbm信息为:"+JsonUtils.prettyPrinter(nsrbm));
        Base64 base64 = new Base64();
        nsrbm = new String(base64.encode(nsrbm.getBytes()));
        logger.info("Base64编码后值:"+nsrbm);
        info.setContent(nsrbm);
    }
}
*/
