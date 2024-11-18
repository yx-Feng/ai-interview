package org.example.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 职位描述
     */
    private String jobDesc;

    /**
     * 1：职位开启
2：职位关闭
     */
    private Integer status;

    /**
     * 分配的数字人面试官id，每个职位需要对应的面试官来进行面试
     */
    private String interviewerId;

    /**
     * 该职位的面试结果最终发给ChatGLM的前缀提示词
     */
    private String prompt;

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(String interviewerId) {
        this.interviewerId = interviewerId;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
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
        return "Job{" +
        "id = " + id +
        ", jobName = " + jobName +
        ", jobDesc = " + jobDesc +
        ", status = " + status +
        ", interviewerId = " + interviewerId +
        ", prompt = " + prompt +
        ", createTime = " + createTime +
        ", updatedTime = " + updatedTime +
        "}";
    }
}
