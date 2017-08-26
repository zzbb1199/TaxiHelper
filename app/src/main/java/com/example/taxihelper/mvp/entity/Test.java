package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class Test {

    /**
     * busiCode : BASE000
     * code : 1
     * content : {"distance":22983,"duration":45,"estimateId":"09135ac099faf7bbff46adf755b30a48","prices":[{"carGroupId":2,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":114.9,"longDistancePrice":83.92,"longDistancePriceLimit":2,"name":"公务轿车","outCityPrice":0,"perKilometrePrice":5,"perLongDistancePrice":4,"perTimePrice":3,"price":353,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":20,"timePrice":135},{"carGroupId":1,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":45.96,"longDistancePrice":45.96,"longDistancePriceLimit":0,"name":"优驾舒享","outCityPrice":0,"perKilometrePrice":2,"perLongDistancePrice":2,"perTimePrice":4.2,"price":300,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":20,"timePrice":189},{"carGroupId":3,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":128.69,"longDistancePrice":75.02,"longDistancePriceLimit":10,"name":"商务7座","outCityPrice":0,"perKilometrePrice":5.6,"perLongDistancePrice":5.78,"perTimePrice":3,"price":340,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":2,"timePrice":135},{"carGroupId":4,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":22.98,"longDistancePrice":83.92,"longDistancePriceLimit":2,"name":"豪华轿车","outCityPrice":0,"perKilometrePrice":1,"perLongDistancePrice":4,"perTimePrice":2,"price":216,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":20,"timePrice":90}]}
     * handler : CLIENT
     * msg : 成功
     * status : SUCCESS
     * uid : f6b03ecf-dd53-4aa6-8c76-f169ab3083fe
     * version : 001
     */

    private String busiCode;
    private int code;
    private ContentBean content;
    private String handler;
    private String msg;
    private String status;
    private String uid;
    private String version;

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class ContentBean {
      
    }
}
