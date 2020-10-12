package edu.msu.team23.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String GREEN_PLAYER = "GREEN_PLAYER";
    public static final String WHITE_PLAYER = "WHITE_PLAYER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Handler for starting the checkers game activity
     * @param view View the event happened on
     */
    public void onStartGame(View view) {
        EditText greenPlayer = findViewById(R.id.greenPlayerNameField);
        EditText whitePlayer = findViewById(R.id.whitePlayerNameField);

        if (areFieldsValid(greenPlayer, whitePlayer)) {
            Intent intent = new Intent(this, CheckersActivity.class);
            intent.putExtra(GREEN_PLAYER, greenPlayer.getText().toString());
            intent.putExtra(WHITE_PLAYER, whitePlayer.getText().toString());
            startActivity(intent);
        }
    }

    /**
     * Determine if the entries in the fields on the main activity are valid
     * @return True if the entries in the fields on the main activity are valid
     */
    private boolean areFieldsValid(EditText greenPlayer, EditText whitePlayer) {
        boolean validFields = true;

        if (greenPlayer != null && whitePlayer != null) {
            if (greenPlayer.getText().toString().length() == 0) {
                validFields = false;
                greenPlayer.setError(getResources().getString(R.string.missing_name_error));
            }
            if (whitePlayer.getText().toString().length() == 0) {
                validFields = false;
                whitePlayer.setError(getResources().getString(R.string.missing_name_error));
            }
            if (validFields && greenPlayer.getText().toString().equals(whitePlayer.getText().toString())) {
                whitePlayer.setError(getResources().getString(R.string.duplicate_name_error));
                validFields = false;
            }
        } else {
            validFields = false;
        }

        return validFields;
    }
}