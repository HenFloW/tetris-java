package inf101v22.tetris.model;

import inf101v22.grid.Coordinate;
import inf101v22.grid.Grid;

import java.awt.*;

public class TetrisBoard extends Grid<Tile> {

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, new Tile(Color.BLACK));
    }

    public int removeLines(){
        int linesRemoved = 0;
        for(int y = this.getRows() - 1 ; y >= 0; y--){

            boolean fullLine = checkLine(y);

            if(fullLine){
                for(int x = 0; x < this.getCols(); x++){
                    set(new Coordinate(y, x),new Tile(Color.BLACK));
                }
                linesRemoved++;
            }
            else if (linesRemoved > 0){
                for (int x = 0; x < this.getCols(); x++) {
                    grid.set(index(new Coordinate(y+linesRemoved,x)), grid.get(index(new Coordinate(y,x))));
                }
            }
        }

        return linesRemoved;
    }

    private boolean checkLine(int y){
        boolean fullLine = true;

        for(int x = 0; x < this.getCols(); x++){
            if (grid.get(index(new Coordinate(y,x))).c != 's'){
                fullLine = false;
            }
        }

        return fullLine;
    }
}
