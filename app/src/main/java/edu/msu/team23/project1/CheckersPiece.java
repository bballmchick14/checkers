package edu.msu.team23.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.EnumMap;

public class CheckersPiece {
    /**
     * Enumeration for all the different images that can be used to display a piece
     */
    private enum PieceImage {
        WHITE_MAN,
        WHITE_KING,
        GREEN_MAN,
        GREEN_KING
    }

    /**
     * Map of IDs to images for each way a piece can be displayed
     * LIGHT_MAN, LIGHT_KING, DARK_MAN, DARK_KING
     */
    private static final EnumMap<PieceImage, Bitmap> PIECE_IMAGES = new EnumMap<>(PieceImage.class);

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
     * @param context Context of the application
     * @param team Team the piece is on
     */
    public CheckersPiece(Context context, CheckersGame.Team team) {
        PIECE_IMAGES.put(PieceImage.WHITE_MAN, BitmapFactory.decodeResource(context.getResources(), R.drawable.spartan_white));
        PIECE_IMAGES.put(PieceImage.WHITE_KING, BitmapFactory.decodeResource(context.getResources(), R.drawable.king_white));
        PIECE_IMAGES.put(PieceImage.GREEN_MAN, BitmapFactory.decodeResource(context.getResources(), R.drawable.spartan_green));
        PIECE_IMAGES.put(PieceImage.GREEN_KING, BitmapFactory.decodeResource(context.getResources(), R.drawable.king_green));

        if (team == CheckersGame.Team.WHITE) {
            pieceImage = PieceImage.WHITE_MAN;
        } else {
            pieceImage = PieceImage.GREEN_MAN;
        }
    }

    /**
     * Draw the checkers piece
     * @param canvas Canvas to draw on
     * @param origin origin of the board
     * @param boardSize size of the board
     * @param space space the piece is in
     */
    public void draw(Canvas canvas, Point origin, int boardSize, Point space) {
        // Get this pieces image
        Bitmap image = PIECE_IMAGES.get(pieceImage);

        if (image != null) {
            // Determine scale factor
            float scaleFactor = (float)boardSize / (float)image.getWidth() / 8;

            // Draw image in the correct space
            canvas.save();
            canvas.translate(origin.x + ((boardSize / 8f) * space.x), origin.y + (boardSize / 8f * space.y));
            canvas.scale(scaleFactor, scaleFactor);
            canvas.drawBitmap(image, 0, 0, null);
            canvas.restore();
        }
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
}
