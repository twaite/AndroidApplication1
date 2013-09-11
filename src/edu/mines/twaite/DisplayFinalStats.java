/**
* Description: This class implements the second activity for this application.
* It will display the team names and scores from the previous activity and 
* determine a winner based on the score. It also allows a user to start a new
* game.
*
* @author Timothy Waite
*/

package edu.mines.twaite;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DisplayFinalStats extends Activity {
	
	/**
	* This method creates the initial view based on the xml file. It will also
	* initialize all the variables to zero. It also loads all the variables from
	* the intent so that the screen can be generated with all of the necessary
	* statistics.
	* 
	* @param savedInstanceState This preserves the instance variables.
	*/
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
        fieldGoalDisplayTwo.setText("Field goals scored: " + Integer.toString(fieldGoalTwo));
        
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
    
    /**
	* This method creates the options menu.
	* 
	* @param menu The options menu to create
	* @return Returns true if the menu is created.
	*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * This method will determine whether the team won, lost, or tied and update
     * the string accordingly.
     * 
     * @param name This is the team name
     * @param score This is the team's score
     * @param otherScore This is the opposing team's score
     * @return Returns the updated string according to win, loss or tie.
     */
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
    
    /**
     * This function will close out the DisplayFinalStats activity and return to the original
     * activity. Because the original activity has been reinitialized it gives the appearance
     * of beginning a new game.
     * 
     * @param view This loads the view so that the button can execute the function onClick
     */
    public void newGame(View view) {
    	super.finish();
    }
    
    /**
     * This overrides the parent function onBackPressed so that the user cannot go back to the
     * main activity without selecting new game.
     * 
     */
    public void onBackPressed() {
    	
    }
}
