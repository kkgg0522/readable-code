package cleancode.studycafe.mine2.model;

import cleancode.studycafe.mine2.model.ffc.LockerAvailableTypes;
import cleancode.studycafe.mine2.model.vo.Price;

public class StudyCafePassInfo {
    private final StudyCafePassType passType;
    private final int duration;
    private final Price price;
    private final LockerAvailableTypes lockerAvailableTypes = new LockerAvailableTypes();


    private StudyCafePassInfo(StudyCafePassType passType, int duration, Price price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafePassInfo of(StudyCafePassType passType, int duration, Price price){

    }

    public static StudyCafePassInfo from(StudyCafePassInfo studyCafePassInfo){
        return new StudyCafePassInfo(studyCafePassInfo.getPassType(),studyCafePassInfo.getDuration(), studyCafePassInfo.getPrice());
    }

    private StudyCafePassType getPassType() {
        return passType;
    }

    private int getDuration() {
        return duration;
    }

    private Price getPrice() {
        return price;
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price.getPrice());
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price.getPrice());
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price.getPrice());
        }
        return "";
    }

    public boolean isNotAvailableLockerType() {
        return lockerAvailableTypes.isNotAvailableLockerType(this.passType);
    }

    public boolean isTypeEquals(StudyCafePassType studyCafePassType) {
        return this.passType == studyCafePassType;
    }
}
