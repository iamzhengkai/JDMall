package com.kevin.jdmall.bean;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.CommentDetailResult.java
 * @author: zk
 * @date: 2017-06-28 18:13
 */

public class CommentDetailResult {

    /**
     * success : true
     * errorMsg :
     * result : [{"id":1,"imgUrls":["/img/p2.jpg","/img/p3.jpg"],"rate":3,"loveCount":15,
     * "commentTime":"2016-03-06 21:36:10","buyTime":"2016-03-01 11:12:13","userLevel":1,
     * "subComment":0,"userName":"2","comment":"满减好划算，还不用担心质量，买了一大堆。",
     * "userImg":"/img/user/1.jpg","productType":"麦片巧克力"}]
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
         * id : 1
         * imgUrls : ["/img/p2.jpg","/img/p3.jpg"]
         * rate : 3
         * loveCount : 15
         * commentTime : 2016-03-06 21:36:10
         * buyTime : 2016-03-01 11:12:13
         * userLevel : 1
         * subComment : 0
         * userName : 2
         * comment : 满减好划算，还不用担心质量，买了一大堆。
         * userImg : /img/user/1.jpg
         * productType : 麦片巧克力
         */

        private int id;
        private int rate;
        private int loveCount;
        private String commentTime;
        private String buyTime;
        private int userLevel;
        private int subComment;
        private String userName;
        private String comment;
        private String userImg;
        private String productType;
        private List<String> imgUrls;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public int getLoveCount() {
            return loveCount;
        }

        public void setLoveCount(int loveCount) {
            this.loveCount = loveCount;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public String getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(String buyTime) {
            this.buyTime = buyTime;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public int getSubComment() {
            return subComment;
        }

        public void setSubComment(int subComment) {
            this.subComment = subComment;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }
    }
}
