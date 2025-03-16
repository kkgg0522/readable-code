package cleancode.studycafe.tobetest.io;

import cleancode.studycafe.tobetest.exception.AppException;
import cleancode.studycafe.tobetest.model.pass.StudyCafePassType;
import cleancode.studycafe.tobetest.model.pass.seat.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputHandlerTest {
    @Test
    @DisplayName("1을 입력 받을 시 HOURLY Type을 정상으로 반환해줘야 한다.")
    void getPassTypeSelectingUserAction() {
        //GIVEN
        InputHandler inputHandler = new InputHandler();
        StudyCafePassType hourlyType = StudyCafePassType.HOURLY;

        //WHEN
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction("1");

        //Then
        assertThat(passType).isEqualTo(hourlyType);
    }

    @Test
    @DisplayName("비정상 입력 시 AppExeption이 발생해야 한다.")
    void exceptionGetPassTypeSelectingUserAction() {
        //GIVEN
        InputHandler inputHandler = new InputHandler();

        //WHEN, Then
        assertThatThrownBy(() ->
                inputHandler.getPassTypeSelectingUserAction("4"))
                .isInstanceOf(AppException.class)
                .hasMessage("잘못된 입력입니다.");

    }

    /*
    @Test
    @DisplayName("바람직하지 않은 테스트 Case " +
            "문제점: 파일 내용을 읽어옴 -> 파일 내용 변경 시 실패하는 Case" +
            "의문점: DB 내용을 읽어 테스트 하는 경우는 어떻게 해야하나?? (File -> DB)")
    void getSelectPassV1() {
        //GIVEN
        InputHandler inputHandler = new InputHandler();
        SeatPassProvider seatPassProvider = new SeatPassFileReader();


        StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();
        StudyCafePassType hourlyType = StudyCafePassType.HOURLY;

        List<StudyCafeSeatPass> passBy = seatPasses.findPassBy(hourlyType);


        //파일 내용 변경 시 해당 테스트 실패 함
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY,2,4000,0);

        //WHEN
        StudyCafeSeatPass selectPass = inputHandler.getSelectPass(passBy,"1");

        //Then
        assertThat(seatPass).isEqualTo(selectPass);
    }
*/

    @Test
    @DisplayName("입력-1의 내용을 읽어와야 한다.")
    void getSelectPassV2() {
        //GIVEN
        InputHandler inputHandler = new InputHandler();

        StudyCafeSeatPass seatPass1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0);
        StudyCafeSeatPass seatPass2 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6000, 0);

        List<StudyCafeSeatPass> passBy = List.of(seatPass1, seatPass2);

        //WHEN
        StudyCafeSeatPass selectPass = inputHandler.getSelectPass(passBy, "1");

        //Then
        assertThat(selectPass).isEqualTo(seatPass1);
    }

    @Test
    @DisplayName("사용자 입력이 사용한다에 해당하지 않을 시 False여야 한다.")
    void getLockerSelection() {
        //GIVEN
        InputHandler inputHandler = new InputHandler();

        //WHEN
        boolean lockerSelection = inputHandler.getLockerSelection("aa");

        //Then
        assertThat(lockerSelection).isFalse();
    }
}