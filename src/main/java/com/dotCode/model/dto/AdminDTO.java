package com.dotCode.model.dto;

import java.util.Date;

public class AdminDTO extends EmployeeDTO {

    public AdminDTO() {}

    public AdminDTO(int empNo, String empId, String empPwd, String empName, String hireDate, int jobCode, String phone, String email, int adminCode) {
        super(empNo, empId, empPwd, empName, jobCode, hireDate, phone, email, adminCode);
    }
}
