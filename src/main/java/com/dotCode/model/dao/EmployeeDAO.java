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
                empDTO.setEmpId(rset.getString("EMP_NO"));
                empDTO.setEmpPwd(rset.getString("EMP_NO"));
                empDTO.setEmpName(rset.getString("EMP_NO"));
                empDTO.setPhone(rset.getString("EMP_NO"));
                empDTO.setEmail(rset.getString("EMP_NO"));
                empDTO.setAdminId(rset.getString("EMP_NO"));
                empDTO.setPositionId(rset.getString("EMP_NO"));
                isTrue = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isTrue;
    }

    public EmployeeDTO getEmpInfo(){
        return this.empDTO;
    }

    public int checkAdmin(){
        int result = 1;
        if ( this.empDTO.getAdminId().equals("0") ) {
            result = 0;
        }
        return result;
    }
}
