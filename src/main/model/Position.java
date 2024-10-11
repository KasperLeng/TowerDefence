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
}
