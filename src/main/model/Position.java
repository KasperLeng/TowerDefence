package model;

// Represents a cell - a location on the board.
public class Position {
    // size of cell in screen coordinates
    public static final int cellSize = 10;

    private int column;   // the column that the object lies in.
    private int row;      // the row that this cell lies in.

    // EFFECTS: constructs cell at given row and column on board
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // EFFECTS: returns column in which cell is located
    public int getColumn() {
        return column;
    }

    // EFFECTS: returns row in which cell is located
    public int getRow() {
        return row;
    }

    // EFFECTS: returns horizontal screen coordinate of top-left corner of cell
    public int getScreenHorizontalCoord() {
        return column * cellSize;
    }

    // EFFECTS: returns vertical screen coordinate of top-left corner of cell
    public int getScreenVerticalCoord() {
        return row * cellSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position cell = (Position) o;

        if (column != cell.column) {
            return false;
        }

        return row == cell.row;
    }

    // @Override
    // public int hashCode() {
    //     int result = column;
    //     result = 31 * result + row;
    //     return result;
    // }

    @Override
    public String toString() {
        return "Position(" + column + ", " + row + ")";
    }
}
