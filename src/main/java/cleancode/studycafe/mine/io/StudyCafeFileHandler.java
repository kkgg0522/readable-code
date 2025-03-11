package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.model.*;
import cleancode.studycafe.mine.model.ffc.StudyCafeLockerPasses;
import cleancode.studycafe.mine.model.ffc.StudyCafePasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    public static final int PASS_TYPE_INDEX = 0;
    public static final int DURATION_INDEX = 1;
    public static final int PRICE_INDEX = 2;
    public static final int DISCOUNT_INDEX = 3;
    public static final String FILE_REGEX = ",";
    public static final String PASS_FILE_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    public static final String LOCKER_FILE_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    public static final String DONT_FILE_READ = "파일을 읽는데 실패했습니다.";

    public StudyCafePasses readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PASS_FILE_PATH));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(FILE_REGEX);

                StudyCafePassInfo passInfo = getPassInfo(values);

                double discountRate = Double.parseDouble(values[DISCOUNT_INDEX]);

                StudyCafePass studyCafePass = StudyCafePass.of(passInfo, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return StudyCafePasses.from(studyCafePasses);
        } catch (IOException e) {
            throw new RuntimeException(DONT_FILE_READ, e);
        }
    }

    private StudyCafePassInfo getPassInfo(String[] values) {
        return StudyCafePassInfo.of(StudyCafePassType.valueOf(values[PASS_TYPE_INDEX]), Integer.parseInt(values[DURATION_INDEX]), Integer.parseInt(values[PRICE_INDEX]));
    }

    public StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_FILE_PATH));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(FILE_REGEX);

                StudyCafePassInfo passInfo = getPassInfo(values);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passInfo);

                lockerPasses.add(lockerPass);
            }

            return StudyCafeLockerPasses.from(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException(DONT_FILE_READ, e);
        }
    }

}
