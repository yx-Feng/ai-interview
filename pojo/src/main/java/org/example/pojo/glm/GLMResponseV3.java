package org.example.pojo.glm;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GLMResponseV3 {

    private String data;
    private String meta;

    @Data
    @ToString
    public static class Meta {
        private String task_status;
        private Usage usage;
        private String task_id;
        private String request_id;
    }

    @Data
    @ToString
    public static class Usage {
        private int completion_tokens;
        private int prompt_tokens;
        private int total_tokens;
    }

}
