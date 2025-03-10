package cleancode.studycafe.mine.model;

public class StudyCafeTicketPass {

    private final StudyCafePass studyCafePass;
    private final double discountRate;

    private StudyCafeTicketPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.studyCafePass = StudyCafePass.of(passType, duration,  price);
        this.discountRate = discountRate;
    }

    public static StudyCafeTicketPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeTicketPass(passType, duration, price, discountRate);
    }

    public StudyCafePassType getPassType() {
        return studyCafePass.getPassType();
    }

    public int getDuration() {
        return studyCafePass.getDuration();
    }

    public int getPrice() {
        return studyCafePass.getPrice();
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public boolean isEqualsPassType(StudyCafePassType passType){
        return this.getPassType() == passType;
    }

    public String display() {
        return studyCafePass.display();
    }

}
