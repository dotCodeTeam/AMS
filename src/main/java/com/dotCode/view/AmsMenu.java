package com.dotCode.view;

import com.dotCode.model.dao.EmployeeDAO;

import java.sql.Connection;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.getConnection;

public class AmsMenu {

    Scanner sc = new Scanner(System.in);
    Connection con = getConnection();
    EmployeeDAO registDAO = new EmployeeDAO();

    public void menu(){

        boolean isLogin = registDAO.logIn(con);

        boolean isTrue = true;
        boolean isCheck = true;
        int choice;

        while ( isTrue ) {
            if ( isLogin ) {
                System.out.println(registDAO.getEmpInfo());
                System.out.println("============ AMS ============");
                System.out.println("1. 출근");
                System.out.println("2. 나의 정보 열람");
                System.out.println("3. 부재 신청");
                if ( registDAO.checkAdmin() == 0 ){
                    System.out.println("4. 사원 정보 열람");
                    System.out.println("5. 출결 현황 관리");
                }
                System.out.println("0. 퇴근");
                System.out.println("=============================");
                System.out.print(">> ");
                choice = sc.nextInt();

                if ( registDAO.checkAdmin() != 0 ){
                    switch (choice) {

                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 0:
                            break;
                    }
                } else {
                    switch (choice) {

                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 0:
                            break;
                    }

                }

            } else {    System.out.println("해당하는 정보가 없습니다."); }

        }

    }

}
