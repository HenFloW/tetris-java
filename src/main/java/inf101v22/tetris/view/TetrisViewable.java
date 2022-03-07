package inf101v22.tetris.view;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.piece.PositionedPiece;

import java.util.Iterator;

public interface TetrisViewable {

    int getColums();

    int getRows();

    int getScore();

    PositionedPiece getPiece();

    PositionedPiece getGhost();

    PositionedPiece getNextPiece();

    Iterator<CoordinateItem<Tile>> iterator();

    GameScreen getGameScreen();

}
