package com.kevin.jdmall.bean;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.ToCartResult.java
 * @author: zk
 * @date: 2017-07-14 20:59
 */

public class ToCartResult {

    /**
     * success : false
     * errorMsg : 库存不足！
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
