package inf101v22.tetris.model.piece;

import inf101v22.grid.Coordinate;
import inf101v22.tetris.model.Tile;

import java.awt.*;
import java.util.List;

public class PositionedPieceFactory {

    int row;
    int col;

    public PositionedPiece getNextPositionedPiece(){
        List<boolean[][]> shapes = PieceShape.STANDARD_TETRIS_PIECES;
        List<Color> colors = PieceShape.STANDARD_COLOR;

        int randomIndex = (int)(Math.random() * shapes.size());

        boolean[][] rndShape = shapes.get(randomIndex);
        Color correctColor = colors.get(randomIndex);


        PieceShape piece = new PieceShape(new Tile('m',correctColor), rndShape);
        Coordinate pieceCord = new Coordinate(0, setCenterColumn(piece));

        PositionedPiece piecePos = new PositionedPiece(piece,pieceCord);

        return piecePos;
    }

    private int setCenterColumn(PieceShape piece) {
        return (int)(this.col/2 - Math.ceil(piece.getWidth()/2));
    }

    public void setGrid(int row, int col) {
        this.col = col;
        this.row = row;
    }
}
