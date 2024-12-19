package chapter14;

// == 컬렉션 프레임워크 & 스트림 API & 람다 표현식 & 메서드 참조 == //

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// 1. 학생 목록 필터링 & 정렬
@AllArgsConstructor
@Getter
@ToString
class StudentClass {
    private String name;
    private int age;
    private double grade;
}

public class H_Practice {
    public static void main(String[] args) {
        // 1. 학생 목록 필터링 & 정렬
        List<StudentClass> studentClasses = Arrays.asList(
                new StudentClass("이승아", 29, 4.3),
                new StudentClass("최명준", 30, 4.1),
                new StudentClass("이다정", 35, 3.8),
                new StudentClass("이지희", 33, 3.1)
        );

        // 1-1. 학점이 4.0 이상인 학생 필터링
        List<StudentClass> filteredStudents = studentClasses.stream()
                .filter(student -> student.getGrade() >= 4.0)
                .collect(Collectors.toList());

        System.out.println("학점이 3.0 이상인 학생: ");
        filteredStudents.forEach(System.out::println);
        // 학점이 3.0 이상인 학생:
        // StudentClass(name=이승아, age=29, grade=4.3)
        // StudentClass(name=최명준, age=30, grade=4.1)

        // 1-2. 학생 이름 목록만 추출
        List<String> studentNames = studentClasses.stream()
                //.map(studentClass -> studentClass.getName())
                .map(StudentClass::getName)
                .collect(Collectors.toList());

        System.out.println("학생 이름 목록: ");
        System.out.println(studentNames); // [이승아, 최명준, 이다정, 이지희]

        // 1-3. 학생들은 점수순으로 정렬
        List<StudentClass> sortedStudents = studentClasses.stream()
                // 컬렉션 프레임워크의 중간 연산: .sorted(정렬)
                // Comparator클래스의 .comparingDouble 정적 메서드 활용
                // : 실수값들에 대한 비교
                // StudentClass::getGrade
                .sorted(Comparator.comparingDouble(StudentClass::getGrade)) // 오름차순
                // .sorted(Comparator.comparingDouble(StudentClass::getGrade).reversed()) - 내림차순
                .collect(Collectors.toList());

        System.out.println("점수 순으로 정렬된 학생들: ");
        sortedStudents.forEach(System.out::println);
        // 점수 순으로 정렬된 학생들:
        // StudentClass(name=이지희, age=33, grade=3.1)
        // StudentClass(name=이다정, age=35, grade=3.8)
        // StudentClass(name=최명준, age=30, grade=4.1)
        // StudentClass(name=이승아, age=29, grade=4.3)

        // 1-4. 학생들 중 '이' 단어가 이름에 포함된 학생 필터링
        List<StudentClass> studentsWithLee = studentClasses.stream()
                .filter(studentClass -> studentClass.getName().contains("이"))
                .collect(Collectors.toList());

        System.out.println("이름에 '이'가 포함된 학생: ");
        studentsWithLee.forEach(System.out::println);
        // 이름에 '이'가 포함된 학생:
        // StudentClass(name=이승아, age=29, grade=4.3)
        // StudentClass(name=이다정, age=35, grade=3.8)
        // StudentClass(name=이지희, age=33, grade=3.1)
    }
}
