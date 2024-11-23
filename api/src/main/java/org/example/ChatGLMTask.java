package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.example.pojo.InterviewRecord;
import org.example.pojo.Job;
import org.example.pojo.bo.AnswerBO;
import org.example.pojo.bo.SubmitAnswerBO;
import org.example.pojo.glm.ChatGLMModel;
import org.example.pojo.glm.EventType;
import org.example.pojo.glm.GLMResponseV3;
import org.example.service.IInterviewRecordService;
import org.example.service.IJobService;
import org.example.utils.GLMTokenUtils;
import org.example.utils.JsonUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ChatGLMTask {

    @Resource
    private IJobService jobService;

    @Resource
    private IInterviewRecordService interviewRecordService;

    public static final Integer connectTimeout = 600;           // 连接超时时间
    public static final Integer writeTimeout = 500;             // 写操作超时时间
    public static final Integer readTimeout = 400;              // 读操作超时时间
    public static final Integer readAndWriteTimeout = 600;      // 读写操作超时时间

    /********** 请求头信息 **********/
    public static final String SSE_CONTENT_TYPE = "text/event-stream";
    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";
    public static final String JSON_CONTENT_TYPE = APPLICATION_JSON + "; charset=utf-8";
    public static final String apiUrl = "https://open.bigmodel.cn/api/paas/v3/model-api/" + ChatGLMModel.CHATGLM_4_Flash.key + "/sse-invoke";

    @Async
    public void display(SubmitAnswerBO answerBO) throws Exception{
        log.info("开始异步AI智能分析...");

        // 拼接提示词与回答的文字内容
        // 1.获得职位提示词前缀
        String jobId = answerBO.getJobId();
        Job job = jobService.getDetail(jobId);
        String prompt = job.getPrompt();

        List<AnswerBO> answerList = answerBO.getQuestionAnswerList();
        String content = "";
        for (AnswerBO answer:answerList) {
            content += "提问：" + answer.getQuestion() + "\n";
            content += "正确答案：" + answer.getReferenceAnswer() + "\n";
            content += "回答内容：" + answer.getAnswerContent() + "\n";
        }

        String submitContent = prompt + "\n\n" + content;
        // System.out.println(submitContent);

        // 把内容提交至ChatGLM进行分析
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Authorization", GLMTokenUtils.generateToken())
                .header("Content-Type", JSON_CONTENT_TYPE)
                .header("User-Agent", DEFAULT_USER_AGENT)
                .header("Accept", SSE_CONTENT_TYPE)
                .post(RequestBody.create(MediaType.parse("application/json"), generateBodyString(submitContent)))
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();


        RealEventSource realEventSource = getRealEventSource(content, answerBO.getCandidateId(), job.getJobName(), answerBO.getTotalSeconds(),request);
        realEventSource.connect(okHttpClient);  // 请求调用ChatGLM，上面代码都是事件，此处为真正调用
    }

    @NotNull
    private RealEventSource getRealEventSource(String answerContent, String candidateId, String jobName, Integer takeTime, Request request) {

        // 实例化EventSource，并注册EventSource监听器
        RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {

            String finalResult = "";

            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                //System.out.println("获得事件");

                GLMResponseV3 response = JsonUtils.jsonToPojo(data, GLMResponseV3.class);
                String result = response.getData();
                finalResult += result;
                log.info("onEvent：{}", result);
                // type消息类型：add-增量、finish-结束、error-错误、interrupted-中断
                if (EventType.finish.key.equals(type)) {
                    GLMResponseV3.Meta meta = JsonUtils.jsonToPojo(response.getMeta(), GLMResponseV3.Meta.class);
                    log.info("任务信息: {}", meta.toString());

                    // 任务结束，保存到数据库
//                    System.out.println(finalResult);
                    InterviewRecord record = new InterviewRecord();
                    record.setCandidateId(candidateId);
                    record.setTakeTime(takeTime);
                    record.setJobName(jobName);
                    record.setAnswerContent(answerContent);
                    record.setResult(finalResult);
                    record.setCreateTime(LocalDateTime.now());
                    record.setUpdatedTime(LocalDateTime.now());

                    interviewRecordService.save(record);

                }
            }
        });
        return realEventSource;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prompt {
        private String role;
        private String content;
    }

    public static String generateBodyString(String content) {

        String requestId = String.format("lee-%d", System.currentTimeMillis());
        float temperature = 0.9f;
        float topP = 0.7f;
        String sseFormat = "data";      // 数据格式
        boolean incremental = true;     // 增量式推送

        List<Prompt> promptList = new ArrayList<>();
        Prompt prompt = new Prompt();
        prompt.setRole("user");
        prompt.setContent(content);
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
