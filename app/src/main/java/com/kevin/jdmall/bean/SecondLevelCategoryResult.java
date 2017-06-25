package com.kevin.jdmall.bean;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.SecondLevelCategoryResult.java
 * @author: zk
 * @date: 2017-06-24 23:56
 */

public class SecondLevelCategoryResult {

    /**
     * success : true
     * errorMsg :
     * result : [{"id":13,"name":"裙装","thirdCategory":[{"id":7,"bannerUrl":"/img/lyq.jpg",
     * "name":"连衣裙"}]},{"id":14,"name":"上装","thirdCategory":[{"id":8,"bannerUrl":"/img/zzs.jpg",
     * "name":"针织衫"},{"id":10,"bannerUrl":"/img/wy.jpg","name":"卫衣"},{"id":11,
     * "bannerUrl":"/img/tx.png","name":"T恤"},{"id":12,"bannerUrl":"/img/xfs.jpg",
     * "name":"雪纺衫"}]},{"id":15,"name":"下装","thirdCategory":[{"id":9,"bannerUrl":"/img/nzk.jpg",
     * "name":"牛仔裤"}]}]
     */

    private boolean success;
    private String errorMsg;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 13
         * name : 裙装
         * thirdCategory : [{"id":7,"bannerUrl":"/img/lyq.jpg","name":"连衣裙"}]
         */

        private int id;
        private String name;
        private List<ThirdCategoryBean> thirdCategory;

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

        public List<ThirdCategoryBean> getThirdCategory() {
            return thirdCategory;
        }

        public void setThirdCategory(List<ThirdCategoryBean> thirdCategory) {
            this.thirdCategory = thirdCategory;
        }

        public static class ThirdCategoryBean {
            /**
             * id : 7
             * bannerUrl : /img/lyq.jpg
             * name : 连衣裙
             */

            private int id;
            private String bannerUrl;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBannerUrl() {
                return bannerUrl;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
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
