package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.model.StudyCafePass;

public interface PassDisplayStrategy {
    String display(StudyCafePass studyCafePass);

    boolean supports(StudyCafePass studyCafePass);
}
