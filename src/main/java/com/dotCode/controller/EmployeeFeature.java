package com.dotCode.controller;

import com.dotCode.model.dto.EmployeeDto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.close;
import static com.dotCode.common.JDBCTemplete.getConnection;

public class EmployeeFeature {

    Scanner sc = new Scanner(System.in);
    Connection con = getConnection();
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    Properties prop = new Properties();
    EmployeeDto row = new EmployeeDto();

    public boolean logIn() {
        boolean isTrue = true;

        System.out.println("============ AMS ============");
        System.out.print("     ID : ");
        String id = sc.nextLine();
        System.out.print("     PW : ");
        String pwd = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/dotCode/mapper/ams-query.xml"));

            String query = prop.getProperty("getEmpInfo");
            System.out.println(query);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);

            rset = pstmt.executeQuery();

            if ( id.equals(rset.getString("EMP_ID")) && pwd.equals(rset.getString("EMP_PW"))){

                if(rset.next()){
                    row.setEmpNo(rset.getString("EMP_NO"));
                    row.setEmpNo(rset.getString("EMP_ID"));
                    row.setEmpNo(rset.getString("EMP_PW"));
                    row.setEmpNo(rset.getString("EMP_NAME"));
                    row.setEmpNo(rset.getString("PHONE"));
                    row.setEmpNo(rset.getString("EMAIL"));
                    row.setEmpNo(rset.getString("ADMIN_ID"));
                    row.setEmpNo(rset.getString("POSITION_ID"));
                }

                System.out.println("=================================");
                System.out.println("로그인 성공!");
                System.out.println(row.getEmpName() + "님 환영합니다! ");
                System.out.println("=================================");

            }   else {
                System.out.println("일치하는 정보가 없습니다.");
                isTrue = false;
            }

        } catch (IOException | NullPointerException | SQLException e) {
            System.out.println("잘못된 입력입니다.");
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


        return isTrue;
    }

    public int checkAdmin(){
        int result = 1;
        if ( row.getAdminId().equals("0") ) {
            result = 0;
        }
        return result;
    }


}
