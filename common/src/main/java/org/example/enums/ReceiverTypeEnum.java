package org.example.enums;

/**
 * @Desc: 消息接受者的类型
 */
public enum ReceiverTypeEnum {
    ALL(1, "所有人"),
    ONE(2, "指定用户");

    public final Integer type;
    public final String value;

    ReceiverTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
