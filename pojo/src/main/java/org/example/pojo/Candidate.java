package org.example.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 应聘者表
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 真实姓名（需国密）
     */
    private String realName;

    /**
     * 应聘者身份证号码
     */
    private String identityNum;

    /**
     * 应聘者手机号
     */
    private String mobile;

    /**
     * 性别，1:男 0:女 2:保密
     */
    private Integer sex;

    /**
     * 应聘者照片
     */
    private String face;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 应聘职位的主键id
     */
    private String jobId;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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
        return "Candidate{" +
        "id = " + id +
        ", realName = " + realName +
        ", identityNum = " + identityNum +
        ", mobile = " + mobile +
        ", sex = " + sex +
        ", face = " + face +
        ", email = " + email +
        ", birthday = " + birthday +
        ", country = " + country +
        ", province = " + province +
        ", city = " + city +
        ", district = " + district +
        ", address = " + address +
        ", jobId = " + jobId +
        ", remark = " + remark +
        ", createdTime = " + createdTime +
        ", updatedTime = " + updatedTime +
        "}";
    }
}
