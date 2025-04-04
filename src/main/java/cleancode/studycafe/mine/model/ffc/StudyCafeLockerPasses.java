package cleancode.studycafe.mine.model.ffc;

import cleancode.studycafe.mine.model.StudyCafeLockerPass;
import cleancode.studycafe.mine.model.StudyCafePass;

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

    public Optional<StudyCafeLockerPass> getLockerPassCondition(StudyCafePass selectPass){
        return lockerPasses.stream()
                .filter(selectPass::isDurationAndTypeEqualsTo)
                .findFirst();

    }
}
