package edu.msu.team23.project1;

import android.content.Context;
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
        init(null, 0);
    }

    public CheckersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CheckersView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        checkersGame = new CheckersGame(getContext(), this, "testGreen", "testWhite");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        checkersGame.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
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
        return null;
    }
}
