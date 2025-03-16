package cleancode.studycafe.tobetest;

import cleancode.studycafe.tobetest.model.pass.seat.StudyCafeSeatPasses;
import cleancode.studycafe.tobetest.exception.AppException;
import cleancode.studycafe.tobetest.io.StudyCafeIOHandler;
import cleancode.studycafe.tobetest.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobetest.model.pass.StudyCafePassType;
import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobetest.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobetest.provider.LockerPassProvider;
import cleancode.studycafe.tobetest.provider.SeatPassProvider;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler ioHandler;
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(StudyCafeIOHandler ioHandler, SeatPassProvider seatPassProvider, LockerPassProvider lockerPassProvider) {
        this.ioHandler = ioHandler;
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = getSelectedPass();

            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            StudyCafePassOrder passOrder = StudyCafePassOrder.of(
                    selectedPass,
                    optionalLockerPass.orElse(null)
            );

            ioHandler.showPassOrderSummary(passOrder);


        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass getSelectedPass() {

        StudyCafePassType passType = ioHandler.askPassTypeSelecting();

        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();
            boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);

            if (isLockerSelected) {
                return Optional.of(lockerPass);
            }
        }

        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }

}
