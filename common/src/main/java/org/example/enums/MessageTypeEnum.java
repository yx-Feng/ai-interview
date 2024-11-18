package org.example.enums;

/**
 * @Desc: 消息类型
 */
public enum MessageTypeEnum {
    SYS_MSG(1, "系统消息"),
    ANNOUNCEMENT(2, "网站公告");

    public final Integer type;
    public final String value;

    MessageTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
