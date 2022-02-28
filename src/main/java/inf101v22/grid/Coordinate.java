package inf101v22.grid;

import java.util.Objects;

public final class Coordinate {

    int row;
    int col;

    public Coordinate(int row, int col){
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "{ " +
                "row='" + row +
                "', col='" + col +
                "' }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Coordinate that = (Coordinate) o;

        return this.col == that.col && this.row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }
}
