import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.example.pojo.glm.ChatGLMModel;
import org.example.pojo.glm.GLMResponseV4;
import org.example.utils.GLMTokenUtils;
import org.example.utils.JsonUtils;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyGLMTestV4 {

    public static final Integer connectTimeout = 600;           // 连接超市时间
    public static final Integer writeTimeout = 500;             // 写操作超时时间
    public static final Integer readTimeout = 400;              // 读操作超时时间
    public static final Integer readAndWriteTimeout = 600;      // 读写操作超时时间

    /********** 请求头信息 **********/
    public static final String SSE_CONTENT_TYPE = "text/event-stream";
    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";
    public static final String JSON_CONTENT_TYPE = APPLICATION_JSON + "; charset=utf-8";

    public static final String apiUrl = "https://open.bigmodel.cn/api/paas/v4/chat/completions";

    @Test
    public void test_completions() throws JsonProcessingException, InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Authorization", GLMTokenUtils.generateToken())
                .header("Content-Type", JSON_CONTENT_TYPE)
                .header("User-Agent", DEFAULT_USER_AGENT)
                .header("Accept", SSE_CONTENT_TYPE)
                .post(RequestBody.create(MediaType.parse("application/json"), generateBodyString()))
                .build();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();

        // 实例化EventSource，并注册EventSource监听器
        RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                //System.out.println("获得事件");
//                System.out.println(data);
//                if (data.contentEquals("[DONE]")) {     // 不推荐这么判断，如果返回的正常内容字符串里本身有这个，那会有问题
                if (isDone(data)) {
                    System.out.println("结束事件");
                } else {
                    GLMResponseV4 response = JsonUtils.jsonToPojo(data, GLMResponseV4.class);
                    System.out.println(response);
                }

            }

            @Override
            public void onClosed(EventSource eventSource) {
                System.out.println("关闭链接事件");
                countDownLatch.countDown();
            }

            @Override
            public void onOpen(EventSource eventSource, Response response) {
                System.out.println("打开链接事件");
            }

            @Override
            public void onFailure(EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
                System.out.println("失败事件");
            }
        });
        realEventSource.connect(okHttpClient);  // 请求调用ChatGLM，上面代码都是事件，此处为真正调用

        // 等待任务，如果不使用这个，任务就直接结束了，因为没有web环境，控制台会关闭
        countDownLatch.await();
    }

    public static boolean isDone(String data) {
        return data.matches("^\\[DONE]$");
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prompt {
        private String role;
        private String content;
    }

    public static String generateBodyString() {

        String requestId = String.format("lee-%d", System.currentTimeMillis());
        float temperature = 0.9f;
        float topP = 0.7f;
        String sseFormat = "data";      // 数据格式 GLMResponse.data
        boolean incremental = true;     // 增量式推送

        List<Prompt> promptList = new ArrayList<>();
        Prompt prompt = new Prompt();
        prompt.setRole("user");
        prompt.setContent("你好，我是风间影月");
        promptList.add(prompt);

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("request_id", requestId);
        paramsMap.put("model", ChatGLMModel.CHATGLM_4_Flash.key);
        paramsMap.put("messages", promptList);
        paramsMap.put("stream", true);      // false：默认同步，true：sse调用
        paramsMap.put("incremental", incremental);
        paramsMap.put("temperature", temperature);
        paramsMap.put("top_p", topP);
        paramsMap.put("sseFormat", sseFormat);

        try {
            return new ObjectMapper().writeValueAsString(paramsMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
