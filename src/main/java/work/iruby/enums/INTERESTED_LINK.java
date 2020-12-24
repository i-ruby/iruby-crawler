package work.iruby.enums;

/**
 * @author Ruby
 * @date 2020/12/24 11:32
 */
public enum INTERESTED_LINK {
    /**
     * 目标入口页面
     */
    INDEX("https://sina.cn/"),
    /**
     * 目标新闻页面
     */
    NEWS("https://news.sina.cn/");

    private final String link;

    INTERESTED_LINK(String link) {
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
