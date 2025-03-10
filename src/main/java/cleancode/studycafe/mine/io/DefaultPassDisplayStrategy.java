package cleancode.studycafe.mine.model;

public enum DefaultPassDisplayStrategy implements PassDisplayStrategy {
    HOURLY(StudyCafePassType.HOURLY) {
        @Override
        public String display(int duration, int price) {
            return String.format("%d시간권 - %d원", duration, price);
        }
    },
    WEEKLY(StudyCafePassType.WEEKLY) {
        @Override
        public String display(int duration, int price) {
            return String.format("%d주권 - %d원", duration, price);
        }
    },
    FIXED(StudyCafePassType.FIXED) {
        @Override
        public String display(int duration, int price) {
            return String.format("%d주권(고정석) - %d원", duration, price);
        }
    },
    NOT_MATCH(StudyCafePassType.FIXED) {
        @Override
        public String display(int duration, int price) {
            return "잘못된 입력입니다.";
        }
    };

    private final StudyCafePassType studyCafePassType;

    DefaultPassDisplayStrategy(StudyCafePassType studyCafePassType) {
        this.studyCafePassType = studyCafePassType;
    }

}
