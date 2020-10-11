package edu.msu.team23.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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
        if (areFieldsValid()) {
            Intent intent = new Intent(this, CheckersActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Determine if the entries in the fields on the main activity are valid
     * @return True if the entries in the fields on the main activity are valid
     */
    private boolean areFieldsValid() {
        boolean validFields = true;

        EditText greenPlayer = findViewById(R.id.greenPlayerNameField);
        EditText whitePlayer = findViewById(R.id.whitePlayerNameField);

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