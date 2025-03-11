package cleancode.studycafe.mine.model;


import cleancode.studycafe.mine.io.strategy.DefaultPassDisplayStrategy;

import java.util.List;

public class StudyCafePassInfo {
    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final List<StudyCafePassType> lockerAvailableType = List.of(
            StudyCafePassType.FIXED
    );


    private StudyCafePassInfo(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafePassInfo of(StudyCafePassType passType, int duration, int price){
        return new StudyCafePassInfo(passType, duration, price);
    }

    public static StudyCafePassInfo from(StudyCafePassInfo studyCafePassInfo){
        return new StudyCafePassInfo(studyCafePassInfo.getPassType(),studyCafePassInfo.getDuration(), studyCafePassInfo.getPrice());
    }



    public String display() {
        return DefaultPassDisplayStrategy.displayByType(this);
    }


    public boolean isNotAvailableLockerType() {
        return !lockerAvailableType.contains(passType);
    }

    public boolean isTypeEquals(StudyCafePassType studyCafePassType) {
        return this.passType == studyCafePassType;
    }



    public boolean isDurationAndTypeEqualsTo(StudyCafePassInfo passInfo) {
        return isTypeEqualsTo(passInfo) && isDurationEqualsTo(passInfo);
    }

    private boolean isTypeEqualsTo(StudyCafePassInfo passInfo) {
        return this.passType == passInfo.passType;
    }

    private boolean isDurationEqualsTo(StudyCafePassInfo passInfo) {
        return this.duration == passInfo.duration;
    }

    public int getDiscountPrice(double discountRate) {
        return (int) (price * discountRate);
    }

    public int getTotalDiscountPrice(int discountPrice){
        return price - discountPrice;
    }

    public int getTotalPrice(int disCountTotalPrice) {
        return price + disCountTotalPrice;
    }

    private StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }
}
