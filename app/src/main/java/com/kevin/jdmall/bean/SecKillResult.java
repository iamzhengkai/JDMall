package com.kevin.jdmall.bean;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.bean.SecKillResult.java
 * @author: zk
 * @date: 2017-06-24 20:51
 */

public class SecKillResult {

    /**
     * success : true
     * errorMsg :
     * result : {"total":10,"rows":[{"allPrice":20,"pointPrice":15,"iconUrl":"/img/p1.jpg",
     * "timeLeft":30,"type":1,"productId":1},{"allPrice":30,"pointPrice":16,
     * "iconUrl":"/img/p/lsj1.jpg","timeLeft":40,"type":1,"productId":2},{"allPrice":20,
     * "pointPrice":17,"iconUrl":"/img/p/df1.jpg","timeLeft":30,"type":1,"productId":3},
     * {"allPrice":30,"pointPrice":19,"iconUrl":"/img/p/g2.jpg","timeLeft":40,"type":1,
     * "productId":4},{"allPrice":20,"pointPrice":18,"iconUrl":"/img/p/fll1.jpg","timeLeft":30,
     * "type":1,"productId":5},{"allPrice":130,"pointPrice":99,"iconUrl":"/img/p/bx1.jpg",
     * "timeLeft":40,"type":1,"productId":6},{"allPrice":35,"pointPrice":25,
     * "iconUrl":"/img/p/yn1.jpg","timeLeft":40,"type":1,"productId":8},{"allPrice":38,
     * "pointPrice":29,"iconUrl":"/img/p/hs1.jpg","timeLeft":40,"type":1,"productId":9},
     * {"allPrice":99,"pointPrice":88,"iconUrl":"/img/p/df2.jpg","timeLeft":40,"type":1,
     * "productId":10},{"allPrice":99,"pointPrice":78,"iconUrl":"/img/p/yn2.jpg","timeLeft":40,
     * "type":1,"productId":11}]}
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
         * total : 10
         * rows : [{"allPrice":20,"pointPrice":15,"iconUrl":"/img/p1.jpg","timeLeft":30,"type":1,
         * "productId":1},{"allPrice":30,"pointPrice":16,"iconUrl":"/img/p/lsj1.jpg",
         * "timeLeft":40,"type":1,"productId":2},{"allPrice":20,"pointPrice":17,
         * "iconUrl":"/img/p/df1.jpg","timeLeft":30,"type":1,"productId":3},{"allPrice":30,
         * "pointPrice":19,"iconUrl":"/img/p/g2.jpg","timeLeft":40,"type":1,"productId":4},
         * {"allPrice":20,"pointPrice":18,"iconUrl":"/img/p/fll1.jpg","timeLeft":30,"type":1,
         * "productId":5},{"allPrice":130,"pointPrice":99,"iconUrl":"/img/p/bx1.jpg",
         * "timeLeft":40,"type":1,"productId":6},{"allPrice":35,"pointPrice":25,
         * "iconUrl":"/img/p/yn1.jpg","timeLeft":40,"type":1,"productId":8},{"allPrice":38,
         * "pointPrice":29,"iconUrl":"/img/p/hs1.jpg","timeLeft":40,"type":1,"productId":9},
         * {"allPrice":99,"pointPrice":88,"iconUrl":"/img/p/df2.jpg","timeLeft":40,"type":1,
         * "productId":10},{"allPrice":99,"pointPrice":78,"iconUrl":"/img/p/yn2.jpg",
         * "timeLeft":40,"type":1,"productId":11}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * allPrice : 20
             * pointPrice : 15
             * iconUrl : /img/p1.jpg
             * timeLeft : 30
             * type : 1
             * productId : 1
             */

            private double allPrice;
            private double pointPrice;
            private String iconUrl;
            private int timeLeft;
            private int type;
            private int productId;

            public double getAllPrice() {
                return allPrice;
            }

            public void setAllPrice(double allPrice) {
                this.allPrice = allPrice;
            }

            public double getPointPrice() {
                return pointPrice;
            }

            public void setPointPrice(double pointPrice) {
                this.pointPrice = pointPrice;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public int getTimeLeft() {
                return timeLeft;
            }

            public void setTimeLeft(int timeLeft) {
                this.timeLeft = timeLeft;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }
        }
    }

    @Override
    public String toString() {
        return "SecKillResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
