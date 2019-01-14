package y530.retrofitdemo.network.model;

import java.util.List;

/**
 * Created by Administrator on 2019/1/14.
 */

public class FeedbackModel {

    /**
     * code : 200
     * data : [{"commodityId":58,"commodityName":"LAB SERIES/朗仕男士多功能洁面乳 男控油深层清洁去角质洗面奶","ifAddActivity":false,"orderBy":19,"picUrl":"/file/11addf05-26dc-4d7d-b1f0-37de4e1c238c.png","sellNumber":16,"sellingPrice":0},{"commodityId":51,"commodityName":"测试测试","ifAddActivity":false,"orderBy":18,"picUrl":"/file/403bbc42-be00-48f6-bd77-4e1c0f95bf97.png","sellNumber":16,"sellingPrice":0.01},{"commodityId":48,"commodityName":"京天华盛AMD四核2G独显英雄联盟LOL台式电脑主机组装DIY游戏整机","ifAddActivity":false,"orderBy":6,"picUrl":"/file/21ff7991-7b6e-48c4-bdbe-f98027be81a9.png","sellNumber":5,"sellingPrice":1},{"commodityId":81,"commodityName":"荣耀V20 胡歌同款 麒麟980芯片 魅眼全视屏 6GB+128GB 魅海蓝 游戏手机 移动联通电信4G全面屏手机 双卡双待","ifAddActivity":false,"orderBy":4,"picUrl":"/file/fbb28921-e94b-4d94-99a2-d9e421e638e9.png","sellNumber":1,"sellingPrice":0.01},{"commodityId":87,"commodityName":"Apple iPhone XS Max (A2103) 256GB 金色 全网通","ifAddActivity":false,"orderBy":3,"picUrl":"/file/58efb133-8b55-4eea-b0c9-90641a76e701.jpg","sellNumber":3,"sellingPrice":1000},{"commodityId":65,"commodityName":"欧莱雅(LOREAL)男士劲能醒肤护肤套装100ml+50ml+50ml*2+30ml*5 控油平衡;清爽;深层清","ifAddActivity":false,"orderBy":3,"picUrl":"/file/f7dabc75-c283-412e-96b9-501a3f8d835a.png","sellNumber":1,"sellingPrice":0}]
     * msg : 获得本地优选商品列表成功
     * success : true
     */

    private int code;
    private String msg;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commodityId : 58
         * commodityName : LAB SERIES/朗仕男士多功能洁面乳 男控油深层清洁去角质洗面奶
         * ifAddActivity : false
         * orderBy : 19
         * picUrl : /file/11addf05-26dc-4d7d-b1f0-37de4e1c238c.png
         * sellNumber : 16
         * sellingPrice : 0
         */

        private int commodityId;
        private String commodityName;
        private boolean ifAddActivity;
        private int orderBy;
        private String picUrl;
        private int sellNumber;
        private int sellingPrice;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public boolean isIfAddActivity() {
            return ifAddActivity;
        }

        public void setIfAddActivity(boolean ifAddActivity) {
            this.ifAddActivity = ifAddActivity;
        }

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getSellNumber() {
            return sellNumber;
        }

        public void setSellNumber(int sellNumber) {
            this.sellNumber = sellNumber;
        }

        public int getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(int sellingPrice) {
            this.sellingPrice = sellingPrice;
        }
    }
}
