package edu.msu.team23.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.EnumMap;

public class CheckersPiece {
    /**
     * Enumeration for all the different images that can be used to display a piece
     */
    private enum PieceImage {
        LIGHT_MAN,
        LIGHT_KING,
        DARK_MAN,
        DARK_KING
    }

    /**
     * Map of IDs to images for each way a piece can be displayed
     * LIGHT_MAN, LIGHT_KING, DARK_MAN, DARK_KING
     */
    private static final EnumMap<PieceImage, Bitmap> PIECE_IMAGES = loadPieceImages();

    /**
     * Distance away from the center of a space to be allowed to snap
     */
    private static final float SNAP_DISTANCE = 0.05f;

    /**
     * Image used to display the piece
     */
    private PieceImage pieceImage;

    /**
     * ID of the piece (negative numbers are light, positive numbers are dark)
     */
    private int id;

    /**
     * Is this piece a king?
     */
    private boolean isKing = false;

    /**
     * x location.
     * We use relative x locations in the range 0-1 for the center of the checkers piece
     */
    private float x;

    /**
     * y location
     * We use relative y locations in the range 0-1 for the center of the checkers piece
     */
    private float y;

    /**
     * Constructor for a checkers piece
     * @param context Contect of the application
     * @param team Team the piece is on
     */
    public CheckersPiece(Context context, CheckersGame.Team team) {

    }

    /**
     * Draw the checkers piece
     * @param canvas Canvas we are drawing on
     * @param marginX Margin x value in pixels
     * @param marginY Margin y value in pixels
     * @param boardSize Size we draw the board in pixels
     * @param scaleFactor Amount we scale the checkers pieces when we draw them
     */
    public void draw(Canvas canvas, int marginX, int marginY, int boardSize, float scaleFactor) {

    }

    /**
     * Test to see if we have touched a checkers piece
     * @param testX X location as a normalized coordinate (0 to 1)
     * @param testY Y location as a normalized coordinate (0 to 1)
     * @param puzzleSize the size of the puzzle in pixels
     * @param scaleFactor the amount to scale a piece by
     * @return true if we hit the piece
     */
    public boolean hit(float testX, float testY, int puzzleSize, float scaleFactor) {
        return false;
    }

    /**
     * Move the checkers piece by dx, dy
     * @param dx x amount to move
     * @param dy y amount to move
     */
    public void move(float dx, float dy) {

    }

    /**
     * Set the position of the piece
     * @param x New x position of the piece
     * @param y New y position of the piece
     */
    public void setPosition(float x, float y) {

    }

    /**
     * Load the possible images for the pieces
     * @return Map of all the possible images of the pieces
     */
    private static EnumMap<PieceImage, Bitmap> loadPieceImages() {
        return new EnumMap<>(PieceImage.class);
    }
}
