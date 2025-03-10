package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.model.StudyCafeTicketPass;
import cleancode.studycafe.mine.model.StudyCafePassType;
import cleancode.studycafe.mine.model.StudyCafeTicketPasses;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        return StudyCafePassType.NOT_MATCH;
    }

    public int getSelectPassIndexFromUser() {
        String userInput = SCANNER.nextLine();
        return Integer.parseInt(userInput) - 1;
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
