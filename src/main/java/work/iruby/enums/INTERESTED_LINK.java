package work.iruby.enums;

/**
 * @author Ruby
 * @date 2020/12/24 11:32
 */
public enum INTERESTED_LINK {
    INDEX("https://sina.cn/"),
    NEWS("https://news.sina.cn/");

    private String link;

    private INTERESTED_LINK(String link) {
        this.link = link;
    }

    public static boolean containLink(String link) {
        for (INTERESTED_LINK value : INTERESTED_LINK.values()) {
            if (link.contains(value.link)) {
                return true;
            }
        }
        return false;
    }
}
