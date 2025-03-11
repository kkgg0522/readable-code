package cleancode.studycafe.mine2.io.strategy;

import cleancode.studycafe.mine2.model.StudyCafePassInfo;

public interface PassDisplayStrategy {
    String display(StudyCafePassInfo passInfo);

    boolean supports(StudyCafePassInfo passInfo);
}
