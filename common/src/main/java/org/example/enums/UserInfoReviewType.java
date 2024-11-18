package org.example.enums;

/**
 * @Desc: 用户信息审核状态
 */
public enum UserInfoReviewType {
    USER_FACE(1, "用户头像", "face"),
    USER_INFO(2, "用户信息", "info");

    public final Integer type;
    public final String value;
    public final String words;

    UserInfoReviewType(Integer type, String value, String words) {
        this.type = type;
        this.value = value;
        this.words = words;
    }
}
