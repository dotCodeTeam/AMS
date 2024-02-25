package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;
import com.dotCode.model.dto.VacantDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;
import static com.dotCode.common.JDBCTemplete.getConnection;

public class EmployeeDAO {

    private AttendanceDTO atdDTO;
    private VacantDTO vacantDTO;
    protected EmployeeDTO empDTO;
    protected Scanner sc = new Scanner(System.in);
    protected Connection con = getConnection();
    protected Properties prop = new Properties();
    protected PreparedStatement pstmt = null;
    protected ResultSet rset = null;

    public EmployeeDAO(){
        empDTO = new EmployeeDTO();
        atdDTO = new AttendanceDTO();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/employee-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean logIn() {
        boolean isTrue = false;
        sc = new Scanner(System.in);

        System.out.println("============ AMS ============");
        System.out.print("     ID : ");
        String id = sc.nextLine();
        System.out.print("     PW : ");
        String pwd = sc.nextLine();

        String query = prop.getProperty("getEmpInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);

            rset = pstmt.executeQuery();

            if ( rset.next() ){
                empDTO.setEmpNo(rset.getInt("EMP_NO"));
                empDTO.setEmpId(rset.getString("EMP_ID"));
                empDTO.setEmpPwd(rset.getString("EMP_PW"));
                empDTO.setEmpName(rset.getString("EMP_NAME"));
                empDTO.setJobCode(rset.getInt("JOB_CODE"));
                empDTO.setHireDate(rset.getString("EMP_HIREDATE"));
                empDTO.setPhone(rset.getString("PHONE"));
                empDTO.setEmail(rset.getString("EMAIL"));
                empDTO.setAdminCode(rset.getInt("ADMIN_CODE"));
                empDTO.setCurrentStatus(rset.getString("CURRENT_STATUS"));

                System.out.println("=============================");
                System.out.println("로그인 성공...");
                System.out.println(empDTO.getEmpName()+ "님 환영합니다!!");
                isTrue = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isTrue;
    }

    public int checkAdmin(){
        int result = 1;
        if ( this.empDTO.getAdminCode() == 0 ) {    result = 0;   }
        return result;
    }

    public EmployeeDTO getEmpInfo(){
        return this.empDTO;
    }

    public AttendanceDTO getAtdInfo(){
        String query = prop.getProperty("getAtdInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empDTO.getEmpNo());
            rset = pstmt.executeQuery();

            if(rset.next()){
                atdDTO.setEmpNo(rset.getInt("EMP_NO"));
                atdDTO.setTotalDayCount(rset.getInt("TOTAL_DAY_COUNT"));
                atdDTO.setOntimeCount(rset.getInt("ONTIME_COUNT"));
                atdDTO.setLateCount(rset.getInt("LATE_COUNT"));
                atdDTO.setAbsentCount(rset.getInt("ABSENT_COUNT"));
                atdDTO.setTotalScore(rset.getInt("TOTAL_SCORE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return this.atdDTO;
    }

}
