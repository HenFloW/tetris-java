package inf101v22.tetris.model.piece;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PositionedPiece implements Iterable<CoordinateItem<Tile>>{

    public List<CoordinateItem<Tile>> pieceList;
    private int width;
    private int height;
    private Coordinate corner;
    private PieceShape pieceShape;


    PositionedPiece(PieceShape pshape, Coordinate corner){

        this.pieceList = new ArrayList<>();

        this.height = pshape.getHeight();
        this.width = pshape.getWidth();
        this.corner = corner;
        this.pieceShape = pshape;

        int y = 0;
        int x = 0;

        for (boolean[] sy: pshape.getShape()) {
            for (boolean sx: sy) {
                if(sx){
                    this.pieceList.add(new CoordinateItem<>(
                            new Coordinate(corner.row+y, corner.col+x),
                            new Tile(pshape.getTile().c, pshape.getTile().color)
                    ));
                }
                x = (++x >= sy.length ? 0: x);
            }
            y++;
        }
    }

    public int getWidt(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Coordinate getCorner(){
        return corner;
    }

    public PositionedPiece move(int deltaY, int deltaX){

        PositionedPiece newPiece = new PositionedPiece(
                this.pieceShape,
                new Coordinate(this.corner.row + deltaY, this.corner.col + deltaX)
        );

        return newPiece;

    }
    public PositionedPiece rotate(){

        boolean[][] newShape = new boolean[this.width][this.height];
        for (int y = this.width - 1; y >= 0; y--) {
            for (int x = this.height - 1; x >= 0; x--) {
                newShape[y][this.height - 1 - x] = this.pieceShape.getShape()[x][y];
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
        List<Coordinate> c = new ArrayList<>();
        for (CoordinateItem<Tile> tile : pieceList) {
            c.add(tile.coordinate);
        }
        return c;
    }


    @Override
    public Iterator<CoordinateItem<Tile>> iterator() {
        return this.pieceList.iterator();
    }

    public void setChar(char c) {
        for (CoordinateItem<Tile> i : pieceList) {
            pieceList.set(pieceList.indexOf(i), new CoordinateItem<>(i.coordinate, new Tile(c, i.item.color)));
        }
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
}
