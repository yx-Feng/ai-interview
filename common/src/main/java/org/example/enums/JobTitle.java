package org.example.enums;

public enum JobTitle {

    JUNIOR_ENGINEER(1, "初级工程师"),
    SENIOR_ENGINEER(2, "高级工程师"),
    JAVA_ENGINEER(3, "Java工程师"),
    FULL_STACK_ENGINEER(4, "全栈工程师"),
    FRONTEND_ENGINEER(5, "前端工程师"),
    BACKEND_ENGINEER(6, "后端工程师"),
    GAME_ENGINEER(7, "游戏工程师"),
    TEST_ENGINEER(8, "测试工程师"),
    NET_ENGINEER(9, "网络工程师"),
    OPERATION_MAINTENANCE_ENGINEER(10, "运维工程师"),
    SYSTEM_ENGINEER(11, "系统工程师"),
    BIG_DATA_ENGINEER(12, "大数据工程师"),
    DBA(13, "DBA"),
    PRODUCT_MANAGER(14, "产品经理"),
    PROJECT_MANAGER(15, "项目经理"),
    ARCHITECT(16, "架构师"),
    TECHNICAL_MANAGER(17, "技术经理"),
    TECHNICAL_DIRECTOR(18, "技术总监");

    public final Integer type;
    public final String value;

    JobTitle(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
