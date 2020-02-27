package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ScoreData implements Parcelable {
    private String homeTeamName;
    private String awayTeamName;
    private String teamHomeScore;
    private String teamAwayScore;
    private Uri homeLogoUri;
    private Uri awayLogoUri;
    private String [] homeScorer;
    private String [] awayScorer;

    //constructor for activity main

    public ScoreData(String homeTeamName, String awayTeamName, String teamHomeScore, String teamAwayScore, Uri homeLogoUri, Uri awayLogoUri) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.teamHomeScore = teamHomeScore;
        this.teamAwayScore = teamAwayScore;
        this.homeLogoUri = homeLogoUri;
        this.awayLogoUri = awayLogoUri;
    }

    //construcktor for scorer Activity


    public ScoreData(String[] homeScorer, String[] awayScorer) {
        this.homeScorer = homeScorer;
        this.awayScorer = awayScorer;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getTeamHomeScore() {
        return teamHomeScore;
    }

    public void setTeamHomeScore(String teamHomeScore) {
        this.teamHomeScore = teamHomeScore;
    }

    public String getTeamAwayScore() {
        return teamAwayScore;
    }

    public void setTeamAwayScore(String teamAwayScore) {
        this.teamAwayScore = teamAwayScore;
    }

    public Uri getHomeLogoUri() {
        return homeLogoUri;
    }

    public void setHomeLogoUri(Uri homeLogoUri) {
        this.homeLogoUri = homeLogoUri;
    }

    public Uri getAwayLogoUri() {
        return awayLogoUri;
    }

    public void setAwayLogoUri(Uri awayLogoUri) {
        this.awayLogoUri = awayLogoUri;
    }

    public String[] getHomeScorer() {
        return homeScorer;
    }

    public void setHomeScorer(String[] homeScorer) {
        this.homeScorer = homeScorer;
    }

    public String[] getAwayScorer() {
        return awayScorer;
    }

    public void setAwayScorer(String[] awayScorer) {
        this.awayScorer = awayScorer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeTeamName);
        dest.writeString(this.awayTeamName);
        dest.writeString(this.teamHomeScore);
        dest.writeString(this.teamAwayScore);
        dest.writeParcelable(this.homeLogoUri, flags);
        dest.writeParcelable(this.awayLogoUri, flags);
        dest.writeStringArray(this.homeScorer);
        dest.writeStringArray(this.awayScorer);
    }

    protected ScoreData(Parcel in) {
        this.homeTeamName = in.readString();
        this.awayTeamName = in.readString();
        this.teamHomeScore = in.readString();
        this.teamAwayScore = in.readString();
        this.homeLogoUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayLogoUri = in.readParcelable(Uri.class.getClassLoader());
        this.homeScorer = in.createStringArray();
        this.awayScorer = in.createStringArray();
    }

    public static final Parcelable.Creator<ScoreData> CREATOR = new Parcelable.Creator<ScoreData>() {
        @Override
        public ScoreData createFromParcel(Parcel source) {
            return new ScoreData(source);
        }

        @Override
        public ScoreData[] newArray(int size) {
            return new ScoreData[size];
        }
    };
}
