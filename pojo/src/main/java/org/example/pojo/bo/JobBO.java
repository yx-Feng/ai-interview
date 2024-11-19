package org.example.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobBO {

    private String id;
    private String jobName;
    private String jobDesc;
    private Integer status;
    private String interviewerId;
    private String prompt;
}
