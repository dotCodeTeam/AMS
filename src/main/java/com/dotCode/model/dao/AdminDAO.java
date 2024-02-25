package com.dotCode.model.dao;

import com.dotCode.model.dto.AttendanceDTO;
import com.dotCode.model.dto.EmployeeDTO;
import com.dotCode.model.dto.VacantDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.dotCode.common.JDBCTemplete.close;

public class AdminDAO extends EmployeeDAO {

    private List<AttendanceDTO> atdDTOList;
    private AttendanceDTO atdDTO;
    private List<VacantDTO> vcntDTOList;
    private VacantDTO vcntDTO;

    public AdminDAO() {
        atdDTOList = new ArrayList<>();
        vcntDTOList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/admin-query.xml"));

            String query = prop.getProperty("getAllAtdInfo");
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
                vcntDTO.setReceiveApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setReceiveDayDate(rset.getString("DAY_DATE"));
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
        System.out.print("사번을 입력하세요 >> ");
        int empNo = sc.nextInt();
        for ( int i = 0 ; i < vcntDTOList.size(); i++ ){
            if(empNo == vcntDTOList.get(i).getEmpNo()){
                vcntDTO = vcntDTOList.get(i);
            }
        }

        int result = 0;
        if ( vcntDTO != null ){
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

                vcntDTOList = getAllVcntInfo(empNo);
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

        for (int i = 0 ; i < vcntDTOList.size(); i++ ){
            if( empNo == vcntDTOList.get(i).getEmpNo() ) {
                vcntDTO = vcntDTOList.get(i);
            }
        }

        if( vcntDTO != null ){
            String query = prop.getProperty("getVcntInfo");
            try {
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,empNo);
                rset = pstmt.executeQuery();
                if ( rset.next()) {
                    vcntDTO.setVacantNo(rset.getInt("VACANT_NO"));
                    vcntDTO.setEmpNo(rset.getInt("EMP_NO"));
                    vcntDTO.setStatusCode(rset.getString("STATUS_CODE"));
                    vcntDTO.setReceiveApplyDate(rset.getString("APPLY_DATE"));
                    vcntDTO.setReceiveDayDate(rset.getString("DAY_DATE"));
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
                vcntDTO.setReceiveApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setReceiveDayDate(rset.getString("DAY_DATE"));
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

    public List<VacantDTO> getAllVcntInfo(int empNo){
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
                vcntDTO.setReceiveApplyDate(rset.getString("APPLY_DATE"));
                vcntDTO.setReceiveDayDate(rset.getString("DAY_DATE"));
                vcntDTO.setCause(rset.getString("CAUSE"));
                vcntDTO.setAcceptStatus(rset.getString("ACCEPT_STATUS"));

                vcntDTOList.add(vcntDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vcntDTOList;
    }




}
