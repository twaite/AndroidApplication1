/**
* Description: This is the main activity for this application. It allows the 
* user to choose different scoring options. These options will be displayed on
* the screen. The user can undo these scores with the undo button at the bottom
* of the screen. The user can also enter a team name at the top of the screen. 
* Lastly, there is an end game button that will take the user to the
* DisplayFinalStats activity. This will show all the scores and statistics for
* the game.
*
* Documentation Statement: This application was primarily tested on a LG Optimus 
* Prime running Android 2.2.2. However, upon completion the application was tested 
* using a Nexus 4 emulator with Level 18 API.
* 
* Please note: When starting a new game the team names are preserved. This is
* intentional. 
*
* @author Timothy Waite
*/
//TODO
package edu.mines.twaite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreScreen extends Activity {
	int scoreOne; //Stores the score of the first team.
	int scoreTwo; //Stores the score of the second team.
	int touchDownOne; //Stores the number of touch downs for team one.
	int fieldGoalOne; //Stores the number of touch downs for team one.
	int extraPointsOne; //Stores the number of extra points for team one.
	int safetyConversionOne; // Stores the number of safeties and conversions for team one.
	int touchDownTwo; //Stores the number of touch downs for team two.
	int fieldGoalTwo; //Stores the number of touch downs for team two.
	int extraPointsTwo; //Stores the number of extra points for team two.
	int safetyConversionTwo; // Stores the number of safeties and conversions for team two.
	ArrayList<Integer> scoreTracker; // Records the points in the order they are added.
	ArrayList<String> teamTracker; // Records a team every time points are scored.
	
	/**
	* This method creates the initial view based on the xml file. It will also
	* initialize all the variables to zero.
	* 
	* @param savedInstanceState This preserves the instance variables.
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_screen);
		initializeVariables();
		updateScoreOne(scoreOne);
		updateScoreTwo(scoreTwo);
	}
	
	/**
	* This method preserves the instance variables on a call to onStop() or
	* onPause(). This allows the user to later return without their data being
	* destroyed. The method simply stores all the instance variables in a map.
	* onSaveInstanceState will also collect the user data entered for the team
	* names and store it for later.
	* 
	* @param savedInstanceState This preserves the instance variables.
	*/
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		
		// Stores all the instance variables in a map.
		savedInstanceState.putInt("scoreOne", scoreOne);
		savedInstanceState.putInt("scoreTwo", scoreTwo);
		savedInstanceState.putInt("tdOne", touchDownOne);
		savedInstanceState.putInt("tdTwo", touchDownTwo);
		savedInstanceState.putInt("epOne", extraPointsOne);
		savedInstanceState.putInt("epTwo", extraPointsTwo);
		savedInstanceState.putInt("fgOne", fieldGoalOne);
		savedInstanceState.putInt("fgTwo", fieldGoalTwo);
		savedInstanceState.putInt("scOne", safetyConversionOne);
		savedInstanceState.putInt("scTwo", safetyConversionTwo);
		savedInstanceState.putIntegerArrayList("scoreTracker", scoreTracker);
		savedInstanceState.putStringArrayList("teamTracker", teamTracker);
		
		//Loads the team names that the user has entered into the map.
		EditText nameOne = (EditText) findViewById(R.id.teamNameOne);
		String nameOneStr = nameOne.getText().toString();
		savedInstanceState.putString("nameOne", nameOneStr);
		
		EditText nameTwo = (EditText) findViewById(R.id.teamNameTwo);
		String nameTwoStr = nameTwo.getText().toString();
		savedInstanceState.putString("nameTwo", nameTwoStr);
	}
	
	/**
	* This method restores all instance variables back to their original state
	* by loading them from the map. It will also return the user entered team
	* names into their EditText fields.
	* 
	* @param savedInstanceState This preserves the instance variables.
	*/
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		// Returns instance variables to their original values.
		scoreOne = savedInstanceState.getInt("scoreOne");
		scoreTwo = savedInstanceState.getInt("scoreTwo");
		touchDownOne = savedInstanceState.getInt("tdOne");
		touchDownTwo = savedInstanceState.getInt("tdTwo");
		extraPointsOne = savedInstanceState.getInt("epOne");
		extraPointsTwo = savedInstanceState.getInt("epTwo");
		fieldGoalOne = savedInstanceState.getInt("fgOne");
		fieldGoalTwo = savedInstanceState.getInt("fgTwo");
		safetyConversionOne = savedInstanceState.getInt("csOne");
		safetyConversionTwo = savedInstanceState.getInt("csTwo");
		scoreTracker = savedInstanceState.getIntegerArrayList("scoreTracker");
		teamTracker = savedInstanceState.getStringArrayList("teamTracker");
		
		// Draws the scores onto the screen again.
		updateScoreOne(scoreOne);
		updateScoreTwo(scoreTwo);
		
		// Returns the user entered team names into their EditText boxes
		EditText nameOne = (EditText) findViewById(R.id.teamNameOne);
		nameOne.setText(savedInstanceState.getString("nameOne"));
		
		EditText nameTwo = (EditText) findViewById(R.id.teamNameTwo);
		nameTwo.setText(savedInstanceState.getString("nameTwo"));
	}
	
	/**
	* This method simply calls it's parent method, but will allow the instance
	* variables to be stored when the app pauses.
	* 
	*/
	public void onPause() {
		super.onPause();
	}

	/**
	 * This method will allow the instance variables to be reset without crashing the app.
	 * 
	 */
	public void onResume() {
		super.onResume();
	}
	
	/**
	 * This method allows the app to stop and record all instance variables.
	 * 
	 */
	public void onStop() {
		super.onStop();
	}
	
	/**
	 * This method will destroy the application when a force close is called or the
	 * phone is turned off.
	 * 
	 */
	public void onDestroy() {
		super.onDestroy();
	}

	/**
	* This method creates the options menu.
	* 
	* @param menu The options menu to create
	* @return Returns true if the menu is created.
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	* This method will add a score of 6 points to team one. It will also update the score
	* box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void touchdownScoreOne(View view) {
		if (!scoreTooHigh(scoreOne)) {
			scoreTracker.add(6);
			teamTracker.add("O");
			scoreOne += 6;
			touchDownOne++;
			updateScoreOne(scoreOne);
		}
	}
	
	/**
	* This method will add a score of 6 points to team two. It will also update the score
	* box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void touchdownScoreTwo(View view) {
		if (!scoreTooHigh(scoreTwo)) {
			scoreTracker.add(6);
			teamTracker.add("T");
			scoreTwo += 6;
			touchDownTwo++;
			updateScoreTwo(scoreTwo);
		}
	}
	
	/**
	* This method will add a score of 3 points to team one. It will also update the score
	* box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void fieldgoalScoreOne(View view) {
		if (!scoreTooHigh(scoreOne)) {
			scoreTracker.add(3);
			teamTracker.add("O");
			scoreOne += 3;
			fieldGoalOne++;
			updateScoreOne(scoreOne);
		}
	}
	
	/**
	* This method will add a score of 3 points to team two. It will also update the score
	* box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void fieldgoalScoreTwo(View view) {
		if (!scoreTooHigh(scoreTwo)) {
			scoreTracker.add(3);
			teamTracker.add("T");
			scoreTwo += 3;
			fieldGoalTwo++;
			updateScoreTwo(scoreTwo);
		}
	}
	
	/**
	* This method will add a score of 1 point to team one, if the team just had a touchdown
	* scored previously. It will also update the score box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void extrapointScoreOne(View view) {
		if (!scoreTooHigh(scoreOne)) {
			if ((scoreTracker.size() > 0) &&
					(scoreTracker.get(scoreTracker.size()-1) == 6) && 
						(teamTracker.get(scoreTracker.size() - 1).equals("O"))) {
				scoreTracker.add(1);
				teamTracker.add("O");
				scoreOne += 1;
				extraPointsOne++;
				updateScoreOne(scoreOne);
			} else {
				String extraPointWarning = "Extra points can only be scored following a touchdown.";
				Toast.makeText(getApplicationContext(), extraPointWarning, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/**
	* This method will add a score of 1 point to team two, if the team just had a touchdown
	* scored previously. It will also update the score box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void extrapointScoreTwo(View view) {
			if (!scoreTooHigh(scoreTwo)) {
			if ((scoreTracker.size() > 0) &&
					(scoreTracker.get(scoreTracker.size()-1) == 6) && 
						(teamTracker.get(scoreTracker.size() - 1).equals("T"))) {
				scoreTracker.add(1);
				teamTracker.add("T");
				scoreTwo += 1;
				extraPointsTwo++;
				updateScoreTwo(scoreTwo);
			} else {
				String extraPointWarning = "Extra points can only be scored following a touchdown.";
				Toast.makeText(getApplicationContext(), extraPointWarning, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/**
	* This method will add a score of 2 points to team one. It will also update the score
	* box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void twoPointsScoreOne(View view) {
		if (!scoreTooHigh(scoreOne)) {
			scoreTracker.add(2);
			teamTracker.add("O");
			scoreOne += 2;
			safetyConversionOne++;
			updateScoreOne(scoreOne);
		}
	}
	
	/**
	* This method will add a score of 3 points to team one. It will also update the score
	* box to show the user the new score.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void twoPointsScoreTwo(View view) {
		if (!scoreTooHigh(scoreTwo)) {
			scoreTracker.add(2);
			teamTracker.add("T");
			scoreTwo += 2;
			safetyConversionTwo++;
			updateScoreTwo(scoreTwo);
		}
	}
	
	/**
	* This method uses the arrayLists stored by the class to undo the past scoring operations.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void undoScore(View view) {
		int lastIndex = scoreTracker.size() - 1; // Store the size of the array
		if (lastIndex < 0) return; // If there are no previous scores return
		int undoInt = scoreTracker.get(lastIndex); 
		scoreTracker.remove(lastIndex); // Remove the end of the list
		String undoTeam = teamTracker.get(lastIndex);
		teamTracker.remove(lastIndex); // Remove the end of the list
		
		// If the team is team one the string will be "O", then remove the score from
		// team one's score, otherwise remove the score from team two.
		if ( undoTeam.equals("O") ) {
			scoreOne -= undoInt;
			updateScoreOne(scoreOne);
		} else if ( undoTeam.equals("T") ){
			scoreTwo -= undoInt;
			updateScoreTwo(scoreTwo);
		}		
	}
	
	/**
	* This method updates the TextView box for team one so that the correct score can
	* be displayed.
	* 
	* @param scoreUpdate This is the score that is to be displayed
	*/
	public void updateScoreOne(int scoreUpdate) {
		String score = Integer.toString(scoreUpdate);
		TextView scoreOneView = (TextView) findViewById(R.id.scoreOne);
		scoreOneView.setText(score);
	}
	
	/**
	* This method updates the TextView box for team two so that the correct score can
	* be displayed.
	* 
	* @param scoreUpdate This is the score that is to be displayed
	*/
	public void updateScoreTwo(int scoreUpdate) {
		String score = Integer.toString(scoreUpdate);
		TextView scoreOneView = (TextView) findViewById(R.id.scoreTwo);
		scoreOneView.setText(score);
	}
	
	/**
	* This method initializes all variables to 0. This is called when starting the
	* application and when starting a new game.
	* 
	*/
	public void initializeVariables() {
		scoreTracker = new ArrayList<Integer>();
		teamTracker = new ArrayList<String>();
		scoreOne = 0;
		scoreTwo = 0;
		touchDownOne = 0;
		fieldGoalOne = 0;
		extraPointsOne = 0;
		safetyConversionOne = 0;
		touchDownTwo = 0;
		fieldGoalTwo = 0;
		extraPointsTwo = 0;
		safetyConversionTwo = 0;
	}
	
	/**
	* This method ends the game. It creates a new intent for the next activity. Then it 
	* passes all of the necessary variables to the new intent. This will allow the next
	* activity to display all of the necessary statistics. 
	* This method also reinitializes all the variables so that a new game can be started.
	* 
	* @param view This loads the view so that the button can execute the function onClick
	*/
	public void endGame(View view) {
		// This intent will be the new activity
		Intent intent = new Intent(this, DisplayFinalStats.class);
		
		//Passes the team names to the next activity
		EditText nameOne = (EditText) findViewById(R.id.teamNameOne);
		String nameOneStr = nameOne.getText().toString();
		
		EditText nameTwo = (EditText) findViewById(R.id.teamNameTwo);
		String nameTwoStr = nameTwo.getText().toString();
		intent.putExtra("teamOne", nameOneStr);
		intent.putExtra("teamTwo", nameTwoStr);
		
		//Pass the scores to DisplayFinalStats
		intent.putExtra("scoreOne", scoreOne);
		intent.putExtra("scoreTwo", scoreTwo);
		intent.putExtra("tdOne", touchDownOne);
		intent.putExtra("tdTwo", touchDownTwo);
		intent.putExtra("epOne", extraPointsOne);
		intent.putExtra("epTwo", extraPointsTwo);
		intent.putExtra("fgOne", fieldGoalOne);
		intent.putExtra("fgTwo", fieldGoalTwo);
		intent.putExtra("scOne", safetyConversionOne);
		intent.putExtra("scTwo", safetyConversionTwo);
		startActivity(intent);
		
		// Clears all the variables and displays the zero scores.
		initializeVariables();
		updateScoreOne(scoreOne);
		updateScoreTwo(scoreTwo);
		nameOne.setHint("Team One");
		nameTwo.setHint("Team Two");
	}
	
	/**
	 * This method will return true and toast a message if the user has entered too many
	 * points.
	 * 
	 * @param score This is the current score.
	 * @return True when the score is too high, false otherwise.
	 */
	public boolean scoreTooHigh(int score) {
		if ( score >= 500 ) {
			String pointsTooHighWarning = "Cannot add any additional points.";
			Toast.makeText(getApplicationContext(), pointsTooHighWarning, Toast.LENGTH_SHORT).show();
			return true;
		} else 
			return false;
	}
}
