package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ScorerActivity extends AppCompatActivity {
    private EditText nameScorer;
    private Intent intentWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        nameScorer = findViewById(R.id.scorer_name_text);
    }

    public void handleScorerName(View view) {
        String namePlay = nameScorer.getText().toString();
        intentWorld = new Intent();
        intentWorld.putExtra("name_player", namePlay);
        setResult(RESULT_OK, intentWorld);
        finish();
    }
}
