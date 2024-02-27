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

import static com.dotCode.common.JDBCTemplete.close;

public class AdminDAO extends EmployeeDAO {

    private List<EmployeeDTO> empDTOList;
    private List<AttendanceDTO> atdDTOList;
    private List<VacantDTO> vcntDTOList;

    public AdminDAO() {
        empDTOList = new ArrayList<>();
        atdDTOList = new ArrayList<>();
        vcntDTOList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/admin-query.xml"));

            String query = prop.getProperty("getAllEmpInfo");
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
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

            query = prop.getProperty("getAllAtdInfo");
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

            query = prop.getProperty("getAllVcntInfo");
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

                vcntDTOList.add(vcntDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }

    @Override
    public boolean logIn() {
        return super.logIn();
    }
    @Override
    public int checkAdmin() {
        return super.checkAdmin();
    }

    @Override
    public void getEmpInfo() {}

    @Override
    public void getAtdInfo() {
        int selectEmpNo;
        System.out.print("조회할 사번 입력 >> ");
        selectEmpNo = sc.nextInt();

        for ( int i = 0 ; i < atdDTOList.size(); i++ ) {
            if(atdDTOList.get(i).getEmpNo() == selectEmpNo){
                atdDTO = atdDTOList.get(i);
            }
        }
        System.out.println(atdDTO);
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
        for(AttendanceDTO atdDTO : atdDTOList){
            System.out.println(atdDTO);
        }
    }

    public List<AttendanceDTO> getAllAtdInfo(int empNo){
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
        }
        return atdDTOList;
    }

