package com.dotCode.model.dto;

import java.util.Date;

public class AdminDTO extends EmployeeDTO {

    public AdminDTO() {
        super();
    }

    public AdminDTO(int empNo, String empId, String empPwd, String empName, int jobCode, String hireDate, String phone, String email, int adminCode, String currentStatus) {
        super(empNo, empId, empPwd, empName, jobCode, hireDate, phone, email, adminCode, currentStatus);
    }
}
