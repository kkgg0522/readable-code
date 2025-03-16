package cleancode.studycafe.tobetest.model.order;

import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobetest.model.pass.seat.StudyCafeSeatPass;

import java.util.Optional;

public class StudyCafePassOrder {
    private final StudyCafeSeatPass seatPass;
    private final StudyCafeLockerPass lockerPass;


    private StudyCafePassOrder(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        this.seatPass = seatPass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass){
        return new StudyCafePassOrder(seatPass, lockerPass);
    }

    public Optional<StudyCafeLockerPass> getLockerPass() {
        return Optional.ofNullable(lockerPass);
    }

    public StudyCafeSeatPass getSeatPass() {
        return seatPass;
    }

    public int getDiscountPrice() {
        return seatPass.getDiscountPrice();
    }

    public int getTotalPrice() {
        return seatPass.getPrice() - getDiscountPrice() + (lockerPass != null ? lockerPass.getPrice() : 0);

    }
}
