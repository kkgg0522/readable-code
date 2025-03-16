package cleancode.studycafe.tobetest.model.pass.locker;

import cleancode.studycafe.tobetest.model.pass.seat.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes){
        return new StudyCafeLockerPasses(passes);
    }

    public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafeSeatPass pass){
        return lockerPasses.stream()
                .filter(pass::isSameDurationType
                )
                .findFirst();
    }
}
