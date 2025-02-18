package chapter13.practice.service;

import chapter13.practice.model.Book;
import chapter13.practice.repository.BookRepository;

import java.util.List;
// implements 클래스가 인터페이스를 구현
// 비즈니스 로직을 처리 - 싱글톤 패턴으로 구현
public class BookServiceImpl implements BookManager<Book> {
    private static BookServiceImpl instance;
    private final BookRepository repository = new BookRepository();

    private BookServiceImpl() {}

    public static BookServiceImpl getInstance() {
        if(instance == null) { // 인스턴스화 했니? 아니. 인스턴스는 null 값이야
            instance = new BookServiceImpl();// 인스턴스 진행
        }
        return instance;
    }

    @Override
    public void addBook(Book book) {
        //책의 ID로 데이터를 조회했을 때, 그 데이터가 저장소에 존재하는가?
        if (repository.findById(book.getId())!= null) { // 많이 쓰임
            // 중복된 id값을 가진 도서가 데이터베이스에 존재하는 경우
            System.out.println("중복된 도서 ID입니다. 추가되지 않습니다.");
            return;
        }

        repository.save(book);
        System.out.println(book.getTitle() + "(이)가 추가되었습니다.");
    }

    @Override
    public List<Book> listAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book findBookById(int id) {
        Book book = repository.findById(id);
        if (book == null) {
            System.out.println("ID " + id + "에 해당하는 도서를 찾을 수 없습니다.");
            // return null; 중복코드
        }
        return book;
    }

    @Override
    public void updateBook(int id, Book updatedBook) {
        Book existingBook = repository.findById(id);

        if (existingBook == null) {
            System.out.println("ID " + id + "에 해당하는 책이 없습니다.");
            return;
        }
        repository.deleteById(id); // 기존 도서 삭제
        repository.save(updatedBook); // 업데이트 된 도서 추가
        System.out.println("ID " + id + " 도서가 업데이트 되었습니다.");
    }

    @Override
    public void removeBook(int id) {
        Book existingBook = repository.findById(id);

        if (existingBook == null) {
            System.out.println("ID " + id + "에 해당하는 책이 없습니다.");
            return;
        }
        repository.deleteById(id);
        System.out.println(existingBook.getTitle() + "이(가) 삭제되었습니다.");
    }
}
