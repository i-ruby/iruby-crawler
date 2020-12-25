package work.iruby;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import work.iruby.dao.MybatisDao;
import work.iruby.entity.News;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ruby
 * @date 2020/12/25 16:14
 */
public class ElasticsearchData {
    public static void main(String[] args) {
        MybatisDao dao = new MybatisDao();
        List<News> newsList = dao.selectNewsList();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> writeData(newsList)).start();
        }
    }

    private static void writeData(List<News> newsList) {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("http://121.4.73.4/", 9200, "http")))) {
            BulkRequest bulkRequest = new BulkRequest();

            for (News news : newsList) {
                IndexRequest request = new IndexRequest("news");
                Map<String, Object> map = new HashMap<>();
                map.put("title", news.getTitle());
                map.put("content", news.getContent());
                map.put("link", news.getLink());
                map.put("createdAt", news.getCreatedAt());
                map.put("updateAt", news.getUpdateAt());
                request.source(map, XContentType.JSON);
                bulkRequest.add(request);
            }
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            System.out.println("status:" + bulk.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
