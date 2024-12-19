package chapter14.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

// === 메서드 참조 === //
// 1. 정적 메서드 참조
class StaticMethod {
    static void printUpperCase(String str) {
        System.out.println(str.toUpperCase());
    }
}

// 2. 인스턴스 메서드 참조
class InstanecMethod {
    void printLowerCase(String str) {
        System.out.println(str.toLowerCase());
    }
}

// 3. 특정 객체의 인스턴스 참조
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
}
// Unknown: 알 수 없음
// 4. 생성자 참조
class Human {
    private String name;
    public Human() {
        this.name = "Unknown";
    }
    public String getName() { return name; }
}

public class C_Method_Ref {
    public static void main(String[] args) {
        // 1. 정적 메서드 참조 예시
        // 클래스명::정적메서드명
        List<String> fruits = Arrays.asList("apple", "mango", "banana");
        fruits.forEach(StaticMethod::printUpperCase);
        // APPLE
        // MANGO
        // BANANA

        // 2. 인스턴스 메서드 참조 예시
        // 인스턴스명::인스턴스 메서드명
        InstanecMethod instanecMethod = new InstanecMethod();
        List<String> fruits2 = Arrays.asList("APPLE", "MANGO", "BANANA");
        fruits2.forEach(instanecMethod::printLowerCase);
        // apple
        // mango
        // banana

        // 3. 특정 객체의 인스턴스 참조 예시
        Calculator calculator = new Calculator();
        // cf) BiFunction: 2개의 인자를 받고 1개의 객체를 리턴시키는 함수형 인터페이스
        //      BiFunction<T, U, R>
        BiFunction<Integer, Integer, Integer> addition = calculator::add;
        int result1 = addition.apply(5, 3);
        int result2 = addition.apply(15, 13);
        System.out.println(result1); // 8
        System.out.println(result2); // 28

        // 4. 생성자 참조 예시 // 많이 쓰임
        Supplier<Human> humanSupplier = Human::new; // new가 생성자 호출이 됨, 정적메서드의 일부
        // humanSupplier를 호출하면 new Human(); 과 똑같다
        Human human1 = humanSupplier.get();
        Human human2 = humanSupplier.get();
        Human human3 = humanSupplier.get();
        System.out.println(human1.getName()); // Unknown
    }
}


