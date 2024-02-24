package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.DocumentDTO;
import com.dotCode.model.dto.EmployeeDTO;
import com.dotCode.model.dto.VacantDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;

public class EmployeeDAO {

    private EmployeeDTO empDTO;
    private AttendanceDTO empAtdDTO;
    private DocumentDTO docDTO;
    private VacantDTO vacantDTO;
    private Scanner sc;
    private Properties prop = new Properties();
    private PreparedStatement pstmt = null;
    private ResultSet rset = null;

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
            pstmt = con.prepareStatement(query1);
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

                // 값을 다 가져오면 초기화
                pstmt = null;
                rset = null;

                pstmt = con.prepareStatement(query2);
                pstmt.setInt(1,empDTO.getEmpNo());
                rset = pstmt.executeQuery();

                if(rset.next()){
                    empAtdDTO.setEmpNo(rset.getInt("EMP_NO"));
                    empAtdDTO.setTotalDayCount(rset.getInt("TOTAL_DAY_COUNT"));
                    empAtdDTO.setOntimeCount(rset.getInt("ONTIME_COUNT"));
                    empAtdDTO.setLateCount(rset.getInt("LATE_COUNT"));
                    empAtdDTO.setAbsentCount(rset.getInt("ABSENT_COUNT"));
                    empAtdDTO.setTotalScore(rset.getInt("TOTAL_SCORE"));
                }
                System.out.println("=============================");
                System.out.println("로그인 성공...");
                System.out.println(empDTO.getEmpName()+ "님 환영합니다!!");
                isTrue = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return isTrue;
    }

    public int checkAdmin(){
        int result = 1;
        if ( this.empDTO.getAdminCode() == 0 ) {    result = 0;   }
        return result;
    }

    public void submitDoc(int empNo,int vacantCategory){

        docDTO = new DocumentDTO();

        String query = ""; // 쿼리 > 받은 값 넣기
    }


    public EmployeeDTO getEmpInfo(){
        return this.empDTO;
    }

    public AttendanceDTO getEmpAtdInfo(){
        return this.empAtdDTO;
    }

}
