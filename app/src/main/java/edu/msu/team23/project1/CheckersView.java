package edu.msu.team23.project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Custom view class for our Puzzle.
 */
public class CheckersView extends View {
    /**
     * Checkers game for this view
     */
    private CheckersGame checkersGame;

    public CheckersView(Context context) {
        super(context);
        init(context);
    }

    public CheckersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CheckersView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * Initialize the view
     * @param context Context of this application
     */
    private void init(Context context) {
        Intent intent = ((Activity)context).getIntent();
        checkersGame = new CheckersGame(getContext(), this, intent.getStringExtra(MainActivity.GREEN_PLAYER), intent.getStringExtra(MainActivity.WHITE_PLAYER));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        checkersGame.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return checkersGame.onTouchEvent(this, event);
    }

    /**
     * Save the game to a bundle
     * @param bundle The bundle we save to
     */
    public void saveInstanceState(Bundle bundle) {

    }

    /**
     * Load the game from a bundle
     * @param bundle The bundle we save to
     */
    public void loadInstanceState(Bundle bundle) {

    }

    /**
     * Get the checkers game associated with this view
     * @return The checkers game associated with this view
     */
    public CheckersGame getCheckersGame() {
        return checkersGame;
    }

    public void externalSetup() {
        checkersGame.externalSetup();
    }

    public void onDone() {
        checkersGame.nextTurn();
    }

    public void onResign() {
        checkersGame.handleResign();
    }
}
