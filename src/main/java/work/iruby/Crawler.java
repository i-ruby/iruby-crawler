package work.iruby;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import work.iruby.dao.DatabaseDao;
import work.iruby.entity.Message;
import work.iruby.entity.News;
import work.iruby.enums.INTERESTED_LINK;
import work.iruby.utils.OkHttps;

import java.io.IOException;
import java.sql.SQLException;
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

    @Override
    public void run() {
        String link;
        try {
            System.out.println(getName() + " is start");
            while ((link = dao.getInterestedLink()) != null) {
                System.out.println(getName() + ":link" + link);
                Message<String> verify = verifyLink(link);
                if (verify.isSuccess()) {
                    dealLink(verify.getContent());
                }
            }
            System.out.println(getName() + " is end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dealLink(String link) throws SQLException, IOException {
        String html = OkHttps.get(link);
        Document document = Jsoup.parse(html);
        String title = document.getElementsByClass("art_tit_h1").text();
        if (!title.trim().isEmpty()) {
            String content = document.getElementsByClass("art_pic_card art_content").select("p").stream()
                    .map(Element::text).collect(Collectors.joining("\n"));
            dao.addNews(new News(title, content, link));
            System.out.println(this.getName() + ":" + title);
        }
        Elements elements = document.select("a");
        elements.forEach(e -> addInterestedLink(e.attr("href")));
        dao.addUnInterestedLink(link);
    }

    private void addInterestedLink(String link) {
        if (verifyLink(link).isSuccess()) {
            try {
                if (!dao.searchUnInterestedLink(link)) {
                    dao.addInterestedLink(link);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private String fixLink(String link) {
        if (link.startsWith("//")) {
            return "https:" + link;
        }
        return link;
    }

    private boolean legalLink(String link) {
        return !link.trim().isEmpty() && !link.toLowerCase().startsWith("javascript");
    }

    private boolean interestedLink(String link) {
        return INTERESTED_LINK.containLink(link);
    }

    private Message<String> verifyLink(String link) {
        Message<String> message = new Message<>(false, link);
        if (!legalLink(link)) {
            return message;
        }
        link = fixLink(link);
        if (!interestedLink(link)) {
            return message;
        }
        message.setSuccess(true);
        message.setContent(link);
        return message;
    }

}
