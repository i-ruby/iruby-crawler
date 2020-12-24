package work.iruby;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import work.iruby.dao.DatabaseDao;
import work.iruby.dao.MybatisDao;
import work.iruby.entity.Message;
import work.iruby.entity.News;
import work.iruby.enums.INTERESTED_LINK;
import work.iruby.utils.OkHttps;

import java.util.stream.Collectors;

/**
 * @author Ruby
 * @date 2020/12/22 23:11
 */
public class Crawler extends Thread {
    private final DatabaseDao dao;

    public Crawler(DatabaseDao dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        new Crawler(new MybatisDao()).run();
    }

    @Override
    public void run() {

        String link = null;

        try {
//            link = dao.getInterestedLink();
//            System.out.println(link);
//            Message<String> verify = verifyLink(link);
//            if (!verify.isSuccess()) {
//                System.out.println("失败," + verify.getContent());
//            } else {
//                System.out.println("成功," + verify.getContent());
//            }
//            link = fixLink(link);
            link = "https://news.sina.cn/2020-12-24/detail-iiznctke8190813.d.html?cre=tianyi&mod=whome&loc=1&r=24&rfunc=23&tj=cx_wap_whome&tr=24&vt=4&pos=108&his=0";
            String html = OkHttps.get(link);
            Document document = Jsoup.parse(html);
            String title = document.getElementsByClass("art_tit_h1").text();
            String content = document.getElementsByClass("art_pic_card art_content").stream()
                    .map(element -> {
                        String str = element.select("p").text()+ "\n";
                        System.out.print(str);
                        return str;
                    })
                    .reduce((x, y) -> x + y).orElse("");
//                    .collect(Collectors.joining("\n"));
//                    .map(element -> element.select("p").text() +"\n")
//                    .reduce((x, y) -> x + y).orElse("");
            dao.addNews(new News(title,content,link));


            Elements elements = document.select("a");
//            elements.forEach(e -> {
//                System.out.println(interestedLink(e.attr("href")));
//            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private String fixLink(String link) {
        if (link.startsWith("//")) {
            return "https:" + link;
        }
        return link;
    }

    private Message<String> verifyLink(String link) {
        if (link.trim().isEmpty() || link.startsWith("JavaScript") || link.startsWith("javascript")) {
            return new Message<>(false, link);
        }
        return new Message<>(true, link);
    }

    private Message<String> interestedLink(String link) {
        return new Message<>(INTERESTED_LINK.containLink(link), link);
    }

}
