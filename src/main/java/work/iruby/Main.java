package work.iruby;

import work.iruby.dao.DatabaseDao;
import work.iruby.dao.MybatisDao;

/**
 * @author Ruby
 * @date 2020/12/22 12:20
 */
public class Main {
    public static void main(String[] args) {
        int crawlerNum = 6;
        DatabaseDao dao = new MybatisDao();
        for (int i = 0; i < crawlerNum; i++) {
            new Crawler(dao).start();
        }
    }
}
