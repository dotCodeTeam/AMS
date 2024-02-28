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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;
import static com.dotCode.common.JDBCTemplete.getConnection;

public class EmployeeDAO {

    protected EmployeeDTO empDTO;
    protected AttendanceDTO atdDTO;
    protected VacantDTO vcntDTO;
    protected Scanner sc;
    protected Connection con = getConnection();
    protected Properties prop = new Properties();
    protected PreparedStatement pstmt = null;
    protected ResultSet rset = null;
    protected SimpleDateFormat sdf;

    public EmployeeDAO(){
        empDTO = new EmployeeDTO();
        atdDTO = new AttendanceDTO();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/employee-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 총 근무일자 데이터에 주입
    public void totalDayCount(){

        int result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String hireDate = empDTO.getHireDate();
            Date employmentDate = dateFormat.parse(hireDate);
            Date currentDate = new Date();

            long timeDiff = currentDate.getTime() - employmentDate.getTime();
            long daysDiff = timeDiff / (24 * 60 * 60 * 1000);

            String query = prop.getProperty("updateAtdTotalDayCount");
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, daysDiff);
            pstmt.setInt(2,empDTO.getEmpNo());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if ( result > 0 ) {
            System.out.println("   총 근무일자 업데이트 완료...");
        } else {
            System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
        }
    }

    // 로그인/로그아웃
    public boolean logIn(String id, String pwd) {
        boolean isTrue = false;

        empDTO = getEmpInfo(id,pwd);

        if ( empDTO.getEmpNo() != 0 ){
            totalDayCount();
            System.out.println("=============================");
            LocalDate currentDate = LocalDate.now();
            System.out.print("   " + currentDate);
            System.out.println(" 로그인 성공...");
            System.out.println("     " + empDTO.getEmpName() + "님 환영합니다!!");
            isTrue = true;
        }

        return isTrue;
    }
    public void logOut(){
        System.out.println("=============================");
        System.out.println("    로그아웃 성공... " );
        System.out.println("    " + empDTO.getEmpName()+"님 오늘도 수고하셨습니다!");
    }

    // 관리자사번 확인하기
    public int checkAdmin(){
        int result = 1;
        if ( empDTO.getAdminCode().equals("S0") ) {    result = 0;   }
        return result;
    }

