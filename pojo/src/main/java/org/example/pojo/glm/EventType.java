package org.example.pojo.glm;

/**
 * @Description: 作为SSE 服务端发送事件的 事件类型
 * @Author 风间影月
 */
public enum EventType {

    add("add", "增量事件，推送内容给客户端，事件流的开启"),
    finish("finish", "结束完毕，数据接收完毕，关闭事件流"),
    error("error", "错误，平台调用异常、或模型使用错误异常等响应的异常事件"),
    interrupted("interrupted", "打断、中断事件，例：触发敏感词等"),
    done("done", "V4 新定义的结束事件类型");

    public final String key;
    public final String value;

    EventType(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
