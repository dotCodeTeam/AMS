package com.dotCode.model.dto;

import java.util.Date;

public class AdminDTO extends EmployeeDTO {

    public AdminDTO() {}

    public AdminDTO(int empNo, String empId, String empPwd, String empName, Date hireDate, int jobCode, String phone, String email, int adminCode) {
        super(empNo, empId, empPwd, empName, hireDate, jobCode, phone, email, adminCode);
    }
}
