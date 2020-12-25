package work.iruby;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ruby
 * @date 2020/12/25 17:12
 */
public class ElasticsearchMain {
    public static void main(String[] args) {
        while (true) {
            System.out.println("please input a keyword");
            Scanner scanner = new Scanner(System.in, "UTF-8");
            String keyword = scanner.nextLine();
            search(keyword);
        }
    }

    private static void search(String keyword) {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("http://121.4.73.4/", 9200, "http")))) {
            SearchRequest request = new SearchRequest("news");
            request.source(new SearchSourceBuilder().query(new MultiMatchQueryBuilder(keyword, "title", "content")));
            SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : searchResponse.getHits()) {
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
