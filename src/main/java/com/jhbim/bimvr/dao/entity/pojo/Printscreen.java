package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class Printscreen implements Serializable {

    private Long PrintscreenId;

    private String PrintscreenUser;

    private String images;

    private String projectId;

    private Long modelId;

    private Long typdprint;

    public Long getTypdprint() {
        return typdprint;
    }

    public void setTypdprint(Long typdprint) {
        this.typdprint = typdprint;
    }

    public Long getPrintscreenId() {
        return PrintscreenId;
    }

    public void setPrintscreenId(Long printscreenId) {
        PrintscreenId = printscreenId;
    }

    public String getPrintscreenUser() {
        return PrintscreenUser;
    }

    public void setPrintscreenUser(String printscreenUser) {
        PrintscreenUser = printscreenUser;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
