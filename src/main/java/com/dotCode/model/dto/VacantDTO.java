package com.dotCode.model.dto;

import java.util.Date;

public class VacantDTO implements java.io.Serializable{

    private String vacantCategory;
    private String vacantName;
    private java.util.Date vacantDate;
    private String vacantCause;

    public VacantDTO(){}

    public VacantDTO(String vacantCategory, String vacantName, Date vacantDate, String vacantCause) {
        this.vacantCategory = vacantCategory;
        this.vacantName = vacantName;
        this.vacantDate = vacantDate;
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

    public Date getVacantDate() {
        return vacantDate;
    }

    public void setVacantDate(Date vacantDate) {
        this.vacantDate = vacantDate;
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
                ", vacantDate=" + vacantDate +
                ", vacantCause='" + vacantCause + '\'' +
                '}';
    }
}
