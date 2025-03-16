package cleancode.studycafe.tobetest;

import cleancode.studycafe.tobetest.io.StudyCafeIOHandler;
import cleancode.studycafe.tobetest.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobetest.io.provider.SeatPassFileReader;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(new StudyCafeIOHandler(), new SeatPassFileReader(), new LockerPassFileReader());
        studyCafePassMachine.run();
    }

}
