package inf101v22.tetris.controller;

import inf101v22.tetris.midi.TetrisSong;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.TetrisModel;
import inf101v22.tetris.view.TetrisView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TetrisController implements java.awt.event.KeyListener, java.awt.event.ActionListener{

    TetrisModel model;
    TetrisView tetrisView;
    Timer timer;
    TetrisSong tetrisSong;

    public TetrisController(TetrisModel model, TetrisView view){
        this.model = model;
        this.tetrisView = view;
        this.timer = new Timer(model.getClockSpeed(),this);
        this.tetrisSong = new TetrisSong();
        tetrisView.addKeyListener(this);
        this.timer.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(model.getGameScreen() == GameScreen.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                model.moveFallingPiece(0, -1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.moveFallingPiece(0, 1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                timer.restart();
                model.moveFallingPiece(1, 0);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                model.rotateFallingPiece();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                timer.restart();
                model.dropMovingPiece();
            }
            tetrisView.repaint();
        }
        if(model.getGameScreen() == GameScreen.GAME_OVER){
            if (e.getKeyCode() == KeyEvent.VK_R){
                this.model = new TetrisModel();
                this.tetrisView.board = model;
                tetrisView.repaint();
                timer.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(model.getGameScreen() == GameScreen.GAME_OVER){
            timer.stop();
        } else {
            model.clockTick();
            timer.setInitialDelay(model.getClockSpeed());
            timer.setDelay(model.getClockSpeed());
            tetrisView.repaint();
        }
    }
}
