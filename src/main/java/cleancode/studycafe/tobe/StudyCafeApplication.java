package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(new StudyCafeIOHandler(), new SeatPassFileReader(), new LockerPassFileReader());
        studyCafePassMachine.run();
    }

}
