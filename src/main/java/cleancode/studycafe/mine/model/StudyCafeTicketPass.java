package cleancode.studycafe.mine.model;

import java.util.ArrayList;
import java.util.List;

public class StudyCafeTicketPass extends StudyCafePass{

    private final double discountRate;

    private final static List<StudyCafePassType> hasLockerType = new ArrayList<>(
                List.of(StudyCafePassType.FIXED)
            );

    private StudyCafeTicketPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        super(passType, duration, price);
        this.discountRate = discountRate;
    }

    public static StudyCafeTicketPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeTicketPass(passType, duration, price, discountRate);
    }

    public boolean containsLockerType() {
        return hasLockerType.contains(getPassType());

    }

    public int getDiscountPrice() {
        return super.getDiscountPrice(discountRate);
    }

    public int getTotalPrice(StudyCafePass lockerPass) {
        int discountPrice = getDiscountPrice();
        int totalPrice = getPrice();

        totalPrice = lockerPass.addPrice(totalPrice);

        return totalPrice - discountPrice;
    }

    public int getTotalPrice() {
        int discountPrice = getDiscountPrice();
        int totalPrice = getPrice();

        return totalPrice - discountPrice;
    }




}
