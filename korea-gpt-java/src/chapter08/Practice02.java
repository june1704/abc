package chapter08;

// === 인터페이스 === //
// Fruit 과일 인터페이스 정의
// - color: 추상 메서드
// - describe: 디폴트 메서드
// - printType: 정적 메서드

interface Fruit {
    // 인터페이스의 추상 메서드
    // : public abstract이 생략되어 있음 (cf. 추상 클래스의 추상 메서드와 다름 !)
    String color(); // 추상 메서드

    // 디폴트 메서드: 재정의(오버라이딩) 가능
    default void describe() {
        System.out.println("과일의 색상은" + color() + "이며, 과일의 모양은 동그랗습니다.");
    }

    // 정적 메서드: 재정의(오버라이딩) 불가능
    static void printType() {
        System.out.println("과일(Fruit)의 타입입니다.");
    }
}

class Apple implements Fruit {
    public String color() {
        return "red";
    }
}

class Orange implements Fruit {
    public String color() {
        return "orange";
    }
}

class Banana implements Fruit {
    public String color() {
        return "yellow";
    }
    @Override
    public void describe() {
        System.out.println("과일의 색상은" + color() + "이며, 과일의 모양은 깁니다.");
    }
}

// === 또 다른 인터페이스 === //
interface TropicalFruit extends Fruit {
    // extends (상속 - 확장하다)
    // : 클래스 간에, 인터페이스 간에 가능

    // cf) implements (구현 - 구현하다)
    // : 인터페이스를 클래스로 구현
    default void tropicalFeature() {
        // 열대 과일의 특징
        System.out.println("열대 과일은 후숙하면 더 맛있습니다.");
    }
}

class Mango implements TropicalFruit {
    public String color() {
        return "golden";
    }

    @Override
    public void describe() {
        System.out.println("과일의 색상은" + color() + "이며, 과일의 모양은 타원형입니다.");
    }
}

public class Practice02 {
    public static void main(String[] args) {
        // 다형성 적용
        Fruit apple = new Apple();
        Fruit orange = new Orange();
        Fruit banana = new Banana();
        Fruit mango = new Mango();

        /*
        개념 정리:
        Fruit mango = new Mango(); (업캐스팅)
        Mango 클래스는 TropicalFruit 인터페이스를 구현하고,
        TropicalFruit 인터페이스는 Fruit 인터페이스를 확장합니다.
        따라서 Mango 객체는 TropicalFruit 타입을 가지고 있으며,
        TropicalFruit는 Fruit를 상속받으므로,
        Mango 객체를 Fruit 타입 변수에 할당하는 것은 업캐스팅이 됩니다.

        TropicalFruit와 Fruit의 관계
        TropicalFruit는 Fruit를 상속(확장)하는 인터페이스이기 때문에,
        TropicalFruit는 Fruit의 하위 개념으로 볼 수 있습니다.
        즉, Mango 객체를 TropicalFruit로 업캐스팅할 수 있고,
        그 후 Fruit로도 업캐스팅할 수 있습니다.

        요약:
        Mango mango = new Mango(); → Mango 타입으로 Mango 객체를 생성합니다.
        Fruit mango = new Mango(); → Mango 객체를 Fruit 타입 변수에 할당한 업캐스팅입니다.
        TropicalFruit tropicalFruit = new Mango(); → Mango 객체를 TropicalFruit 타입 변수에 할당한 업캐스팅입니다.
        이 모든 것이 다 업캐스팅에 해당하며, Mango 객체는 TropicalFruit와 Fruit 타입으로도 취급될 수 있습니다.
         */

        // == 다형성 예제 == //
        // mango가 Fruit로 형 변환(업캐스팅)
        Fruit[] fruits = { apple, orange, banana, mango };

        for (Fruit fruit : fruits) {
            fruit.describe();

        if (fruit instanceof TropicalFruit) {
            TropicalFruit tropicalFruit = (TropicalFruit) fruit; // 다운캐스팅
            tropicalFruit.tropicalFeature(); //열대 과일은 후숙하면 더 맛있습니다.
        }
    }
        // cf) A instanceof B 연산자
        // : "A 객체가 B 클래스(종류)와 같은지 확인해줘" 도구
        // >> 확인의 결과값을 boolean으로 반환
//        class 동물 {}
//        class 고양이 extends 동물 {}
//        class 강아지 extends 동물 {}
//
//        동물 나비 = new 고양이();
//        동물 멍멍이 = new 강아지();
//
//        if (나비 instanceof 고양이) {
//            고양이 a1 = (고양이) 나비; // 실행 O
//        }
//
//        if (멍멍이 instanceof 고양이) {
//            고양이 a2 = (고양이) 멍멍이; // 실행 x
//        }
//
        if(mango instanceof Fruit) {
            Fruit mangoAsPruit = (Fruit) mango; // 강제 형 변환 다운캐스팅
            mangoAsPruit.describe(); // 과일의 색상은golden이며, 과일의 모양은 타원형입니다.
        }

        if (banana instanceof TropicalFruit) {
            TropicalFruit bananaAsTropical = (TropicalFruit) banana;
            bananaAsTropical.tropicalFeature();
        } else {
            System.out.println("바나나는 열대 과일이 아닙니다."); // 바나나는 열대 과일이 아닙니다.
        }

        Fruit.printType();// 과일(Fruit)의 타입입니다.
    }
}
