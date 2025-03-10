package cleancode.studycafe.mine.model;

import cleancode.studycafe.mine.io.DefaultPassDisplayStrategy;

public abstract class StudyCafePass {
    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    protected int addPrice(int price) {
        return this.price + price;
    }

    protected StudyCafePass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public boolean isEqualsType(StudyCafePassType studyCafePassType) {
        return this.getPassType() == studyCafePassType;
    }

    private boolean isEqualsDuration(int duration) {
        return this.duration == duration;
    }

    public boolean isEqualsPassTypeAndDuration(StudyCafePass studyCafePass) {
        return isEqualsType(studyCafePass.getPassType()) && isEqualsDuration(studyCafePass.getDuration());
    }

    public String display() {
        return DefaultPassDisplayStrategy.getDisplayBypassType(this);
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
        int price = getPrice();
        return (int) (price - (price * (1 - discountRate)));
    }

}