    public void updateAtdInfo(){
        System.out.print("근태 정보를 변경할 사원의 사번 입력 >> ");
        int empNo = sc.nextInt();

        for ( int i = 0 ; i < atdDTOList.size(); i++ ){
            if ( empNo == atdDTOList.get(i).getEmpNo() ){
                atdDTO = atdDTOList.get(i);
            }
        }

        if ( atdDTO != null ) {
            sc.nextLine();
            System.out.println("===== 변경할 항목 선택 =====");
            System.out.println(" 1.총 근무 일자 ");
            System.out.println(" 2.정시 출근 일자 ");
            System.out.println(" 3.지각 ");
            System.out.println(" 4.결근 ");
            System.out.println(" 5.근태 점수 ");
            System.out.println(" 6.이전으로 ");
            System.out.println("=========================");
            System.out.print(">> ");
            int culumn = sc.nextInt();

            String query = "";
            int updateValue;
            int result = 0;

            try {
                switch (culumn){
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
                        System.out.print("결근 변경할 값 입력 >> ");
                        updateValue = sc.nextInt();
                        query = prop.getProperty("updateAtdAbsentCount");
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,updateValue);
                        pstmt.setInt(2,atdDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 5:
                        sc.nextLine();
                        System.out.print("근태 점수 변경할 값 입력 >> ");
                        updateValue = sc.nextInt();
                        query = prop.getProperty("updateAtdTotalScore");
                        pstmt = con.prepareStatement(query);
                        pstmt.setInt(1,updateValue);
                        pstmt.setInt(2,atdDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 6:
                        result = -1;
                        break;
                    default:
                        System.out.println("잘못된 입력입니다...");
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(rset);
                close(pstmt);
            }

            if ( result > 0 ) {
                atdDTOList = getAllAtdInfo(empNo);
                atdDTO = getAtdInfo(empNo);
                System.out.println(atdDTO);
                System.out.println(getAtdInfo(empNo).getEmpNo() + "번 사원의 근태 정보가 변경되었습니다.");
            }
            else if ( result < 0 ){  System.out.println("이전으로 돌아갑니다...");  }
            else {
                System.out.println(atdDTO);
                System.out.println("정보 변경에 실패 했습니다...");
            }

        }
        else  {
            System.out.println("해당하는 정보가 없습니다...");
        }
    }

    public void acceptVacant(){
        vcntDTO = new VacantDTO();
        System.out.print("사번을 입력하세요 >> ");
        int empNo = sc.nextInt();
        for ( int i = 0 ; i < vcntDTOList.size(); i++ ){
            if(empNo == vcntDTOList.get(i).getEmpNo()){
                vcntDTO = vcntDTOList.get(i);
            }
        }

        int result = 0;
        if ( vcntDTO.getEmpNo() != 0 ){
            sc.nextLine();
            System.out.println("========= 전체 부재 신청 정보 ========");
            for( VacantDTO vcntDTO : vcntDTOList ){
                System.out.println(vcntDTO);
            }
            System.out.println("========= 해당 사원 정보 ========");
            System.out.println(vcntDTO);
            System.out.println();
            System.out.println("신청을 허가하시겠습니까? ");
            System.out.print(">> ");
            String answer = sc.nextLine().toUpperCase();

            String query = prop.getProperty("updateVcntAcpt");
            try {
                if ( answer.equals("Y") ){
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1,"Y");
                    pstmt.setInt(2,empNo);
                    result = pstmt.executeUpdate();

                    if( result > 0 ) {
                        System.out.println(vcntDTO.getEmpNo() + "번 사원의 부재 신청이 허가됩니다.");
                    } else {System.out.println("처리 불가...");}

                } else if ( answer.equals("N") ){
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1,"N");
                    pstmt.setInt(2,empNo);
                    result = pstmt.executeUpdate();

                    if( result > 0 ) {
                        System.out.println(vcntDTO.getEmpNo() + "번 사원의 부재 신청이 거부됩니다.");
                    } else {System.out.println("처리 불가...");}

                } else {
                    System.out.println("입력이 잘못되었습니다...");
                }

                vcntDTOList = getAllVcntInfo(vcntDTOList);
                vcntDTO = getVcntInfo(empNo);
                System.out.println(vcntDTO);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else  {
            System.out.println("해당하는 사원의 부재 신청 정보가 없습니다...");
        }
    }
    public void getVcntInfo(){
        System.out.print("사번을 입력하세요 >> ");
        int empNo = sc.nextInt();
        vcntDTO = new VacantDTO();
        vcntDTO = getVcntInfo(empNo);

        if( vcntDTO.getEmpNo() != 0 ){
            String query = prop.getProperty("getVcntInfo");
            try {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empNo);
                rset = pstmt.executeQuery();
                if ( rset.next()) {
                    vcntDTO.setVacantNo(rset.getInt("VACANT_NO"));
                    vcntDTO.setEmpNo(rset.getInt("EMP_NO"));
                    vcntDTO.setStatusCode(rset.getString("STATUS_CODE"));
                    vcntDTO.setApplyDate(rset.getString("APPLY_DATE"));
                    vcntDTO.setDayDate(rset.getString("DAY_DATE"));
                    vcntDTO.setCause(rset.getString("CAUSE"));
                    vcntDTO.setAcceptStatus(rset.getString("ACCEPT_STATUS"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            System.out.println(vcntDTO);

        } else {
            System.out.println("해당하는 사원의 부재 신청 정보가 없습니다...");
        }
    }

    public VacantDTO getVcntInfo(int empNo){
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
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vcntDTO;
    }

    public void getAllVcntInfo(){
        for ( VacantDTO vcntDTO : vcntDTOList ){
            System.out.println(vcntDTO);
        }
    }

    public List<VacantDTO> getAllVcntInfo(List<VacantDTO> vcntDTOList){
        vcntDTOList.clear();

        String query = prop.getProperty("getAllVcntInfo");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
                atdDTO = new AttendanceDTO();
                vcntDTO.setVacantNo(rset.getInt("VACANT_NO"));
                vcntDTO.setEmpNo(rset.getInt("EMP_NO"));
                vcntDTO.setStatusCode(rset.getString("STATUS_CODE"));
                vcntDTO.setApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setDayDate(rset.getString("DAY_DATE"));
                vcntDTO.setCause(rset.getString("CAUSE"));
                vcntDTO.setAcceptStatus(rset.getString("ACCEPT_STATUS"));

                vcntDTOList.add(vcntDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vcntDTOList;
    }

    public void getAllEmpInfo(){
        empDTOList.clear();
        String query = prop.getProperty("getAllEmpInfo");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
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

    public EmployeeDTO getEmpInfo(int empNo){
        String query = prop.getProperty("getEmpInfo");
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
    public EmployeeDTO getEmpInfo(String empId){
        String query = prop.getProperty("getEmpInfo");
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



    public void AddEmp() {

        System.out.println("============ Add Employee ============");
        System.out.print("     ID : ");
        String id = sc.nextLine();
        System.out.print("     PW : ");
        String pw = sc.nextLine();
        System.out.print("     NAME : ");
        String name = sc.nextLine();
        System.out.print("     Status_Code : ");
        String statusCode = sc.nextLine();
        System.out.print("     Job_Code ( J6 : 사장 / J5 : 부장 / J4 : 차장 / J3 : 과장 / J2 : 대리 / J1 : 사원 ) : ");
        String jobCode = sc.nextLine();
        System.out.print("     Phone : ");
        String phone = sc.nextLine();
        System.out.print("     Email : ");
        String email = sc.nextLine();
        System.out.print("     Admin_Code : ");
        String adminCode = sc.nextLine();

        String query = prop.getProperty("insertEmp");

        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate crntDate = LocalDate.now();
            Date dateCrnt = sdf.parse(String.valueOf(crntDate));
            java.sql.Date hireDate = new java.sql.Date(dateCrnt.getTime());

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, statusCode);
            pstmt.setString(5, jobCode);
            pstmt.setDate(6, hireDate);
            pstmt.setString(7, phone);
            pstmt.setString(8, email);
            pstmt.setString(9, adminCode);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                int empNo = empDTO.getEmpNo(); // 4
                getAllEmpInfo();            // 리스트 초기화 후 최신화
                empDTO = getEmpInfo(id);    // 신입 정보
                System.out.println(empDTO.getEmpName() + "님의 사원 정보가 성공적으로 등록되었습니다.");
                System.out.println(empDTO);

                for (int i= 0 ; i < empDTOList.size(); i++ ){
                    if ( empNo == empDTOList.get(i).getEmpNo()){
                        empDTO = empDTOList.get(i);
                    }
                }

                System.out.println(empDTO);

            } else {
                System.out.println("사원 정보 등록에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }

    public void updateEmpInfo(){
        getAllEmpInfo();
        for ( EmployeeDTO empDTO : empDTOList ){
            System.out.println(empDTO);
        }
        System.out.print("정보를 변경할 사원의 사번 입력 >> ");
        int empNo = sc.nextInt();

        empDTO = new EmployeeDTO();
        for ( int i = 0 ; i < empDTOList.size(); i++ ){
            if ( empNo == empDTOList.get(i).getEmpNo()){
                empDTO = empDTOList.get(i);
            }
        }

        try {
            if ( empDTO.getEmpNo() != 0 ) {
                sc.nextLine();
                System.out.println("===== 변경할 항목 선택 =====");
                System.out.println(" 1. 사원 ID");
                System.out.println(" 2. 사원 PW");
                System.out.println(" 3. 사원 이름");
                System.out.println(" 4. 근태 현황");
                System.out.println(" 5. 직책 코드");
                System.out.println(" 6. 전화번호");
                System.out.println(" 7. E-MAIL");
                System.out.println(" 8. 관리자 코드");
                System.out.println("=========================");
                System.out.print(">> ");
                int culumn = sc.nextInt();

                String query;
                String updateValue;
                int result = 0;

                switch (culumn){
                    case 1:
                        sc.nextLine();
                        System.out.print("사원 ID 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();

                        int checkId = 0;
                        for (int i = 0 ; i < empDTOList.size(); i++ ){
                            if( updateValue.equals(empDTOList.get(i).getEmpId()) ){
                                System.out.println("중복된 ID입니다...");
                                checkId = 1;
                                break;
                            }
                        }
                        if ( checkId == 0 ){
                            query = prop.getProperty("updateEmpId");
                            pstmt = con.prepareStatement(query);
                            pstmt.setString(1,updateValue);
                            pstmt.setInt(2,empDTO.getEmpNo());
                            result = pstmt.executeUpdate();
                        }
                        break;
                    case 2:
                        sc.nextLine();
                        System.out.print("사원 PW 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpPw");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 3:
                        sc.nextLine();
                        System.out.print("사원 이름 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpName");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.print("근태 현황 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpStatusCode");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                    case 5:
                        sc.nextLine();
                        System.out.print("직책 코드 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpJobCode");
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
                        System.out.print("관리자 코드 변경할 값 입력 >> ");
                        updateValue = sc.nextLine();
                        query = prop.getProperty("updateEmpAdminCode");
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1,updateValue);
                        pstmt.setInt(2,empDTO.getEmpNo());
                        result = pstmt.executeUpdate();
                        break;
                }

                if ( result > 0) {  getAllEmpInfo();  }
                else {  System.out.println("변경 실패...");  }

                getEmpInfo(empNo);
                System.out.println(empDTO);
            }
            else  {
                System.out.println("해당하는 정보가 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    public void deleteEmpInfo(int empNo){

        if ( this.empDTOList.get(empNo).getEmpNo() == empNo ){
            empDTO = this.empDTOList.remove(empNo);
            System.out.println("삭제되었습니다.");
        } else {
            System.out.println("해당 사원 번호가 없습니다. 다시 확인해주세요.");

      for ( int i = 0 ; i < empDTOList.size(); i++ ){
          empDTOList.get(i).setEmpNo(i+1);
            }
        }


    }




}
