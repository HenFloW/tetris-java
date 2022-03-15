package inf101v22.tetris.helper;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.view.TetrisViewable;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class DrawHelper {

    int paddingX;
    int paddingY;
    int tilePadding;
    int tileSize;
    Graphics canvas;
    TetrisViewable board;
    Font tetrisFont;


    public DrawHelper(TetrisViewable board){
        this.board = board;
        //this.tetrisFont = createNewFont(".\\fonts\\prstartk.ttf", 12f);
    }

    private Font createNewFont(String filePath, float size){
        try {
            InputStream file = getClass().getResourceAsStream(filePath);
            System.out.println(file);
            Font font = Font.createFont(Font.TRUETYPE_FONT, file);
            return font.deriveFont(size);

        } catch (IOException |FontFormatException e) {
            System.out.println("Error creating font");
            return null;
        }
    }


    public void drawPiece(int y, int x, PositionedPiece piece){

        int xc = piece.getCoordinates().get(0).col;
        int yc =  piece.getCoordinates().get(0).row;

        for(Coordinate i : piece.getCoordinates()){ // A handeler so i can change the corner cooridnate to the most left and
            if(i.col <= xc && i.row > yc){          // the highest row
                xc = i.col;
            }
        }

        Iterator<CoordinateItem<Tile>> pieceIter = piece.iterator();

        while (pieceIter.hasNext()){
            CoordinateItem<Tile> selectedItem = pieceIter.next();

            this.canvas.setColor(selectedItem.item.color);

            this.canvas.fillRect(
                    (selectedItem.coordinate.col-xc)*tileSize +x,
                    (selectedItem.coordinate.row-yc)*tileSize +y,
                    tileSize-tilePadding,
                    tileSize-tilePadding);
        }
    }

    public void drawTileOnBoard(CoordinateItem<Tile> tile){

        this.canvas.setColor(tile.item.color);

        this.canvas.fillRect(
                tile.coordinate.col*tileSize + paddingX,
                tile.coordinate.row*tileSize + paddingY,
                tileSize-tilePadding,
                tileSize-tilePadding);
    }

    public void drawPieceOnBoard(PositionedPiece piece, String type){

        Iterator<CoordinateItem<Tile>> pieceIter = piece.iterator();

        while (pieceIter.hasNext()){
            CoordinateItem<Tile> selectedItem = pieceIter.next();
            if(type == "main"){
                drawTileOnBoard(selectedItem);
            }
            if(type == "ghost"){
                this.canvas.setColor(selectedItem.item.color);
                int border = (int)(tileSize*0.3f);
                this.canvas.fillRect(
                        selectedItem.coordinate.col*tileSize + paddingX+border,
                        selectedItem.coordinate.row*tileSize + paddingY+border,
                        tileSize-tilePadding-border*2,
                        tileSize-tilePadding-border*2);
            }
        }
    }

    public void drawTetrisBox(int x, int y, int width, int height, int thickness, Color borderColor, Color secondColor, Color insideColor){

        canvas.setColor(borderColor);
        canvas.fillRect(x-thickness,y-thickness,width+thickness*2,height+thickness*2);

        canvas.setColor(secondColor);
        int thickness1 = (int)((thickness*2)/3);
        canvas.fillRect(x-thickness1, y-thickness1, width+thickness1*2, height+thickness1*2);

        int thickness2 = (int)((thickness1*2)/3);
        canvas.setColor(borderColor);
        canvas.fillRect(x-thickness2, y-thickness2, width+thickness2*2, height+thickness2*2);

        int thickness3 = (int)((thickness2*2)/3);
        canvas.setColor(secondColor);
        canvas.fillRect(x-thickness3, y-thickness3, width+thickness3*2, height+thickness3*2);


        canvas.setColor(insideColor);
        canvas.fillRect(x, y, width, height);

    }

    public void tetrisText(int x, int y, String message, Color color, float size){
        canvas.setColor(color);
        //canvas.setFont(tetrisFont.deriveFont(size));
        canvas.setFont(canvas.getFont().deriveFont(size));
        canvas.drawString(message, x, y);
    }

    public void setPadding(int tilePadding, int paddingX, int paddingY) {
        this.tilePadding = tilePadding;
        this.paddingX = paddingX;
        this.paddingY = paddingY;
    }

    public void setSize(int size){
        this.tileSize = size;
    }

    public void setCanvas(Graphics c){
        this.canvas = c;
    }

}
