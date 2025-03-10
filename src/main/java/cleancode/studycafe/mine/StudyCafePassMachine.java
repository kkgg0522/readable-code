package cleancode.studycafe.mine;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.io.InputHandler;
import cleancode.studycafe.mine.io.OutputHandler;
import cleancode.studycafe.mine.io.StudyCafeFileHandler;
import cleancode.studycafe.mine.model.*;
import cleancode.studycafe.mine.model.fcc.StudyCafeLockerPasses;
import cleancode.studycafe.mine.model.fcc.StudyCafeTicketPasses;
import cleancode.studycafe.mine.model.StudyCafePassType;
import cleancode.studycafe.mine.model.StudyCafeTicketPass;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeTicketPasses studyCafeTicketPasses = StudyCafeFileHandler.readStudyCafePasses();
    private final StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeFileHandler.readLockerPasses();


    public void run() {
        try {
            outputHandler.showWelcomeMessage();

            StudyCafePassType studyCafePassType = getPassTypeSelectingUserAction();

            StudyCafeTicketPass selectedTicketPass = getSelectedCafeTicketPass(studyCafePassType);
            StudyCafeLockerPass lockerPass = studyCafeLockerPasses.find(selectedTicketPass);

            if (lockerPass != null) {
                outputHandler.askLockerPass(lockerPass);
                boolean lockerSelection = inputHandler.getLockerSelection();

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedTicketPass, lockerPass);
                    return;
                }
            }

            outputHandler.showPassOrderSummary(selectedTicketPass);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePassType getPassTypeSelectingUserAction() {
        StudyCafePassType passTypeSelectingUserAction = inputHandler.getPassTypeSelectingUserAction();

        if(passTypeSelectingUserAction != StudyCafePassType.NOT_MATCH){
            return passTypeSelectingUserAction;
        }

        outputHandler.showRetryMessage();
        return getPassTypeSelectingUserAction();
    }

    private StudyCafeTicketPass getSelectedCafeTicketPass(StudyCafePassType studyCafePassType){
        StudyCafeTicketPasses findTicketPasses = studyCafeTicketPasses.find(studyCafePassType);
        outputHandler.showPassListForSelection(findTicketPasses);
        return getStudyCafeTicketPassFromUser(findTicketPasses);

    }

    private StudyCafeTicketPass getStudyCafeTicketPassFromUser(StudyCafeTicketPasses findTicketPasses) {
        int index = inputHandler.getSelectPassIndexFromUser();

        if(findTicketPasses.hasIndex(index)){
            return findTicketPasses.getIndex(index);
        }

        outputHandler.showRetryMessage(findTicketPasses);
        return getStudyCafeTicketPassFromUser(findTicketPasses);
    }


}
