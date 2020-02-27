package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    private static final String TAG = MatchActivity.class.getCanonicalName();
    public static String DATA_KEY = "DATA_KEY";
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Uri homeUri;
    private Uri awayUri;
    private TextView homeTeamName;
    private TextView awayTeamName;
    private TextView homeScore;
    private TextView awayScore;
    private Intent intentWorld;
    private String[] scorerPlayerHome;
    private String[] scorerPlayerAway;
    //TODO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //1.Menampilkan detail match sesuai data dari main activity
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        homeTeamName = findViewById(R.id.txt_home);
        awayTeamName = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle != null){
            try {
                ScoreData dataTeam = getIntent().getParcelableExtra(DATA_KEY);
                homeTeamName.setText(dataTeam.getHomeTeamName());
                awayTeamName.setText(dataTeam.getAwayTeamName());
                homeScore.setText(dataTeam.getTeamHomeScore());
                awayScore.setText(dataTeam.getTeamAwayScore());
                Bitmap homePath = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dataTeam.getHomeLogoUri());
                homeLogo.setImageBitmap(homePath);
                Bitmap awayPath = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dataTeam.getAwayLogoUri());
                awayLogo.setImageBitmap(awayPath);
            } catch (IOException e){
                Log.e(TAG, e.getMessage());
            }
        }


        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
    }

    public void handleAddScoreHome(View view) {
        intentWorld = new Intent(this, ScorerActivity.class);
        startActivityForResult(intentWorld, 1);
    }

    public void handleAddScoreAway(View view) {
        intentWorld = new Intent(this, ScorerActivity.class);
        startActivityForResult(intentWorld, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CANCELED){
            return;
        }
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String namePlayer = data.getStringExtra("name_player");
                int scoreHome = Integer.parseInt(homeScore.getText().toString());
                scoreHome++;
                String skor = Integer.toString(scoreHome);
                scorerPlayerHome = new String[scoreHome];
                scorerPlayerHome[scoreHome-1] = namePlayer;
                homeScore.setText(skor);
            }
        } else if(requestCode == 2){
            if (resultCode == RESULT_OK){
                String namePlayer = data.getStringExtra("name_player");
                int scoreAway = Integer.parseInt(awayScore.getText().toString());
                scoreAway++;
                String skor = Integer.toString(scoreAway);
                scorerPlayerAway = new String[scoreAway];
                scorerPlayerHome[scoreAway-1] = namePlayer;
                awayScore.setText(skor);
            }
        }
    }
}
