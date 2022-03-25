package inf101v22.tetris.model;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.grid.Grid;

import java.awt.*;
import java.util.Iterator;

public class TetrisBoard extends Grid<Tile> {

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, new Tile(Color.BLACK));
    }

    public char[][] toCharArray2d(){
        char[][] list = new char[getRows()][getCols()];

        Iterator<CoordinateItem<Tile>> iter = iterator();
        while(iter.hasNext()){
            CoordinateItem<Tile> t = iter.next();
            list[t.coordinate.row][t.coordinate.col] = t.item.c;
        }

        return list;
    }

    @Override
    public String toString(){
        char[][] list = toCharArray2d();

        String text = "";
        for(char[] y : list){
            for (char x : y){
                text += x;
            }
            text+="\n";
        }

        return text.strip();
    }

    public int removeLines(){
        int linesRemoved = 0;
        for(int y = this.getRows() - 1 ; y >= 0; y--){
            if(checkLine(y)){
                for(int x = 0; x < this.getCols(); x++){
                    set(new Coordinate(y, x),new Tile(Color.BLACK));
                }
                linesRemoved++;
            }
            else if (linesRemoved > 0){
                for (int x = 0; x < this.getCols(); x++) {
                    set(new Coordinate(y+linesRemoved,x), get(new Coordinate(y,x)));
                }
            }
        }

        return linesRemoved;
    }

    private boolean checkLine(int y){
        boolean fullLine = true;

        for(int x = 0; x < this.getCols(); x++){
            if (get(new Coordinate(y,x)).c != 's'){
                fullLine = false;
            }
        }

        return fullLine;
    }
}
