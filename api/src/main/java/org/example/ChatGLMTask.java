package org.example;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Job;
import org.example.pojo.bo.AnswerBO;
import org.example.pojo.bo.SubmitAnswerBO;
import org.example.service.IJobService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ChatGLMTask {

    @Resource
    private IJobService jobService;

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
        System.out.println(submitContent);

        // 把内容提交至ChatGLM进行分析

    }
}
