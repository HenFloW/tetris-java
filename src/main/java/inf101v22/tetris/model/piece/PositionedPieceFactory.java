package inf101v22.tetris.model.piece;

import inf101v22.grid.Coordinate;
import inf101v22.tetris.model.Tile;

import java.awt.*;
import java.util.List;

public class PositionedPieceFactory {

    int row;
    int col;

    public PositionedPiece createPiece(boolean[][] p, Color c, Coordinate cord){

        PieceShape piece = new PieceShape(new Tile('m',c), p);
        Coordinate pieceCord = new Coordinate(cord.row, cord.col);
        PositionedPiece piecePos = new PositionedPiece(piece,pieceCord);

        return piecePos;
    }

    public PositionedPiece getNextPositionedPiece(){
        List<boolean[][]> shapes = PieceShape.STANDARD_TETRIS_PIECES;
        List<Color> colors = PieceShape.STANDARD_COLOR;

        int randomIndex = (int)(Math.random() * shapes.size());

        boolean[][] rndShape = shapes.get(randomIndex);
        Color correctColor = colors.get(randomIndex);

        return createPiece(rndShape, correctColor, new Coordinate(0, setCenterColumn(rndShape)));
    }

    private int setCenterColumn(boolean[][] p) {
        return (int)(this.col/2 - Math.ceil(p[0].length/2));
    }

    public void setGrid(int row, int col) {
        this.col = col;
        this.row = row;
    }
}
