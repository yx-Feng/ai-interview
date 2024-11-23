package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InterviewRecordVO implements Serializable {

    private String interviewRecordId;
    private String candidateId;

    private Integer takeTime;
    private String answerContent;
    private String result;
    private LocalDateTime createTime;
    private LocalDateTime updatedTime;

    private String realName;
    private String identityNum;
    private String mobile;
    private String sex;

    private String jobId;
    private String jobName;

}
