package cleancode.studycafe.mine;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.io.InputHandler;
import cleancode.studycafe.mine.io.OutputHandler;
import cleancode.studycafe.mine.io.StudyCafeFileHandler;
import cleancode.studycafe.mine.model.*;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.from(StudyCafeFileHandler.readLockerPasses());
    private final StudyCafeTicketPasses studyCafeTicketPasses = StudyCafeTicketPasses.from(StudyCafeFileHandler.readStudyCafePasses());


    public void run() {
        try {
            outputHandler.showWelcomeMessage();

            StudyCafePassType studyCafePassType = getPassTypeSelectingUserAction();

            StudyCafeTicketPass cafePass = getSelectedCafeTicketPass(studyCafePassType);

            StudyCafeLockerPass lockerPass = studyCafeLockerPasses.find(cafePass);

            if(studyCafePassType == StudyCafePassType.FIXED){
                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(cafePass, lockerPass);
                    return;
                }
            }

            outputHandler.showPassOrderSummary(cafePass, lockerPass);
            
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
