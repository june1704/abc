package chapter05;
/*
    === 인스턴스 변수 ===
    선수 이름 (name - 문자열)
    타율 (battingAverage - 실수)
    홈런 수(homeRuns - 정수)


    === 정적(클래스) 변수 ===
    : 생성된 선수의 총 인원(playerCount - 정수)
    >> 모든 인스턴스에서 공유

    // === 생성자 ===
    // >> 인스턴스 변수 초기화 & 정적 변수 후위 증가(변수명++)

    // === 인스턴스 메서드 ===
    // : 선수의 타율과 홈런 수를 업데이트하는 메서드
    // >> 매개변수로 newBattingAverage, newHomeRuns를 받아
    //    기존의 인스턴스 변수에 재할당

     */
//class BaseballPlayer {
//    String name ="최명준";
//    double battingAverage = 0;
//    int homeRuns = 0;
//}
//class playerCount(int){
//
//}

class BaseballPlayer {
    String name;
    double battingAverage;
    int homeRuns;

    static int playerCount;

    BaseballPlayer(String name, double battingAverage, int homeRuns) {
        this.name = name;
        this.battingAverage = battingAverage;
        this.homeRuns = homeRuns;

        playerCount++; // 선수 생성 시 전체 수 증가
    }
    void updatesStatus(double newBattingAverage, int newHomeRuns) {
        //인스턴스 변수 = 매개변수값;
        battingAverage = newBattingAverage;
        homeRuns = newHomeRuns;
    }

    // === 정적 메서드 ===
    // : 특정 홈런 수 이상인 선수를 평가
    static String evaluateHomeRun(int homeRunCount) {
        if (homeRunCount > 20) {
            return "홈런상 후보에 등록됩니다.";
        } else {
            return "홈런상 후보에 들어갈 수 없습니다.";
        }
    }
}

public class MethodPractice {
    public static void main(String[] args) {

        // 인스턴스 생성
        BaseballPlayer player1 = new BaseballPlayer("최명준", 0.001, 15);
        BaseballPlayer player2 = new BaseballPlayer("김영경", 0.011, 25);
        BaseballPlayer player3 = new BaseballPlayer("이승아", 0.111, 35);
        BaseballPlayer player4 = new BaseballPlayer("원신희", 1.111, 45);

        System.out.println("총 생성된 선수의 수: " + BaseballPlayer.playerCount); // 4

        player1.updatesStatus(0.002, 16);
        System.out.println(player1.battingAverage); // 0.002
        System.out.println(player1.homeRuns); // 16

        System.out.println(BaseballPlayer.evaluateHomeRun(21));
        System.out.println(BaseballPlayer.evaluateHomeRun(19));
    }
}
