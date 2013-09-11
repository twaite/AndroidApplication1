package edu.mines.twaite;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DisplayFinalStats extends Activity {

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_final_stats);
        Intent intent = getIntent();
        
        //Set the scores, must be done first to decide the winner.
        int teamOneScore = intent.getIntExtra("scoreOne", 0);
        TextView teamOneScoreFinal = (TextView) findViewById(R.id.scoreOneFinal);
        teamOneScoreFinal.setText("Score: " + Integer.toString(teamOneScore));
        
        int teamTwoScore = intent.getIntExtra("scoreTwo", 0);
        String teamTwoScoreStr = Integer.toString(teamTwoScore);
        teamTwoScoreStr = "Score: " + teamTwoScoreStr;
        TextView teamTwoScoreFinal = (TextView) findViewById(R.id.scoreTwoFinal);
        teamTwoScoreFinal.setText(teamTwoScoreStr);
        
      //Set the team names
        String teamOneName = intent.getStringExtra("teamOne");
        //Set name to a default value of "Team One" if string is empty
        if (teamOneName.equals("")) teamOneName = "Team One";
        teamOneName = addWinLossToString(teamOneName, teamOneScore, teamTwoScore);
        TextView teamOne = (TextView) findViewById(R.id.teamOneGO);
        teamOne.setText(teamOneName);
        
        String teamTwoName = intent.getStringExtra("teamTwo");
        if (teamTwoName.equals("")) teamTwoName = "Team Two";
        teamTwoName = addWinLossToString(teamTwoName, teamTwoScore, teamOneScore);
        TextView teamTwo = (TextView) findViewById(R.id.teamTwoGO);
        teamTwo.setText(teamTwoName);
        
        //Display the number of touchdowns
        int touchdownsOne = intent.getIntExtra("tdOne", 0);
        TextView touchdownsDisplayOne = (TextView) findViewById(R.id.touchdownsOne);
        touchdownsDisplayOne.setText("Touchdowns scored: " + Integer.toString(touchdownsOne));
        
        int touchdownsTwo = intent.getIntExtra("tdTwo", 0);
        TextView touchdownsDisplayTwo = (TextView) findViewById(R.id.touchdownsTwo);
        touchdownsDisplayTwo.setText("Touchdowns scored: " + Integer.toString(touchdownsTwo));
        
        //Display the number of extra points
        int extraPointsOne = intent.getIntExtra("epOne", 0);
        TextView extraPointsDisplayOne = (TextView) findViewById(R.id.extraPointsOne);
        extraPointsDisplayOne.setText("Extra points scored: " + Integer.toString(extraPointsOne));
        
        int extraPointsTwo = intent.getIntExtra("epTwo", 0);
        TextView extraPointsDisplayTwo = (TextView) findViewById(R.id.extraPointsTwo);
        extraPointsDisplayTwo.setText("Extra points scored: " + Integer.toString(extraPointsTwo));
        
      //Display the number of field goals
        int fieldGoalOne = intent.getIntExtra("fgOne", 0);
        TextView fieldGoalDisplayOne = (TextView) findViewById(R.id.fieldGoalOne);
        fieldGoalDisplayOne.setText("Field goals scored: " + Integer.toString(fieldGoalOne));
        
        int fieldGoalTwo = intent.getIntExtra("fgTwo", 0);
        TextView fieldGoalDisplayTwo = (TextView) findViewById(R.id.fieldGoalTwo);
        fieldGoalDisplayTwo.setText("Extra points scored: " + Integer.toString(fieldGoalTwo));
        
      //Display the number of safeties and conversions
        int safetyConversionsOne = intent.getIntExtra("scOne", 0);
        TextView safetyConversionDisplayOne = (TextView) findViewById(R.id.safetyConversionsOne);
        safetyConversionDisplayOne.setText("Safeties/Conversions scored: " + Integer.toString(safetyConversionsOne));
        
        int safetyConversionsTwo = intent.getIntExtra("scTwo", 0);
        TextView safetyConversionDisplayTwo = (TextView) findViewById(R.id.safetyConversionsTwo);
        safetyConversionDisplayTwo.setText("Safeties/Conversions scored: " + Integer.toString(safetyConversionsTwo));

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
    
    public String addWinLossToString(String name, int score, int otherScore) {
    	if ( score > otherScore ) {
    		return name + " Won!";
    	} else if ( otherScore > score ) {
    		return name + " Lost!";
    	} else if ( otherScore == score ) {
    		return name + " Tied!";
    	} else {
    		return name;
    	}
    }
    
    public void newGame(View view) {
    	super.onBackPressed();
    }
}
