package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;

public class AdminDAO extends EmployeeDAO {

    public AdminDAO() {}

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

    ArrayList<EmployeeDTO> empList= new ArrayList<>();
    public void AddEmp(Connection con){

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
            pstmt.setString(1,id);
            pstmt.setString(2,pw);
            pstmt.setString(3,name);
            pstmt.setString(4,jobCode);
            pstmt.setString(5,hireDate);
            pstmt.setString(6,phone);
            pstmt.setString(7,email);
            pstmt.setString(8,adminCode);

            int rset = pstmt.executeUpdate();

            if (rset > 0) {
                System.out.println("사원 정보가 성공적으로 등록되었습니다.");
            } else {
                System.out.println("사원 정보 등록에 실패했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return ;



    }

//                                System.out.println("2. 사원 정보 조회");
//                                System.out.println("3. 사원 해고");
//                                System.out.println("4. 처음으로"
    @Override
    public AttendanceDTO getEmpAtdInfo() {
        return super.getEmpAtdInfo();
    }

}
