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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    public static String DATA_KEY = "DATA_KEY";
    public static int HOME_LOGO_REQUEST = 1;
    public static int AWAY_LOGO_REQUEST = 2;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Uri homeUri;
    private Uri awayUri;
    private EditText homeTeamName;
    private EditText awayTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeTeamName = findViewById(R.id.home_team);
        awayTeamName = findViewById(R.id.away_team);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team



    }
    //3. Ganti Logo Home Team
    public void handleChangeHome(View view) {
        Intent intentHome = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentHome, HOME_LOGO_REQUEST);
    }
    //4. Ganti Logo Away Team
    public void handleChangeAway(View view) {
        Intent intentAway = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentAway, AWAY_LOGO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return ;
        }
        if (requestCode == HOME_LOGO_REQUEST){
            if (data != null){
                try {
                    homeUri = data.getData();
                    Bitmap home = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                    homeLogo.setImageBitmap(home);
                } catch(IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if(requestCode == AWAY_LOGO_REQUEST){
            if (data != null){
                try {
                    awayUri = data.getData();
                    Bitmap home = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
                    awayLogo.setImageBitmap(home);
                } catch(IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    //5. Next Button Pindah Ke MatchActivity
    public void handleNextScore(View view) {
        String homename = homeTeamName.getText().toString();
        String awayname = awayTeamName.getText().toString();
        Uri homePathImage = homeUri;
        Uri awayPathImage = awayUri;
        String homeScore = "0";
        String awayScore = "0";
        ScoreData dataTeam = new ScoreData(homename, awayname, homeScore, awayScore, homePathImage, awayPathImage);
        Intent dataTeamIntent = new Intent(this, MatchActivity.class);
        dataTeamIntent.putExtra(DATA_KEY, dataTeam);
        startActivity(dataTeamIntent);
    }
}
