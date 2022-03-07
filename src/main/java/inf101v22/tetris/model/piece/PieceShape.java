package inf101v22.tetris.model.piece;

import inf101v22.tetris.model.Tile;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PieceShape {

    private Tile tile;
    private boolean[][] shape;


    // T
    public static boolean[][] T = new boolean[][] {
            {  true,  true,  true },
            { false,  true, false }
        };
    // S
    public static boolean[][] S = new boolean[][] {
            { false,  true,  true },
            {  true,  true, false }
        };
    // Z
    public static boolean[][] Z = new boolean[][] {
            {  true,  true, false },
            { false,  true,  true }
        };
    // I
    public static boolean[][] I = new boolean[][] {
            { true,  true,  true, true }
        };
    // J
    public static boolean[][] J = new boolean[][] {
            { true, false, false },
            { true, true,  true }
        };
    // L
    public static boolean[][] L = new boolean[][] {
            { false, false,  true },
            {  true,  true,  true }
        };
    // O
    public static boolean[][] O = new boolean[][] {
            {  true,  true },
            {  true,  true }
        };

    static final List<boolean[][]> STANDARD_TETRIS_PIECES = Arrays.asList(T,S,Z,I,J,L,O);
    static final List<Color> STANDARD_COLOR = Arrays.asList(
            new Color(120,00,120),
            Color.GREEN,
            Color.RED,
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW
    );

    public PieceShape(Tile tile, boolean[][] piece){
        this.tile = tile;
        this.shape = piece;
    }

    public int getWidth(){
        return shape[0].length;
    }

    public int getHeight(){
        return shape.length;
    }

    public Tile getTile(){
        return this.tile;
    }

    public boolean[][] getShape(){
        return this.shape;
    }
}
