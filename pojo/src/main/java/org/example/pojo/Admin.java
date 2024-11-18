package org.example.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 运营管理平台的admin级别用户
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 人脸图片信息
     */
    private String face;

    /**
     * 管理人员的姓名
     */
    private String realName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间 更新时间
     */
    private LocalDateTime updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Admin{" +
        "id = " + id +
        ", username = " + username +
        ", password = " + password +
        ", face = " + face +
        ", realName = " + realName +
        ", remark = " + remark +
        ", createdTime = " + createdTime +
        ", updatedTime = " + updatedTime +
        "}";
    }
}
