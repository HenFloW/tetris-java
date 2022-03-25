package inf101v22.tetris.controller;

import inf101v22.tetris.model.GameScreen;

public interface TetrisControllable {

    /**
     * This method will take the current piece and move it down with the delta-x and y
     *
     * @param deltaRow
     * @param deltaCol
     * @return a boolean if the move is valid and the move is made
     */
    boolean moveFallingPiece(int deltaRow, int deltaCol);

    /**
     * This will take a piece and rotate it, if its allowed it will update the current piece to the rotated piece
     * @return a boolean if the piece had a valid rotate
     */
    boolean rotateFallingPiece();

    /**
     * This method moves the piece down one on the y-axis until it can't do a valid move
     * @return a boolean that returns true if its dropped
     */
    boolean dropMovingPiece();

    /**
     *
     * @return the current gamestate
     */
    GameScreen getGameScreen();

    /**
     * This can chamge the state the game is in
     * @param state
     */
    void setGameScreen(GameScreen state);

    /**
     * This gets the clockspeed depending on how many pieces placed and the default clockspeed
     * @return the clockspeed,
     */
    int getClockSpeed();

    /**
     * This does a clock tick, when this method is runned, all methods related to changeing
     * the game to the next state will be runned.
     */
    void clockTick();

}
