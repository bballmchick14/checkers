package edu.msu.team23.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers);
        Intent intent = getIntent();
        getCheckersView().externalSetup(this);
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
        getCheckersView().onDone(this);
    }
}