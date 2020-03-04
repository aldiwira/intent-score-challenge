package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView winningStat;
    private ScoreData scoreData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        winningStat = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            scoreData = getIntent().getParcelableExtra("DATA_KEY");
            winningStat.setText("The Winner Is ");
        }
    }
    private String checkWinner() {
        String stat;
        int home = Integer.parseInt(scoreData.getTeamHomeScore());
        int away = Integer.parseInt(scoreData.getTeamAwayScore());

        if (home > away){
            stat = scoreData.getHomeTeamName();
        } else if(home < away){
            stat = scoreData.getAwayTeamName();
        } else {
            stat = "draw";
        }
        return stat;
    }
}
