package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private final Cell[][] board;
    private final int landMineCount;

    public GameBoard(GameLevel gameLevel) {
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSIze();
        board = new Cell[colSize][rowSize];

        landMineCount = gameLevel.getLandMineCount();
    }

    public void initializeGame() {
        int rowSize = getRowSize();
        int colSize = getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = new EmptyCell();
            }
        }

        for (int i = 0; i < landMineCount; i++) {
            int landMineRow = new Random().nextInt(rowSize);
            int landMineCol = new Random().nextInt(colSize);

            board[landMineRow][landMineCol] = new LandMineCell();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {

                if (isLandMineCell(row, col)) {
                    continue;
                }

                int count = countNearbyLandMines(row, col);

                if(count == 0) continue;

                board[row][col] = new NumberCell(count);

            }
        }
    }

    public int getRowSize(){
        return board.length;
    }

    public int getColSize(){
        return board[0].length;
    }

    public String getSign(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        return cell.getSign();
    }

    public Cell findCell(int row, int col) {
        return board[row][col];
    }

    public boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        Cell cell = findCell(selectedRowIndex,selectedColIndex);
        return cell.isLandMine();
    }

    public void flag(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex,colIndex);
        cell.flag();
    }

    public void open(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex,colIndex);
        cell.open();
    }

    public void openSurroundedCells(int row, int col) {
        if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
            return;
        }
        if (isOpenedCell(row, col)) {
            return;
        }
        if (isLandMineCell(row, col)) {
            return;
        }

        open(row,col);

        if (doesCellHaveLandMineCount(row, col)) {
//            BOARD[row][col] = Cell.ofNearbyLandMineCount(NEARBY_LAND_MINE_COUNTS[row][col]);
            return;
        }

        openSurroundedCells(row - 1, col - 1);
        openSurroundedCells(row - 1, col);
        openSurroundedCells(row - 1, col + 1);
        openSurroundedCells(row, col - 1);
        openSurroundedCells(row, col + 1);
        openSurroundedCells(row + 1, col - 1);
        openSurroundedCells(row + 1, col);
        openSurroundedCells(row + 1, col + 1);
    }

    public boolean isAllCellChecked() {
        return Arrays.stream(board) //Stream<String[]>
                .flatMap(Arrays::stream) //Stream<String>
                .allMatch(Cell::isChecked);
        /*
        //        return Arrays.stream(BOARD) //Stream<String[]>
//                .flatMap(Arrays::stream) //Stream<String>
//                .noneMatch(CLOSE_CELL_SIGN::equals);

//                .noneMatch(cell -> CLOSE_CELL_SIGN.equals(cell));


//        return Arrays.stream(BOARD)
//                .flatMap(stringArr -> Arrays.stream(stringArr))
//                .noneMatch(cell -> cell.equals(CLOSE_CELL_SIGN));


//        Stream<String[]> stringArraySteam = Arrays.stream(BOARD);
//        Stream<String> stringStream = stringArraySteam
//                .flatMap(stringArr -> {
//                    Stream<String> stringStream2 = Arrays.stream(stringArr);
//                    return stringStream2;
//                });
//        return stringStream
//                .noneMatch(cell -> cell.equals(CLOSE_CELL_SIGN));*/
    }

    private int countNearbyLandMines(int row, int col) {
        int count = 0;

        int rowSize = getRowSize();
        int colSize = getColSize();


        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < colSize && isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < colSize && isLandMineCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMineCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < rowSize && col + 1 < colSize && isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

    private boolean doesCellHaveLandMineCount(int row, int col) {
        return findCell(row, col).hasLandMineCount();
    }

    private boolean isOpenedCell(int row, int col) {
        return findCell(row, col).isOpened();
    }
}
