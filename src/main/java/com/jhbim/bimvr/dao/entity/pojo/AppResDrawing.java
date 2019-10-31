package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class AppResDrawing implements Serializable {
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
        this.url = url == null ? null : url.trim();
    }

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName == null ? null : drawName.trim();
    }

    public Integer getDrawType() {
        return drawType;
    }

    public void setDrawType(Integer drawType) {
        this.drawType = drawType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resPictureId=").append(resPictureId);
        sb.append(", modelId=").append(modelId);
        sb.append(", projectId=").append(projectId);
        sb.append(", companyId=").append(companyId);
        sb.append(", url=").append(url);
        sb.append(", drawName=").append(drawName);
        sb.append(", drawType=").append(drawType);
        sb.append("]");
        return sb.toString();
    }
}