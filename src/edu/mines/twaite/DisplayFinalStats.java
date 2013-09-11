package edu.mines.twaite;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayFinalStats extends Activity {

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_final_stats);
        Intent intent = getIntent();
        
        //Set the team names
        String teamOneName = intent.getStringExtra("teamOne");
        //Set name to a default value of "Team One" if string is empty
        if (teamOneName.equals("")) teamOneName = "Team One";
        TextView teamOne = (TextView) findViewById(R.id.teamOneGO);
        teamOne.setText(teamOneName);
        
        String teamTwoName = intent.getStringExtra("teamTwo");
        if (teamTwoName.equals("")) teamTwoName = "Team Two";
        TextView teamTwo = (TextView) findViewById(R.id.teamTwoGO);
        teamTwo.setText(teamTwoName);
        
        //Set the scores
        int teamOneScore = intent.getIntExtra("scoreOne", 0);
        TextView teamOneScoreFinal = (TextView) findViewById(R.id.scoreOneFinal);
        teamOneScoreFinal.setText("Score: " + Integer.toString(teamOneScore));
        
        int teamTwoScore = intent.getIntExtra("scoreTwo", 0);
        String teamTwoScoreStr = Integer.toString(teamTwoScore);
        teamTwoScoreStr = "Score: " + teamTwoScoreStr;
        TextView teamTwoScoreFinal = (TextView) findViewById(R.id.scoreTwoFinal);
        teamTwoScoreFinal.setText(teamTwoScoreStr);
        
        //Display the number of touchdowns
        int touchdowns = intent.getIntExtra("scoreOneFinal", 0);
        String touchdownsStr = Integer.toString(touchdowns);
        touchdownsStr = "Touchdowns Scored: " + touchdownsStr;
        TextView touchdownsDisplay = (TextView) findViewById(R.id.touchdowns);
        touchdownsDisplay.setText(touchdownsStr);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
