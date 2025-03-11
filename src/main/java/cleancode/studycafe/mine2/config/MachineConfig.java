package cleancode.studycafe.mine2.config;

import cleancode.studycafe.mine2.io.InputHandler;
import cleancode.studycafe.mine2.io.OutputHandler;
import cleancode.studycafe.mine2.io.StudyCafeFileHandler;

public class MachineConfig {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    private MachineConfig(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeFileHandler studyCafeFileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
    }

    public static MachineConfig of(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeFileHandler studyCafeFileHandler){
        return new MachineConfig(inputHandler, outputHandler, studyCafeFileHandler);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public StudyCafeFileHandler getStudyCafeFileHandler() {
        return studyCafeFileHandler;
    }
}
