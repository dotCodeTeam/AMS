package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;

import java.sql.Connection;

public class AdminDAO extends EmployeeDAO {

    public AdminDAO() {}

    @Override
    public boolean logIn() {
        return super.logIn();
    }

    @Override
    public EmployeeDTO getEmpInfo() {
        return super.getEmpInfo();
    }

    @Override
    public int checkAdmin() {
        return super.checkAdmin();
    }

    @Override
    public AttendanceDTO getEmpAtdInfo() {
        return super.getEmpAtdInfo();
    }
}
