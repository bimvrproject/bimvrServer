package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class ResDrawing implements Serializable {
    private Integer resPictureId;

    private String modelId;

    private Long projectId;

    private Long companyId;

    private String url;

    private String drawName;

    private Integer drawType;

    private static final long serialVersionUID = 1L;

    public Integer getResPictureId() {
        return resPictureId;
    }

    public void setResPictureId(Integer resPictureId) {
        this.resPictureId = resPictureId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }

    public Integer getDrawType() {
        return drawType;
    }

    public void setDrawType(Integer drawType) {
        this.drawType = drawType;
    }

    @Override
    public String toString() {
        return "ResDrawing{" +
                "resPictureId=" + resPictureId +
                ", modelId='" + modelId + '\'' +
                ", projectId=" + projectId +
                ", companyId=" + companyId +
                ", url='" + url + '\'' +
                ", drawName='" + drawName + '\'' +
                ", drawType=" + drawType +
                '}';
    }
}