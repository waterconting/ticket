package com.ticket.ticket.log.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author ZY
 * @Date 2022/6/17 14:35
 */
@Entity
@Table(name = "user" , schema = "ticket" , catalog = "")
public class UserEntity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "卡密")
    private String stuckPassword;
    @ApiModelProperty(value = "使用时间类型")
    private String useTimeType;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty(value = "微信uid")
    private String uid;
    @ApiModelProperty(value = "试用次数")
    private String tryNumber;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "stuckPassword")
    public String getStuckPassword() {
        return stuckPassword;
    }

    public void setStuckPassword(String stuckPassword) {
        this.stuckPassword = stuckPassword;
    }

    @Basic
    @Column(name = "useTimeType")
    public String getUseTimeType() {
        return useTimeType;
    }

    public void setUseTimeType(String useTimeType) {
        this.useTimeType = useTimeType;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "endTime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "tryNumber")
    public String getTryNumber() {
        return tryNumber;
    }

    public void setTryNumber(String tryNumber) {
        this.tryNumber = tryNumber;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", stuckPassword='" + stuckPassword + '\'' +
                ", useTimeType='" + useTimeType + '\'' +
                ", phone='" + phone + '\'' +
                ", endTime=" + endTime +
                ", uid='" + uid + '\'' +
                ", tryNumber='" + tryNumber + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
