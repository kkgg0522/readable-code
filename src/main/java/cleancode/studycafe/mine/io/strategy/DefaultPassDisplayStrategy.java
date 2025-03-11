package cleancode.studycafe.mine.io.strategy;

import cleancode.studycafe.mine.model.StudyCafePassType;
import cleancode.studycafe.mine.model.StudyCafePassInfo;

import java.util.Arrays;

public enum DefaultPassDisplayStrategy implements PassDisplayStrategy {
    HOURLY(StudyCafePassType.HOURLY) {
        @Override
        public String display(StudyCafePassInfo passInfo) {
            return String.format("%d시간권 - %d원", passInfo.getDuration(), passInfo.getPrice());
        }
    },
    WEEKLY(StudyCafePassType.WEEKLY) {
        @Override
        public String display(StudyCafePassInfo passInfo) {
            return String.format("%d주권 - %d원", passInfo.getDuration(), passInfo.getPrice());
        }
    },
    FIXED(StudyCafePassType.FIXED) {
        @Override
        public String display(StudyCafePassInfo passInfo) {
            return String.format("%d주권 - %d원", passInfo.getDuration(), passInfo.getPrice());
        }
    },
    NOT_MATCH(StudyCafePassType.NOT_MATCH) {
        @Override
        public String display(StudyCafePassInfo passInfo) {
            return "";
        }
    };

    private final StudyCafePassType passType;

    DefaultPassDisplayStrategy(StudyCafePassType passType) {
        this.passType = passType;
    }

    @Override
    public boolean supports(StudyCafePassInfo passInfo) {
        return passInfo.isTypeEquals(passType);
    }

    public static String displayByType(StudyCafePassInfo passInfo){
        DefaultPassDisplayStrategy strategy = getStrategyBy(passInfo);

        return strategy.display(passInfo);

    }

    public static DefaultPassDisplayStrategy getStrategyBy(StudyCafePassInfo passInfo){
        return Arrays.stream(values())
                .filter(display -> display.supports(passInfo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
