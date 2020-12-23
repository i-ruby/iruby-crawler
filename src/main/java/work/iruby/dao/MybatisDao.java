package work.iruby.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import work.iruby.entity.News;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @author Ruby
 * @date 2020/12/23 20:18
 */
public class MybatisDao implements DatabaseDao {
    private final SqlSessionFactory sqlSessionFactory;
    private static final String CRAWLER_MAPPER = "work.iruby.CrawlerMapper.";

    public MybatisDao() {
        String resource = "db/mybatis/mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public String getInterestedLink() throws SQLException {
        String link = "";
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            link = session.selectOne(CRAWLER_MAPPER + "selectLink");
        }
        return link;
    }

    @Override
    public void addInterestedLink(String link) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert(CRAWLER_MAPPER + "insertInterestedLink", link);
        }
    }

    @Override
    public void deleteInterestedLink(String link) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete(CRAWLER_MAPPER + "deleteInterestedLink", link);
        }
    }

    @Override
    public void addUnInterestedLink(String link) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert(CRAWLER_MAPPER + "insertUninterestedLink", link);
        }
    }

    @Override
    public boolean searchUnInterestedLink(String link) throws SQLException {
        int count;
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            count = session.selectOne(CRAWLER_MAPPER + "searchUnInterestedLink", link);
        }
        return count > 0;
    }

    @Override
    public void addNews(News news) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert(CRAWLER_MAPPER + "insertNews", news);
        }
    }

}
