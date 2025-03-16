package cleancode.studycafe.tobetest.io.provider;

import cleancode.studycafe.tobetest.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobetest.provider.LockerPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LockerPassFileReaderTest {
    private static final String PASS_LIST_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    @DisplayName("두 CSV 파일 경로는 같다. 따라서 두 리스트는 같아야 한다.")
    @Test
    void getLockerPasses() {
        //GIVEN
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();

        StudyCafeLockerPasses lockerPasses = lockerPassProvider.getLockerPasses();
        StudyCafeLockerPasses lockerPassesByPath = lockerPassProvider.getLockerPasses(PASS_LIST_CSV_PATH);

        //WHEN
        boolean samePasses = lockerPasses.isSamePasses(lockerPassesByPath);

        //Then
        assertThat(samePasses).isTrue();
    }

    @DisplayName("경로가 정상적이지 않으면 예외가 발생해야한다.")
    @Test
    public void exceptionGetLockerPasses() throws Exception {
        //GIVEN
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();

        //WHEN, Then
        assertThatThrownBy(() ->
                lockerPassProvider.getLockerPasses(PASS_LIST_CSV_PATH + "aaaa"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("파일을 읽는데 실패했습니다.");
    }
}