package com.quzy.coding.bean;

import java.util.List;

public class MyData {
    /**
     * measure : [{"date":"2018-06-01 14:43:45","id":11,"title":"枫溪美郡13号楼","havePay":"否"},{"date":"2018-06-01 15:31:03","id":12,"title":"枫溪美郡13号楼","havePay":"1.00"}]
     * state : ok
     * budget : [{"date":"2018-09-29 09:31:21","id":45,"title":"绿地新里城 5 - 1 - 101 半包预算"}]
     * master : [{"date":"2018-10-12 10:36:06","telphone":"13391012289","dictName":"木工","id":20,"title":"张三","isVip":"是"}]
     */

    private String state;
    private List<MeasureBean> measure;
    private List<BudgetBean> budget;
    private List<MasterBean> master;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MeasureBean> getMeasure() {
        return measure;
    }

    public void setMeasure(List<MeasureBean> measure) {
        this.measure = measure;
    }

    public List<BudgetBean> getBudget() {
        return budget;
    }

    public void setBudget(List<BudgetBean> budget) {
        this.budget = budget;
    }

    public List<MasterBean> getMaster() {
        return master;
    }

    public void setMaster(List<MasterBean> master) {
        this.master = master;
    }

    public static class MeasureBean {
        /**
         * date : 2018-06-01 14:43:45
         * id : 11
         * title : 枫溪美郡13号楼
         * havePay : 否
         */

        private String date;
        private int id;
        private String title;
        private String havePay;
        private String payNum="0.0";

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHavePay() {
            return havePay;
        }

        public void setHavePay(String havePay) {
            this.havePay = havePay;
        }

        public String getPayNum() {
            return payNum;
        }

        public void setPayNum(String payNum) {
            this.payNum = payNum;
        }
    }

    public static class BudgetBean {
        /**
         * date : 2018-09-29 09:31:21
         * id : 45
         * title : 绿地新里城 5 - 1 - 101 半包预算
         */

        private String date;
        private int id;
        private String title;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class MasterBean {
        /**
         * date : 2018-10-12 10:36:06
         * telphone : 13391012289
         * dictName : 木工
         * id : 20
         * title : 张三
         * isVip : 是
         */

        private String date;
        private String telphone;
        private String dictName;
        private int id;
        private String title;
        private String isVip;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getDictName() {
            return dictName;
        }

        public void setDictName(String dictName) {
            this.dictName = dictName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIsVip() {
            return isVip;
        }

        public void setIsVip(String isVip) {
            this.isVip = isVip;
        }
    }
}
