package inf101v22.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E>{

    private final int row;
    private final int col;

    public List<E> grid;

    public Grid(int rows, int cols, E type){
        this.row = rows;
        this.col = cols;
        this.grid = new ArrayList<>();

        for (int i = 0; i < cols*rows; i++) {
            this.grid.add(type);
        }
    }

    public Grid(int rows, int cols){
        this.row = rows;
        this.col = cols;
        this.grid = new ArrayList<>();

        for (int i = 0; i < cols*rows; i++) {
            this.grid.add(null);
        }
    }

    @Override
    public int getRows() {
        return this.row;
    }

    @Override
    public int getCols() {
        return this.col;
    }

    @Override
    public void set(Coordinate coordinate, E value) {
        int x = coordinate.col;
        int y = coordinate.row;

        grid.set(x+10*y, value);
    }

    @Override
    public E get(Coordinate coordinate) {
        int x = coordinate.col;
        int y = coordinate.row;

        return this.grid.get(x+10*y);
    }

    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        int x = coordinate.col;
        int y = coordinate.row;

        return x < this.col && y < this.row;
    }

    @Override
    public Iterator<CoordinateItem<E>> iterator() {

        List<CoordinateItem<E>> iterator = new ArrayList<>();

        for (int y = 0; y < this.row; y++) {
            for (int x = 0; x < this.col; x++) {

                int index = x+10*y;
                iterator.add(x+10*y, new CoordinateItem<>(new Coordinate(y,x), grid.get(index)));

            }
        }

        return iterator.iterator();
    }
}
