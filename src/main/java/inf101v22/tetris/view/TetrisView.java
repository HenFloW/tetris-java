package inf101v22.tetris.view;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.helper.DrawHelper;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.piece.PositionedPiece;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class TetrisView extends JComponent {
    {
        this.setFocusable(true);
    }

    public TetrisViewable board;
    private DrawHelper draw;
    public int width;
    public int height;

    public TetrisView(TetrisViewable board){
        this.board = board;
        this.draw = new DrawHelper(board);
    }

    @Override
    public void paint(Graphics canvas) {
        this.height = getHeight();
        this.width = getWidth();
        this.draw.setCanvas(canvas);

        super.paint(canvas);

        GameScreen state = board.getGameScreen();

        if (state == GameScreen.ACTIVE_GAME){

            int size = findSize(0.02f,3,board);
            int paddingX = (width-(size*board.getColums())) / 2;
            int paddingY = (height-(size*board.getRows())) / 2;
            int tilePadding = (int)(size*0.1f);

            draw.setPadding(tilePadding, paddingX, paddingY);
            draw.setSize(size);

            draw.drawTetrisBox(
                    paddingX-tilePadding, paddingY-tilePadding,
                    (size+tilePadding)*(board.getColums()-1)+tilePadding*4,
                    (size+tilePadding)*(board.getRows()-1) - tilePadding*6,
                    (int)(size*.5f),
                    Color.DARK_GRAY,
                    Color.WHITE,
                    Color.BLACK);

            drawBoard(board);


            PositionedPiece nextPiece = board.getNextPiece();

            int nextTileBoxX = paddingX+(size+tilePadding)*(board.getColums())+(int)(size*.5f)*2;
            int nextTileBoxY = paddingY-tilePadding;
            int nextTileBoxPixelWidth = 5*(size+tilePadding);
            int nextTileBoxPixelHeight = 3*(size+tilePadding);
            int nextPiecePixelWidth = (size-tilePadding)*nextPiece.getWidt();
            int nextPiecePixelHeight = (size-tilePadding)*nextPiece.getActualHeight();

            draw.drawTetrisBox(
                    nextTileBoxX,
                    nextTileBoxY,
                    nextTileBoxPixelWidth,
                    nextTileBoxPixelHeight,
                    (int)(size*.5f),
                    Color.DARK_GRAY,
                    Color.WHITE,
                    Color.BLACK);

            draw.drawPiece(
                    nextTileBoxY + nextTileBoxPixelHeight/2 - nextPiecePixelHeight/2,
                    nextTileBoxX + nextTileBoxPixelWidth/2 - nextPiecePixelWidth/2,
                    nextPiece);

            PositionedPiece p = board.getPiece();
            PositionedPiece gh = board.getGhost();

            draw.drawPiece(gh, "ghost");
            draw.drawPiece(p, "main");

            draw.tetrisText(50,50,"HighScore: 120", Color.red, 30);

        }
        if (state == GameScreen.GAME_OVER){

            Font myFont = new Font("SansSerif", Font.BOLD, 56);
            String message = "Game Over! Score: " + board.getScore();
            int stringWidth = GraphicHelperMethods.getStringWidth(canvas, myFont, message);
            int stringHeight = GraphicHelperMethods.getStringHeight(canvas, myFont, message);

            canvas.setColor(Color.BLACK);
            canvas.setFont(myFont);
            canvas.drawString(message, getWidth()/2-stringWidth/2, getHeight()/2-stringHeight/2);

            super.repaint();
        }
    }

    private void drawBoard(TetrisViewable board) {
        Iterator<CoordinateItem<Tile>> tileIterator = board.iterator();

        while (tileIterator.hasNext()){
            CoordinateItem<Tile> tile = tileIterator.next();
            draw.drawTileOnBoard(tile);
        }
    }

    private int findSize(float borderPadding,int paddingBetween, TetrisViewable tileGrid){

        int possibleWidth = (Math.round(width-borderPadding*2-paddingBetween*tileGrid.getColums())) / tileGrid.getColums();
        int possibleHeight = (Math.round(height-borderPadding*2-paddingBetween*tileGrid.getRows())) / tileGrid.getRows();

        if (possibleHeight > possibleWidth){
            return possibleWidth;
        } else {
            return possibleHeight;
        }
    }
}
