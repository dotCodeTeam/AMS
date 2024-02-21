package com.dotCode.model.dto;

public class EmployeeDto implements java.io.Serializable{

    private String empNo;
    private String empId;
    private String empPwd;
    private String empName;
    private String phone;
    private String email;
    private String adminId;
    private String positionId;

    public EmployeeDto(){}

    public EmployeeDto(String empNo, String empId, String empPwd, String empName, String phone, String email, String positionId, String adminId) {
        this.empNo = empNo;
        this.empId = empId;
        this.empPwd = empPwd;
        this.empName = empName;
        this.phone = phone;
        this.email = email;
        this.positionId = positionId;
        this.adminId = adminId;
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "empNo='" + empNo + '\'' +
                ", empId='" + empId + '\'' +
                ", empPwd='" + empPwd + '\'' +
                ", empName='" + empName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", positionId='" + positionId + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
