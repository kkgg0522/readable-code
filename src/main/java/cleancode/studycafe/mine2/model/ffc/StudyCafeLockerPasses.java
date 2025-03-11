package cleancode.studycafe.mine2.model.ffc;

import cleancode.studycafe.mine2.model.StudyCafeLockerPass;
import cleancode.studycafe.mine2.model.StudyCafePass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses from(List<StudyCafeLockerPass> lockerPasses){
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public Optional<StudyCafeLockerPass> getLockerPassCondition(StudyCafePass selectTicketPass){
        return lockerPasses.stream()
                .filter(lockerPass ->
                        selectTicketPass.isTypeEqualsTo(lockerPass)
                                && selectTicketPass.isDurationEqualsTo(lockerPass)
                )
                .findFirst();

    }
}
