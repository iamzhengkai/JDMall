package com.kevin.jdmall.bean;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.ResetResult.java
 * @author: zk
 * @date: 2017-06-07 20:55
 */

public class ResetResult {

    /**
     * success : true
     * errorMsg :
     * result :
     */

    private boolean success;
    private String errorMsg;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
