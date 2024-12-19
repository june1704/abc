package ex;

import java.util.Scanner;

public class eee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        System.out.print("숫자를 입력하세요: ");

        if (a % 2 == 0) {
            System.out.println("짝수 입니다.");
        } else {
            System.out.println("홀수 입니다.");
        }
        sc.close();
    }
}
