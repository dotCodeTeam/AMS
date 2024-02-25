package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;

public class AdminDAO extends EmployeeDAO {

    public AdminDAO() {
    }

    @Override
    public boolean logIn(Connection con) {
        return super.logIn(con);
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

    ArrayList<EmployeeDTO> empList = new ArrayList<>();

    public void AddEmp(Connection con) {

        System.out.println("============ Add Employee ============");
        System.out.print("     Emp_No : ");
        int empNo = sc.nextInt();
        System.out.print("     ID : ");
        String id = sc.nextLine();
        System.out.print("     PW : ");
        String pw = sc.nextLine();
        System.out.print("     NAME : ");
        String name = sc.nextLine();
        System.out.print("     Job_Code : ");
        int jobCode = sc.nextInt();
        System.out.print("     Hire_Date : ");
        String hireDate = sc.nextLine();
        System.out.print("     Phone : ");
        String phone = sc.nextLine();
        System.out.print("     Email : ");
        String email = sc.nextLine();
        System.out.print("     Admin_Code : ");
        String adminCode = sc.nextLine();

        String query = prop.getProperty("insertEmp");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, empNo);
            pstmt.setString(2, id);
            pstmt.setString(3, pw);
            pstmt.setString(4, name);
            pstmt.setInt(5, jobCode);
            pstmt.setString(6, hireDate);
            pstmt.setString(7, phone);
            pstmt.setString(8, email);
            pstmt.setString(9, adminCode);

            int rset = pstmt.executeUpdate();


            if (rset > 0) {
                this.empList.add(empDTO);
                System.out.println(empDTO.getEmpName() + "님의 사원 정보가 성공적으로 등록되었습니다.");

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                int newEmpNo = -1;
                if (generatedKeys.next()) {
                    newEmpNo = generatedKeys.getInt(1);
                }
                String query1 = prop.getProperty("insertVacant");

                // VacantDTO에 대한 정보 설정
                pstmt = con.prepareStatement(query1);
                pstmt.setInt(1, newEmpNo);

                // VacantDTO 추가
                int vacantRowsAffected = pstmt.executeUpdate();

                if (vacantRowsAffected > 0) {
                    System.out.println("VacantDTO 정보가 추가되었습니다.");
                } else {
                    System.out.println("VacantDTO 정보 추가 실패");
                }
            } else {
                System.out.println("사원 정보 등록에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
//                                System.out.println("2. 사원 정보 수정");
//                                System.out.println("3. 사원 해고");
//                                System.out.println("4. 처음으로"
    }

    public void 
}
