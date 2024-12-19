package chapter15.boardApp.view;

import chapter15.boardApp.controller.BoardController;
import chapter15.boardApp.dto.BoardRequestDto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardView {
    public static void main(String[] args) {
        BoardController boardController = new BoardController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("[ 게시판 관리 시스템 ]");
                System.out.println("1. 게시글 전체 조회");
                System.out.println("2. 게시글 단건 조회");
                System.out.println("3. 게시글 등록");
                System.out.println("4. 게시글 수정");
                System.out.println("5. 게시글 삭제");
                System.out.println("0. 종료");
                System.out.print("선택: ");
                int choice = sc.nextInt();
                sc.nextLine(); // 버퍼 제거

                switch (choice) { // 스프링이 대신 해줌
                    case 1:
                        boardController.getAllBoards();
                        break;
                    case 2:
                        System.out.print("조회할 게시글 ID: ");
                        long id = sc.nextLong();
                        boardController.getBoardById(id);
                        break;
                    case 3:
                        System.out.print("제목: ");
                        String title = sc.nextLine();
                        System.out.print("내용: ");
                        String content = sc.nextLine();
                        System.out.print("작성자: ");
                        String author = sc.nextLine();
                        boardController.createBoard(new BoardRequestDto(title, content, author));
                        break;
                    case 4:
                        System.out.print("수정할 게시글 ID");
                        long updateId = sc.nextLong();
                        sc.nextLine(); // 버퍼 제거
                        System.out.print("새 제목:");
                        String newTitle = sc.nextLine();
                        System.out.print("새 내용:");
                        String newContent = sc.nextLine();
                        boardController.updateBoard(updateId, new BoardRequestDto(newTitle, newContent, null)); // 스프링 가면 그대로
                        break;
                    case 5:
                        System.out.print("삭제할 게시글 ID: ");
                        long deleteId = sc.nextLong();
                        boardController.deleteBoard(deleteId);
                        break; // 해당 case를 종료하는 키워드
                    case 0:
                        System.out.println("=== 종료합니다. ===");
                        sc.close();
                        return; // 반복문을 종료하는 키워드
                    default:
                        System.out.println("잘못된 선택입니다. 0~5사이의 숫자를 입력하세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("입력이 유효하지 않습니다. 숫자를 입력하세요.");
                sc.nextLine(); // 유료하지 않은 값을 제거, 버퍼제거 역할까지
            } catch (IllegalArgumentException e) {
                // 인수값으로 부적절한 값을 넘길 때 던지는 예외
                System.out.println("오류: " + e.getMessage());
            }
        }
    }
}