package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.*;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class GameApplication {
    public static void main(String[] args) {
        GameLevel veryBeginner = new VeryBeginner();
        GameLevel beginner = new Beginner();
        GameLevel middle = new Middle();
        GameLevel advanced = new Advanced();

        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        Minesweeper minesweeper = new Minesweeper(beginner,inputHandler,outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }
}
