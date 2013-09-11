package edu.mines.twaite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreScreen extends Activity {
	int scoreOne, scoreTwo;
	int tdOne, fgOne, epOne, scOne;
	int tdTwo, fgTwo, epTwo, scTwo;
	ArrayList<Integer> scoreTracker;
	ArrayList<String> teamTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_screen);
		initializeVariables();
		updateScoreOne(scoreOne);
		updateScoreTwo(scoreTwo);
	}
	
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("scoreOne", scoreOne);
		savedInstanceState.putInt("scoreTwo", scoreTwo);
		savedInstanceState.putInt("tdOne", tdOne);
		savedInstanceState.putInt("tdTwo", tdTwo);
		savedInstanceState.putInt("epOne", epOne);
		savedInstanceState.putInt("epTwo", epTwo);
		savedInstanceState.putInt("fgOne", fgOne);
		savedInstanceState.putInt("fgTwo", fgTwo);
		savedInstanceState.putInt("scOne", scOne);
		savedInstanceState.putInt("scTwo", scTwo);
		savedInstanceState.putIntegerArrayList("scoreTracker", scoreTracker);
		savedInstanceState.putStringArrayList("teamTracker", teamTracker);
		EditText nameOne = (EditText) findViewById(R.id.teamNameOne);
		String nameOneStr = nameOne.getText().toString();
		savedInstanceState.putString("nameOne", nameOneStr);
		EditText nameTwo = (EditText) findViewById(R.id.teamNameTwo);
		String nameTwoStr = nameTwo.getText().toString();
		savedInstanceState.putString("nameTwo", nameTwoStr);
	}
	
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		scoreOne = savedInstanceState.getInt("scoreOne");
		scoreTwo = savedInstanceState.getInt("scoreTwo");
		tdOne = savedInstanceState.getInt("tdOne");
		tdTwo = savedInstanceState.getInt("tdTwo");
		epOne = savedInstanceState.getInt("epOne");
		epTwo = savedInstanceState.getInt("epTwo");
		fgOne = savedInstanceState.getInt("fgOne");
		fgTwo = savedInstanceState.getInt("fgTwo");
		scOne = savedInstanceState.getInt("csOne");
		scTwo = savedInstanceState.getInt("csTwo");
		updateScoreOne(scoreOne);
		updateScoreTwo(scoreTwo);
		EditText nameOne = (EditText) findViewById(R.id.teamNameOne);
		nameOne.setText(savedInstanceState.getString("nameOne"));
		EditText nameTwo = (EditText) findViewById(R.id.teamNameTwo);
		nameTwo.setText(savedInstanceState.getString("nameTwo"));
	}
	
	public void onPause() {
		super.onPause();
	}

	public void onResume() {
		super.onResume();
	}
	
	public void onStop() {
		super.onStop();
	}
	
	public void onDestroy() {
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void touchdownScoreOne(View view) {
		scoreTracker.add(6);
		teamTracker.add("O");
		scoreOne += 6;
		tdOne++;
		updateScoreOne(scoreOne);
	}
	
	public void touchdownScoreTwo(View view) {
		scoreTracker.add(6);
		teamTracker.add("T");
		scoreTwo += 6;
		tdTwo++;
		updateScoreTwo(scoreTwo);
	}
	
	public void fieldgoalScoreOne(View view) {
		scoreTracker.add(3);
		teamTracker.add("O");
		scoreOne += 3;
		fgOne++;
		updateScoreOne(scoreOne);
	}
	
	public void fieldgoalScoreTwo(View view) {
		scoreTracker.add(3);
		teamTracker.add("T");
		scoreTwo += 3;
		fgTwo++;
		updateScoreTwo(scoreTwo);
	}
	
	public void extrapointScoreOne(View view) {
		if ((scoreTracker.size() > 0) &&
				(scoreTracker.get(scoreTracker.size()-1) == 6) && 
					(teamTracker.get(scoreTracker.size() - 1).equals("O"))) {
			scoreTracker.add(1);
			teamTracker.add("O");
			scoreOne += 1;
			epOne++;
			updateScoreOne(scoreOne);
		} else {
			String extraPointWarning = "Extra points can only be scored following a touchdown.";
			Toast.makeText(getApplicationContext(), extraPointWarning, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void extrapointScoreTwo(View view) {
		if ((scoreTracker.size() > 0) &&
				(scoreTracker.get(scoreTracker.size()-1) == 6) && 
					(teamTracker.get(scoreTracker.size() - 1).equals("T"))) {
			scoreTracker.add(1);
			teamTracker.add("T");
			scoreTwo += 1;
			epTwo++;
			updateScoreTwo(scoreTwo);
		} else {
			String extraPointWarning = "Extra points can only be scored following a touchdown.";
			Toast.makeText(getApplicationContext(), extraPointWarning, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void twoPointsScoreOne(View view) {
		scoreTracker.add(2);
		teamTracker.add("O");
		scoreOne += 2;
		scOne++;
		updateScoreOne(scoreOne);
	}
	
	public void twoPointsScoreTwo(View view) {
		scoreTracker.add(2);
		teamTracker.add("T");
		scoreTwo += 2;
		scTwo++;
		updateScoreTwo(scoreTwo);
	}
	
	public void undoScore(View view) {
		int lastIndex = scoreTracker.size() - 1;
		if (lastIndex < 0) return;
		int undoInt = scoreTracker.get(lastIndex);
		scoreTracker.remove(lastIndex);
		String undoTeam = teamTracker.get(lastIndex);
		teamTracker.remove(lastIndex);
		if ( undoTeam.equals("O") ) {
			scoreOne -= undoInt;
			updateScoreOne(scoreOne);
		} else if ( undoTeam.equals("T") ){
			scoreTwo -= undoInt;
			updateScoreTwo(scoreTwo);
		} else {
			//TODO
		}
		
	}
	
	public void updateScoreOne(int scoreUpdate) {
		String score = Integer.toString(scoreUpdate);
		TextView scoreOneView = (TextView) findViewById(R.id.scoreOne);
		scoreOneView.setText(score);
	}
	
	public void updateScoreTwo(int scoreUpdate) {
		String score = Integer.toString(scoreUpdate);
		TextView scoreOneView = (TextView) findViewById(R.id.scoreTwo);
		scoreOneView.setText(score);
	}
	
	public void initializeVariables() {
		scoreTracker = new ArrayList<Integer>();
		teamTracker = new ArrayList<String>();
		scoreOne = 0;
		scoreTwo = 0;
		tdOne = 0;
		fgOne = 0;
		epOne = 0;
		scOne = 0;
		tdTwo = 0;
		fgTwo = 0;
		epTwo = 0;
		scTwo = 0;
	}
	
	public void endGame(View view) {
		Intent intent = new Intent(this, DisplayFinalStats.class);
		EditText nameOne = (EditText) findViewById(R.id.teamNameOne);
		String nameOneStr = nameOne.getText().toString();
		EditText nameTwo = (EditText) findViewById(R.id.teamNameTwo);
		String nameTwoStr = nameTwo.getText().toString();
		intent.putExtra("teamOne", nameOneStr);
		intent.putExtra("teamTwo", nameTwoStr);
		startActivity(intent);
	}
}
