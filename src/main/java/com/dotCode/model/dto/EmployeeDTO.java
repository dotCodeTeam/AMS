package com.dotCode.model.dto;

import java.util.Date;

public class EmployeeDTO implements java.io.Serializable{

    private int empNo;
    private String empId;
    private String empPwd;
    private String empName;
    private int jobCode;
    private String hireDate;
    private String phone;
    private String email;
    private int adminCode;

    public EmployeeDTO(){}

    public EmployeeDTO(int empNo, String empId, String empPwd, String empName, int jobCode, String hireDate, String phone, String email, int adminCode) {
        this.empNo = empNo;
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.jobCode = jobCode;
        this.hireDate = hireDate;
        this.phone = phone;
        this.email = email;
        this.adminCode = adminCode;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPwd() {
        return empPwd;
    }

    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getJobCode() {
        return jobCode;
    }

    public void setJobCode(int jobCode) {
        this.jobCode = jobCode;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(int adminCode) {
        this.adminCode = adminCode;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empNo=" + empNo +
                ", empId='" + empId + '\'' +
                ", empPwd='" + empPwd + '\'' +
                ", empName='" + empName + '\'' +
                ", jobCode=" + jobCode +
                ", hireDate=" + hireDate +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", adminCode=" + adminCode +
                '}';
    }
}
