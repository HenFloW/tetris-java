package inf101v22.tetris.model.piece;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PositionedPiece implements Iterable<CoordinateItem<Tile>>{

    private Coordinate corner;
    private PieceShape pieceShape;


    PositionedPiece(PieceShape pShape, Coordinate corner){
        this.corner = corner;
        this.pieceShape = pShape;
    }

    public int getWidt(){
        return pieceShape.getWidth();
    }

    public int getHeight(){
        return pieceShape.getHeight();
    }

    public Coordinate getCorner(){
        return this.corner;
    }

    public PositionedPiece move(int deltaY, int deltaX){

        PositionedPiece newPiece = new PositionedPiece(
                this.pieceShape,
                new Coordinate(this.corner.row + deltaY, this.corner.col + deltaX)
        );

        return newPiece;

    }
    public PositionedPiece rotate(){

        boolean[][] newShape = new boolean[this.getWidt()][this.getHeight()];
        for (int y = this.getWidt() - 1; y >= 0; y--) {
            for (int x = this.getHeight() - 1; x >= 0; x--) {
                newShape[y][this.getHeight() - 1 - x] = this.pieceShape.getShape()[x][y];
            }
        }

        return new PositionedPiece(
                new PieceShape(
                        pieceShape.getTile(),
                        newShape),
                new Coordinate(
                        this.corner.row,
                        this.corner.col
                ));
    }

    public List<Coordinate> getCoordinates(){
        List<Coordinate> cordList = new ArrayList<>();

        Iterator<CoordinateItem<Tile>> pieceItr = this.iterator();

        while (pieceItr.hasNext()) {
            Coordinate cord = pieceItr.next().coordinate;
            cordList.add(cord);
        }
        return cordList;
    }


    public void setChar(char c) {
        this.pieceShape = new PieceShape(
                new Tile(c, this.pieceShape.getTile().color),
                this.pieceShape.getShape());
    }

    public int getActualHeight(){
        int h = 0;
        for(int i = 0; i < this.pieceShape.getHeight(); i++){
            boolean containsTrue = false;
            for(boolean b : pieceShape.getShape()[i]){
                if(b){
                    containsTrue = true;
                }
            }
            if(containsTrue){h++;};
        }

        return h;
    }

    @Override
    public Iterator<CoordinateItem<Tile>> iterator() {
        ArrayList<CoordinateItem<Tile>> pieceList = new ArrayList<>();

        int y = 0;
        int x = 0;

        for (boolean[] shapeY: pieceShape.getShape()) {
            for (boolean shapeX: shapeY) {
                if(shapeX){
                    pieceList.add(new CoordinateItem<>(
                            new Coordinate(corner.row+y, corner.col+x),
                            new Tile(pieceShape.getTile().c, pieceShape.getTile().color)
                    ));
                }
                x = (++x >= shapeY.length ? 0: x);
            }
            y++;
        }

        return pieceList.iterator();
    }
}
