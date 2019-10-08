package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {
    private Long id;

    private String name;

    private String suffix;

    private String localUrl;

    private String visitUrl;

    private Long size;

    private Date createTime;

    private String description;

    private Integer checkTimes;

    private Integer downloadTimes;

    private String tag;

    private Long userId;

    private Integer categoryId;

    private Integer isDownloadable;

    private Integer isUploadable;

    private Integer isVisible;

    private Integer isDeletable;

    private Integer isUpdatable;

    private Date lastModifyTime;

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

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl == null ? null : localUrl.trim();
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl == null ? null : visitUrl.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(Integer checkTimes) {
        this.checkTimes = checkTimes;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIsDownloadable() {
        return isDownloadable;
    }

    public void setIsDownloadable(Integer isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    public Integer getIsUploadable() {
        return isUploadable;
    }

    public void setIsUploadable(Integer isUploadable) {
        this.isUploadable = isUploadable;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Integer isDeletable) {
        this.isDeletable = isDeletable;
    }

    public Integer getIsUpdatable() {
        return isUpdatable;
    }

    public void setIsUpdatable(Integer isUpdatable) {
        this.isUpdatable = isUpdatable;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", suffix=").append(suffix);
        sb.append(", localUrl=").append(localUrl);
        sb.append(", visitUrl=").append(visitUrl);
        sb.append(", size=").append(size);
        sb.append(", createTime=").append(createTime);
        sb.append(", description=").append(description);
        sb.append(", checkTimes=").append(checkTimes);
        sb.append(", downloadTimes=").append(downloadTimes);
        sb.append(", tag=").append(tag);
        sb.append(", userId=").append(userId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", isDownloadable=").append(isDownloadable);
        sb.append(", isUploadable=").append(isUploadable);
        sb.append(", isVisible=").append(isVisible);
        sb.append(", isDeletable=").append(isDeletable);
        sb.append(", isUpdatable=").append(isUpdatable);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append("]");
        return sb.toString();
    }
}