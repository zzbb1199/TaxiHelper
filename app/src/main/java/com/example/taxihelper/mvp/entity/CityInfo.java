package com.example.taxihelper.mvp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Raven on 2017/8/8.
 */

public class CityInfo {

    /**
     * cityId : 1
     * cityName : 北京
     * services : {"7":{"id":7,"name":"接机"},"8":{"id":8,"name":"送机"},"11":{"id":11,"name":"包车（4小时）"},"13":{"id":13,"name":"预约用车"},"14":{"id":14,"name":"立即叫车"}}
     */

    private int cityId;
    private String cityName;
    private ServicesBean services;

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

    public ServicesBean getServices() {
        return services;
    }

    public void setServices(ServicesBean services) {
        this.services = services;
    }

    public static class ServicesBean {
        /**
         * 7 : {"id":7,"name":"接机"}
         * 8 : {"id":8,"name":"送机"}
         * 11 : {"id":11,"name":"包车（4小时）"}
         * 13 : {"id":13,"name":"预约用车"}
         * 14 : {"id":14,"name":"立即叫车"}
         */

        @SerializedName("7")
        private _$7Bean _$7;
        @SerializedName("8")
        private _$8Bean _$8;
        @SerializedName("11")
        private _$11Bean _$11;
        @SerializedName("13")
        private _$13Bean _$13;
        @SerializedName("14")
        private _$14Bean _$14;

        public _$7Bean get_$7() {
            return _$7;
        }

        public void set_$7(_$7Bean _$7) {
            this._$7 = _$7;
        }

        public _$8Bean get_$8() {
            return _$8;
        }

        public void set_$8(_$8Bean _$8) {
            this._$8 = _$8;
        }

        public _$11Bean get_$11() {
            return _$11;
        }

        public void set_$11(_$11Bean _$11) {
            this._$11 = _$11;
        }

        public _$13Bean get_$13() {
            return _$13;
        }

        public void set_$13(_$13Bean _$13) {
            this._$13 = _$13;
        }

        public _$14Bean get_$14() {
            return _$14;
        }

        public void set_$14(_$14Bean _$14) {
            this._$14 = _$14;
        }

        public static class _$7Bean {
            /**
             * id : 7
             * name : 接机
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$8Bean {
            /**
             * id : 8
             * name : 送机
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$11Bean {
            /**
             * id : 11
             * name : 包车（4小时）
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$13Bean {
            /**
             * id : 13
             * name : 预约用车
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$14Bean {
            /**
             * id : 14
             * name : 立即叫车
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
