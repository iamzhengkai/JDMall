package com.kevin.jdmall.bean;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.LoginResult.java
 * @author: zk
 * @date: 2017-03-16 00:45
 */

public class LoginResult {

    /**
     * success : true
     * errorMsg :
     * result : {"id":"用户id","userName":"用户名","userIcon":"头像路径","waitPayCount":"待付款数","waitReceiveCount":"待收货数","userLevel":"用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）"}
     */

    private boolean success;
    private String errorMsg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 用户id
         * userName : 用户名
         * userIcon : 头像路径
         * waitPayCount : 待付款数
         * waitReceiveCount : 待收货数
         * userLevel : 用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）
         */

        private int id;
        private String userName;
        private String userIcon;
        private String waitPayCount;
        private String waitReceiveCount;
        private String userLevel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getWaitPayCount() {
            return waitPayCount;
        }

        public void setWaitPayCount(String waitPayCount) {
            this.waitPayCount = waitPayCount;
        }

        public String getWaitReceiveCount() {
            return waitReceiveCount;
        }

        public void setWaitReceiveCount(String waitReceiveCount) {
            this.waitReceiveCount = waitReceiveCount;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
