package work.iruby.entity;

/**
 * @author Ruby
 * @date 2020/12/23 14:29
 */
public class News {
    private String title;
    private String content;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public News(String title, String content, String link) {
        this.title = title;
        this.content = content;
        this.link = link;
    }
}
