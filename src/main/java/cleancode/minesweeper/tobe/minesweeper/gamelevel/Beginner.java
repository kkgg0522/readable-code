package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Beginner implements GameLevel {

    @Override
    public int getRowSize() {
        return 8;
    }

    @Override
    public int getColSIze() {
        return 10;
    }

    @Override
    public int getLandMineCount() {
        return 10;
    }
}
