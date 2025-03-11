package cleancode.studycafe.mine2;

import cleancode.studycafe.mine2.config.MachineConfig;
import cleancode.studycafe.mine2.exception.AppException;
import cleancode.studycafe.mine2.io.InputHandler;
import cleancode.studycafe.mine2.io.OutputHandler;
import cleancode.studycafe.mine2.io.StudyCafeFileHandler;
import cleancode.studycafe.mine2.model.StudyCafeLockerPass;
import cleancode.studycafe.mine2.model.StudyCafePass;
import cleancode.studycafe.mine2.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;


    public StudyCafePassMachine(MachineConfig machineConfig) {
        inputHandler = machineConfig.getInputHandler();
        outputHandler = machineConfig.getOutputHandler();
        studyCafeFileHandler = machineConfig.getStudyCafeFileHandler();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();

            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            actStudyCafe(studyCafePassType);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void actStudyCafe(StudyCafePassType studyCafePassType) {
        StudyCafePass selectTicketPassBy = getSelectTicketPassBy(studyCafePassType);

        Optional<StudyCafeLockerPass> studyCafeLockerPassCond = getStudyCafeLockerPassCond(selectTicketPassBy);

        studyCafeLockerPassCond.ifPresentOrElse(
                lockerPass -> outputHandler.showPassOrderSummary(selectTicketPassBy, lockerPass),
                () -> outputHandler.showPassOrderSummary(selectTicketPassBy)
        );
    }

    private StudyCafePass getSelectTicketPassBy(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> hourlyPasses = getStudyCafeTicketPassesCond(studyCafePassType);
        outputHandler.showPassListForSelection(hourlyPasses);

        return inputHandler.getSelectPass(hourlyPasses);
    }

    private List<StudyCafePass> getStudyCafeTicketPassesCond(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();

        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }


    private Optional<StudyCafeLockerPass> getStudyCafeLockerPassCond(StudyCafePass selectTicketPassBy) {
        if (selectTicketPassBy.isNotAvailableLockerType()) {
            return Optional.empty();
        }

        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        return lockerPasses.stream()
                .filter(lockerPass ->
                        lockerPass.getPassType() == selectTicketPassBy.getPassType()
                                && lockerPass.getDuration() == selectTicketPassBy.getDuration()
                )
                .findFirst()
                .flatMap(lockerPass -> {
                    outputHandler.askLockerPass(lockerPass);
                    return inputHandler.getLockerSelection() ? Optional.of(lockerPass) : Optional.empty();
                });


    }

}
