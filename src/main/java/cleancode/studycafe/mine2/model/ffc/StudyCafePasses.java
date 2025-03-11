package cleancode.studycafe.mine2.model.ffc;

import cleancode.studycafe.mine2.model.StudyCafePass;
import cleancode.studycafe.mine2.model.StudyCafePassType;

import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePass) {
        this.studyCafePasses = studyCafePass;
    }

    public static StudyCafePasses from(List<StudyCafePass> studyCafePass){
        return new StudyCafePasses(studyCafePass);
    }

    public StudyCafePasses getStudyCafePassCondition(StudyCafePassType studyCafePassType){
        return from(studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isTypeEquals(studyCafePassType))
                .toList());
    }

    public int size() {
        return studyCafePasses.size();
    }

    public StudyCafePass get(int index) {
        return StudyCafePass.from(studyCafePasses.get(index));
    }

    public boolean hasIndex(int selectIndex) {
        return size() > selectIndex;
    }
}
