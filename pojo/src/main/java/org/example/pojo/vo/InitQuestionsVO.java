package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InitQuestionsVO {

    private String questionLibId;
    private String question;
    private String referenceAnswer;
    private String aiSrc;
    private String interviewerId;
}
