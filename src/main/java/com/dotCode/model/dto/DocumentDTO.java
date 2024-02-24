package com.dotCode.model.dto;

public class DocumentDTO {

    private int empNo;
    private int docNo;
    private int vacantCategory;

    public DocumentDTO(){}

    public DocumentDTO(int empNo, int docNo, int vacantCategory) {
        this.empNo = empNo;
        this.docNo = docNo;
        this.vacantCategory = vacantCategory;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getDocNo() {
        return docNo;
    }

    public void setDocNo(int docNo) {
        this.docNo = docNo;
    }

    public int getVacantCategory() {
        return vacantCategory;
    }

    public void setVacantCategory(int vacantCategory) {
        this.vacantCategory = vacantCategory;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "empNo=" + empNo +
                ", docNo=" + docNo +
                ", vacantCategory=" + vacantCategory +
                '}';
    }
}
