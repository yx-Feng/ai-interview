package org.example.enums;

/**
 * @Desc: Vip会员类型，0：普通用户；1：终身VIP；2：星球会员
 */
public enum VIPType {

    COMMON_USER(0, "普通用户"),
    LIFE_MEMBER(1, "终身VIP"),
    PLANET_MEMBER(2, "星球会员");

    public final Integer type;
    public final String value;

    VIPType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
