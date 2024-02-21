package com.dotCode.view;

import com.dotCode.controller.EmployeeFeature;

import java.util.Scanner;

public class AmsMenu {

    Scanner sc = new Scanner(System.in);
    EmployeeFeature empFture = new EmployeeFeature();

    public void menu(){

        boolean isLogin = empFture.logIn();

        boolean isTrue = true;
        boolean isCheck = true;
        int choice;

        while (isTrue) {
            if ( isLogin ) {
                System.out.println("============ AMS ============");
                System.out.println("1. 출근");
                System.out.println("2. 나의 근태조회");
                System.out.println("3. 부재 신청");
                if ( empFture.checkAdmin() == 0 ){
                    System.out.println("4. 사원 정보 열람");
                    System.out.println("5. 출결 현황 관리");
                }
                System.out.println("0. 퇴근");
                System.out.println("=============================");
                System.out.print(">> ");
                choice = sc.nextInt();

                if ( empFture.checkAdmin() != 0 ){
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


            }


        }

    }

}
