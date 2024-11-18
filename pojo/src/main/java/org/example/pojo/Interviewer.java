package org.example.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数字人面试官表
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public class Interviewer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 数字人面试官的名称
     */
    private String aiName;

    /**
     * 数字人形象图照片
     */
    private String image;

    private LocalDateTime createTime;

    private LocalDateTime updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAiName() {
        return aiName;
    }

    public void setAiName(String aiName) {
        this.aiName = aiName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Interviewer{" +
        "id = " + id +
        ", aiName = " + aiName +
        ", image = " + image +
        ", createTime = " + createTime +
        ", updatedTime = " + updatedTime +
        "}";
    }
}
