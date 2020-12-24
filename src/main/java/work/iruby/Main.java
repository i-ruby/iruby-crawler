package work.iruby;

import work.iruby.dao.DatabaseDao;
import work.iruby.dao.MybatisDao;

/**
 * @author Ruby
 * @date 2020/12/22 12:20
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int crawlerNum = 4;
        DatabaseDao dao = new MybatisDao();
        new Crawler(dao).start();
        //保证初始数据
        Thread.sleep(10000);
        for (int i = 0; i < crawlerNum; i++) {
            new Crawler(dao).start();
        }
    }
}
