package com.dotCode.model.dao;

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
import static com.dotCode.common.JDBCTemplete.getConnection;

public class EmployeeDAO {

    private Scanner sc;
    private PreparedStatement pstmt = null;
    private ResultSet rset = null;
    private Properties prop = new Properties();
    private EmployeeDTO empDTO = new EmployeeDTO();

    public EmployeeDAO(){
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

        String query = prop.getProperty("getEmpInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);

            rset = pstmt.executeQuery();

            if ( rset.next() ){
                empDTO.setEmpNo(rset.getString("EMP_NO"));
                empDTO.setEmpId(rset.getString("EMP_ID"));
                empDTO.setEmpPwd(rset.getString("EMP_PW"));
                empDTO.setEmpName(rset.getString("EMP_NAME"));
                empDTO.setPhone(rset.getString("PHONE"));
                empDTO.setEmail(rset.getString("EMAIL"));
                empDTO.setAdminCode(rset.getString("ADMIN_ID"));
                empDTO.setJobCode(rset.getString("POSITION_ID"));
                System.out.println("=============================");
                System.out.println("로그인 되었습니다!!");
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

    public EmployeeDTO getEmpInfo(){
        return this.empDTO;
    }

}
