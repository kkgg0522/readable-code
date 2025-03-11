package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.model.StudyCafeLockerPass;
import cleancode.studycafe.mine.model.StudyCafePass;
import cleancode.studycafe.mine.model.ffc.StudyCafePasses;

public class OutputHandler {

    public void showWelcomeMessage(){
        showWelcomeTitle();
        showAnnouncement();
    }

    public void showWelcomeTitle() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showPassListForSelection(StudyCafePasses studyCafePasses) {
        System.out.println();
        System.out.println("이용권 목록");
        for (int index = 0; index < studyCafePasses.size(); index++) {
            StudyCafePass pass = studyCafePasses.get(index);
            System.out.println(String.format("%s. ", index + 1) + pass.display());
        }
    }

    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            lockerPass.display()
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }
    public void showPassOrderSummary(StudyCafePass selectedPass) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + selectedPass.display());


        int discountPrice = selectedPass.getDiscountPrice();
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = selectedPass.getTotalDiscountPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + selectedPass.display());
        System.out.println("사물함: " + lockerPass.display());


        int discountPrice = selectedPass.getDiscountPrice();
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalDiscountPrice = selectedPass.getTotalDiscountPrice();
        int totalPrice = lockerPass.getTotalPrice(totalDiscountPrice);
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

    public void showUserInputNotMatch() {
        System.out.println("잘못된 입력입니다. 다시 시도해주세요");
    }

    public void showNotCatchExceptionMessage() {
        System.out.println("알 수 없는 오류가 발생했습니다.");
    }
}
