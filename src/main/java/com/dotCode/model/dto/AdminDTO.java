package com.dotCode.model.dto;

import java.util.Date;

public class AdminDTO extends EmployeeDTO {

    public AdminDTO() {
        super();
    }

    public AdminDTO(int empNo, String empId, String empPwd, String empName, String statusCode, String jobCode, String hireDate, String phone, String email, String adminCode) {
        super(empNo, empId, empPwd, empName, statusCode, jobCode, hireDate, phone, email, adminCode);
    }
}
