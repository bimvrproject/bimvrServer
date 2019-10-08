package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class App implements Serializable {
    private Long id;

    private String name;

    private String visitUrl;

    private Integer isDownloadable;

    private Integer isVisible;

    private Long projectId;

    private Integer type;
    private String scheme;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl == null ? null : visitUrl.trim();
    }

    public Integer getIsDownloadable() {
        return isDownloadable;
    }

    public void setIsDownloadable(Integer isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", visitUrl=").append(visitUrl);
        sb.append(", isDownloadable=").append(isDownloadable);
        sb.append(", isVisible=").append(isVisible);
        sb.append(", projectId=").append(projectId);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}