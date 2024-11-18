package org.example.enums;

/**
 * @Desc: 用户信息审核状态
 */
public enum UserInfoReviewStatus {
    PENDING(0, "待审核"),
    PASS(1, "审核通过"),
    FAILED(2, "审核未通过");

    public final Integer type;
    public final String value;

    UserInfoReviewStatus(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
