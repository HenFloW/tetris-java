package inf101v22.tetris.controller;

import inf101v22.tetris.midi.TetrisSong;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.view.TetrisView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TetrisController implements java.awt.event.KeyListener, java.awt.event.ActionListener{

    TetrisControllable contoller;
    TetrisView tetrisView;
    Timer timer;
    TetrisSong tetrisSong;

    public TetrisController(TetrisControllable controller, TetrisView view){
        this.contoller = controller;
        this.tetrisView = view;
        this.timer = new Timer(controller.getClockSpeed(),this);
        this.tetrisSong = new TetrisSong();
        tetrisView.addKeyListener(this);
        this.timer.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(contoller.getGameScreen() == GameScreen.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                contoller.moveFallingPiece(0, -1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                contoller.moveFallingPiece(0, 1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                timer.restart();
                contoller.moveFallingPiece(1, 0);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                contoller.rotateFallingPiece();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                timer.restart();
                contoller.dropMovingPiece();
            }
            tetrisView.repaint();
        }
        if(contoller.getGameScreen() == GameScreen.GAME_OVER){
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contoller.clockTick();
        timer.setDelay(contoller.getClockSpeed());
        tetrisView.repaint();
    }
}
