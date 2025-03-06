package cleancode.minesweeper.tobe;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {
    private final int LAND_MINE_COUNT = 10;

    private final Cell[][] board;

    public GameBoard(int rowSize, int colSize) {
        board = new Cell[rowSize][colSize];
    }

    public void initializeGame() {
        int rowSize = board.length;
        int colSize = board[0].length;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = Cell.create();
            }
        }

        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int landMineRow = new Random().nextInt(rowSize);
            int landMineCol = new Random().nextInt(colSize);

            Cell landMineCell = findCell(landMineRow, landMineCol);
            landMineCell.turnOnLandMine() ;
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {

                if (isLandMineCell(row, col)) {
                    continue;
                }

                int count = countNearbyLandMines(row, col);
                Cell cell = findCell(row, col);
                board[row][col].updateNearbyLandMineCount(count);
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
        if (col + 1 < rowSize && isLandMineCell(row, col + 1)) {
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
