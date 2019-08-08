package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberPaymentRecord implements Serializable {
    private Long paymentId;

    private Long companyId;

    private BigDecimal payment;

    private Date time;

    private static final long serialVersionUID = 1L;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentId=").append(paymentId);
        sb.append(", companyId=").append(companyId);
        sb.append(", payment=").append(payment);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}