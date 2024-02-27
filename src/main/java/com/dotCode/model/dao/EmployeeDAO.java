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
    protected Scanner sc = new Scanner(System.in);
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
            System.out.println("고용일자 업데이트 완료...");
        } else {
            System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
        }
    }

    public boolean logIn(String id, String pwd) {
        boolean isTrue = false;

        empDTO = getEmpInfo(id,pwd);

        if ( empDTO.getEmpNo() != 0 ){
            totalDayCount();
            System.out.println("=============================");
            LocalDate currentDate = LocalDate.now();
            System.out.print(currentDate);
            System.out.println(" 로그인 성공...");
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

    public int checkInTime(){
        sdf = new SimpleDateFormat("HH:mm");
        int result = 0;
        boolean isLate = false;
        sc = new Scanner(System.in);
        System.out.print("출근 시간을 입력하세요 (HH:mm)>> ");
        String now = sc.nextLine();
        try {
            Date workStartTime = sdf.parse("09:00");        // 출근시간
            Date actualTime = sdf.parse(now);

            String query = prop.getProperty("updateAtdLateCount");

            if (actualTime.after(workStartTime)) {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,1);
                pstmt.setInt(2,1);
                pstmt.setInt(3,empDTO.getEmpNo());
                result = pstmt.executeUpdate();
            } else {
                query = prop.getProperty("updateAtdOntimeCount");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,1);
                pstmt.setInt(2,empDTO.getEmpNo());
                result = pstmt.executeUpdate();
                isLate = true;
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

        if ( result > 0 & isLate == true ) {
            System.out.println("지각 처리 되었습니다...");
        } else if ( result > 0 & isLate == false ){
            System.out.println("정시 출근 처리 되었습니다...");
        } else {
            System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
        }


        return result;
    }

    public void checkIn(){
        if(empDTO.getStatusCode().equals("A1")){
            System.out.println("이미 근무 중 입니다...");
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
                getEmpInfo();
                getAtdInfo();
                System.out.println("오늘 하루도 화이팅입니다!");
            }   else {
                System.out.println("처리 할 수 없습니다... 관리자에게 문의하세요.");
            }
        }
    }

    public int checkOutTime(){
        sdf = new SimpleDateFormat("HH:mm");
        int result = 0;
        sc = new Scanner(System.in);
        System.out.print("퇴근 시간을 입력하세요 (HH:mm)>> ");
        String now = sc.nextLine();
        try {
            Date workDoneTime = sdf.parse("18:00");     // 퇴근시간
            Date actualTime = sdf.parse(now);

            if (actualTime.after(workDoneTime)) {
                result = 1;
            }
        } catch (ParseException e) {
            result = -1;
            System.out.println("입력 형식이 올바르지 않습니다. (HH:mm 형식으로 입력하세요)");
        } finally {
            close(rset);
            close(pstmt);
        }

        return result;
    }

    public void checkOut(){
        if(empDTO.getStatusCode().equals("A2")){
            System.out.println("이미 퇴근 처리가 되었습니다...");
        }
        else {
            int checkOutTime = checkOutTime();
            int result = 0;
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
            } else {
                result = -1;
            }

            if(result > 0 & checkOutTime == 1) {
                getEmpInfo();
                System.out.println("퇴근 처리 되었습니다.");
            } else if( result < 0 & checkOutTime == 0 ){
                System.out.println("아직 퇴근할 시간이 아닙니다!");
            } else if (result < 0 & checkOutTime == -1 ){

            } else {
                System.out.println("처리 할 수 없습니다... 관리자에게 문의하세요.");
            }

        }

    }


    public int checkAdmin(){
        int result = 1;
        if ( empDTO.getAdminCode().equals("S0") ) {    result = 0;   }
        return result;
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
    public AttendanceDTO getAtdInfo(int empNo){
        String query = prop.getProperty("getAtdInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empNo);

            rset = pstmt.executeQuery();

            if ( rset.next() ){
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

        return atdDTO;
    }

    public void setVcntInfo(){

        vcntDTO = new VacantDTO();
        int empNo = empDTO.getEmpNo();

        System.out.print("부재신청코드를 입력해 주세요 (B1 : 출장, B2 : 외출, C1 : 월차, C2 : 연차) >> ");
        String vacantCode = sc.nextLine();
        System.out.print("부재 희망하는 날짜 입력 (YYYY-MM-DD) >> ");
        String dayDate = sc.nextLine();
        System.out.print("사유 >> ");
        String cause = sc.nextLine();

        if ( !vacantCode.equals("B1") & !vacantCode.equals("B2") & !vacantCode.equals("C1") & !vacantCode.equals("C2")) {
            System.out.println("부재코드 입력이 잘못되었습니다...");

        }   else {
            String query = prop.getProperty("setVcntInfo");
            int result = 0;
            try {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate crntDate = LocalDate.now();
                Date dateCrnt = sdf.parse(String.valueOf(crntDate));
                java.sql.Date currentDate = new java.sql.Date(dateCrnt.getTime());
                Date dateDay = sdf.parse(dayDate);
                java.sql.Date dyDate = new java.sql.Date(dateDay.getTime());

                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empNo);
                pstmt.setString(2,vacantCode);
                pstmt.setDate(3,currentDate);
                pstmt.setDate(4,dyDate);
                pstmt.setString(5,cause);

                result = pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                result = -1;
            }

            if( result > 0 ){
                System.out.println("정상적으로 신청이 완료되었습니다.");
            } else if ( result < 0 ){
                System.out.println("날짜 입력 형식을 맞춰주세요...");
            } else {
                System.out.println("처리 할 수 없습니다... 관리자에게 문의하세요.");
            }
        }


    }


}
