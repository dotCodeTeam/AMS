package com.dotCode.model.dto;

import java.util.Date;

public class VacantDTO implements java.io.Serializable {

    private int vacantNo;
    private int empNo;
    private String statusCode;
    private String applyDate;
    private String dayDate;
    private String cause;
    private String acceptStatus;

    public VacantDTO() {
    }

    public VacantDTO(int vacantNo, int empNo, String statusCode, String applyDate, String dayDate, String cause, String acceptStatus) {
        this.vacantNo = vacantNo;
        this.empNo = empNo;
        this.statusCode = statusCode;
        this.applyDate = applyDate;
        this.dayDate = dayDate;
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

    @Override
    public String toString() {
        return "VacantDTO{" +
                "vacantNo=" + vacantNo +
                ", empNo=" + empNo +
                ", statusCode='" + statusCode + '\'' +
                ", applyDate='" + applyDate + '\'' +
                ", dayDate='" + dayDate + '\'' +
                ", cause='" + cause + '\'' +
                ", acceptStatus='" + acceptStatus + '\'' +
                '}';
    }
}

