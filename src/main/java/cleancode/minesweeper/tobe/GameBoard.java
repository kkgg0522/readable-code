package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.*;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.CellPositions;
import cleancode.minesweeper.tobe.position.RelativePosition;

import java.util.List;


public class GameBoard {

    private final Cell[][] board;
    private final int landMineCount;

    public GameBoard(GameLevel gameLevel) {
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSIze();
        board = new Cell[rowSize][colSize];

        landMineCount = gameLevel.getLandMineCount();
    }

    public CellSnapshot getSnapshot(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.getSnapshot();
    }

    public void initializeGame() {
        CellPositions cellPositions = CellPositions.from(board);


        initializeEmptyCells(cellPositions);

        List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
        initializeLandMineCells(landMinePositions);

        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
        initializeNumberCells(numberPositionCandidates);
    }

    private void initializeEmptyCells(CellPositions cellPositions) {
        List<CellPosition> allPositions = cellPositions.getPositions();
        for (CellPosition position : allPositions) {
            updateCellAt(position, new EmptyCell());
        }
    }

    private void initializeLandMineCells(List<CellPosition> landMinePositions) {
        for (CellPosition landMinePosition : landMinePositions) {
            updateCellAt(landMinePosition, new LandMineCell());
        }
    }



    private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
        for (CellPosition candidatePosition : numberPositionCandidates) {
            int count = countNearbyLandMines(candidatePosition);
            if(count != 0){
                updateCellAt(candidatePosition, new NumberCell(count));
            }
        }
    }

    private void updateCellAt(CellPosition position, Cell cell) {
        board[position.getRowIndex()][position.getColIndex()] = cell;
    }

    public int getRowSize(){
        return board.length;
    }

    public int getColSize(){
        return board[0].length;
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
        Cells cells = Cells.form(board);
        return cells.isAllChecked();

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
