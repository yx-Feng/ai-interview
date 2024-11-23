package org.example.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAnswerBO {

    private String candidateId;
    private String jobId;
    private List<AnswerBO> questionAnswerList;
    private Integer totalSeconds;
}
