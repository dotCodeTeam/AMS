package com.dotCode.model.dto;

import java.util.Date;

public class EmptyDto implements java.io.Serializable{

    private String emptyCategory;
    private String emptyName;
    private java.util.Date emptyDate;
    private String emptyCause;

    public EmptyDto(){}

    public EmptyDto(String emptyCategory, String emptyName, Date emptyDate, String emptyCause) {
        this.emptyCategory = emptyCategory;
        this.emptyName = emptyName;
        this.emptyDate = emptyDate;
        this.emptyCause = emptyCause;
    }

    public String getEmptyCategory() {
        return emptyCategory;
    }

    public void setEmptyCategory(String emptyCategory) {
        this.emptyCategory = emptyCategory;
    }

    public String getEmptyName() {
        return emptyName;
    }

    public void setEmptyName(String emptyName) {
        this.emptyName = emptyName;
    }

    public Date getEmptyDate() {
        return emptyDate;
    }

    public void setEmptyDate(Date emptyDate) {
        this.emptyDate = emptyDate;
    }

    public String getEmptyCause() {
        return emptyCause;
    }

    public void setEmptyCause(String emptyCause) {
        this.emptyCause = emptyCause;
    }

    @Override
    public String toString() {
        return "EmptyDto{" +
                "emptyCategory='" + emptyCategory + '\'' +
                ", emptyName='" + emptyName + '\'' +
                ", emptyDate=" + emptyDate +
                ", emptyCause='" + emptyCause + '\'' +
                '}';
    }

}
