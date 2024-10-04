package abstraction.Polymrphism;

import abstraction.Polymrphism.Plane;
import abstraction.Polymrphism.Turbo;
import abstraction.Polymrphism.TurboPlane;

/**
 * 자바에서는 타입 상속을 통하여 다형성을 구현한다.
 */

public class Polymorphism {
    public void typeCasting() {
        TurboPlane tp = new TurboPlane();
        tp.fly();
        tp.boost();

        Plane p = tp; //업캐스팅
        p.fly(); // TurboPlane에서 재정의된 fly 메서드가 실행된다.

        Turbo t = tp; //업캐스팅
        t.boost(); //  TurboPlane에서 구현된 fly 메서드가 실행된다.
    }
}
