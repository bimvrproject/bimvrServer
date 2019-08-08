package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class ResDrawing implements Serializable {
    private Integer resPictureId;

    private String modelId;

    private Long projectId;

    private Long companyId;

    private String urlJson;

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

    public String getUrlJson() {
        return urlJson;
    }

    public void setUrlJson(String urlJson) {
        this.urlJson = urlJson == null ? null : urlJson.trim();
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
        sb.append(", urlJson=").append(urlJson);
        sb.append("]");
        return sb.toString();
    }
}