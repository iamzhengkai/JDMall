package com.kevin.jdmall.bean;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.CommentCountResult.java
 * @author: zk
 * @date: 2017-06-28 18:11
 */

public class CommentCountResult {

    /**
     * success : true
     * errorMsg :
     * result : {"moderateCom":1,"allComment":1,"hasImgCom":2,"negativeCom":1,"positiveCom":1}
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
         * moderateCom : 1
         * allComment : 1
         * hasImgCom : 2
         * negativeCom : 1
         * positiveCom : 1
         */

        private int moderateCom;
        private int allComment;
        private int hasImgCom;
        private int negativeCom;
        private int positiveCom;

        public int getModerateCom() {
            return moderateCom;
        }

        public void setModerateCom(int moderateCom) {
            this.moderateCom = moderateCom;
        }

        public int getAllComment() {
            return allComment;
        }

        public void setAllComment(int allComment) {
            this.allComment = allComment;
        }

        public int getHasImgCom() {
            return hasImgCom;
        }

        public void setHasImgCom(int hasImgCom) {
            this.hasImgCom = hasImgCom;
        }

        public int getNegativeCom() {
            return negativeCom;
        }

        public void setNegativeCom(int negativeCom) {
            this.negativeCom = negativeCom;
        }

        public int getPositiveCom() {
            return positiveCom;
        }

        public void setPositiveCom(int positiveCom) {
            this.positiveCom = positiveCom;
        }
    }
}
