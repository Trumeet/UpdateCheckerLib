package kh.android.updatecheckerlib;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Project UpdateChecker
 * Created by Trumeet on 2016/10/28.
 * Edited by Trumeet
 */

public class UpdateChecker {
    public static class UpdateInfo {
        private Market mMarket;
        private String mPackageName;
        private String mChangeLog;
        private String mVersionName;

        public String getChangeLog() {
            return mChangeLog;
        }

        public Market getMarket() {
            return mMarket;
        }

        public String getPackageName() {
            return mPackageName;
        }

        public String getVersionName() {
            return mVersionName;
        }

        private UpdateInfo(){}
    }
    public enum Market {
        MARKET_COOLAPK,
        MARKET_GOOGLEPLAY,
        MARKET_WANDOUJIA
    }

    public static UpdateInfo check (Market market, String packageName) throws IOException{
        Document document;
        UpdateInfo info = new UpdateInfo();
        info.mMarket = market;
        info.mPackageName = packageName;
        switch (market) {     
            case MARKET_COOLAPK:
                document = Jsoup.connect("http://coolapk.com/apk/" + packageName).get();
                info.mVersionName = document.select("span[class=list_app_info]").get(0).html();
                info.mChangeLog = document.select("p[class=apk_left_title_info]").get(0).html().replaceAll("<br>", "\n");
                return info;
            case MARKET_GOOGLEPLAY:
                document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + packageName).get();
                info.mVersionName = document.select("div[itemprop=softwareVersion]").get(0).html();
                Elements elements = document.select("div.recent-change");
                String changeLog = "";
                String enter = "\n";
                for (Element element : elements) {
                    changeLog += element.text();
                    changeLog += enter;
                }
                info.mChangeLog = changeLog;
                return info;
            case MARKET_WANDOUJIA :
                document = Jsoup.connect("http://www.wandoujia.com/apps/" + packageName).get();
                info.mVersionName = document.select("div[class=con]").get(0).text().substring(3);
                info.mChangeLog = document.select("div[class=con]").get(1).html();
                return info;
        }
        return null;
    }
}
