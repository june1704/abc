package chapter12.view;

import chapter12.controller.StudentController;
import chapter12.model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final Scanner sc;
    private final StudentController controller;

    public StudentView() {
        this.sc = new Scanner(System.in);
        this.controller = new StudentController();
    }

    public void showMenu() {
        int choice = 0;
        while (true) {
            System.out.println("=== 학생 관리 시스템 ===");
            System.out.println("1. 학생 추가");
            System.out.println("2. 전체 학생 조회");
            System.out.println("3. 특정 학생 조회");
            System.out.println("4. 학생 수정");
            System.out.println("5. 학생 삭제");
            System.out.println("6. 프로그램 종료");
            System.out.print("메뉴를 선택해주세요: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudentView();
                    break;
                case 2:
                    showAllStudentView();
                    break;
                case 3:
                    findStudentView();
                    break;
                case 4:
                    updateStudentView();
                    break;
                case 5:
                    removeStudentView();
                    break;
                case 6:
                    System.out.println("===== Process Exit =====");
                    return;
            }
        }
    }

    private void addStudentView() {
        System.out.print("학생의 이름을 입력해주세요: ");
        String name = sc.nextLine();

        System.out.print("학생의 나이를 입력해주세요: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("학생의 학번을 입력해주세요: ");
        String id = sc.nextLine();

        controller.addStudent(name, age, id);
        System.out.println("정상적으로 입력되었습니다.");
    }

    private void showAllStudentView() {
        List<Student> students = controller.getAllStudents();

        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    private void findStudentView() {
        System.out.println("Enter Student ID to find: ");
        String id = sc.nextLine();
        Student student = controller.findStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    private void updateStudentView() {
        System.out.println("Enter Student Id to update: ");
        String id = sc.nextLine();
        System.out.println("Enter new Name: ");
        String newName = sc.nextLine();
        System.out.println("Enter new Age: ");
        int newAge = sc.nextInt();
        sc.nextLine();

        if (controller.updateStudent(id, newName, newAge)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found");
        }
    }


    private void removeStudentView() {
        System.out.println("Enter Student Id to remove: ");
        String id = sc.nextLine();

        if (controller.removeStudent(id)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found");
        }
    }
}