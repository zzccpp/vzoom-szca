package com.vzoom.sz.bean;

import lombok.Data;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 16:53
 * @describe vzoom-szca <描述>
 */
@Data
public class SZCAInfo {

    private int id;
    private String nsrsbh;
    private String publicKey;
    private String sign;
    private String creatTime;

}
