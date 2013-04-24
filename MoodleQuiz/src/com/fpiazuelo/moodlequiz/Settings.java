package com.fpiazuelo.moodlequiz;

import android.content.Context;
import android.content.SharedPreferences;


public class Settings {
		
	private final String SHARED_PREFS_FILE = "HMPrefs";
	private final String MOODLE_URL = "http://cristaleriamartinaranda.com/moodle/";
	private final String MOODLE_SERVICE = MOODLE_URL + "wspp/service_pp2.php";
	private final String MOODLE_NAMESPACE = "json"; // Constantes.MOODLE_URL+"wspp/wsdl2/";
	private final String MOODLE_USER = "moodle_user";
	private final String MOODLE_PASSWORD = "moodle_password";
	private final String Session_Client = "session_client";
	private final String Session_Key = "session_key";
	//private final String IS_LOGIN = "";
	
	private Context mContext;
		
	public Settings(Context context){
			mContext = context;
			
		}
	
	private SharedPreferences getSettings(){
		 return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
		}
	
	//Session_Client PROCEDURES
	public String getSession_Client(){
		return getSettings().getString(Session_Client, null);  
	}
	public void setSession_Client(String session_Client){
	    SharedPreferences.Editor editor = getSettings().edit();
	    editor.putString(Session_Client, session_Client );
	    editor.commit();
	}
	
	//Session_Key PROCEDURES
	public String getSession_Key(){
		return getSettings().getString(Session_Key, null);  
	}
	public void setSession_Key(String session_Key){
	    SharedPreferences.Editor editor = getSettings().edit();
	    editor.putString(Session_Key, session_Key );
	    editor.commit();
	}
	//MOODLE_USER PROCEDURES
		public String getMoodleUser(){
			return getSettings().getString(MOODLE_USER, null);  
		}
		public void setMoodleUser(String moodle_user){
		    SharedPreferences.Editor editor = getSettings().edit();
		    editor.putString(MOODLE_USER, moodle_user );
		    editor.commit();
		}
	//MOODLE_PWD PROCEDURES
		public String getMoodlePassword(){
			return getSettings().getString(MOODLE_PASSWORD, null);  
		}
		public void setMoodlePassword(String moodle_password){
		    SharedPreferences.Editor editor = getSettings().edit();
		    editor.putString(MOODLE_PASSWORD, moodle_password );
		    editor.commit();
		}
		//MOODLE_URL PROCEDURES
		public String getMoodleUrl(){
			return getSettings().getString(MOODLE_URL, null);  
		}
		public void setMoodleUrl(String moodle_url){
		    SharedPreferences.Editor editor = getSettings().edit();
		    editor.putString(MOODLE_URL, moodle_url );
		    editor.commit();
		}
		
		//MOODLE_SERVICE PROCEDURES
		public String getMoodleService(){
			return getSettings().getString(MOODLE_SERVICE, null);  
		}
		public void setMoodleService(String moodle_service){
		    SharedPreferences.Editor editor = getSettings().edit();
		    editor.putString(MOODLE_SERVICE, moodle_service );
		    editor.commit();
		}
		
		//MOODLE_NAMESPACE PROCEDURES
		public String getMoodleNameSpace(){
			return getSettings().getString(MOODLE_NAMESPACE, null);  
		}
		public void setMoodleNameSpace(String moodle_namespace){
		    SharedPreferences.Editor editor = getSettings().edit();
		    editor.putString(MOODLE_NAMESPACE, moodle_namespace );
		    editor.commit();
		}
}
