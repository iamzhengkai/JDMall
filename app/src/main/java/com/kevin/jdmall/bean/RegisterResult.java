package com.kevin.jdmall.bean;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.RegisterResult.java
 * @author: zk
 * @date: 2017-03-16 09:09
 */

public class RegisterResult {

    /**
     * success : true
     * errorMsg :
     * result : {"id":"用户id"}
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
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
