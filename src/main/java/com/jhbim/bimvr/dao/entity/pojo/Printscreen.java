package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class Printscreen implements Serializable {

    private Long PrintscreenId;

    private Long PrintscreenUserid;

    private String images;

    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getPrintscreenId() {
        return PrintscreenId;
    }

    public void setPrintscreenId(Long printscreenId) {
        PrintscreenId = printscreenId;
    }

    public Long getPrintscreenUserid() {
        return PrintscreenUserid;
    }

    public void setPrintscreenUserid(Long printscreenUserid) {
        PrintscreenUserid = printscreenUserid;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
