package com.fpiazuelo.moodlequiz;

import net.patrickpollet.moodlews_gson.core.LoginReturn;
import net.patrickpollet.moodlews_gson.core.Mdl_restserverBindingStub;
import net.patrickpollet.moodlews_gson.core.Mdl_soapserverBindingStub;
import net.patrickpollet.moodlews_gson.core.QuizRecord;

import com.fpiazuelo.moodlequiz.QuizzesList.MoodleConnectTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	private Settings sett;
	private EditText txtUser;
	private EditText txtPassword;
	private CheckBox chkRememberUser;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View loginButton = findViewById(R.id.btnLogin );
        loginButton.setOnClickListener(this);
        txtUser = (EditText) findViewById(R.id.txtMoodleUser );
        txtPassword = (EditText) findViewById(R.id.txtPassword  );
        chkRememberUser = (CheckBox) findViewById(R.id.chkRememberUser);
        
        sett = new Settings(this);
        
        Initialize();
    }

    public void Initialize(){
    	
    	if (sett.getMoodleUser() != null)
    		txtUser.setText(sett.getMoodleUser());
    	if (sett.getMoodlePassword() != null)
    		txtPassword.setText(sett.getMoodlePassword());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
    	case R.id.btnLogin :
    	new MoodleConnectTask().execute();
    		
    	/*Intent i = new Intent(this, QuizzesList.class);
    	startActivity(i);*/
    	break;
    	// More
		}
	}

    public class MoodleConnectTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			//Connect to Moodle
			try {
/*				Mdl_restserverBindingStub moodle = new Mdl_restserverBindingStub(
						sett.getMoodleService() , sett.getMoodleNameSpace(), true); // Constantes.WS_DEBUG);
*/				Mdl_soapserverBindingStub moodle = new Mdl_soapserverBindingStub(
						sett.getMoodleService() , sett.getMoodleNameSpace(), true); // Constantes.WS_DEBUG);
				
		       	LoginReturn lr = moodle.login(txtUser.getText().toString(), txtPassword.getText().toString());
								
		       	if ( lr != null ) {
		       		sett.setSession_Client(String.valueOf(lr.getClient()));
		       		sett.setSession_Key(lr.getSessionkey());
		       		
		       		if ( chkRememberUser.isChecked() ){
		       			sett.setMoodleUser(txtUser.getText().toString());
		       			sett.setMoodlePassword(txtPassword.getText().toString());
		       		}
		       			
		       			return true;
		       		
		       		
		       	}
		       	else
		       	{
		    		Toast.makeText(getBaseContext(),"Login Incorrect for " + txtUser.getText().toString(), Toast.LENGTH_LONG).show();
		    		System.out.print(" Error in login class."  );
		    		
		       	}	
		       		
		       	
			}
			catch ( Exception ex) {
				System.out.print(" LOGIN ERROR. Error in " + ex.getClass() + "class, message : " + ex.getMessage() );
			}
			return false;
					
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
			Intent i = new Intent(getBaseContext() , QuizzesList.class);
       		startActivity(i);
			}
				//setListAdapter(mAdapter);
	    }		
		
    }
    
}
