package com.kevin.jdmall.bean;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.BrandResult.java
 * @author: zk
 * @date: 2017-06-25 21:07
 */

public class BrandResult {

    /**
     * success : true
     * errorMsg :
     * result : [{"id":"品牌id","name":"品牌名称"}]
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
         * id : 品牌id
         * name : 品牌名称
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
