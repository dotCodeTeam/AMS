package com.dotCode.view;

import com.dotCode.model.dao.AdminDAO;
import com.dotCode.model.dao.EmployeeDAO;

import java.util.Scanner;

public class AmsMenu {
    Scanner sc;
    EmployeeDAO empDAO;
    AdminDAO adminDAO;

    public void menu(){
        sc = new Scanner(System.in);

        boolean isMenu = true;

        while ( isMenu ) {
            empDAO = new EmployeeDAO();  // 인스턴스 초기화

            if ( empDAO.logIn() ) {

                int choice;
                boolean isLogin = true;

                while ( isLogin ) {
                    System.out.println("============ AMS ============");
                    System.out.println("1. 출근");
                    System.out.println("2. 퇴근");
                    System.out.println("3. 나의 정보 열람");
                    System.out.println("4. 부재 신청");
                    System.out.println("5. 증빙서류 제출");
                    System.out.println("6. 로그아웃");
                    if ( empDAO.checkAdmin() == 0 ) {
                        System.out.println("7. 전체 사원 정보");
                        System.out.println("8. 사원 근태 관리");
                    }
                    System.out.println("0. 프로그램 종료");
                    System.out.println("=============================");
                    System.out.print(">> ");
                    choice = sc.nextInt();

                    int selectChoice;
                    if ( empDAO.checkAdmin() != 0 ){
                        sc.nextLine();
                        switch (choice) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                System.out.println(empDAO.getEmpInfo());
                                System.out.println(empDAO.getAtdInfo());
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6: isLogin = false;
                                System.out.println("로그아웃 성공... " );
                                System.out.println(empDAO.getEmpInfo().getEmpName() + "님 오늘도 수고하셨습니다!");
                                break;
                            case 0:
                                isLogin = false;
                                isMenu = false;
                                break;
                            default:
                                System.out.println("잘못된 입력입니다...");
                                break;
                        }
                    }
                    else {
                        adminDAO = new AdminDAO();
                        sc.nextLine();
                        switch (choice) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                System.out.println(empDAO.getEmpInfo());
                                System.out.println(empDAO.getAtdInfo());
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                isLogin = false;
                                System.out.println("로그아웃 성공... " );
                                System.out.println(empDAO.getEmpInfo().getEmpName() + "님 오늘도 수고하셨습니다!");
                                break;
                            case 7:
                                System.out.println("========= 전체 사원 정보 =========");
                                System.out.println("1. 사원 등록");
                                System.out.println("2. 사원 정보 조회");
                                System.out.println("3. 사원 해고");
                                System.out.println("4. 처음으로");
                                System.out.println("================================");
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
                                boolean isTrue = true;
                                while (isTrue){
                                    System.out.println("======= 사원 근태 관리 ========");
                                    System.out.println("1. 근태 정보 조회");
                                    System.out.println("2. 근태 정보 변경");
                                    System.out.println("3. 부재 신청 허가");
                                    System.out.println("4. 처음으로");
                                    System.out.println("=============================");
                                    System.out.print(">> ");
                                    selectChoice = sc.nextInt();
                                    switch ( selectChoice ) {
                                        case 1:
                                            sc.nextLine();
                                            int selectType;
                                            boolean isSelect = true;
                                            while (isSelect) {
                                                System.out.println("======= 근태 정보 조회 ========");
                                                System.out.println("1. 사번 조회");
                                                System.out.println("2. 전체 조회");
                                                System.out.println("3. 이전으로");
                                                System.out.println("=============================");
                                                System.out.print(">> ");
                                                selectType = sc.nextInt();
                                                switch (selectType) {
                                                    case 1:
                                                        adminDAO.getAtdInfo();
                                                        break;
                                                    case 2:
                                                        adminDAO.getAllAtdInfo();
                                                        break;
                                                    case 3:
                                                        isSelect = false;
                                                        break;
                                                    default:
                                                        System.out.println("잘못된 입력입니다...");
                                                        break;
                                                }
                                            }
                                            break;
                                        case 2:
                                            adminDAO.updateAtdInfo();
                                            break;
                                        case 3:
                                            break;
                                        case 4:
                                            isTrue = false;
                                            break;
                                        default:
                                            System.out.println("잘못된 입력입니다...");
                                            break;
                                    }
                                }
                                break;
                            case 0:
                                isLogin = false;
                                isMenu = false;
                                break;
                            default:
                                System.out.println("잘못된 입력입니다...");
                                break;
                        }

                    }

                }

            } else {
                sc.nextLine();
                System.out.println("로그인 실패...");
                System.out.println("해당하는 정보가 없습니다.");}

        }

    }

}
