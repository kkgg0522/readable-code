package cleancode.studycafe.mine2.model.ffc;

import cleancode.studycafe.mine2.model.StudyCafePassType;

import java.util.List;

public class LockerAvailableTypes {

    private final List<StudyCafePassType> lockerAvailableType = List.of(
            StudyCafePassType.FIXED
    );

    public boolean isNotAvailableLockerType(StudyCafePassType passType) {
        return !lockerAvailableType.contains(passType);
    }
}
