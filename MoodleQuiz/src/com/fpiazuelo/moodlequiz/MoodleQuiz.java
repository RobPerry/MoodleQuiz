package com.fpiazuelo.moodlequiz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MoodleQuiz extends Activity implements OnClickListener {
	private Settings sett;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodle_quiz);
        View loginMenu = findViewById(R.id.menuLogin );
        loginMenu.setOnClickListener(this);
        Initialize();
    }

    public void Initialize(){
    	sett = new Settings(this);
    	sett.setMoodleNameSpace("json");
    	sett.setMoodleUrl("http://cristaleriamartinaranda.com/moodle/");
    	sett.setMoodleService(sett.getMoodleUrl() + "wspp/service_pp2.php");
    	sett.setMoodleUser("admin");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_moodle_quiz, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
    	case R.id.menuLogin :
    		    		
    	Intent i = new Intent(this, Login.class);
    	startActivity(i);
    	break;
    	// More
		}
	}
	
}
