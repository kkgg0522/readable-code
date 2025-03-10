package cleancode.studycafe.mine.io;

import cleancode.studycafe.mine.model.StudyCafePass;
import cleancode.studycafe.mine.model.StudyCafePassType;

import java.util.Arrays;

public enum DefaultPassDisplayStrategy implements PassDisplayStrategy {
    HOURLY(StudyCafePassType.HOURLY) {
        @Override
        public String display(StudyCafePass studyCafePass) {
            return String.format("%d시간권 - %d원", studyCafePass.getDuration(), studyCafePass.getPrice());
        }
    },
    WEEKLY(StudyCafePassType.WEEKLY) {
        @Override
        public String display(StudyCafePass studyCafePass) {
            return String.format("%d주권 - %d원", studyCafePass.getDuration(), studyCafePass.getPrice());
        }
    },
    FIXED(StudyCafePassType.FIXED) {
        @Override
        public String display(StudyCafePass studyCafePass) {
            return String.format("%d주권 - %d원", studyCafePass.getDuration(), studyCafePass.getPrice());
        }
    },
    NOT_MATCH(StudyCafePassType.NOT_MATCH) {
        @Override
        public String display(StudyCafePass studyCafePass) {
            return "잘못된 입력입니다.";
        }
    };

    private final StudyCafePassType studyCafePassType;

    DefaultPassDisplayStrategy(StudyCafePassType studyCafePassType) {
        this.studyCafePassType = studyCafePassType;
    }

    @Override
    public boolean supports(StudyCafePass studyCafePass) {
        return studyCafePass.isEqualsType(studyCafePassType);
    }

    public static String getDisplayBypassType(StudyCafePass studyCafePass){
        DefaultPassDisplayStrategy strategy = findBy(studyCafePass);
        return strategy.display(studyCafePass);
    }


    private static DefaultPassDisplayStrategy findBy(StudyCafePass studyCafePass) {
        return Arrays.stream(values())
                .filter(s -> s.supports(studyCafePass))
                .findFirst()
                .orElse(NOT_MATCH);
    }
}
