package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;
import com.dotCode.model.dto.VacantDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;

public class AdminDAO extends EmployeeDAO {

    private List<EmployeeDTO> empDTOList;
    private List<AttendanceDTO> atdDTOList;
    private List<VacantDTO> vcntDTOList;
    private int empNo = 0;

    public AdminDAO(EmployeeDTO empDTO){
        empNo = empDTO.getEmpNo();

        empDTOList = new ArrayList<>();
        atdDTOList = new ArrayList<>();
        vcntDTOList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/admin-query.xml"));

            getAllEmpInfo();
            getAllAtdInfo();
            getAllVcntInfo();

        }  catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }

    @Override
    public boolean logIn(String id, String pwd) {
        return super.logIn(id,pwd);
    }
    @Override
    public void logOut() {
        super.logOut();
    }
    @Override
    public int checkAdmin() {
        return super.checkAdmin();
    }
    @Override
    public void checkIn() {
        super.checkIn();
    }
    @Override
    public void checkOut() {
        super.checkOut();
    }
    @Override
    public int checkInTime() {
        return super.checkInTime();
    }
    @Override
    public int checkOutTime() {
        return super.checkOutTime();
    }
    @Override
    public EmployeeDTO getEmpInfo() {
        return super.getEmpInfo();
    }
    @Override
    public EmployeeDTO getEmpInfo(String id, String pwd) {
        return super.getEmpInfo(id, pwd);
    }
    @Override
    public void getAtdInfo() {
        super.getAtdInfo();
    }
    @Override
    public void setVcntInfo() {
        super.setVcntInfo();
    }

    public void getAllEmpInfo(){
        empDTOList.clear();
        String query = prop.getProperty("getAllEmpInfo");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while ( rset.next() ){
                empDTO = new EmployeeDTO();
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

                empDTOList.add(empDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public EmployeeDTO getEmpInfo(int empNo){        // 내 정보 불러올 때
        String query = prop.getProperty("getEmpNoInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empNo);
            rset = pstmt.executeQuery();

            if(rset.next()){
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
        }
        return empDTO;
    }
    public EmployeeDTO getEmpInfo(String empId){     // 사원등록 할 때
        String query = prop.getProperty("getEmpIdInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empId);
            rset = pstmt.executeQuery();

            if(rset.next()){
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
        }
        return empDTO;
    }

    // 검색 기능 메소드
    public void searchEmpInfo(){
        sc = new Scanner(System.in);
        System.out.print("조회할 사번 입력 >> ");
        int empNo = sc.nextInt();

        empDTO = new EmployeeDTO();
        empDTO = getEmpInfo(empNo);
        if ( empDTO.getEmpNo() != 0 ){
            System.out.println(empDTO);
        } else {
            System.out.println("    해당하는 정보가 없습니다...");
        }
        empDTO = getEmpInfo(this.empNo);
    }
    public void searchAtdInfo(){
        sc = new Scanner(System.in);
        System.out.print("조회할 사번 입력 >> ");
        int empNo = sc.nextInt();

        atdDTO = new AttendanceDTO();
        atdDTO = getAtdInfo(empNo);
        if ( atdDTO.getEmpNo() != 0 ){
            System.out.println(atdDTO);
        } else {
            System.out.println("    해당하는 정보가 없습니다...");
        }
        atdDTO = getAtdInfo(this.empNo);
    }
    public void searchVcntInfo(){
        sc = new Scanner(System.in);
        System.out.print("조회할 사번 입력 >> ");
        int empNo = sc.nextInt();

        vcntDTO = new VacantDTO();
        vcntDTO = getVcntInfo(empNo);

        if ( vcntDTO.getEmpNo() != 0 ){
            for ( int i = 0 ; i < vcntDTOList.size(); i++ ){
                if ( empNo == vcntDTOList.get(i).getEmpNo()){
                    System.out.println(vcntDTOList.get(i));
                }
            }
        } else {
            System.out.println("해당하는 정보가 없습니다...");
        }
    }

    // 사원 관련 기능 메소드
    public void createEmpInfo() {
        sc = new Scanner(System.in);
        System.out.println("============ 사원 등록 ============");

        String id = "";
        boolean isTrue = true;
        while (isTrue){
            int checkId = 0;
            System.out.print("     ID : ");
            id = sc.nextLine();
            for ( int i = 0; i < empDTOList.size(); i++ ){
                if( id.equals(empDTOList.get(i).getEmpId()) ) {
                    System.out.println("중복되는 ID가 있습니다... 다시 입력해주세요.");
                    checkId = 1;
                }
            }
            if ( checkId == 0 ){    isTrue = false;     }
        }
        System.out.print("     PW : ");
        String pw = sc.nextLine();
        System.out.print("     NAME : ");
        String name = sc.nextLine();

        String jobCode = "";
        isTrue = true;
        while (isTrue){
            int checkId = 0;
            System.out.print("  Job_Code ( J6 : 사장 / J5 : 부장 / J4 : 차장 / J3 : 과장 / J2 : 대리 / J1 : 사원 ) : ");
            jobCode = sc.nextLine().toUpperCase();
            if ( !jobCode.equals("J6") & !jobCode.equals("J5") & !jobCode.equals("J4") & !jobCode.equals("J3") & !jobCode.equals("J2") & !jobCode.equals("J1") ) {
                System.out.println("해당하는 코드는 없습니다... 다시 입력해주세요.");
                checkId = 1;
            }
            if ( checkId == 0 ){    isTrue = false;     }
        }
        System.out.print("     Phone : ");
        String phone = sc.nextLine();
        System.out.print("     Email : ");
        String email = sc.nextLine();

        String adminCode = "";
        isTrue = true;
        while (isTrue){
            int checkId = 0;
            System.out.print("  Admin_Code ( S0 : 관리자 / S1 : 일반 ) : ");
            adminCode = sc.nextLine().toUpperCase();

            if ( !adminCode.equals("S0") & !adminCode.equals("S1") ){
                System.out.println("해당하는 코드는 없습니다... 다시 입력해주세요.");
                checkId = 1;
            }
            if ( checkId == 0 ){    isTrue = false;     }
        }

        java.sql.Date hireDate;
        int result = 0;
        String query = prop.getProperty("insertEmp");
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate crntDate = LocalDate.now();
            Date dateCrnt = sdf.parse(String.valueOf(crntDate));
            hireDate = new java.sql.Date(dateCrnt.getTime());

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, jobCode);
            pstmt.setDate(5, hireDate);
            pstmt.setString(6, phone);
            pstmt.setString(7, email);
            pstmt.setString(8, adminCode);

            result = pstmt.executeUpdate();

            if (result > 0) {
                empDTO = getEmpInfo(id);    // 신입 정보
                System.out.println("     " + empDTO.getEmpName() + "님의 사원 정보가 성공적으로 등록되었습니다.");
                System.out.println(empDTO);
                createAtdInfo(empDTO.getEmpNo());
                // 리스트 초기화 후 최신화
                getAllEmpInfo();
                empDTO = getEmpInfo(this.empNo);
                atdDTO = getAtdInfo(this.empNo);    // 내 정보로 초기화

            } else {
                System.out.println("    사원 정보 등록에 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("처리 불가...");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    public void updateEmpInfo(){
        sc = new Scanner(System.in);
        printAllEmpInfo();
        System.out.print("정보를 변경할 사원의 사번 입력 >> ");
        int empNo = sc.nextInt();

        empDTO = new EmployeeDTO();
        empDTO = getEmpInfo(empNo);

        try {
            if ( empDTO.getEmpNo() != 0 ) {
                sc.nextLine();
                System.out.println("===== 변경할 항목 선택 =====");
                System.out.println(" 1. ID");
                System.out.println(" 2. PW");
                System.out.println(" 3. 이름");
                System.out.println(" 4. 근태 현황");
                System.out.println(" 5. 직책 코드");
                System.out.println(" 6. 전화번호");
                System.out.println(" 7. E-MAIL");
                System.out.println(" 8. 관리자 코드");
                System.out.println("=========================");
                System.out.print(">> ");
                int culumn = sc.nextInt();

                String query;
                String updateValue = "";
                int result = 0;
                int checkValue = 0;
                boolean isTrue = true;

                switch (culumn){
                    case 1:
                        sc.nextLine();
                        while (isTrue){
                            checkValue = 0;
                            System.out.print("ID 변경할 값 입력 >> ");
                            updateValue = sc.nextLine();

                            for (int i = 0 ; i < empDTOList.size(); i++ ){
                                if( updateValue.equals(empDTOList.get(i).getEmpId()) ){
                                    System.out.println("중복된 ID입니다...");
                                    checkValue = 1;
                                    break;
                                }
                            }
                            if ( checkValue == 0 ){
                                query = prop.getProperty("updateEmpId");
                                pstmt = con.prepareStatement(query);
                                pstmt.setString(1,updateValue);
                                pstmt.setInt(2,empDTO.getEmpNo());
                                result = pstmt.executeUpdate();
                                isTrue = false;
                            }
                        }
                        break;
                    case 2:
                        sc.nextLine();
                        System.out.print("PW 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpPw");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 3:
                        sc.nextLine();
                        System.out.print("이름 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpName");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 4:
                        sc.nextLine();
                        while (isTrue){
                            checkValue = 0;
                            System.out.print("근태 현황 변경할 값 입력 ( A1 : 근무중 / A2 : 퇴근 / B1 : 출장 / B2 : 외출 / C1 : 월차 / C2 : 연차 / D1 : 병가 ) >> ");
                            updateValue = sc.nextLine().toUpperCase();

                            if ( !updateValue.equals("A1") & !updateValue.equals("A2") & !updateValue.equals("B1") &!updateValue.equals("B2") &!updateValue.equals("C1") &!updateValue.equals("C2") &!updateValue.equals("D1") ){
                                System.out.println("해당하는 코드는 없습니다... 다시 입력해주세요.");
                                checkValue = 1;
                            }
                            if ( checkValue == 0 ){    isTrue = false;     }
                        }
                        query = prop.getProperty("updateEmpStatus");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 5:
                        sc.nextLine();
                        while (isTrue){
                            checkValue = 0;
                            System.out.print("직책 코드 변경할 값 입력 ( J6 : 사장 / J5 : 부장 / J4 : 차장 / J3 : 과장 / J2 : 대리 / J1 : 사원 ) : ");
                            updateValue = sc.nextLine().toUpperCase();
                            if ( !updateValue.equals("J6") & !updateValue.equals("J5") & !updateValue.equals("J4") & !updateValue.equals("J3") & !updateValue.equals("J2") & !updateValue.equals("J1") ) {
                                System.out.println("해당하는 코드는 없습니다... 다시 입력해주세요.");
                                checkValue = 1;
                            }
                            if ( checkValue == 0 ){    isTrue = false;     }
                        }
                        query = prop.getProperty("updateEmpJob");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 6:
                        sc.nextLine();
                        System.out.print("전화 번호 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpPhone");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 7:
                        sc.nextLine();
                        System.out.print("E-MAIL 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpEmail");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 8:
                        sc.nextLine();
                        while (isTrue){
                            checkValue = 0;
                            System.out.print("관리자 코드 변경할 값 입력 ( S0 : 관리자 / S1 : 일반 ) >> ");
                            updateValue = sc.nextLine().toUpperCase();

                            if ( !updateValue.equals("S0") & !updateValue.equals("S1") ){
                                System.out.println("해당하는 코드는 없습니다... 다시 입력해주세요.");
                                checkValue = 1;
                            }
                            if ( checkValue == 0 ){    isTrue = false;     }
                        }

                        query = prop.getProperty("updateEmpAdmin");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                }

                if ( result > 0) {
                    empDTO = getEmpInfo(empNo);
                    System.out.println(empDTO);
                    empDTO = getEmpInfo(this.empNo);
                }
                else {  System.out.println("    변경 실패...");  }

            }
            else  {
                System.out.println("    해당하는 정보가 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    public void deleteEmpInfo(){
        sc = new Scanner(System.in);

        printAllEmpInfo();
        System.out.print("사원 번호를 입력 하세요 : ");
        int empNo = sc.nextInt();

        empDTO = new EmployeeDTO();
        empDTO = getEmpInfo(empNo);

        if ( empDTO.getEmpNo() != 0 ){
            atdDTO = getAtdInfo(empNo);
            int resultEmp = 0;
            int resultAtd = 0;
            String query;
            try {
                query = prop.getProperty("deleteAtdInfo");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empNo);

                resultEmp = pstmt.executeUpdate();

                query = prop.getProperty("deleteEmpInfo");
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empNo);

                resultAtd = pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(pstmt);
            }

            if ( resultEmp > 0 & resultAtd > 0) {
                System.out.println(empDTO);
                System.out.println(atdDTO);
                System.out.println("     " +empNo + "번의 정보가 삭제되었습니다.");
                empDTO = getEmpInfo(this.empNo);
                atdDTO = getAtdInfo(this.empNo);        // 내 정보로 초기화
            } else {
                System.out.println("처리할 수 없습니다... 관리자에게 문의하세요.");
            }

        } else {
            System.out.println("    해당 정보가 없습니다...");
        }

    }

    // 근태 관련 기능 메소드
    public void createAtdInfo(int empNo){
        int result = 0;
        String query = prop.getProperty("insertAtd");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empNo);
            pstmt.setInt(2,1);
            pstmt.setInt(3,1);
            pstmt.setInt(4,0);
            pstmt.setInt(5,0);
            pstmt.setInt(6,100);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if ( result > 0 ) {
            System.out.println(getAtdInfo(empNo));
        } else {
            System.out.println("처리할 수 없습니다.");
        }
    }
    public AttendanceDTO getAtdInfo(int empNo){
        String query = prop.getProperty("getAtdInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empNo);
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
        }
        return atdDTO;
    }
    public void getAllAtdInfo(){
        atdDTOList.clear();
        String query = prop.getProperty("getAllAtdInfo");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
                atdDTO = new AttendanceDTO();
                atdDTO.setEmpNo(rset.getInt("EMP_NO"));
                atdDTO.setTotalDayCount(rset.getInt("TOTAL_DAY_COUNT"));
                atdDTO.setOntimeCount(rset.getInt("ONTIME_COUNT"));
                atdDTO.setLateCount(rset.getInt("LATE_COUNT"));
                atdDTO.setAbsentCount(rset.getInt("ABSENT_COUNT"));
                atdDTO.setTotalScore(rset.getInt("TOTAL_SCORE"));

                atdDTOList.add(atdDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    public void updateAtdInfo(){
        sc = new Scanner(System.in);

        System.out.print("정보를 변경할 사원의 사번 입력 >> ");
        int empNo = sc.nextInt();

        atdDTO = new AttendanceDTO();
        atdDTO = getAtdInfo(empNo);

        if ( atdDTO.getEmpNo() != 0 ) {
            sc.nextLine();
            System.out.println("===== 변경할 항목 선택 =====");
            System.out.println(" 1.총 근무 일자 ");
            System.out.println(" 2.정시 출근 일자 ");
            System.out.println(" 3.지각 ");
            System.out.println(" 4.근태 점수 ");
            System.out.println(" 5.이전으로 ");
            System.out.println("=========================");
            System.out.print(">> ");
            int selectCulumn = sc.nextInt();

            String query;
            int updateValue;
            int result = 0;

            try {
                switch (selectCulumn){
                    case 1:
                        sc.nextLine();
                        System.out.print("총 근무 일자 변경할 값 입력 >> ");
                        updateValue = sc.nextInt();
                        query = prop.getProperty("updateAtdTotalDayCount");
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,updateValue);
                        pstmt.setInt(2,atdDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 2:
                        sc.nextLine();
                        System.out.print("정시 출근 일자 변경할 값 입력 >> ");
                        updateValue = sc.nextInt();
                        query = prop.getProperty("updateAtdOntimeCount");
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,updateValue);
                        pstmt.setInt(2,atdDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 3:
                        sc.nextLine();
                        System.out.print("지각 변경할 값 입력 >> ");
                        updateValue = sc.nextInt();
                        query = prop.getProperty("updateAtdLateCount");
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,updateValue);
                        pstmt.setInt(2,atdDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.print("근태 점수 변경할 값 입력 >> ");
                        updateValue = sc.nextInt();
                        query = prop.getProperty("updateAtdTotalScore");
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,updateValue);
                        pstmt.setInt(2,atdDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 5:
                        System.out.println("    이전으로 돌아갑니다...");
                        break;
                    default:
                        System.out.println("    잘못된 입력입니다...");
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(rset);
                close(pstmt);
            }

            if ( result > 0 ) {
                atdDTO = getAtdInfo(empNo);
                System.out.println(atdDTO);
                System.out.println("    " + atdDTO.getEmpNo() + "번 사원의 근태 정보가 변경되었습니다.");
                getAllAtdInfo();
                atdDTO = getAtdInfo(this.empNo);
            }
            else {
                System.out.println(atdDTO);
                System.out.println("    정보 변경에 실패 했습니다...");
            }
        }
        else  {
            System.out.println("    해당하는 정보가 없습니다...");
        }
    }
    public void resetAtdInfo(){
        sc = new Scanner(System.in);

        System.out.print("초기화할 사원의 사번 입력 >> ");
        int empNo = sc.nextInt();

        atdDTO = new AttendanceDTO();
        atdDTO = getAtdInfo(empNo);

        if ( atdDTO.getEmpNo() != 0 ) {
            sc.nextLine();
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("초기화 하시겠습니까? (Y/N) ");
                System.out.print(">> ");
                String answer = sc.nextLine().toUpperCase();

                int result = 0;
                if ( answer.equals("Y") ) {
                    String query = prop.getProperty("resetAtdInfo");
                    try {
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,0);
                        pstmt.setInt(2,0);
                        pstmt.setInt(3,0);
                        pstmt.setInt(4,100);
                        pstmt.setInt(5,empNo);
                        result = pstmt.executeUpdate();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    if ( result > 0 ) {
                        atdDTO = getAtdInfo(empNo);
                        System.out.println(atdDTO);
                        System.out.println(atdDTO.getEmpNo() + "번 사원의 근태 정보가 초기화되었습니다...");
                        getAllAtdInfo();
                        atdDTO = getAtdInfo(this.empNo);
                        isTrue = false;
                    } else {
                        System.out.println("처리할 수 없습니다...");
                    }
                } else if( answer.equals("N")){
                    System.out.println("메뉴로 돌아갑니다...");
                    isTrue = false;
                }  else {
                    System.out.println("입력이 잘못되었습니다...");
                }
            }
        }
        else  {
            System.out.println("    해당하는 정보가 없습니다...");
        }
    }

    // 부재 관련 기능 메소드
    public void acceptVacant(){
        sc = new Scanner(System.in);

        System.out.println("========= 전체 부재 신청 정보 ========");
        for( VacantDTO vcntDTO : vcntDTOList ){
            System.out.println(vcntDTO);
        }
        System.out.println();
        System.out.print("사번을 입력하세요 >> ");
        int empNo = sc.nextInt();

        vcntDTO = new VacantDTO();
        vcntDTO = getVcntInfo(empNo);

        int result = 0;
        if ( vcntDTO.getEmpNo() != 0 ){
            sc.nextLine();
            System.out.println("========= 해당 사원 신청 정보 ========");
            for(int i = 0; i < vcntDTOList.size(); i++ ){
                if ( empNo == vcntDTOList.get(i).getEmpNo()){
                    System.out.println(vcntDTOList.get(i));
                }
            }
            System.out.println();
            System.out.print("부재 신청 번호 입력 >> ");
            int vcntNo = sc.nextInt();

            sc.nextLine();
            System.out.print("허가하시겠습니까? (Y/N) >> ");
            String answer = sc.nextLine().toUpperCase();

            String query = prop.getProperty("updateVcntAcpt");
            try {
                if ( answer.equals("Y") ) {
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1,"Y");
                    pstmt.setString(2,"NULL");
                    pstmt.setInt(3,vcntNo);
                    result = pstmt.executeUpdate();

                    if( result > 0 ) {
                        vcntDTO = selectVcntInfo(vcntNo);
                        System.out.println(vcntDTO);
                        System.out.println("     " + vcntDTO.getEmpNo() + "번 사원의 부재 신청이 허가됩니다.");
                    } else {System.out.println("처리 불가...");}

                } else if ( answer.equals("N") ) {
                    System.out.print("사유를 입력해 주세요 >> ");
                    String cause = sc.nextLine();

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1,"N");
                    pstmt.setString(2,cause);
                    pstmt.setInt(3,vcntNo);
                    result = pstmt.executeUpdate();

                    if( result > 0 ) {
                        vcntDTO = selectVcntInfo(vcntNo);
                        System.out.println(vcntDTO);
                        System.out.println("     " + vcntDTO.getEmpNo() + "번 사원의 부재 신청이 거부됩니다.");
                    } else {System.out.println("처리 불가...");}

                } else {
                    System.out.println("입력이 잘못되었습니다...");
                }

                getAllVcntInfo();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else  {
            System.out.println("해당하는 사원의 부재 신청 정보가 없습니다...");
        }
    }
    public VacantDTO selectVcntInfo(int vcntNo){            // 부재번호로 조회
        String query = prop.getProperty("selectVcntInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,vcntNo);
            rset = pstmt.executeQuery();

            if(rset.next()){
                vcntDTO.setVacantNo(rset.getInt("VACANT_NO"));
                vcntDTO.setEmpNo(rset.getInt("EMP_NO"));
                vcntDTO.setStatusCode(rset.getString("STATUS_CODE"));
                vcntDTO.setApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setDayDate(rset.getString("DAY_DATE"));
                vcntDTO.setCause(rset.getString("CAUSE"));
                vcntDTO.setAcceptStatus(rset.getString("ACCEPT_STATUS"));
                vcntDTO.setAcceptCause(rset.getString("ACCEPT_CAUSE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vcntDTO;
    }
    public VacantDTO getVcntInfo(int empNo){            // 사번으로 조회
        String query = prop.getProperty("getVcntInfo");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empNo);
            rset = pstmt.executeQuery();

            if(rset.next()){
                vcntDTO.setVacantNo(rset.getInt("VACANT_NO"));
                vcntDTO.setEmpNo(rset.getInt("EMP_NO"));
                vcntDTO.setStatusCode(rset.getString("STATUS_CODE"));
                vcntDTO.setApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setDayDate(rset.getString("DAY_DATE"));
                vcntDTO.setCause(rset.getString("CAUSE"));
                vcntDTO.setAcceptStatus(rset.getString("ACCEPT_STATUS"));
                vcntDTO.setAcceptCause(rset.getString("ACCEPT_CAUSE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vcntDTO;
    }
    public void getAllVcntInfo(){
        vcntDTOList.clear();
        String query = prop.getProperty("getAllVcntInfo");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
                vcntDTO = new VacantDTO();
                vcntDTO.setVacantNo(rset.getInt("VACANT_NO"));
                vcntDTO.setEmpNo(rset.getInt("EMP_NO"));
                vcntDTO.setStatusCode(rset.getString("STATUS_CODE"));
                vcntDTO.setApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setDayDate(rset.getString("DAY_DATE"));
                vcntDTO.setCause(rset.getString("CAUSE"));
                vcntDTO.setAcceptStatus(rset.getString("ACCEPT_STATUS"));
                vcntDTO.setAcceptCause(rset.getString("ACCEPT_CAUSE"));

                vcntDTOList.add(vcntDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // 가져온 정보 전체 출력 기능
    public void printAllEmpInfo(){
        getAllEmpInfo();
        for ( EmployeeDTO empDTO : empDTOList ){
            System.out.println(empDTO);
        }
    }
    public void printAllAtdInfo(){
        getAllAtdInfo();
        for ( AttendanceDTO atdDTO : atdDTOList ){
            System.out.println(atdDTO);
        }
    }
    public void printAllVcntInfo(){
        getAllVcntInfo();
        for ( VacantDTO vcntDTO : vcntDTOList ){
            System.out.println(vcntDTO);
        }
    }




}
