package edu.msu.team23.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class CheckersActivity extends AppCompatActivity {
    public static final String WINNER = "WINNER";
    public static final String LOSER = "LOSER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers);
        getCheckersView().externalSetup();
    }

    /**
     * Save the instance state into a bundle
     * @param bundle the bundle to save into
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /**
     * Get the checkers view
     * @return CheckersView reference
     */
    private CheckersView getCheckersView() {
        return (CheckersView)findViewById(R.id.checkersView);
    }

    public void onDone(View view) {
        getCheckersView().onDone();
    }

    public void onResign(View view) {
        getCheckersView().onResign();
    }
}