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
import org.example.pojo.glm.EventType;
import org.example.pojo.glm.GLMResponseV3;
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
public class MyGLMTestV3 {

    public static final Integer connectTimeout = 600;           // 连接超时时间
    public static final Integer writeTimeout = 500;             // 写操作超时时间
    public static final Integer readTimeout = 400;              // 读操作超时时间
    public static final Integer readAndWriteTimeout = 600;      // 读写操作超时时间

    /********** 请求头信息 **********/
    public static final String SSE_CONTENT_TYPE = "text/event-stream";
    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";
    public static final String JSON_CONTENT_TYPE = APPLICATION_JSON + "; charset=utf-8";

    /**
     * api请求的调用方式
     *      invoke: 同步调用
     *      async-invoke: 异步调用
     *      sse-invoke: SSE 调用
     */
    public static final String apiUrl = "https://open.bigmodel.cn/api/paas/v3/model-api/" + ChatGLMModel.CHATGLM_4_Flash.key + "/sse-invoke";

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

                GLMResponseV3 response = JsonUtils.jsonToPojo(data, GLMResponseV3.class);
                System.out.println(response);
                log.info("onEvent：{}", response.getData());
                // type消息类型：add-增量、finish-结束、error-错误、interrupted-中断
                if (EventType.finish.key.equals(type)) {
                    GLMResponseV3.Meta meta = JsonUtils.jsonToPojo(response.getMeta(), GLMResponseV3.Meta.class);
                    log.info("任务信息: {}", meta.toString());
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
        String sseFormat = "data";      // 数据格式
        boolean incremental = true;     // 增量式推送

        List<Prompt> promptList = new ArrayList<>();
        Prompt prompt = new Prompt();
        prompt.setRole("user");
        prompt.setContent("国内网站接入chatgpt会被封禁吗");
        promptList.add(prompt);

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("request_id", requestId);
        paramsMap.put("prompt", promptList);
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
