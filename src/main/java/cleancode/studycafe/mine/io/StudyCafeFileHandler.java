package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.model.StudyCafeLockerPass;
import cleancode.studycafe.mine.model.StudyCafeTicketPass;
import cleancode.studycafe.mine.model.StudyCafePassType;
import cleancode.studycafe.mine.model.fcc.StudyCafeLockerPasses;
import cleancode.studycafe.mine.model.fcc.StudyCafeTicketPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    public static final String REGEX = ",";
    public static final String LOCKER_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    public static final String TICKET_CSV_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    public static final String FILE_READ_FAILED = "파일을 읽는데 실패했습니다.";

    public static StudyCafeTicketPasses readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(TICKET_CSV_PATH));
            List<StudyCafeTicketPass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(REGEX);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafeTicketPass studyCafePass = StudyCafeTicketPass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return StudyCafeTicketPasses.from(studyCafePasses);
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_FAILED, e);
        }
    }


    public static StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_CSV_PATH));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(REGEX);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);

                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return StudyCafeLockerPasses.from(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_FAILED, e);
        }
    }

}
