package com.dotCode.model.dto;

import java.util.Date;

public class VacantDTO implements java.io.Serializable {

    private int vacantNo;
    private int empNo;
    private String statusCode;
    private String receiveApplyDate;
    private Date sendApplyDate;
    private String receiveDayDate;
    private Date sendDayDate;
    private String cause;
    private String acceptStatus;

    public VacantDTO() {
    }

    public VacantDTO(int vacantNo, int empNo, String statusCode, String receiveApplyDate, Date sendApplyDate, String receiveDayDate, Date sendDayDate, String cause, String acceptStatus) {
        this.vacantNo = vacantNo;
        this.empNo = empNo;
        this.statusCode = statusCode;
        this.receiveApplyDate = receiveApplyDate;
        this.sendApplyDate = sendApplyDate;
        this.receiveDayDate = receiveDayDate;
        this.sendDayDate = sendDayDate;
        this.cause = cause;
        this.acceptStatus = acceptStatus;
    }

    public int getVacantNo() {
        return vacantNo;
    }

    public void setVacantNo(int vacantNo) {
        this.vacantNo = vacantNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReceiveApplyDate() {
        return receiveApplyDate;
    }

    public void setReceiveApplyDate(String receiveApplyDate) {
        this.receiveApplyDate = receiveApplyDate;
    }

    public Date getSendApplyDate() {
        return sendApplyDate;
    }

    public void setSendApplyDate(Date sendApplyDate) {
        this.sendApplyDate = sendApplyDate;
    }

    public String getReceiveDayDate() {
        return receiveDayDate;
    }

    public void setReceiveDayDate(String receiveDayDate) {
        this.receiveDayDate = receiveDayDate;
    }

    public Date getSendDayDate() {
        return sendDayDate;
    }

    public void setSendDayDate(Date sendDayDate) {
        this.sendDayDate = sendDayDate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    @Override
    public String toString() {
        return "VacantDTO{" +
                "vacantNo=" + vacantNo +
                ", empNo=" + empNo +
                ", statusCode='" + statusCode + '\'' +
                ", receiveApplyDate='" + receiveApplyDate + '\'' +
                ", sendApplyDate=" + sendApplyDate +
                ", receiveDayDate='" + receiveDayDate + '\'' +
                ", sendDayDate=" + sendDayDate +
                ", cause='" + cause + '\'' +
                ", acceptStatus='" + acceptStatus + '\'' +
                '}';
    }
}

