package work.iruby.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import work.iruby.entity.News;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ruby
 * @date 2020/12/23 14:46
 */
public class JdbcDao implements DatabaseDao {
    Connection connection;

    @SuppressFBWarnings("DMI_CONSTANT_DB_PASSWORD")
    public JdbcDao() {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        String jdbcUrl = "jdbc:h2:file:" + new File(projectDir, "crawler").getAbsolutePath();
        System.out.println(jdbcUrl);
        try {
            connection = DriverManager.getConnection(jdbcUrl, "root", "root");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressFBWarnings
    @Override
    public String getInterestedLink() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select link from interested_link limit 1");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }


    @Override
    public void addInterestedLink(String link) {

    }

    @Override
    public void deleteInterestedLink(String link) {

    }

    @Override
    public void addUnInterestedLink(String link) {

    }

    @Override
    public boolean searchUnInterestedLink(String link) {
        return false;
    }


    @SuppressFBWarnings
    @Override
    public void addNews(News news) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into news (title,content,link,created_at,update_at) values (?,?,?,now(),now())");
        statement.setString(1, news.getTitle());
        statement.setString(2, news.getContent());
        statement.setString(3, news.getLink());
        System.out.println(statement.execute());
    }
}
