package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    private String id;

    private String orderNum;

    private String productCode;

    private String orderAmount;

    private String subject;

    private String orderStatus;

    private String paidAmount;

    private String productId;

    private Integer buyCounts;

    private Date createTime;

    private Date paidTime;

    private String body;

    private String sign;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount == null ? null : paidAmount.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", productCode=").append(productCode);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", subject=").append(subject);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", productId=").append(productId);
        sb.append(", buyCounts=").append(buyCounts);
        sb.append(", createTime=").append(createTime);
        sb.append(", paidTime=").append(paidTime);
        sb.append(", body=").append(body);
        sb.append(", sign=").append(sign);
        sb.append("]");
        return sb.toString();
    }
}