    // 출퇴근 시간 확인하기
    public int checkInTime(){
        sdf = new SimpleDateFormat("HH:mm");
        sc = new Scanner(System.in);

        System.out.print("출근 시간을 입력하세요 (HH:mm)>> ");
        String now = sc.nextLine();

        int result = 0;
        int nowType = 0;
        try {
            Date workStartTime = sdf.parse("09:00");        // 정시출근시간
            Date absentTime = sdf.parse("10:00");           // 결근시간
            Date actualTime = sdf.parse(now);

            String query;
            if (actualTime.after(workStartTime) & (actualTime.before(absentTime) || actualTime.equals(absentTime))) {
                query = prop.getProperty("updateAtdLateCount");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,1);
                pstmt.setInt(2,1);
                pstmt.setInt(3,empDTO.getEmpNo());
                result = pstmt.executeUpdate();
                nowType = 1;
            } else if ( actualTime.after(absentTime)){
                query = prop.getProperty("updateAtdAbsentCount");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,1);
                pstmt.setInt(2,5);
                pstmt.setInt(3,empDTO.getEmpNo());
                result = pstmt.executeUpdate();
                nowType = -1;
            } else {
                query = prop.getProperty("updateAtdOntimeCount");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,5);
                pstmt.setInt(2,empDTO.getEmpNo());
                result = pstmt.executeUpdate();
            }
        } catch (ParseException e) {
            result = -1;
            System.out.println("입력 형식이 올바르지 않습니다. (HH:mm 형식으로 입력하세요)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        if ( result > 0 & nowType == 0 ) {
            System.out.println("=============================");
            System.out.println("    정시 출근 되었습니다...");
        } else if ( result > 0 & nowType == 1 ){
            System.out.println("=============================");
            System.out.println("    지각 처리 되었습니다...");
        } else if ( result > 0 & nowType == -1 ){
            System.out.println("=============================");
            System.out.println("    결근 처리 되었습니다...");
        } else if ( result == 0 ){
            System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
        }


        return result;
    }
    public void checkIn(){
        if(empDTO.getStatusCode().equals("A1")){
            System.out.println("    이미 근무 중 입니다...");
        }
        else {
            int checkInTime = checkInTime();
            int result = 0;
            try {
                if ( checkInTime == 1 ){
                    String query = prop.getProperty("updateStatus");
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1,"A1");
                    pstmt.setInt(2,empDTO.getEmpNo());

                    result = pstmt.executeUpdate();
                }
                else {
                    result = -1;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(rset);
                close(pstmt);
            }

            if ( result > 0 & checkInTime == 1) {
                System.out.println("    오늘 하루도 화이팅입니다!");
            }   else if ( result == 0 ){
                System.out.println("처리 할 수 없습니다... 관리자에게 문의하세요.");
            }
        }
    }
    public int checkOutTime(){
        sdf = new SimpleDateFormat("HH:mm");
        sc = new Scanner(System.in);

        System.out.print("퇴근 시간을 입력하세요 (HH:mm)>> ");
        String now = sc.nextLine();

        int result = 0;
        try {
            Date workDoneTime = sdf.parse("18:00");     // 퇴근시간
            Date actualTime = sdf.parse(now);

            if (actualTime.after(workDoneTime) || actualTime.equals(workDoneTime)) {
                result = 1;
            }
        } catch (ParseException e) {
            result = -1;
        } finally {
            close(rset);
            close(pstmt);
        }

        return result;
    }
    public void checkOut(){
        if(empDTO.getStatusCode().equals("A2")){
            System.out.println("    이미 퇴근 처리가 되었습니다...");
        }
        else {
            int result = 0;
            int checkOutTime = checkOutTime();
            if ( checkOutTime == 1 ) {
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
            }

            if(result > 0 & checkOutTime == 1) {
                getEmpInfo();
                System.out.println("퇴근 처리 되었습니다.");
            } else if ( result == 0 & checkOutTime == 0 ){
                System.out.println("=============================");
                System.out.println("    아직 퇴근할 수 없습니다!");
            } else if ( result == 0 & checkOutTime == -1 ){
                System.out.println("입력 형식이 올바르지 않습니다. (HH:mm 형식으로 입력하세요)");
            } else {
                System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
            }

        }

    }

    // 내 개인정보 가져오기
    public EmployeeDTO getEmpInfo(){
        String query = prop.getProperty("getEmpInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empDTO.getEmpId());
            pstmt.setString(2,empDTO.getEmpPwd());

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
        System.out.println(empDTO);
        return empDTO;
    }
    public EmployeeDTO getEmpInfo(String id,String pwd){
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

        return empDTO;
    }

    // 내 근태정보 가져오기
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
                atdDTO.setLastReset(rset.getString("LAST_RESET"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        System.out.println(atdDTO);

    }

    // 부재신청하기
    public void setVcntInfo(){
        sc = new Scanner(System.in);
        vcntDTO = new VacantDTO();
        int empNo = empDTO.getEmpNo();

        boolean isTrue = true;
        String vacantCode = "";
        while (isTrue){
            int checkId = 0;
            System.out.print("  부재신청코드를 입력해 주세요 (B1 : 출장, B2 : 외출, C1 : 월차, C2 : 연차) >> ");
            vacantCode = sc.nextLine().toUpperCase();
            if ( !vacantCode.equals("B1") & !vacantCode.equals("B2") & !vacantCode.equals("C1") & !vacantCode.equals("C2") ){
                System.out.println("해당하는 코드는 없습니다... 다시 입력해주세요.");
                checkId = 1;
            }
            if ( checkId == 0 ){    isTrue = false;     }
        }
        java.sql.Date currentDate;
        java.sql.Date dayDate;

        String query = prop.getProperty("setVcntInfo");
        int result = 0;
        isTrue = true;
        while ( isTrue ){
            try {
                System.out.print("  부재 희망하는 날짜 입력 (YYYY-MM-DD) >> ");
                String vcntDate = sc.nextLine();
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate crntDate = LocalDate.now();
                Date dateCrnt = sdf.parse(String.valueOf(crntDate));
                currentDate = new java.sql.Date(dateCrnt.getTime());
                Date dateDay = sdf.parse(vcntDate);
                dayDate = new java.sql.Date(dateDay.getTime());

                System.out.print("  사유 >> ");
                String cause = sc.nextLine();

                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empNo);
                pstmt.setString(2,vacantCode);
                pstmt.setDate(3,currentDate);
                pstmt.setDate(4,dayDate);
                pstmt.setString(5,cause);

                result = pstmt.executeUpdate();
                isTrue = false;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                System.out.println("날짜 입력 형식을 맞춰주세요...");
                result = -1;
            }
        }

        if( result > 0 ){
            System.out.println("    정상적으로 신청이 완료되었습니다!");
        } else {
            System.out.println("처리 할 수 없습니다... 관리자에게 문의하세요.");
        }


    }


}
