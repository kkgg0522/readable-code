package cleancode.studycafe.mine2.io;

import cleancode.studycafe.mine2.model.StudyCafeLockerPass;
import cleancode.studycafe.mine2.model.StudyCafePass;
import cleancode.studycafe.mine2.model.StudyCafePassType;

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

    public List<StudyCafePass> readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PASS_FILE_PATH));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(FILE_REGEX);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[PASS_TYPE_INDEX]);
                int duration = Integer.parseInt(values[DURATION_INDEX]);
                int price = Integer.parseInt(values[PRICE_INDEX]);
                double discountRate = Double.parseDouble(values[DISCOUNT_INDEX]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePasses;
        } catch (IOException e) {
            throw new RuntimeException(DONT_FILE_READ, e);
        }
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_FILE_PATH));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(FILE_REGEX);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[PASS_TYPE_INDEX]);
                int duration = Integer.parseInt(values[DURATION_INDEX]);
                int price = Integer.parseInt(values[PRICE_INDEX]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return lockerPasses;
        } catch (IOException e) {
            throw new RuntimeException(DONT_FILE_READ, e);
        }
    }

}
