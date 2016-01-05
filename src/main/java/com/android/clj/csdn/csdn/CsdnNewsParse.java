package com.android.clj.csdn.csdn;

import android.util.Log;

import com.android.clj.csdn.bean.News;
import com.android.clj.csdn.bean.NewsDetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * csdn文档解析类
 */
public class CsdnNewsParse {

    private static CsdnNewsParse mInstance;

    public static CsdnNewsParse getmInstance() {
        if (mInstance == null) {
            synchronized (CsdnNewsParse.class) {
                if (mInstance == null) {
                    mInstance = new CsdnNewsParse();
                }

            }
        }
        return mInstance;
    }

    public List<News> getNewsList(String htmlUrl) {
        List<News> newsList = new ArrayList<News>();
        News news = null;
        //解析成文档
        Document document = Jsoup.parse(htmlUrl);
        //通过类名解析元素
        Elements elements = document.getElementsByClass("unit");
        for (int i = 0; i < elements.size(); i++) {
            news = new News();
            Element element = elements.get(i);
            //h1
            Element h1 = element.getElementsByTag("h1").get(0);
            //h1_a
            Element h1_a = h1.child(0);
            //h1_a_title
            String title = h1_a.text();
            //h1_a_href
            String href = h1_a.attr("href");
            news.setTitle(title);
            news.setLink(href);
            Log.i("clj", title);
            Log.i("clj", href);
            //h4
            Element h4 = element.getElementsByTag("h4").get(0);
            //h4_ago
            Element h4_ago = h4.getElementsByClass("ago").get(0);
            //h4_view_time
            Element h4_view_time = h4.getElementsByClass("view_time").get(0);
            //h4_num_recom
            Element h4_num_recom = h4.getElementsByClass("num_recom").get(0);
            //h4_ago_text
            String ago = h4_ago.text();
            //h4_view_time_text
            String view_time = h4_view_time.text();
            //h4_view_time_text
            String num_recom = h4_num_recom.text();
            Log.i("clj", ago);
            Log.i("clj", view_time);
            Log.i("clj", num_recom);
            news.setAgo(ago);
            news.setView_time(view_time);
            news.setNum_recom(num_recom);

            Element dl = element.getElementsByTag("dl").get(0);// dL
            Element dt = dl.child(0);// dt
            try {// 可能没有图片
                Element dt_href = dt.child(0);
                String imgHref = dt_href.attr("href");
                Log.i("clj", imgHref);
                Element dt_src = dt_href.child(0);
                String imgLink = dt_src.attr("src");
                news.setImgLink(imgLink);
                Log.i("clj", imgLink);
            } catch (IndexOutOfBoundsException e) {

            }
            //d1_dd
            Element dd = dl.child(1);
            //d1_dd_text
            String content = dd.text();
            Log.i("clj", content);
            news.setContent(content);
            newsList.add(news);
        }
        return newsList;
    }

    public NewsDetail getNewsDetial(String html, int width, int height) {
        NewsDetail news = new NewsDetail();
        Document doc = Jsoup.parse(html);
        //获取文字的评论链接
        Element comments = doc.select("td.comm").select("a").first();
        String commentLink = "http://m.csdn.net/" + comments.attr("href");
        news.setCommentsLink(commentLink);
        Log.i("clj", "comments link =" + commentLink);
        // 获得文章中的第一个detail
        Element detail = doc.select("div.wrapper").first();
        //获取title
        Element title = detail.select("h1").first();
        news.setTitle(title.text());
        //infor
        Element infor = detail.select("div.infor").first();
        news.setInfor(infor.text());
        //text 下所有的p元素s
//        Elements elements = detail.select(".text p");
//        List<String> textList = new ArrayList<>();
//        for (Element element : elements) {
//            String text = element.text();
//            Log.i("clj", text);
//            textList.add("<span>" + text + "</p>");
//        }
        Elements elements = detail.select(".text p");
        StringBuffer buffer = new StringBuffer();
        for (Element element : elements) {
            element.select("img").attr("width", "100%").attr("style", "");
            buffer.append("<p>");
            buffer.append(element.html());
            buffer.append("</p>");
            Log.i("clj", buffer.toString());
        }
        news.setTexts(buffer.toString());
        return news;
    }
}
