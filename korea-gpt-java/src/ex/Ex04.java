package ex;

class Parent {
    void displayInfo() {
        System.out.println("부모 클래스");
    }
}
class  Child extends Parent {
    @Override
    void displayInfo() {
        System.out.println("자식 클래스");
    }
    void childMethod() {
        System.out.println("자식 클래스만이 가진 메서드입니다.");
    }
}
public class Ex04 {
    public static void main(String[] args) {
        Parent Child1 = new Child(); // 부모는 자식을 불러와서 자식 고유메서드는 못 불러옴 (자동 형 변환 - 업캐스팅)
        Child1.displayInfo();

        Child child2 = new Child(); // 자식은 자식을 불러오기 때문에 다 불러올 수 있음
        child2.displayInfo();
        child2.childMethod();

        Parent parent1 = child2; // 부모는 자식을 불러와서 자식 고유메서드는 못 불러옴
        parent1.displayInfo();

        Child child = (Child) parent1; // 자식은 부모를 부르기 위해선 강제 형변환 시켜줘야 부를 수 있다 (강제 형 변환 - 다운캐스팅)
        child.childMethod();
        child.displayInfo();

    }
}
