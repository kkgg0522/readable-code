package cleancode.studycafe.mine.io.strategy;

import cleancode.studycafe.mine.model.StudyCafePassInfo;

public interface PassDisplayStrategy {
    String display(StudyCafePassInfo passInfo);

    boolean supports(StudyCafePassInfo passInfo);
}
