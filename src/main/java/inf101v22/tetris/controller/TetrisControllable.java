package inf101v22.tetris.controller;

import inf101v22.tetris.model.GameScreen;

public interface TetrisControllable {

    boolean moveFallingPiece(int deltaRow, int deltaCol);
    boolean rotateFallingPiece();
    boolean dropMovingPiece();
    GameScreen getGameScreen();
    void setGameScreen(GameScreen state);
    int getClockSpeed();
    void clockTick();

}
