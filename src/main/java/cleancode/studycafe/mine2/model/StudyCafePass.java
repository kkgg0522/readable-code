package cleancode.studycafe.mine2.model;


public class StudyCafePass {
    private final StudyCafePassInfo passInfo;
    private final double discountRate;

    private StudyCafePass(StudyCafePassInfo status, double discountRate) {
        this.passInfo = status;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassInfo passInfo, double discountRate) {
        return new StudyCafePass(passInfo, discountRate);
    }

    public static StudyCafePass from(StudyCafePass studyCafePass) {
        return new StudyCafePass(studyCafePass.getPassInfo(), studyCafePass.getDiscountRate());
    }

    private StudyCafePassInfo getPassInfo(){
        return StudyCafePassInfo.from(this.passInfo);
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public boolean isTypeEquals(StudyCafePassType studyCafePassType) {
        return passInfo.isTypeEquals(studyCafePassType);
    }

    public boolean isDurationAndTypeEqualsTo(StudyCafeLockerPass lockerPass) {
        return lockerPass.isDurationAndTypeEqualsTo(passInfo);
    }

    public String display() {
        return passInfo.display();
    }


    public boolean isNotAvailableLockerType() {
        return passInfo.isNotAvailableLockerType();
    }

    public int getDiscountPrice() {
        return passInfo.getDiscountPrice(discountRate);
    }

    public int getTotalDiscountPrice() {
        return passInfo.getTotalDiscountPrice(getDiscountPrice());
    }
}
