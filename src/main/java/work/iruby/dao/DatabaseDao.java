package work.iruby.dao;

import work.iruby.entity.News;

import java.sql.SQLException;

/**
 * @author by Ruby
 * @date 2020/12/22 23:17
 */
public interface DatabaseDao {

    /**
     * 获得一条感兴趣的链接
     *
     * @return link
     * @throws SQLException SQLException
     */
    String getInterestedLink() throws SQLException;

    /**
     * 将这条链接插入到感兴趣的表中
     *
     * @param link 将这条链接插入到感兴趣的表中
     * @throws SQLException SQLException
     */
    void addInterestedLink(String link) throws SQLException;

    /**
     * 将这条链从感兴趣的表中移除
     *
     * @param link link
     * @throws SQLException SQLException
     */
    void deleteInterestedLink(String link) throws SQLException;

    /**
     * 将这条链接插入到不感兴趣的表中
     *
     * @param link link
     * @throws SQLException SQLException
     */
    void addUnInterestedLink(String link) throws SQLException;

    /**
     * 到不感兴趣的表中查找这个链接
     *
     * @param link link
     * @return 有为true, 无为false
     * @throws SQLException SQLException
     */
    boolean searchUnInterestedLink(String link) throws SQLException;

    /**
     * 将新闻插入到新闻表中
     *
     * @param news news
     * @throws SQLException SQLException
     */
    void addNews(News news) throws SQLException;
}
