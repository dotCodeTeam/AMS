package com.dotCode.model.dto;

import java.util.Date;

public class VacantDTO implements java.io.Serializable{

    private String vacantCategory;
    private String vacantName;
    private String receiveVacantDate;
    private Date sendVacantDate;
    private String vacantCause;

    public VacantDTO(){}

    public VacantDTO(String vacantCategory, String vacantName, String receiveVacantDate, Date sendVacantDate, String vacantCause) {
        this.vacantCategory = vacantCategory;
        this.vacantName = vacantName;
        this.receiveVacantDate = receiveVacantDate;
        this.sendVacantDate = sendVacantDate;
        this.vacantCause = vacantCause;
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

    @Override
    public String toString() {
        return "VacantDTO{" +
                "vacantCategory='" + vacantCategory + '\'' +
                ", vacantName='" + vacantName + '\'' +
                ", receiveVacantDate='" + receiveVacantDate + '\'' +
                ", sendVacantDate=" + sendVacantDate +
                ", vacantCause='" + vacantCause + '\'' +
                '}';
    }
}
