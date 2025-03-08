package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.*;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.RelativePosition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


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
                CellPosition cellPosition = CellPosition.of(row, col);

                if (isLandMineCellAt(cellPosition)) {
                    continue;
                }

                int count = countNearbyLandMines(cellPosition);

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

    public String getSign(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.getSign();
    }

    public Cell findCell(CellPosition cellPosition) {
        return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
    }

    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

       return cellPosition.isRowIndexMoreThanEquals(rowSize) || cellPosition.isColIndexMoreThanEquals(colSize);
    }

    public boolean isLandMineCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isLandMine();
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

    public void flagAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.flag();
    }

    public void openAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.open();
    }

    public void openSurroundedCells(CellPosition cellPosition) {
//        if (cellPosition.isRowIndexMoreThanEquals(getRowSize())
//                || cellPosition.isColIndexMoreThanEquals(getColSize())) {
//            return;
//        }

        if (isOpenedCellAt(cellPosition)) {
            return;
        }
        if (isLandMineCellAt(cellPosition)) {
            return;
        }

        openAt(cellPosition);

        if (doesCellHaveLandMineCount(cellPosition)) {
//            BOARD[row][col] = Cell.ofNearbyLandMineCount(NEARBY_LAND_MINE_COUNTS[row][col]);
            return;
        }

        List<CellPosition> cellPositions = calculateSurroundedPositions(cellPosition, getRowSize(), getColSize());
        cellPositions.forEach(this::openSurroundedCells);

//        for (RelativePosition relativePosition : RelativePosition.SURROUNDED_POSITION) {
//            if(cellPosition.canCalculatePositionBy(relativePosition)){
//                CellPosition nextCellPosition = cellPosition.calculatePositionBy(relativePosition);
//                openSurroundedCells(nextCellPosition);
//            }
//        }
    }


    private int countNearbyLandMines(CellPosition cellPosition) {

        int rowSize = getRowSize();
        int colSize = getColSize();

        long count = calculateSurroundedPositions(cellPosition, rowSize, colSize)
                .stream()
                .filter(this::isLandMineCellAt)
                .count();

        return (int) count;
    }

    private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition, int rowSize, int colSize) {
        return RelativePosition.SURROUNDED_POSITION.stream()
                .filter(cellPosition::canCalculatePositionBy)
                .map(cellPosition::calculatePositionBy)
                .filter(position -> position.isRowIndexLessThen(rowSize))
                .filter(position -> position.isColIndexLessThen(colSize))
                .toList();
    }

    private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.hasLandMineCount();
    }

    private boolean isOpenedCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isOpened();
    }


}
