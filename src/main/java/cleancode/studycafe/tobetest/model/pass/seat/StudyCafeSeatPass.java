package cleancode.studycafe.tobetest.model.pass.seat;

import cleancode.studycafe.tobetest.model.pass.StudyCafePass;
import cleancode.studycafe.tobetest.model.pass.StudyCafePassType;
import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPass;

import java.util.Objects;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }


    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSamePassType(this.passType)
        && lockerPass.isSameDuration(this.duration);
    }


    public boolean cannotUseLocker() {
        return this.passType.isNotLockerType();
    }

    public int getDiscountPrice() {
        return (int) (this.price * this.discountRate);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyCafeSeatPass seatPass = (StudyCafeSeatPass) o;
        return getDuration() == seatPass.getDuration() && getPrice() == seatPass.getPrice() && Double.compare(discountRate, seatPass.discountRate) == 0 && getPassType() == seatPass.getPassType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassType(), getDuration(), getPrice(), discountRate);
    }
}
