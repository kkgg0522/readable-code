package cleancode.studycafe.tobetest.model.order;

import cleancode.studycafe.tobetest.model.pass.StudyCafePassType;
import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobetest.model.pass.seat.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;


class StudyCafePassOrderTest {

    @DisplayName("주문 시 적용된 할인 가격을 정확히 계산해야 한다.")
    @Test
    void getDiscountPrice() {
        //GIVEN
        int price = 250000;
        double discountRate = 0.1;
        int discountPrice = (int) (price * discountRate);

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 2, price, discountRate);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED,2,30000);

        StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        //WHEN
        int orderDiscountPrice = studyCafePassOrder.getDiscountPrice();
        //Then
        assertThat(orderDiscountPrice).isEqualTo(discountPrice);
    }

    @DisplayName("주문 시 전체 가격을 정확히 계산해야 한다.")
    @Test
    void getTotalPrice() {
        //GIVEN
        int seatPrice = 250000;
        double discountRate = 0.1;
        int discountPrice = (int) (seatPrice * discountRate);

        int lockerPrice = 30000;

        int customTotalPrice = seatPrice - discountPrice + lockerPrice;

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 2, seatPrice, discountRate);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED,2,lockerPrice);

        StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(seatPass, lockerPass);

        //WHEN
        int totalPrice = studyCafePassOrder.getTotalPrice();

        //Then
        assertThat(totalPrice).isEqualTo(customTotalPrice);

    }
}