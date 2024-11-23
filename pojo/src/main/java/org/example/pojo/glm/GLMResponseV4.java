package org.example.pojo.glm;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GLMResponseV4 {

    private String id;
    private Long created;
    private String model;
    private List<Choices> choices;
    private Usage usage;
    private String[] status;

    @Data
    @ToString
    public static class Choices {
        private Integer index;
        private String finish_reason;
        private Object delta;
    }

    @Data
    @ToString
    public static class Usage {
        private int completion_tokens;
        private int prompt_tokens;
        private int total_tokens;
    }

}
