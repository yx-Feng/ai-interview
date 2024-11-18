package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题）
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@TableName("question_lib")
public class QuestionLib implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 面试题（文字内容）
     */
    private String question;

    /**
     * 参考答案
     */
    private String referenceAnswer;

    /**
     * 面试数字人对应的地址
     */
    private String aiSrc;

    /**
     * 分配的数字人面试官id，每个职位需要对应的面试官来进行面试
     */
    private String interviewerId;

    /**
     * 1：启用本题
0：关闭本题
     */
    private Integer isOn;

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }

    public String getAiSrc() {
        return aiSrc;
    }

    public void setAiSrc(String aiSrc) {
        this.aiSrc = aiSrc;
    }

    public String getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(String interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Integer getIsOn() {
        return isOn;
    }

    public void setIsOn(Integer isOn) {
        this.isOn = isOn;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "QuestionLib{" +
        "id = " + id +
        ", question = " + question +
        ", referenceAnswer = " + referenceAnswer +
        ", aiSrc = " + aiSrc +
        ", interviewerId = " + interviewerId +
        ", isOn = " + isOn +
        ", createTime = " + createTime +
        ", updatedTime = " + updatedTime +
        "}";
    }
}
