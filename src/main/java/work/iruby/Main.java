package work.iruby;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import work.iruby.dao.DatabaseDao;
import work.iruby.dao.JdbcDao;
import work.iruby.entity.News;
import work.iruby.utils.OkHttps;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Ruby
 * @date 2020/12/22 12:20
 */
public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        String sinaIndex = "https://sina.cn/";
//        HashSet<String> isVisit = new HashSet<>();
//        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        String html = OkHttps.get(sinaIndex);
        Document document = Jsoup.parse(html);
        Elements elements = document.select("a");
        elements.forEach(e -> System.out.println(e.attr("href")));

        DatabaseDao dao = new JdbcDao();
        System.out.println(dao.getInterestedLink());
        News news = new News("标题", "内容", "链接");
        dao.addNews(news);
    }
}
