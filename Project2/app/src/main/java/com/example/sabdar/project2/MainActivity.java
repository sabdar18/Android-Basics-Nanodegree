package com.example.sabdar.project2;

import android.content.res.Resources;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //constant Variables for Storing Scores Variables
    private static final String TEAM_A_SCORE = "TEAM_A_SCORE";
    private static final String TEAM_B_SCORE = "TEAM_B_SCORE";

    //constant Scores
    private static final int ZERO_POINT  = 0;
    private static final int ONE_POINT = 1;
    private static final int TWO_POINTS = 2;
    private static final int THREE_POINTS = 3;

    //Declaring variables for Scores & initialize
    private int teamAScore;
    private int teamBScore;

    //Declaring TextViews
    private TextView teamAScoreTV;
    private TextView teamBScoreTV;

    //Declaring Buttons
    private Button teamAOnePointButton;
    private Button teamATwoPointButton;
    private Button teamAThreePointButton;
    private Button teamBOnePointButton;
    private Button teamBTwoPointButton;
    private Button teamBThreePointButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize textViews
        teamAScoreTV = findViewById(R.id.teamA_ScoreTV);
        teamBScoreTV = findViewById(R.id.teamB_ScoreTV);

        //Initialize Buttons
        teamAOnePointButton = findViewById(R.id.teamA_OnePointButton);
        teamATwoPointButton = findViewById(R.id.teamA_TwoPointButton);
        teamAThreePointButton = findViewById(R.id.teamA_ThreePointButton);
        teamBOnePointButton = findViewById(R.id.teamB_OnePointButton);
        teamBTwoPointButton = findViewById(R.id.teamB_TwoPointButton);
        teamBThreePointButton = findViewById(R.id.teamB_ThreePointButton);
        resetButton = findViewById(R.id.resetButton);

        //set onclick listener for buttons
        teamAOnePointButton.setOnClickListener(this);
        teamATwoPointButton.setOnClickListener(this);
        teamAThreePointButton.setOnClickListener(this);
        teamBOnePointButton.setOnClickListener(this);
        teamBTwoPointButton.setOnClickListener(this);
        teamBThreePointButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        //add Dynamic Content Description For Buttons and Score TextView
        initContentDescription();
    }

    //add Dynamic Content Description For Buttons and Score TextView
    private void initContentDescription() {
        Resources res = getResources();
        teamAOnePointButton.setContentDescription(res.getQuantityString(R.plurals.buttonDescription, ONE_POINT, getString(R.string.teamA), ONE_POINT));
        teamATwoPointButton.setContentDescription(res.getQuantityString(R.plurals.buttonDescription, TWO_POINTS, getString(R.string.teamA), TWO_POINTS));
        teamAThreePointButton.setContentDescription(res.getQuantityString(R.plurals.buttonDescription, THREE_POINTS, getString(R.string.teamA), THREE_POINTS));
        teamBOnePointButton.setContentDescription(res.getQuantityString(R.plurals.buttonDescription, ONE_POINT, getString(R.string.teamB), ONE_POINT));
        teamBTwoPointButton.setContentDescription(res.getQuantityString(R.plurals.buttonDescription, TWO_POINTS, getString(R.string.teamB), TWO_POINTS));
        teamBThreePointButton.setContentDescription(res.getQuantityString(R.plurals.buttonDescription, THREE_POINTS, getString(R.string.teamB), THREE_POINTS));

        //set team Scores Text views content Description
        setTeamScoresDescription();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TEAM_A_SCORE, teamAScore);
        outState.putInt(TEAM_B_SCORE, teamBScore);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        teamAScore = savedInstanceState.getInt(TEAM_A_SCORE);
        teamBScore = savedInstanceState.getInt(TEAM_B_SCORE);
        displayScores();
    }

    //On Click Listener Method for entire Activity
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.teamA_OnePointButton:
                addScoreForTeamA(ONE_POINT);
                break;
            case R.id.teamA_TwoPointButton:
                addScoreForTeamA(TWO_POINTS);
                break;
            case R.id.teamA_ThreePointButton:
                addScoreForTeamA(THREE_POINTS);
                break;
            case R.id.teamB_OnePointButton:
                addScoreForTeamB(ONE_POINT);
                break;
            case R.id.teamB_TwoPointButton:
                addScoreForTeamB(TWO_POINTS);
                break;
            case R.id.teamB_ThreePointButton:
                addScoreForTeamB(THREE_POINTS);
                break;
            case R.id.resetButton:
                resetScores();
                break;
            default:
                break;
        }
    }

    //add Score for team A
    private void addScoreForTeamA(int score) {
        teamAScore += score;
        displayScores();
    }

    //add Score for team B
    private void addScoreForTeamB(int score) {
        teamBScore += score;
        displayScores();
    }

    //display Scores
    private void displayScores() {
        teamAScoreTV.setText(String.valueOf(teamAScore));
        teamBScoreTV.setText(String.valueOf(teamBScore));
        setTeamScoresDescription();
    }

    //set team Scores Text views content Description
    private void setTeamScoresDescription() {
        Resources res = getResources();
        teamAScoreTV.setContentDescription(res.getQuantityString(R.plurals.scoreDescription, teamAScore, getString(R.string.teamA), teamAScore));
        teamBScoreTV.setContentDescription(res.getQuantityString(R.plurals.scoreDescription, teamBScore, getString(R.string.teamB), teamBScore));
    }

    //reset Scores
    private void resetScores() {
        teamAScore = ZERO_POINT;
        teamBScore = ZERO_POINT;
        displayScores();
    }

}
