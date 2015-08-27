package com.mylody.myone.bean;

/**
 * User: HappyHacking
 * Date: 2015-08-27
 * Time: 16:53
 * Description: 东西内容实体类
 */
public class ThingBean {

    private String rs;
    private EntTg entTg;

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public EntTg getEntTg() {
        return entTg;
    }

    public void setEntTg(EntTg entTg) {
        this.entTg = entTg;
    }

    /**
     * "entTg": {
     * "strLastUpdateDate": "2015-08-08 00:11:36",
     * "strPn": "0",
     * "strBu": "http:\/\/pic.yupoo.com\/hanapp\/ERng0tpg\/mCfIg.jpg",
     * "strTm": "2015-08-10",
     * "strWu": "http:\/\/m.wufazhuce.com\/thing\/2015-08-10",
     * "strId": "583",
     * "strTt": "啤酒射灯",
     * "strTc": "“嘿，帮我把这瓶……灯打开？”"
     * },
     * "rs": "SUCCESS"
     */
    public static class EntTg {
        private String strLastUpdateDate;
        private String strPn;
        private String strBu;
        private String strTm;
        private String strWu;
        private String strId;
        private String strTt;
        private String strTc;
        private String strMarketTime;

        public String getStrLastUpdateDate() {
            return strLastUpdateDate;
        }

        public void setStrLastUpdateDate(String strLastUpdateDate) {
            this.strLastUpdateDate = strLastUpdateDate;
        }

        public String getStrPn() {
            return strPn;
        }

        public void setStrPn(String strPn) {
            this.strPn = strPn;
        }

        public String getStrBu() {
            return strBu;
        }

        public void setStrBu(String strBu) {
            this.strBu = strBu;
        }

        public String getStrTm() {
            return strTm;
        }

        public void setStrTm(String strTm) {
            this.strTm = strTm;
        }

        public String getStrWu() {
            return strWu;
        }

        public void setStrWu(String strWu) {
            this.strWu = strWu;
        }

        public String getStrId() {
            return strId;
        }

        public void setStrId(String strId) {
            this.strId = strId;
        }

        public String getStrTt() {
            return strTt;
        }

        public void setStrTt(String strTt) {
            this.strTt = strTt;
        }

        public String getStrTc() {
            return strTc;
        }

        public void setStrTc(String strTc) {
            this.strTc = strTc;
        }

        public String getStrMarketTime() {
            return strMarketTime;
        }

        public void setStrMarketTime(String strMarketTime) {
            this.strMarketTime = strMarketTime;
        }
    }
}
