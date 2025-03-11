package cleancode.studycafe.mine;

import cleancode.studycafe.mine.config.MachineConfig;
import cleancode.studycafe.mine.io.InputHandler;
import cleancode.studycafe.mine.io.OutputHandler;
import cleancode.studycafe.mine.io.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        MachineConfig machineConfig = MachineConfig.of(new InputHandler(), new OutputHandler(), new StudyCafeFileHandler());

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(machineConfig);
        studyCafePassMachine.run();
    }

}
