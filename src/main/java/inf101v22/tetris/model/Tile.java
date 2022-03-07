package inf101v22.tetris.model;

import java.awt.*;

public class Tile {
    public final Color color;
    public final char c;

    public Tile(char character, Color color){
        this.color = color;
        this.c = character;
    }

    public Tile(Color color){
        this('-', color);
    }

}
