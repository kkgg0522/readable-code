package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.model.StudyCafePassType;

import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String HOURLY_NUMBER = "1";
    public static final String WEEKLY_NUMBER = "2";
    public static final String FIXED_NUMBER = "3";
    public static final String LOCKER_USE_NUMBER = "1";

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if (HOURLY_NUMBER.equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if (WEEKLY_NUMBER.equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if (FIXED_NUMBER.equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        return StudyCafePassType.NOT_MATCH;
    }

    public int getSelectPass() {
        String userInput = SCANNER.nextLine();
        return Integer.parseInt(userInput) - 1;
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return LOCKER_USE_NUMBER.equals(userInput);
    }

}
