package encapsulation;

public class TellDontAsk {
    /**
     * 데이터를 물어보지 않고 기능의 실행만을 요청한다.
     */

    private void bad() {
        // 기능 수정을 위해선 해당 코드가 사용되는 곳을 모두 찾아야 한다.
        if (member.isMale() && member.getExpriyDate().getDate() < currentDate) {
            // ...
        }
    }

    private void good() {
        // 캡슐화된 코드를 수정하는 것만으로 기능 수정이 가능하다.
        if (member.isExpried()) {
            // ...
        }
    }
}
