package cleancode.studycafe.mine2.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석"),
    NOT_MATCH("잘못 된 입력");

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

}
