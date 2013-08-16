package com.vdc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerAccountLog implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 63067666824790740L;

	private Long logId;

    private Date logTime;

    private Long logBy;

    private Long customerId;

    private BigDecimal previousAmount;

    private Integer isRecharge;

    private BigDecimal changeAmount;

    private BigDecimal currentAmount;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Long getLogBy() {
        return logBy;
    }

    public void setLogBy(Long logBy) {
        this.logBy = logBy;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(BigDecimal previousAmount) {
        this.previousAmount = previousAmount;
    }

    public Integer getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(Integer isRecharge) {
        this.isRecharge = isRecharge;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }
}