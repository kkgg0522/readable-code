package cleancode.studycafe.mine.model;

import cleancode.studycafe.mine.io.DefaultPassDisplayStrategy;

public class StudyCafePass {
    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    protected StudyCafePass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafePass(passType, duration, price);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    protected int getDiscountPrice(double discountRate) {
        return (int) (getPrice() * (1 - discountRate));
    }


    public String display() {
        return DefaultPassDisplayStrategy.getDisplayBypassType(this);
    }

    public boolean isEqualsType(StudyCafePassType studyCafePassType) {
        return this.getPassType() == studyCafePassType;
    }

    private boolean isEqualsDuration(int duration) {
        return this.duration == duration;
    }

    protected int addPrice(int price) {
        return this.price + price;
    }

    public boolean isEqualsPassTypeAndDuration(StudyCafePass studyCafePass) {
        return isEqualsType(studyCafePass.getPassType()) && isEqualsDuration(studyCafePass.getDuration());
    }

}
