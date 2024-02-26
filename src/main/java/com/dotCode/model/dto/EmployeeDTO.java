package com.dotCode.model.dto;

import java.util.Date;

public class EmployeeDTO implements java.io.Serializable{

    private int empNo;
    private String empId;
    private String empPwd;
    private String empName;
    private String statusCode;
    private String jobCode;
    private String hireDate;
    private String phone;
    private String email;
    private String adminCode;

    public EmployeeDTO(){}

    public EmployeeDTO(int empNo, String empId, String empPwd, String empName, String statusCode, String jobCode, String hireDate, String phone, String email, String adminCode) {
        this.empNo = empNo;
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.statusCode = statusCode;
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
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

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    @Override
    public String toString() {
        return  empName +
                "님의 개인정보\n{" +
                " 사번=" + empNo +
                ", 아이디='" + empId + '\'' +
                ", 비밀번호='" + empPwd + '\'' +
                ", 근무상태=" + statusCode +
                ", 이름='" + empName + '\'' +
                ", 직급=" + jobCode +
                ", 고용일자=" + hireDate +
                ", 핸드폰='" + phone + '\'' +
                ", 이메일='" + email + '\'' +
                ", 관리사번=" + adminCode +
                '}';
    }
}
