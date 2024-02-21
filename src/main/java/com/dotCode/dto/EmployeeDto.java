package com.dotCode.dto;

public class EmployeeDto {

    private String empId;
    private String empPwd;
    private String empName;
    private String phone;
    private String email;
    private String authority;

    public EmployeeDto(){}

    public EmployeeDto(String empId, String empPwd, String empName, String phone, String email, String access) {
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.phone = phone;
        this.email = email;
        this.authority = access;
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

    public String getAccess() {
        return authority;
    }

    public void setAccess(String access) {
        this.authority = access;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "empId='" + empId + '\'' +
                ", empPwd='" + empPwd + '\'' +
                ", empName='" + empName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", access='" + authority + '\'' +
                '}';
    }
}
