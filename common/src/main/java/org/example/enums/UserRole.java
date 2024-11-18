package org.example.enums;

/**
 * @Desc: 用户身份角色，1: Admin管理员，2: 普通用户，3: 禁止访问，封号
 */
public enum UserRole {

    ADMIN(1, "Admin管理员"),
    COMMON_USER(2, "普通用户"),
    FORBIDDEN(0, "禁止访问，封号");

    public final Integer type;
    public final String value;

    UserRole(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
