package edu.msu.team23.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class CheckersGame {
    /**
     * Enumeration for the players in the game
     */
    private enum Player {
        PLAYER_1,
        PLAYER_2
    }

    /**
     * Enumeration for the teams in the game
     */
    enum Team {
        LIGHT,
        DARK
    }

    /**
     * Image of the checker board
     */
    // TODO Set this to the actual image file
    private static final Bitmap BOARD_IMAGE = null;

    /**
     * Percentage of the display width or height that
     * is occupied by the checkers game
     */
    private static float SCALE_IN_VIEW = 0.9f;

    /**
     * Bundle key for storing the checkers pieces
     */
    private static final String CHECKERS_PIECES = "CheckersGame.checkersPieces";

    /**
     * View this checkers game is in
     */
    private CheckersView view;

    /**
     * Name of player 1
     */
    private String player1;

    /**
     * Name of player 2
     */
    private String player2;

    /**
     * Name of the player whose turn it is.
     */
    private String playerTurn;

    /**
     * Representation of the game board. an object will appear if
     */
    private CheckersPiece[][] board;

    /**
     * Size of the board in pixels
     */
    private int boardSize;

    /**
     * How much we scale the checkers pieces
     */
    private float scaleFactor;

    /**
     * Left margin in pixels
     */
    private int marginX;

    /**
     * Yop margin in pixels
     */
    private int marginY;

    /**
     * The checkers piece being dragged. If we are not dragging, the variable is null
     */
    private CheckersPiece dragging = null;

    /**
     * Most recent relative X touch when dragging
     */
    private float lastRelX;

    /**
     * Most recent relative Y touch when dragging
     */
    private float lastRelY;

    /**
     * Constructor foe the CheckersGame class
     * @param context Application context
     * @param view View this game is a part of
     */
    public CheckersGame(Context context, CheckersView view) {

    }

    /**
     * Draws the checkers game
     * @param canvas Canvas to draw on
     */
    public void draw(Canvas canvas) {

    }

    /**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {
        return false;
    }

    /**
     * Handle a touch message. This is when we get an initial touch
     * @param view The view that is the source of the touch
     * @param x x location for the touch, relative to the game - 0 to 1 over the game
     * @param y y location for the touch, relative to the game - 0 to 1 over the game
     * @return true if the touch is handled
     */
    private boolean onTouched(View view, float x, float y) {
        return false;
    }

    /**
     * Handle a release of a touch message.
     * @param x x location for the touch release, relative to the game - 0 to 1 over the game
     * @param y y location for the touch release, relative to the game - 0 to 1 over the game
     * @return true if the touch is handled
     */
    private boolean onReleased(View view, float x, float y) {
        return false;
    }

    /**
     * Handles advancing to the next player's turn
     */
    public void nextTurn() {

    }

    /**
     * Determines if a player has won the game
     * @return Name of a player if they have won the game, null otherwise
     */
    public String hasWon() {
        return null;
    }

    /**
     * Save the puzzle to a bundle
     * @param bundle The bundle we save to
     */
    public void saveInstanceState(Bundle bundle) {

    }

    /**
     * Read the puzzle from a bundle
     * @param bundle The bundle we save to
     */
    public void loadInstanceState(Bundle bundle) {

    }

    /**
     * Determine which space a piece should snap to depending on it's position.
     * This function does not test valid moves, only which space to attempt to move to based on its
     * position.
     * @param x X position of the piece
     * @param y Y position of the piece
     * @return The grid coordinate of the space to snap to. If it is not close enough to any space,
     * return null.
     */
    private static Point snapToSpace(float x, float y) {
        return null;
    }

    /**
     * Determine if a move from one space to another is valid.
     * @param beginSpace Space the piece will be moving from
     * @param endSpace Space the piece will be moving to
     * @return True if the move is valid
     */
    private static boolean isValidMove(Point beginSpace, Point endSpace) {
        return false;
    }
}
