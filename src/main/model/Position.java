package model;

// Represents a position - a location on the board.
public class Position {

    public static final int pixelSize = 30;

    private int column; // the column that the object lies in.
    private int row; // the row that this object lies in.

    // EFFECTS: constructs position at given row and column on board
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // EFFECTS: returns column the object is located in.
    public int getColumn() {
        return column;
    }

    // EFFECTS: returns row the object is located in.
    public int getRow() {
        return row;
    }

    // EFFECTS: checks if two positions are the same.
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
