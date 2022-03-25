package inf101v22.tetris;

import inf101v22.tetris.controller.TetrisController;
import inf101v22.tetris.model.TetrisModel;
import inf101v22.tetris.view.TetrisView;

import javax.swing.*;

public class TetrisMain {
    public static final String WINDOW_TITLE = "INF101 Tetris";

    public static void main(String[] args) {

        TetrisModel model = new TetrisModel();
        TetrisView view = new TetrisView(model);
        TetrisController controller = new TetrisController(model,view);

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(view);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

}
