package com.dotCode.model.dto;

import java.util.Date;

public class EmployeeDTO implements java.io.Serializable{

    private String empNo;
    private String empId;
    private String empPwd;
    private String empName;
    private Date hireDate;
    private String jobCode;
    private String phone;
    private String email;
    private String adminCode;

    public EmployeeDTO(){}

    public EmployeeDTO(String empNo, String empId, String empPwd, String empName, Date hireDate, String jobCode, String phone, String email, String adminCode) {
        this.empNo = empNo;
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.hireDate = hireDate;
        this.jobCode = jobCode;
        this.phone = phone;
        this.email = email;
        this.adminCode = adminCode;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
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

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empNo='" + empNo + '\'' +
                ", empId='" + empId + '\'' +
                ", empPwd='" + empPwd + '\'' +
                ", empName='" + empName + '\'' +
                ", hireDate=" + hireDate +
                ", jobCode='" + jobCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", adminCode='" + adminCode + '\'' +
                '}';
    }
}
