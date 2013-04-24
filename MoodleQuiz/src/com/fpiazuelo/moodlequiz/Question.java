package com.fpiazuelo.moodlequiz;

import java.util.ArrayList;

import com.fpiazuelo.moodlequiz.QuizzesList.MoodleConnectTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.patrickpollet.moodlews_gson.core.LoginReturn;
import net.patrickpollet.moodlews_gson.core.Mdl_restserverBindingStub;
import net.patrickpollet.moodlews_gson.core.QuizRecord;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class Question extends Activity {
	
	ArrayAdapter<String> qAdapter;
	ArrayList<String> listQuestions = new ArrayList<String>();
	private QuizRecord quiz;
	Integer quiz_index;
	public Settings sett;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	try
    	{
    	super.onCreate(savedInstanceState);	
    	
        ScrollView sv = new ScrollView(this);
        final LinearLayout ll = new LinearLayout(this);
        sv.addView(ll);
        ll.setOrientation(android.widget.LinearLayout.VERTICAL);
        
        TextView tv = new TextView(this);
        tv.setText("Test 1");
        /*sv.addView(tv);*/
      
        setContentView(sv);
         setTitle("Title");

         //Get parameters from previous Activity
         Bundle extras = getIntent().getExtras();
         if (extras == null)
        	 return;
         quiz_index = extras.getInt("QuizIndex");
         quiz = new QuizRecord();
         sett = new Settings(this);
         
         new MoodleConnectTask().execute();
   
 		//added for real booelan returned by some WS 
 		// THESE calls fails with latest gson-2.2.1.jar ? 
 		//gsonb.registerTypeAdapter(Boolean.class, new JsonBooleanDeserializer());
 		//gsonb.registerTypeAdapter(boolean.class, new JsonBooleanDeserializer());
         
               
        
        //qAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listQuestions);
        //ListView lv = getListView();
        //TextView tv = new TextView(this);
        //tv.setText("Test 1");
        //lv.addHeaderView(tv);
        /*View ll = getView();*/
        //listQuestions.add(" Item 2");
        //setListAdapter(qAdapter);
        
    	} catch (Exception ex) {
    		System.out.print(" Error in " + ex.getClass() + "class, message : " + ex.getMessage() );
            
        }
    }

	
    public class MoodleConnectTask extends AsyncTask<Void, Void, QuizRecord> {
		@Override
		protected QuizRecord doInBackground(Void... params) {
			//Connect to Moodle
			try {
				Mdl_restserverBindingStub moodle = new Mdl_restserverBindingStub(
						sett.getMoodleService(), sett.getMoodleNameSpace(), true); // Constantes.WS_DEBUG);
		       	//LoginReturn lr = moodle.login(LOGIN, PWD);
		       	quiz =  moodle.get_quiz(Integer.valueOf(sett.getSession_Client()), sett.getSession_Key(),quiz_index,"");
				
				
			}
			catch ( Exception ex) {
				System.out.print(" Error in " + ex.getClass() + "class, message : " + ex.getMessage() );
				return null;
			}
			return quiz;
					
		}
		@Override
		protected void onPostExecute(QuizRecord result) {
		if (result != null)
		{
			String jString = result.getData();
			String jString2 =result.getQuestions();
			GsonBuilder gsonb=new GsonBuilder();
	        Gson gson=gsonb.create();
	 		gson.fromJson(jString,this.getClass());
	 		 
		}
		//setListAdapter(mAdapter);
	    }		
	
    }    
    
 /*   private void createRadioButton() {
    	
        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
        for(int i=0; i<5; i++){
            rb[i]  = new RadioButton(this);
            rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText("Test");
        }
        ll.addView(rg);//you add the whole RadioGroup to the layout
        ll.addView(submit); 
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i = 0; i < 5; i++) { 
                    rg.removeView(rb[i]);//now the RadioButtons are in the RadioGroup
                }  
                ll.removeView(submit);
                Questions();
            }
        });   
    }*/
    /*public class MoodleConnectTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			//Connect to Moodle
			try {
				Mdl_restserverBindingStub moodle = new Mdl_restserverBindingStub(
						MOODLE_SERVICE, MOODLE_NAMESPACE, true); // Constantes.WS_DEBUG);
		       	LoginReturn lr = moodle.login(LOGIN, PWD);
				QuizRecord quiz =  moodle.get_quiz(lr.getClient(), lr.getSessionkey(), 1, "0");
				System.out.print("Quiz Record, " + quiz.getName());
				System.out.print("QuizRecord Data: " + quiz.getData() );	
				
			}
			catch ( Exception ex) {
				System.out.print(" Error in " + ex.getClass() + "class, message : " + ex.getMessage() );
			}
			return null;
					
		}
		@Override
		protected void onPostExecute(Void result) {
		
	    }		
	
    }    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_question, menu);
        return true;
    }
}
