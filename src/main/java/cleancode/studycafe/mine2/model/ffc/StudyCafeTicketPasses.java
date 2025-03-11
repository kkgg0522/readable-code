package cleancode.studycafe.mine2.model.ffc;

import cleancode.studycafe.mine2.model.StudyCafePass;
import cleancode.studycafe.mine2.model.StudyCafePassType;

import java.util.List;

public class StudyCafeTicketPasses {
    private final List<StudyCafePass> studyCafePasses;

    private StudyCafeTicketPasses(List<StudyCafePass> studyCafeTicketPass) {
        this.studyCafePasses = studyCafeTicketPass;
    }

    public static StudyCafeTicketPasses from(List<StudyCafePass> studyCafeTicketPass){
        return new StudyCafeTicketPasses(studyCafeTicketPass);
    }

    public StudyCafeTicketPasses getStudyCafeTicketPassCondition(StudyCafePassType studyCafePassType){
        return from(studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isTypeEquals(studyCafePassType))
                .toList());
    }

    public int size() {
        return studyCafePasses.size();
    }

    public StudyCafePass get(int index) {
        return studyCafePasses.get(index);
    }
}
