package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class Test {

    /**
     * busiCode : BASE000
     * code : 1
     * content : [{"cityId":6,"cityName":"上海","citySpell":"sh","isHot":true,"lat":31.231706,"lng":121.472644},{"cityId":1,"cityName":"北京","citySpell":"bj","isHot":true,"lat":39.904987,"lng":116.405281},{"cityId":2,"cityName":"天津","citySpell":"tj","isHot":false,"lat":39.125595,"lng":117.190186},{"cityId":40,"cityName":"唐山","citySpell":"ts","isHot":false,"lat":39.635113,"lng":118.175392},{"cityId":20,"cityName":"武汉","citySpell":"wh","isHot":false,"lat":30.584355,"lng":114.298572},{"cityId":435,"cityName":"乌兰浩特","citySpell":"wlht","isHot":false,"lat":46.072732,"lng":122.093123},{"cityId":329,"cityName":"乌鲁木齐","citySpell":"wlmq","isHot":false,"lat":43.792818,"lng":87.617733},{"cityId":116,"cityName":"无锡","citySpell":"wx","isHot":false,"lat":31.574729,"lng":120.301663},{"cityId":127,"cityName":"温州","citySpell":"wz","isHot":false,"lat":28.000575,"lng":120.672111}]
     * msg : 成功
     * status : SUCCESS
     * uid : f6ac1dbe-0003-427b-a231-344b320729b61467776213673
     * version : 002
     */

    private String busiCode;
    private int code;
    private String msg;
    private String status;
    private String uid;
    private String version;
    private List<ContentBean> content;

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

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * cityId : 6
         * cityName : 上海
         * citySpell : sh
         * isHot : true
         * lat : 31.231706
         * lng : 121.472644
         */

        private int cityId;
        private String cityName;
        private String citySpell;
        private boolean isHot;
        private double lat;
        private double lng;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCitySpell() {
            return citySpell;
        }

        public void setCitySpell(String citySpell) {
            this.citySpell = citySpell;
        }

        public boolean isIsHot() {
            return isHot;
        }

        public void setIsHot(boolean isHot) {
            this.isHot = isHot;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
