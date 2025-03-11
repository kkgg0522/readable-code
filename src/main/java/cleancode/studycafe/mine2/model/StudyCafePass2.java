package cleancode.studycafe.mine2.model;

import cleancode.studycafe.mine2.model.ffc.LockerAvailableTypes;

public class StudyCafePass2 {
    private final StudyCafePassInfo passInfo;
    private final double discountRate;

    private StudyCafePass2(StudyCafePassInfo status, double discountRate) {
        this.passInfo = status;
        this.discountRate = discountRate;
    }

    public static StudyCafePass2 of(StudyCafePassInfo passInfo, double discountRate) {
        return new StudyCafePass2(passInfo, discountRate);
    }

    public static StudyCafePass2 from(StudyCafePass2 studyCafePass) {
        return new StudyCafePass2(studyCafePass.getPassInfo(), studyCafePass.getDiscountRate());
    }

    private StudyCafePassInfo getPassInfo(){
        return StudyCafePassInfo.from(this.passInfo);
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
