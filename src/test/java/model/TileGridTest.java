package model;

import inf101v22.grid.Coordinate;
import inf101v22.tetris.model.TetrisBoard;
import inf101v22.tetris.model.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class TileGridTest {
    @Test
    void BoardGridLenght(){
        TetrisBoard board = new TetrisBoard(15, 20);

        assertEquals(15, board.toCharArray2d().length);
        assertEquals(20, board.toCharArray2d()[0].length);

    }

    @Test
    void gridToString(){
        TetrisBoard board = new TetrisBoard(15, 20);

        assertEquals(
                "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------",
                        board.toString());

        for (int i = 0; i < board.getCols(); i++) {
            board.set(new Coordinate(1,i), new Tile('C', Color.red));
        }

        assertEquals(
                "--------------------\n" +
                        "CCCCCCCCCCCCCCCCCCCC\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------\n" +
                        "--------------------",
                        board.toString());

        assertFalse(board.get(new Coordinate(1, 0)).c=='-');
        assertFalse(board.get(new Coordinate(1, 3)).c=='-');
        assertFalse(board.get(new Coordinate(1, 7)).c=='-');
        assertTrue(board.get(new Coordinate(1, 5)).c=='C');
        ;
    }
    @Test
    void TileColorInGrid(){
        TetrisBoard board = new TetrisBoard(2, 3);
        Coordinate cord1 = new Coordinate(0,0);
        Coordinate cord2 = new Coordinate(1,0);
        Coordinate cord3 = new Coordinate(1,2);

        board.set(cord1, new Tile(Color.GREEN));
        board.set(cord2, new Tile(Color.RED));
        board.set(cord3, new Tile(Color.BLUE));

        assertEquals(Color.BLUE, board.get(cord3).color);
        assertEquals(Color.RED, board.get(cord2).color);
        assertEquals(Color.GREEN, board.get(cord1).color);
        assertEquals(Color.BLACK, board.get(new Coordinate(0,1)).color );

    }
}
