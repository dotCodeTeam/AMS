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
    private String currentStatus;

    public EmployeeDTO(){}

    public EmployeeDTO(int empNo, String empId, String empPwd, String empName, int jobCode, String hireDate, String phone, String email, int adminCode,String currentStatus) {
        this.empNo = empNo;
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.jobCode = jobCode;
        this.hireDate = hireDate;
        this.phone = phone;
        this.email = email;
        this.adminCode = adminCode;
        this.currentStatus = currentStatus;
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

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public String toString() {
        return  empName +
                "님의 개인정보\n{" +
                " 사번=" + empNo +
                ", 아이디='" + empId + '\'' +
                ", 비밀번호='" + empPwd + '\'' +
                ", 이름= '" + empName + '\'' +
                ", 직급=" + jobCode +
                ", 고용일자=" + hireDate +
                ", 핸드폰='" + phone + '\'' +
                ", 이메일='" + email + '\'' +
                ", 관리사번=" + adminCode +
                ", 부재상태=" + currentStatus +
                '}';
    }
}
