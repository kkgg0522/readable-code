package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe.io.provider.LockerPassProvider;
import cleancode.studycafe.tobe.io.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(new StudyCafeIOHandler(), new SeatPassProvider(), new LockerPassProvider());
        studyCafePassMachine.run();
    }

}
