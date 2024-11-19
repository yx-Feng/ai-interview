package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CandidateVO {

    private String candidateId;
    private String realName;
    private String identityNum;
    private String mobile;
    private Integer sex;
    private String email;
    private LocalDate birthday;
    private String jobId;
    private String jobName;
    private LocalDate createdTime;
}
