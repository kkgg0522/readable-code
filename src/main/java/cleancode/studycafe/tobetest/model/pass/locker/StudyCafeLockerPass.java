package cleancode.studycafe.tobetest.model.pass.locker;

import cleancode.studycafe.tobetest.model.pass.StudyCafePass;
import cleancode.studycafe.tobetest.model.pass.StudyCafePassType;

import java.util.Objects;

public class StudyCafeLockerPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
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



    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    public boolean isSameDuration(int duration) {
        return this.duration == duration;
    }

    public boolean isNotSame(StudyCafeLockerPass studyCafeLockerPass2) {
        return !this.equals(studyCafeLockerPass2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyCafeLockerPass that = (StudyCafeLockerPass) o;
        return getDuration() == that.getDuration() && getPrice() == that.getPrice() && getPassType() == that.getPassType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassType(), getDuration(), getPrice());
    }
}
