package org.example.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionLibBO {

    private String id;
    private String question;
    private String referenceAnswer;
    private String aiSrc;
    private String interviewerId;
    private String isOn;
}
