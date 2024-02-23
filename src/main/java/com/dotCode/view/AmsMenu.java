package com.dotCode.view;

import com.dotCode.common.JDBCTemplete;
import com.dotCode.model.dao.EmployeeDAO;

import java.sql.Connection;
import java.util.Scanner;

import static com.dotCode.common.JDBCTemplete.getConnection;

public class AmsMenu {
    Scanner sc = new Scanner(System.in);
    Connection con = getConnection();
    EmployeeDAO registDAO = new EmployeeDAO();

    public void menu(){

        boolean isTrue = true;

        while ( isTrue ) {

            boolean isLogin = false;
            isLogin = registDAO.logIn(con);


            if ( isLogin ) {

                int choice;
                boolean isMenu = true;

                while ( isMenu ) {

                    System.out.println("============ AMS ============");
                    System.out.println("1. 출근");
                    System.out.println("2. 퇴근");
                    System.out.println("3. 나의 정보 열람");
                    System.out.println("4. 부재 신청");
                    System.out.println("5. 증빙서류 제출");
                    System.out.println("6. 로그아웃");
                    if ( registDAO.checkAdmin() == 0 ) {
                        System.out.println("7. 전체 사원 정보");
                        System.out.println("8. 사원 출결 관리");
                    }
                    System.out.println("0. 프로그램 종료");
                    System.out.println("=============================");
                    System.out.print(">> ");
                    choice = sc.nextInt();

                    int selectChoice;
                    if ( registDAO.checkAdmin() != 0 ){
                        sc.nextLine();
                        switch (choice) {

                            case 1:
                                break;
                            case 2:
                                break;
                            case 3: registDAO.getEmpInfo();
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6: isMenu = false; break;
                            case 0: isTrue = false; break;
                        }
                    }
                    else {
                        sc.nextLine();
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
                            case 6: isMenu = false;
                                break;
                            case 7:
                                System.out.println("============ 전체 사원 정보 ============");
                                System.out.println("1. 사원 등록");
                                System.out.println("2. 사원 정보 조회");
                                System.out.println("3. 사원 해고");
                                System.out.println("4. 처음으로");
                                System.out.println("=============================");
                                System.out.print(">> ");
                                selectChoice = sc.nextInt();
                                switch ( selectChoice ) {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        break;

                                }
                                break;
                            case 8:
                                System.out.println("============ 사원 관리 ============");
                                System.out.println("1. 조회");
                                System.out.println("2. 변경");
                                System.out.println("3. 부재 신청 허가");
                                System.out.println("4. 증빙 서류 관리");
                                System.out.println("5. 처음으로");
                                System.out.println("=============================");
                                System.out.print(">> ");
                                selectChoice = sc.nextInt();
                                switch ( selectChoice ) {
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
                                }
                                break;
                            case 0: isTrue = false; break;
                        }

                    }

                }

            } else {    System.out.println("해당하는 정보가 없습니다.");}

        }

    }

}
