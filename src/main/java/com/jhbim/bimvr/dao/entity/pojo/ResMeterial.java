package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class ResMeterial implements Serializable {
    private Integer resMeterialId;

    private String modelId;

    private Long projectId;

    private Long companyId;

    private String url;

    private static final long serialVersionUID = 1L;

    public Integer getResMeterialId() {
        return resMeterialId;
    }

    public void setResMeterialId(Integer resMeterialId) {
        this.resMeterialId = resMeterialId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resMeterialId=").append(resMeterialId);
        sb.append(", modelId=").append(modelId);
        sb.append(", projectId=").append(projectId);
        sb.append(", companyId=").append(companyId);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}