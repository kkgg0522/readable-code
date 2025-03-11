package cleancode.studycafe.mine2.model;

import cleancode.studycafe.mine2.model.ffc.LockerAvailableTypes;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;
    private final LockerAvailableTypes lockerAvailableTypes = new LockerAvailableTypes();

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    private StudyCafePass(StudyCafePass studyCafePass) {
        this.passType = studyCafePass.getPassType();
        this.duration = studyCafePass.getDuration();
        this.price = studyCafePass.getPrice();
        this.discountRate = studyCafePass.getDiscountRate();
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public static StudyCafePass from(StudyCafePass studyCafePass) {
        return new StudyCafePass(studyCafePass);
    }

    private StudyCafePassType getPassType() {
        return passType;
    }

    private int getDuration() {
        return duration;
    }

    private int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

    public boolean isNotAvailableLockerType() {
        return lockerAvailableTypes.isNotAvailableLockerType(this.passType);
    }

    public boolean isDurationEqualsTo(StudyCafeLockerPass lockerPass) {
        return lockerPass.isDurationEqualsTo(this.duration);
    }

    public boolean isTypeEqualsTo(StudyCafeLockerPass lockerPass) {
        return lockerPass.isTypeEqualsTo(this.passType);
    }

    public boolean isTypeEquals(StudyCafePassType studyCafePassType) {
        return this.passType == studyCafePassType;
    }
}
