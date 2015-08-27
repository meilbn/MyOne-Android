package com.mylody.myone.bean;

/**
 * User: HappyHacking
 * Date: 2015-08-27
 * Time: 11:22
 * Description: 首页内容实体类
 */
public class HomeBean {

    private String result;
    private HpEntity hpEntity;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public HpEntity getHpEntity() {
        return hpEntity;
    }

    public void setHpEntity(HpEntity hpEntity) {
        this.hpEntity = hpEntity;
    }

    public static class HpEntity {

        /**
         * "strLastUpdateDate": "2015-08-05 12:00:51",
         * "strDayDiffer": "",
         * "strHpId": "1056",
         * "strHpTitle": "VOL.1037",
         * "strThumbnailUrl": "http:\/\/pic.yupoo.com\/hanapp\/EQZGOl1v\/LLC0G.jpg",
         * "strOriginalImgUrl": "http:\/\/pic.yupoo.com\/hanapp\/EQZGOl1v\/LLC0G.jpg",
         * "strAuthor": "吻&Alex 作品",
         * "strContent": "两个人之间最好的感觉就是，表面相互嫌弃，心中不离不弃。 by 李云龙",
         * "strMarketTime": "2015-08-10",
         * "sWebLk": "http:\/\/m.wufazhuce.com\/one\/2015-08-10",
         * "strPn": "27560",
         * "wImgUrl": "http:\/\/211.152.49.184:9000\/upload\/onephoto\/f1438747251710.jpg"
         */
        private String strLastUpdateDate;
        private String strDayDiffer;
        private String strHpId;
        private String strHpTitle;
        private String strThumbnailUrl;
        private String strOriginalImgUrl;
        private String strPaintName;
        private String strAuthor;
        private String strContent;
        private String strMarketTime;
        private String strDay;
        private String strMonthYear;

        public String getStrDay() {
            return strDay;
        }

        public void setStrDay(String strDay) {
            this.strDay = strDay;
        }

        public String getStrMonthYear() {
            return strMonthYear;
        }

        public void setStrMonthYear(String strMonthYear) {
            this.strMonthYear = strMonthYear;
        }

        private String sWebLk;
        private String strPn;
        private String wImgUrl;

        public String getStrLastUpdateDate() {
            return strLastUpdateDate;
        }

        public void setStrLastUpdateDate(String strLastUpdateDate) {
            this.strLastUpdateDate = strLastUpdateDate;
        }

        public String getStrDayDiffer() {
            return strDayDiffer;
        }

        public void setStrDayDiffer(String strDayDiffer) {
            this.strDayDiffer = strDayDiffer;
        }

        public String getStrHpId() {
            return strHpId;
        }

        public void setStrHpId(String strHpId) {
            this.strHpId = strHpId;
        }

        public String getStrHpTitle() {
            return strHpTitle;
        }

        public void setStrHpTitle(String strHpTitle) {
            this.strHpTitle = strHpTitle;
        }

        public String getStrThumbnailUrl() {
            return strThumbnailUrl;
        }

        public void setStrThumbnailUrl(String strThumbnailUrl) {
            this.strThumbnailUrl = strThumbnailUrl;
        }

        public String getStrOriginalImgUrl() {
            return strOriginalImgUrl;
        }

        public void setStrOriginalImgUrl(String strOriginalImgUrl) {
            this.strOriginalImgUrl = strOriginalImgUrl;
        }

        public String getStrPaintName() {
            return strPaintName;
        }

        public void setStrPaintName(String strPaintName) {
            this.strPaintName = strPaintName;
        }

        public String getStrAuthor() {
            return strAuthor;
        }

        public void setStrAuthor(String strAuthor) {
            this.strAuthor = strAuthor;
        }

        public String getStrContent() {
            return strContent;
        }

        public void setStrContent(String strContent) {
            this.strContent = strContent;
        }

        public String getStrMarketTime() {
            return strMarketTime;
        }

        public void setStrMarketTime(String strMarketTime) {
            this.strMarketTime = strMarketTime;
        }

        public String getsWebLk() {
            return sWebLk;
        }

        public void setsWebLk(String sWebLk) {
            this.sWebLk = sWebLk;
        }

        public String getStrPn() {
            return strPn;
        }

        public void setStrPn(String strPn) {
            this.strPn = strPn;
        }

        public String getwImgUrl() {
            return wImgUrl;
        }

        public void setwImgUrl(String wImgUrl) {
            this.wImgUrl = wImgUrl;
        }
    }
}
