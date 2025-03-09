package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

public interface InputHandler {

    String getUserInput();

    UserAction getUserActionInputFromUser();

    CellPosition getCellPositionFromUser();
}
