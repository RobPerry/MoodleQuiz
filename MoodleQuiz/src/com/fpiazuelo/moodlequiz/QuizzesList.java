package com.fpiazuelo.moodlequiz;

import java.util.ArrayList;

import net.patrickpollet.gson.GsonUtils;
import net.patrickpollet.gson.MyHttpTransportSE;
import net.patrickpollet.gson.MyRestSerializationEnvelope;
import net.patrickpollet.moodlews_gson.core.GradeItemRecord;
import net.patrickpollet.moodlews_gson.core.LoginReturn;
import net.patrickpollet.moodlews_gson.core.Mdl_restserverBindingStub;
import net.patrickpollet.moodlews_gson.core.QuizRecord;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//New imports
import net.patrickpollet.gson.*;

public class QuizzesList extends ListActivity {
	/*private static final String MOODLE_URL = "http://cristaleriamartinaranda.com/moodle/";
	private static final String MOODLE_SERVICE = MOODLE_URL +
			"wspp/service_pp2.php";
	private static final String MOODLE_NAMESPACE = "json"; */// Constantes.MOODLE_URL+"wspp/wsdl2/";
	/*public  static final String LOGIN = "admin";
	public static final String PWD = "Becariou23*";*/
	
	public Settings sett;
	
	
	/*ArrayAdapter<String[]> mAdapter;
	ArrayList<String[]> listItems = new ArrayList<String[]>();*/
	ArrayAdapter<String> mAdapter;
	ArrayList<String> listItems = new ArrayList<String>();
	ArrayList<Integer> listIds = new ArrayList<Integer>();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quizzes_list);
        sett = new Settings(this);
        
         new MoodleConnectTask().execute();
         
         //mAdapter = new ArrayAdapter<String[]>(this,android.R.layout.simple_list_item_1 ,listItems);
         mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,listItems);
        setListAdapter(mAdapter);
                
    }

    public class MoodleConnectTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			//Connect to Moodle
			try {
								
			Mdl_restserverBindingStub moodle = new Mdl_restserverBindingStub(
						sett.getMoodleService(), sett.getMoodleNameSpace(), true); // Constantes.WS_DEBUG);
/*			       	LoginReturn lr = moodle.login(LOGIN, PWD);*/
				
				QuizRecord[] listquizzes =  moodle.get_all_quizzes(Integer.valueOf(sett.getSession_Client()), sett.getSession_Key(), "timeopen", "0");
				
					for (int i = 0; i < listquizzes.length; i++) {
						listIds.add(listquizzes[i].getId()) ;
						listItems.add( listquizzes[i].getName());
														
					}
				
				
			}
			catch ( Exception ex) {
				System.out.print(" Error in " + ex.getClass() + "class, message : " + ex.getMessage() );
			}
			return null;
					
		}
		@Override
		protected void onPostExecute(Void result) {
		mAdapter.notifyDataSetChanged();
		//setListAdapter(mAdapter);
	    }		
	
    }    
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
    	try{
    		
    		Toast.makeText(this,"Starting test " + listItems.get(position) + "... ", Toast.LENGTH_LONG).show();
    		Intent i = new Intent(this, Question.class);
    		i.putExtra("QuizIndex",listIds.get(position));
        	startActivity(i);
    	}
    	catch(Exception ex ){
    		Toast.makeText(this,"Error in " + ex.getClass() + "class, message : " + ex.getMessage(), Toast.LENGTH_LONG).show();
    		System.out.print(" Error in " + ex.getClass() + "class, message : " + ex.getMessage() );
    	}
    	
    	
    }
      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quizzes_list, menu);
        return true;
    }
}
