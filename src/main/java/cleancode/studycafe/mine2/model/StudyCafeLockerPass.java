package cleancode.studycafe.mine2.model;

public class StudyCafeLockerPass {

    private final StudyCafePassInfo passInfo;

    private StudyCafeLockerPass(StudyCafePassInfo passInfo) {
        this.passInfo = passInfo;
    }

    public static StudyCafeLockerPass of(StudyCafePassInfo passInfo) {
        return new StudyCafeLockerPass(passInfo);
    }

    public boolean isDurationAndTypeEqualsTo(StudyCafePassInfo passInfo) {
        return passInfo.isDurationAndTypeEqualsTo(passInfo);
    }

    public String display() {
        return passInfo.display();
    }

    public int getTotalPrice(int disCountTotalPrice) {
        return passInfo.getTotalPrice(disCountTotalPrice);
    }

}
