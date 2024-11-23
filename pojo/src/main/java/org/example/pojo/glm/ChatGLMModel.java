package org.example.pojo.glm;

public enum ChatGLMModel {

    CHATGLM_6B_SSE("chatGLM_6b_SSE", "ChatGLM-6B 测试模型"),
    CHATGLM_LITE("chatglm_lite", "适用于对反应速度和成本比较敏感，且任务较为简单的场景，比如聊天、客服、分类、提取等"),
    CHATGLM_LITE_32K("chatglm_lite_32k", "适用于对反应速度和成本比较敏感，且任务较为简单的场景，比如聊天、客服、分类、提取等"),
    CHATGLM_STD("chatglm_std", "适用于需要兼顾效果和成本的场景，比如新闻写作、企业内部知识库、摘要生成、垂直检索等"),
    CHATGLM_PRO("chatglm_pro", "适用于对知识量、推理能力、创造力要求较高的场景，比如广告文案、小说写作、知识类写作、代码生成等"),
    CHATGLM_TURBO("chatglm_turbo", "适用于对知识量、推理能力、创造力要求较高的场景"),
    CHATGLM_4_0520("glm-4-0520", "我们当前的最先进最智能的模型，指令遵从能力大幅提升18.6%，具有128k上下文"),
    CHATGLM_4_PLUS("glm-4-plus", "性能全面提升，长文本和复杂任务能力显著增强"),
    CHATGLM_4_Flash("glm-4-flash", "免费模型");

    public final String key;
    public final String value;

    ChatGLMModel(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
