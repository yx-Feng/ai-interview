package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobVO {

    private String jobId;
    private String jobName;
    private String jobDesc;
    private Integer status;
    private String prompt;
    private String interviewerId;
    private String interviewerName;
    private String createTime;
    private String updateTime;
}
