package inf101v22.tetris.model;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.controller.TetrisControllable;
import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.view.TetrisViewable;

import java.util.Iterator;

public class TetrisModel implements TetrisViewable, TetrisControllable {

    public TetrisBoard board;
    private PositionedPieceFactory spawn;
    private PositionedPiece piece;
    private PositionedPiece nextPiece;
    private GameScreen state;
    final private int clockSpeed;
    private int piecesPlaced;
    private int score;

    public TetrisModel(){
        this.board = new TetrisBoard(20,10);
        this.state = GameScreen.ACTIVE_GAME;
        this.clockSpeed = 2000;
        this.piecesPlaced = 0;
        this.spawn = new PositionedPieceFactory(); this.spawn.setGrid(board.getRows(), board.getCols());
        this.piece = spawn.getNextPositionedPiece();
        this.nextPiece = spawn.getNextPositionedPiece();
    }

    @Override
    public int getColums() {
        return board.getCols();
    }

    @Override
    public int getRows() {
        return board.getRows();
    }

    @Override
    public PositionedPiece getPiece() {
        return piece;
    }

    @Override
    public PositionedPiece getNextPiece() {
        return nextPiece;
    }

    @Override
    public PositionedPiece getGhost() {
        PositionedPiece ghost = this.piece;

        while(legalMove(ghost.move(1, 0))){
            ghost = ghost.move(1, 0);
        }

        return ghost;
    }

    @Override
    public Iterator<CoordinateItem<Tile>> iterator() {
        return board.iterator();
    }


    @Override
    public GameScreen getGameScreen() {
        return state;
    }

    @Override
    public void setGameScreen(GameScreen state) { this.state = state; }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getClockSpeed() {
        return (int)(this.clockSpeed*Math.pow(0.985, piecesPlaced));
    }

    @Override
    public void clockTick() {
        if (!moveFallingPiece(1,0)) {
            dropMovingPiece();
        }
    }

    @Override
    public boolean moveFallingPiece(int deltaRow, int deltaCol) {
        PositionedPiece movedPiece = this.piece.move(deltaRow, deltaCol);

        if (legalMove(movedPiece)){

            updatePieceTile(movedPiece, false);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean rotateFallingPiece() {
        PositionedPiece rotatedPiece = this.piece.rotate();

        if(legalMove(rotatedPiece)){

            updatePieceTile(rotatedPiece, false);

            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean dropMovingPiece() {
        dropSet();
        int removedLines = board.removeLines();
        score += removedLines*removedLines;

        updatePieceTile(nextPiece, false);
        nextPiece = spawn.getNextPositionedPiece();

        if(!legalMove(piece)){
            this.state = GameScreen.GAME_OVER;
            return false;
        }

        piecesPlaced++;

        return true;
    }

    private boolean legalMove(PositionedPiece movedPiece) {
        boolean allowed = true;

        for(CoordinateItem<Tile> i : movedPiece){

            if(!board.coordinateIsOnGrid(i.coordinate)){
                allowed = false;
            }
            else if (board.grid.get(board.index(i.coordinate)).c == 's'){
                allowed = false;
            }
        }

        return allowed;
    }

    public void dropSet(){
        while(moveFallingPiece(1,0));
        piece.setChar('s');
        updatePieceTile(piece, true);
    }

    public void updatePieceTile(PositionedPiece piece, boolean set){
        if(set){
            for(CoordinateItem<Tile> tile : piece){
                board.set(tile.coordinate, tile.item);
            }
        }
        this.piece = piece;
    }
}
