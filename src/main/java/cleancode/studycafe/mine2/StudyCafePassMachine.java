package cleancode.studycafe.mine2;

import cleancode.studycafe.mine2.config.MachineConfig;
import cleancode.studycafe.mine2.exception.AppException;
import cleancode.studycafe.mine2.io.InputHandler;
import cleancode.studycafe.mine2.io.OutputHandler;
import cleancode.studycafe.mine2.io.StudyCafeFileHandler;
import cleancode.studycafe.mine2.model.StudyCafeLockerPass;
import cleancode.studycafe.mine2.model.StudyCafePass;
import cleancode.studycafe.mine2.model.StudyCafePassType;
import cleancode.studycafe.mine2.model.ffc.StudyCafeLockerPasses;
import cleancode.studycafe.mine2.model.ffc.StudyCafePasses;

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

            StudyCafePassType studyCafePassType = getPassTypeSelectingUserAction();

            actStudyCafe(studyCafePassType);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showNotCatchExceptionMessage();
        }
    }

    private StudyCafePassType getPassTypeSelectingUserAction() {
        outputHandler.askPassTypeSelection();

        StudyCafePassType passTypeSelectingUserAction = inputHandler.getPassTypeSelectingUserAction();

        if(passTypeSelectingUserAction != StudyCafePassType.NOT_MATCH){
            return passTypeSelectingUserAction;
        }

        outputHandler.showUserInputNotMatch();
        return getPassTypeSelectingUserAction();
    }

    private void actStudyCafe(StudyCafePassType studyCafePassType) {
        StudyCafePass selectPassBy = getSelectPassBy(studyCafePassType);

        Optional<StudyCafeLockerPass> studyCafeLockerPassCond = getStudyCafeLockerPassCond(selectPassBy);

        studyCafeLockerPassCond.ifPresentOrElse(
                lockerPass -> outputHandler.showPassOrderSummary(selectPassBy, lockerPass),
                () -> outputHandler.showPassOrderSummary(selectPassBy)
        );
    }

    private StudyCafePass getSelectPassBy(StudyCafePassType studyCafePassType) {
        StudyCafePasses studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        StudyCafePasses studyCafePassCondition = studyCafePasses.getStudyCafePassCondition(studyCafePassType);

        return validInputFromUser(studyCafePassCondition);
    }

    private Optional<StudyCafeLockerPass> getStudyCafeLockerPassCond(StudyCafePass selectPass) {
        if (selectPass.isNotAvailableLockerType()) {
            return Optional.empty();
        }

        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
        Optional<StudyCafeLockerPass> lockerPassCondition = lockerPasses.getLockerPassCondition(selectPass);

        lockerPassCondition.flatMap(lockerPass -> {
            outputHandler.askLockerPass(lockerPass);
            return inputHandler.getLockerSelection() ? Optional.of(lockerPass) : Optional.empty();
        });

        return Optional.empty();
    }

    private StudyCafePass validInputFromUser(StudyCafePasses studyCafePasses){
        outputHandler.showPassListForSelection(studyCafePasses);
        int selectIndex = inputHandler.getSelectPass();

        if(studyCafePasses.hasIndex(selectIndex))
            return studyCafePasses.get(selectIndex);

        outputHandler.showUserInputNotMatch();
        return validInputFromUser(studyCafePasses);
    }




}
