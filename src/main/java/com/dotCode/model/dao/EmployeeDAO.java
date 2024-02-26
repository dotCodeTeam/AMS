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
import java.text.ParseException;
import java.util.Properties;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.dotCode.common.JDBCTemplete.close;
import static com.dotCode.common.JDBCTemplete.getConnection;

public class EmployeeDAO {

    protected EmployeeDTO empDTO;
    private AttendanceDTO atdDTO;
    protected Scanner sc = new Scanner(System.in);
    protected Connection con = getConnection();
    protected Properties prop = new Properties();
    protected PreparedStatement pstmt = null;
    protected ResultSet rset = null;
    protected SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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
                empDTO.setStatusCode(rset.getString("STATUS_CODE"));
                empDTO.setJobCode(rset.getString("JOB_CODE"));
                empDTO.setHireDate(rset.getString("EMP_HIREDATE"));
                empDTO.setPhone(rset.getString("PHONE"));
                empDTO.setEmail(rset.getString("EMAIL"));
                empDTO.setAdminCode(rset.getString("ADMIN_CODE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        if ( empDTO.getEmpId() != null ) {
            System.out.println("=============================");
            System.out.println("로그인 성공...");
            System.out.println(empDTO.getEmpName()+ "님 환영합니다!!");
            isTrue = true;
        }

        return isTrue;
    }

    public void logOut(){
            System.out.println("=============================");
            System.out.println("로그아웃 성공... " );
            System.out.println(empDTO.getEmpName()+"님 오늘도 수고하셨습니다!");
    }

    public boolean checkInTime(){
        boolean isTrue = false;
        sc = new Scanner(System.in);
        System.out.print("출근 시간을 입력하세요 (HH:mm)>> ");
        String now = sc.nextLine();
        try {
            Date workStartTime = sdf.parse("09:00");        // 출근시간
            Date actualTime = sdf.parse(now);

            String query = prop.getProperty("updateAtdLateCount");

            if (actualTime.after(workStartTime)) {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empDTO.getEmpNo());
            } else {
                isTrue = true;
                query = prop.getProperty("updateAtdOntimeCount");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empDTO.getEmpNo());
            }
        } catch (ParseException e) {
            System.out.println("입력 형식이 올바르지 않습니다. (HH:mm 형식으로 입력하세요)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return isTrue;
    }
    public boolean checkOutTime(){
        boolean isTrue = false;
        sc = new Scanner(System.in);
        System.out.print("퇴근 시간을 입력하세요 (HH:mm)>> ");
        String now = sc.nextLine();
        try {
            Date workDoneTime = sdf.parse("18:00");     // 퇴근시간
            Date actualTime = sdf.parse(now);

            if (actualTime.after(workDoneTime)) {
                isTrue = true;
            }
        } catch (ParseException e) {
            System.out.println("입력 형식이 올바르지 않습니다. (HH:mm 형식으로 입력하세요)");
        } finally {
            close(rset);
            close(pstmt);
        }

        return isTrue;
    }

    public void checkIn(){
        int result = 0;
        try {
            if ( checkInTime() ){
                String query = prop.getProperty("updateStatus");
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,"A1");
                pstmt.setInt(2,empDTO.getEmpNo());

                result = pstmt.executeUpdate();

            } else {
                result = -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        if ( result > 0 ) {
            System.out.println("출근 처리 되었습니다.");
            System.out.println("오늘 하루도 화이팅입니다!");
        } else if( result < 0 ) {
            System.out.println("지각 처리 되었습니다.");
            System.out.println("오늘 하루도 화이팅입니다!");
        } else {
            System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
        }
    }

    public void checkOut(){
        int result = 0;
        if ( checkOutTime() ) {
            String query = prop.getProperty("updateStatus");
            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,"A2");
                pstmt.setInt(2,empDTO.getEmpNo());

                result = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(rset);
                close(pstmt);
            }
        } else {
            result = -1;
        }

        if(result > 0) {
            System.out.println("퇴근 처리 되었습니다.");
        } else if( result < 0 ){
            System.out.println("아직 퇴근할 시간이 아닙니다!");
        }
        else {
            System.out.println("처리 할 수 없습니다... 관리자에게 문의하세요.");
        }
    }

    public int checkAdmin(){
        int result = 1;
        if ( this.empDTO.getAdminCode().equals("S0") ) {    result = 0;   }
        return result;
    }

    public boolean getEmpInfo(String id,String pwd){
        boolean istrue = false;
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
                empDTO.setStatusCode(rset.getString("STATUS_CODE"));
                empDTO.setJobCode(rset.getString("JOB_CODE"));
                empDTO.setHireDate(rset.getString("EMP_HIREDATE"));
                empDTO.setPhone(rset.getString("PHONE"));
                empDTO.setEmail(rset.getString("EMAIL"));
                empDTO.setAdminCode(rset.getString("ADMIN_CODE"));
            }

            if ( empDTO != null ) {istrue = true;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        close(rset);
        close(pstmt);
        }
        return istrue;
    }

    public void getEmpInfo(){
        System.out.println(empDTO);
    }
    public void getAtdInfo(){
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

        System.out.println(atdDTO);

    }

}
