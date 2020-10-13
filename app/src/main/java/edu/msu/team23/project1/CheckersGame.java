package edu.msu.team23.project1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.MessageFormat;

public class CheckersGame {
    /**
     * Enumeration for the teams in the game
     */
    enum Team {
        WHITE,
        GREEN
    }

    /**
     * Image of the checker board
     */
    private final Bitmap BOARD_IMAGE;

    /**
     * Number of spaces on a side of the board
     */
    private static final int SPACE_WIDTH = 8;

    /**
     * Bundle keys for saving and loading state
     */
    private static final String CHECKERS_PIECES = "CheckersGame.checkersPieces";
    private static final String GREEN_PLAYER = "CheckersGame.greenPlayer";
    private static final String WHITE_PLAYER = "CheckersGame.whitePlayer";
    private static final String TEAM_TURN = "CheckersGame.teamTurn";

    /**
     * View this checkers game is in
     */
    private CheckersView view;

    /**
     * Name of the player on the green team
     */
    private String greenPlayer;

    /**
     * Name of the player on the white team
     */
    private String whitePlayer;

    /**
     * Name of the player whose turn it is.
     */
    private Team teamTurn;

    /**
     * Representation of the game board.
     */
    private CheckersPiece[][] board;

    /**
     * The checkers piece being dragged. If we are not dragging, the variable is null
     */
    private CheckersPiece draggingPiece = null;

    /**
     * The space the dragging piece came from, if there is no dragging piece, the variable will be null
     */
    private Point draggingSpace = null;

    /**
     * Most recent relative X touch when dragging
     */
    private float lastRelX;

    /**
     * Most recent relative Y touch when dragging
     */
    private float lastRelY;

    /**
     * Size of the board in pixels
     */
    private int boardSize;

