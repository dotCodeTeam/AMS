package com.dotCode.model.dto;

import java.util.Date;

public class VacantDTO implements java.io.Serializable{

    private String vacantCategory;
    private String vacantName;
    private String receiveCurrentDate;
    private Date sendCurrentDate;
    private String receiveVacantDate;
    private Date sendVacantDate;
    private String vacantCause;
    private String acceptStatus;

    public VacantDTO(){}

    public VacantDTO(String vacantCategory, String vacantName, String receiveCurrentDate, Date sendCurrentDate, String receiveVacantDate, Date sendVacantDate, String vacantCause,String acceptStatus) {
        this.vacantCategory = vacantCategory;
        this.vacantName = vacantName;
        this.receiveCurrentDate = receiveCurrentDate;
        this.sendCurrentDate = sendCurrentDate;
        this.receiveVacantDate = receiveVacantDate;
        this.sendVacantDate = sendVacantDate;
        this.vacantCause = vacantCause;
        this.acceptStatus = acceptStatus;
    }

    public String getVacantCategory() {
        return vacantCategory;
    }

    public void setVacantCategory(String vacantCategory) {
        this.vacantCategory = vacantCategory;
    }

    public String getVacantName() {
        return vacantName;
    }

    public void setVacantName(String vacantName) {
        this.vacantName = vacantName;
    }

    public String getReceiveCurrentDate() {
        return receiveCurrentDate;
    }

    public void setReceiveCurrentDate(String receiveCurrentDate) {
        this.receiveCurrentDate = receiveCurrentDate;
    }

    public Date getSendCurrentDate() {
        return sendCurrentDate;
    }

    public void setSendCurrentDate(Date sendCurrentDate) {
        this.sendCurrentDate = sendCurrentDate;
    }

    public String getReceiveVacantDate() {
        return receiveVacantDate;
    }

    public void setReceiveVacantDate(String receiveVacantDate) {
        this.receiveVacantDate = receiveVacantDate;
    }

    public Date getSendVacantDate() {
        return sendVacantDate;
    }

    public void setSendVacantDate(Date sendVacantDate) {
        this.sendVacantDate = sendVacantDate;
    }

    public String getVacantCause() {
        return vacantCause;
    }

    public void setVacantCause(String vacantCause) {
        this.vacantCause = vacantCause;
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
                "vacantCategory='" + vacantCategory + '\'' +
                ", vacantName='" + vacantName + '\'' +
                ", receiveCurrentDate='" + receiveCurrentDate + '\'' +
                ", sendCurrentDate=" + sendCurrentDate +
                ", receiveVacantDate='" + receiveVacantDate + '\'' +
                ", sendVacantDate=" + sendVacantDate +
                ", vacantCause='" + vacantCause + '\'' +
                ", acceptStatus='" + acceptStatus + '\'' +
                '}';
    }
}
