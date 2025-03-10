package cleancode.studycafe.mine.model.fcc;

import cleancode.studycafe.mine.model.StudyCafeLockerPass;
import cleancode.studycafe.mine.model.StudyCafePassType;
import cleancode.studycafe.mine.model.StudyCafeTicketPass;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses from(List<StudyCafeLockerPass> lockerPasses){
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public StudyCafeLockerPass find(StudyCafeTicketPass ticketPass){
        if(ticketPass.getPassType() != StudyCafePassType.FIXED) {
            return null;
        }

        return lockerPasses.stream()
                .filter(option ->
                        option.isEqualsPassTypeAndDuration(ticketPass)
                )
                .findFirst()
                .orElse(null);
    }
}