    /**
     * Constructor foe the CheckersGame class
     * @param context Application context
     * @param view View this game is a part of
     * @param greenPlayer Name of the player on the green team
     * @param whitePlayer Name of the player on the white team
     */
    public CheckersGame(Context context, CheckersView view, String greenPlayer, String whitePlayer) {
        BOARD_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.board);
        this.greenPlayer = greenPlayer;
        this.whitePlayer = whitePlayer;
        this.view = view;
        reset(context);
    }

    /**
     * Draws the checkers game
     * @param canvas Canvas to draw on
     */
    public void draw(Canvas canvas) {
        // Determine the size of the board
        boardSize = canvas.getWidth();

        // Determine where to draw the board and how much to scale it
        float boardScaleFactor = (float)boardSize / (float)BOARD_IMAGE.getWidth();

        // Draw the board
        canvas.save();
        canvas.scale(boardScaleFactor, boardScaleFactor);
        canvas.drawBitmap(BOARD_IMAGE, 0, 0, null);
        canvas.restore();

        // Draw the pieces
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[col].length; row++) {
                if (board[row][col] != null) {
                    board[row][col].draw(canvas, boardSize);
                }
            }
        }

        // Draw the dragging piece again so it will appear on top
        if (draggingPiece != null) {
            draggingPiece.draw(canvas, boardSize);
        }
    }

    /**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {
        // Convert an x,y location to a relative location on the board
        float relX = event.getX() / boardSize;
        float relY = event.getY() / boardSize;

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                return onTouched(view, relX, relY);
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                return onReleased(view, relX, relY);
            case MotionEvent.ACTION_MOVE:
                return onMove(view, relX, relY);
        }
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
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null && board[row][col].getTeam() == teamTurn && board[row][col].hit(x, y, boardSize)) {
                    draggingPiece = board[row][col];
                    draggingSpace = new Point(row, col);
                    lastRelX = x;
                    lastRelY = y;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Handle a release of a touch message.
     * @param x x location for the touch release, relative to the game - 0 to 1 over the game
     * @param y y location for the touch release, relative to the game - 0 to 1 over the game
     * @return true if the touch is handled
     */
    private boolean onReleased(View view, float x, float y) {
        if (draggingPiece != null) {
            draggingPiece = null;
            draggingSpace = null;
            return true;
        }
        return false;
    }

    /**
     * Handle a move message.
     * @param relX Most recent relative X touch when dragging
     * @param relY Most recent relative Y touch when dragging
     * @return true if the touch is handled
     */
    private boolean onMove(View view, float relX, float relY) {
        if(draggingPiece != null) {
            //
            // Gather values for below calculations
            //
            // Image of the piece
            Bitmap image = draggingPiece.getImage();
            // Scale factor of the piece
            float scaleFactor = (float)boardSize / (float)image.getWidth() / 8;
            // Size of half od the piece in pixels
            int halfPieceSize = (int)(draggingPiece.getImage().getWidth() * scaleFactor / 2);
            // Relative position of the piece
            PointF pieceRelPos = draggingPiece.getRelPos();
            // Proposed new relative X position from the move
            int pendingNewX = (int)((pieceRelPos.x + (relX - lastRelX)) * boardSize);
            // Proposed new relative Y position from the move
            int pendingNewY = (int)((pieceRelPos.y + (relY - lastRelY)) * boardSize);

            float dx; // Relative distance to move in the X
            float dy; // Relative distance to move in the Y

            //
            // Determine if the proposed move will move the piece off the board in X and Y directions.
            // If it will go off the board, don't let it, otherwise move as normal.
            //
            if (pendingNewX - halfPieceSize < 0) {
                dx = 0;
                lastRelX = (0f + halfPieceSize) / boardSize;
                draggingPiece.setPosition(lastRelX, pieceRelPos.y);
            } else if (pendingNewX + halfPieceSize > boardSize) {
                dx = 0;
                lastRelX = (float)(boardSize - halfPieceSize) / boardSize;
                draggingPiece.setPosition(lastRelX, pieceRelPos.y);
            } else {
                dx = relX - lastRelX;
            }

            if (pendingNewY - halfPieceSize < 0) {
                dy = 0;
                lastRelY = (0f + halfPieceSize) / boardSize;
                draggingPiece.setPosition(pieceRelPos.x, lastRelY);
            } else if (pendingNewY + halfPieceSize > boardSize) {
                dy = 0;
                lastRelY = (float)(boardSize - halfPieceSize) / boardSize;
                draggingPiece.setPosition(pieceRelPos.x, lastRelY);
            } else {
                dy = relY - lastRelY;
            }

            draggingPiece.move(dx, dy);
            lastRelX = dx == 0 ? lastRelX : relX;
            lastRelY = dy == 0 ? lastRelY : relY;
            view.invalidate();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles advancing to the next player's turn
     */
    public void nextTurn(Context context) {
        teamTurn = teamTurn == Team.GREEN ? Team.WHITE : Team.GREEN;
        setTurnView(context, teamTurn);
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

    /**
     * Reset the game to its initial state
     */
    public void reset(Context context) {
        // Set the turn
        teamTurn = Team.GREEN;

        // Set the spaces of all the pieces
        board = new CheckersPiece[][]{
                {null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE)},
                {new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null},
                {null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE), null, new CheckersPiece(context, Team.WHITE)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null},
                {null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN)},
                {new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null, new CheckersPiece(context, Team.GREEN), null},
        };

        // Set the actual piece positions on the screen
        setPiecePositions();
    }

    /**
     * Setup for views outside this view
     * @param context Context of this application
     */
    public void externalSetup(Context context) {
        setTurnView(context, Team.GREEN);
    }

    /**
     * Change the display to say who's turn it is
     */
    private void setTurnView(Context context, Team team) {
        TextView turnView = (TextView)((Activity) context).findViewById(R.id.turnView);
        turnView.setText(MessageFormat.format("{0}{1}", team == Team.GREEN ? greenPlayer : whitePlayer, context.getResources().getString(R.string.turn_suffix)));
    }

    /**
     * Update the relative position of all the pieces based on their space
     */
    private void setPiecePositions() {
        for (int col = 0; col < board.length; col ++) {
            for (int row = 0; row < board[col].length; row++) {
                if (board[row][col] != null) {
                    PointF pos = spaceToPos(row, col);
                    board[row][col].setPosition(pos.x, pos.y);
                }
            }
        }
    }

    /**
     * Generate a relative position on the screen based on the space the piece is in
     * @param row Row of the space
     * @param col Column of the space
     * @return relative position of the piece
     */
    private PointF spaceToPos(int row, int col) {
        float toCenterOfSpace = (1f/SPACE_WIDTH/2);
        float toNextSpace = (1f/SPACE_WIDTH);
        return new PointF(toCenterOfSpace + (toNextSpace * col), toCenterOfSpace + (toNextSpace * row));
    }
}
