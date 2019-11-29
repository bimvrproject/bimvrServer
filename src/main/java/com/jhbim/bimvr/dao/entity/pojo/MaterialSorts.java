package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;
//材质种类
public class MaterialSorts implements Serializable {
    private Long id;

    private String materialSortid;

    private String materialSortname;

    private Date updatedTime;

    private String updatedBy;

    private Date createdTime;

    private String createdBy;

    private Integer revision;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialSortid() {
        return materialSortid;
    }

    public void setMaterialSortid(String materialSortid) {
        this.materialSortid = materialSortid == null ? null : materialSortid.trim();
    }

    public String getMaterialSortname() {
        return materialSortname;
    }

    public void setMaterialSortname(String materialSortname) {
        this.materialSortname = materialSortname == null ? null : materialSortname.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", materialSortid=").append(materialSortid);
        sb.append(", materialSortname=").append(materialSortname);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", revision=").append(revision);
        sb.append("]");
        return sb.toString();
    }
}