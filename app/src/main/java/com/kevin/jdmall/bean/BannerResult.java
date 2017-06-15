package com.kevin.jdmall.bean;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.BannerResult.java
 * @author: zk
 * @date: 2017-06-14 13:09
 */

public class BannerResult {

    /**
     * success : true
     * errorMsg :
     * result : [{"id":"广告id","type":"跳转类型（1跳转到网页，2跳转到商品详情，3跳转到分类去）","adUrl":"图片路径",
     * "webUrl":"如果是跳转网页类型，则返回网页地址","adKind":"广告类型（1为导航banner，2为广告banner）"}]
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
         * id : 广告id
         * type : 跳转类型（1跳转到网页，2跳转到商品详情，3跳转到分类去）
         * adUrl : 图片路径
         * webUrl : 如果是跳转网页类型，则返回网页地址
         * adKind : 广告类型（1为导航banner，2为广告banner）
         */

        private int id;
        private int type;
        private String adUrl;
        private String webUrl;
        private int adKind;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAdUrl() {
            return adUrl;
        }

        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public int getAdKind() {
            return adKind;
        }

        public void setAdKind(int adKind) {
            this.adKind = adKind;
        }
    }

    @Override
    public String toString() {
        return "BannerResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
