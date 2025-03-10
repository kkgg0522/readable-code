package cleancode.studycafe.mine.model;

import java.util.List;

public class StudyCafeTicketPasses {
    private final List<StudyCafeTicketPass> studyCafeTicketPasses;

    private StudyCafeTicketPasses(List<StudyCafeTicketPass> studyCafeTicketPasses) {
        this.studyCafeTicketPasses = studyCafeTicketPasses;
    }

    public static StudyCafeTicketPasses from(List<StudyCafeTicketPass> studyCafeTicketPasses){
        return new StudyCafeTicketPasses(studyCafeTicketPasses);
    }

    public StudyCafeTicketPasses find(StudyCafePassType studyCafePassType){
        return from(studyCafeTicketPasses.stream()
                .filter(studyCafeTicketPass -> studyCafeTicketPass.isEqualsPassType(studyCafePassType))
                .toList());
    }

    public int size() {
        return this.studyCafeTicketPasses.size();
    }

    public StudyCafeTicketPass getIndex(int index){
        return studyCafeTicketPasses.get(index);
    }

    public boolean hasIndex(int index){
        return size() > index;
    }
}
