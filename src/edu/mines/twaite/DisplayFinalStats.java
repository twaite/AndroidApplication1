package edu.mines.twaite;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayFinalStats extends Activity {

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_final_stats);
        Intent intent = getIntent();
        
        String teamOneName = intent.getStringExtra("teamOne");
        TextView teamOne = (TextView) findViewById(R.id.teamOneGO);
        teamOne.setText(teamOneName);
        
        String teamTwoName = intent.getStringExtra("teamTwo");
        TextView teamTwo = (TextView) findViewById(R.id.teamTwoGO);
        teamTwo.setText(teamTwoName);

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
