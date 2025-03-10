package cleancode.studycafe.mine.model;

public class StudyCafeLockerPass {

    private final StudyCafePass studyCafePass;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        this.studyCafePass = StudyCafePass.of(passType, duration, price);
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }

    public StudyCafePassType getPassType() {
        return studyCafePass.getPassType();
    }

    public int getDuration() {
        return studyCafePass.getDuration();
    }

    public int getPrice() {
        return studyCafePass.getDuration();
    }

    public String display() {
        return studyCafePass.display();
    }

}
