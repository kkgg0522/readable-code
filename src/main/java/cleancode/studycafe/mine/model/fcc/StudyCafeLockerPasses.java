package cleancode.studycafe.mine.model;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses from(List<StudyCafeLockerPass> lockerPasses){
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public StudyCafeLockerPass find(StudyCafeTicketPass cafePass){
        if(cafePass.getPassType() != StudyCafePassType.FIXED) {
            return null;
        }

        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == cafePass.getPassType()
                                && option.getDuration() == cafePass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }
}
