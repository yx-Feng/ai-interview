package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionLibVO {

    private String questionLibId;
    private String question;
    private String referenceAnswer;
    private String aiSrc;
    private String interviewerId;
    private int isOn;
    private String interviewerName;
    private LocalDateTime createTime;
    private LocalDateTime updatedTime;
}
