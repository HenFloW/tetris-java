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

    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    public int index(Coordinate cords){
        return cords.col + cords.row*this.col;
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
        this.grid.set(index(coordinate), value);
    }

    @Override
    public E get(Coordinate coordinate) {
        return this.grid.get(index(coordinate));
    }

    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        int x = coordinate.col;
        int y = coordinate.row;

        if(x < 0 || x > this.col || y < 0 || y > this.row){
            return false;
        } else {
            return x < this.col && y < this.row;
        }

    }

    @Override
    public Iterator<CoordinateItem<E>> iterator() {
        ArrayList<CoordinateItem<E>> iter = new ArrayList<>();
        int colNum = 0;
        int rowNum = 0;

        for (E i: grid) {

            Coordinate newCord = new Coordinate(rowNum, colNum);
            CoordinateItem<E> item = new CoordinateItem<>(newCord, i);
            iter.add(item);

            rowNum = (  colNum+1 >= this.col ? ++rowNum : rowNum );
            colNum = (  ++colNum >= this.col ?        0 : colNum);

        }

        return iter.iterator();

    }
}
