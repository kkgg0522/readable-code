package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.*;

public class GameApplication {
    public static void main(String[] args) {
        GameLevel veryBeginner = new VeryBeginner();
        GameLevel beginner = new Beginner();
        GameLevel middle = new Middle();
        GameLevel advanced = new Advanced();
        Minesweeper minesweeper = new Minesweeper(advanced);
        minesweeper.run();
    }
}
