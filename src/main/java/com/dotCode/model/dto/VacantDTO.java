package com.dotCode.model.dto;

import java.util.Date;

public class VacantDTO implements java.io.Serializable{

    private int vacantNo;
    private int empNo;
    private String statusCode;
    private String applyDate;
    private String dayDate;
    private String cause;
    private String acceptStatus;
    private String acceptCause;

    public VacantDTO(){}

    public VacantDTO(int vacantNo, int empNo, String statusCode, String applyDate, String dayDate, String cause, String acceptStatus, String acceptCause) {
        this.vacantNo = vacantNo;
        this.empNo = empNo;
        this.statusCode = statusCode;
        this.applyDate = applyDate;
        this.dayDate = dayDate;
        this.cause = cause;
        this.acceptStatus = acceptStatus;
        this.acceptCause = acceptCause;
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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
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

    public String getAcceptCause() {
        return acceptCause;
    }

    public void setAcceptCause(String acceptCause) {
        this.acceptCause = acceptCause;
    }

    @Override
    public String toString() {
        return "부재 신청 정보 {" +
                " 번호=" + vacantNo +
                ", 사번=" + empNo +
                ", 부재 코드='" + statusCode + '\'' +
                ", 신청 날짜='" + applyDate + '\'' +
                ", 희망 날짜='" + dayDate + '\'' +
                ", 사유='" + cause + '\'' +
                ", 허가상태='" + acceptStatus + '\'' +
                ", 거절사유='" + acceptCause + '\'' +
                '}';
    }
}
