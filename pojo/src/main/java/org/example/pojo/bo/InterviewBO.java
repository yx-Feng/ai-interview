package org.example.pojo.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InterviewBO {

    private String id;

    @NotBlank(message = "数字人面试官的名称不能为空")
    private String aiName;

    @NotBlank(message = "数字人面试官的形象图不能为空")
    private String image;
}
