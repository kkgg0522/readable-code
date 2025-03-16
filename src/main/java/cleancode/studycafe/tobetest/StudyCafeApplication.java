package cleancode.studycafe.tobetest;

import cleancode.studycafe.tobetest.io.StudyCafeIOHandler;
import cleancode.studycafe.tobetest.io.provider.LockerPassProvider;
import cleancode.studycafe.tobetest.io.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(new StudyCafeIOHandler(), new SeatPassProvider(), new LockerPassProvider());
        studyCafePassMachine.run();
    }

}
