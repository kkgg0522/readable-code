package cleancode.studycafe.mine.model;

public class StudyCafeTicketPass extends StudyCafePass{

    private final double discountRate;

    private StudyCafeTicketPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        super(passType, duration, price);
        this.discountRate = discountRate;
    }

    public static StudyCafeTicketPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeTicketPass(passType, duration, price, discountRate);
    }

    public int getDiscountPrice() {
        return super.getDiscountPrice(discountRate);
    }

    public int getTotalPrice(StudyCafePass lockerPass) {
        int discountPrice = getDiscountPrice();
        int totalPrice = getPrice();
        if(lockerPass != null){
            totalPrice = lockerPass.addPrice(totalPrice);
        }

        return totalPrice - discountPrice;
    }
}
