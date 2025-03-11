package cleancode.studycafe.mine2;

import cleancode.studycafe.mine2.config.MachineConfig;
import cleancode.studycafe.mine2.io.InputHandler;
import cleancode.studycafe.mine2.io.OutputHandler;
import cleancode.studycafe.mine2.io.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        MachineConfig machineConfig = MachineConfig.of(new InputHandler(), new OutputHandler(), new StudyCafeFileHandler());

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(machineConfig);
        studyCafePassMachine.run();
    }

}
