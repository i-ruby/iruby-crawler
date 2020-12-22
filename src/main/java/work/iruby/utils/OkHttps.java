package work.iruby.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Ruby
 * @date 2020/12/22 16:29
 */
public class OkHttps {
    private static OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36")
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Optional<ResponseBody> responseBody = Optional.ofNullable(response.body());
            if (responseBody.isPresent()) {
                return responseBody.get().string();
            } else {
                return "";
            }
        }
    }


    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36")
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Optional<ResponseBody> responseBody = Optional.ofNullable(response.body());
            if (responseBody.isPresent()) {
                return responseBody.get().string();
            } else {
                return "";
            }
        }
    }
}
