package com.jhbim.bimvr.dao.entity.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jhbim.bimvr.helper.LongJsonDeserializer;
import com.jhbim.bimvr.helper.LongJsonSerializer;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectId;

    private String projectName;

    private String projectModelAddr;

    private Integer projectStatus;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private String projectAddress;

    private String projectContent;



    private static final long serialVersionUID = 1L;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectModelAddr() {
        return projectModelAddr;
    }

    public void setProjectModelAddr(String projectModelAddr) {
        this.projectModelAddr = projectModelAddr == null ? null : projectModelAddr.trim();
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectModelAddr='" + projectModelAddr + '\'' +
                ", projectStatus=" + projectStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", projectAddress='" + projectAddress + '\'' +
                ", projectContent='" + projectContent + '\'' +
                '}';
    }
}