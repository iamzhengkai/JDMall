package com.kevin.jdmall.bean;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.ProductCommentResult.java
 * @author: zk
 * @date: 2017-06-28 10:44
 */

public class ProductCommentResult {

    /**
     * success : true
     * errorMsg :
     * result : [{"imgUrls":["/img/p2.jpg","/img/p3.jpg"],"time":"2016-03-06 21:36:10","rate":3,
     * "userName":"2","type":1,"comment":"满减好划算，还不用担心质量，买了一大堆。"}]
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
         * imgUrls : ["/img/p2.jpg","/img/p3.jpg"]
         * time : 2016-03-06 21:36:10
         * rate : 3
         * userName : 2
         * type : 1
         * comment : 满减好划算，还不用担心质量，买了一大堆。
         */

        private String time;
        private int rate;
        private String userName;
        private int type;
        private String comment;
        private List<String> imgUrls;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }
    }
}
