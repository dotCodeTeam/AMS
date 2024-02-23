package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;

public class EmployeeDAO {

    private Scanner sc;
    private PreparedStatement pstmt1 = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rset1 = null;
    private ResultSet rset2 = null;
    private Properties prop = new Properties();
    private EmployeeDTO empDTO = null;
    private AttendanceDTO empAtdDTO = null;

    public EmployeeDAO(){
        empDTO = new EmployeeDTO();
        empAtdDTO = new AttendanceDTO();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/ams-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean logIn(Connection con) {
        boolean isTrue = false;
        sc = new Scanner(System.in);

        System.out.println("============ AMS ============");
        System.out.print("     ID : ");
        String id = sc.nextLine();
        System.out.print("     PW : ");
        String pwd = sc.nextLine();

        String query1 = prop.getProperty("getEmpInfo");
        String query2 = prop.getProperty("getAttendanceInfo");

        try {
            pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1,id);
            pstmt1.setString(2,pwd);


            rset1 = pstmt1.executeQuery();

            if ( rset1.next() ){
                empDTO.setEmpNo(rset1.getInt("EMP_NO"));
                empDTO.setEmpId(rset1.getString("EMP_ID"));
                empDTO.setEmpPwd(rset1.getString("EMP_PW"));
                empDTO.setEmpName(rset1.getString("EMP_NAME"));
                empDTO.setJobCode(rset1.getInt("JOB_CODE"));
                empDTO.setHireDate(rset1.getString("EMP_HIREDATE"));
                empDTO.setPhone(rset1.getString("PHONE"));
                empDTO.setEmail(rset1.getString("EMAIL"));
                empDTO.setAdminCode(rset1.getInt("ADMIN_CODE"));
                empDTO.setCurrentStatus(rset1.getString("CURRENT_STATUS"));

                pstmt2 = con.prepareStatement(query2);
                pstmt2.setInt(1,empDTO.getEmpNo());
                rset2 = pstmt2.executeQuery();

                if(rset2.next()){
                    empAtdDTO.setEmpNo(rset2.getInt("EMP_NO"));
                    empAtdDTO.setTotalDayCount(rset2.getInt("TOTAL_DAY_COUNT"));
                    empAtdDTO.setOntimeCount(rset2.getInt("ONTIME_COUNT"));
                    empAtdDTO.setLateCount(rset2.getInt("LATE_COUNT"));
                    empAtdDTO.setAbsentCount(rset2.getInt("ABSENT_COUNT"));
                    empAtdDTO.setTotalScore(rset2.getInt("TOTAL_SCORE"));
                }
                System.out.println("=============================");
                System.out.println("로그인 성공...");
                System.out.println(empDTO.getEmpName()+ "님 환영합니다!!");
                isTrue = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset2);
            close(rset1);
            close(pstmt2);
            close(pstmt1);
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

    public AttendanceDTO getEmpAtdInfo(){
        return this.empAtdDTO;
    }

}
