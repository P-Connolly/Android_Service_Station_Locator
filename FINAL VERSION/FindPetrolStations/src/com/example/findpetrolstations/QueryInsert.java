//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import org.apache.http.*;
import java.util.ArrayList;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

public class QueryInsert extends ListActivity {
	
	
       @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        
        try{
        	DataHolder x = new DataHolder();
        	String content = x.commentEntered;
        	String garageid = Integer.toString(x.idListChosen);
        	String user = x.nameEntered;
        	
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("content",content));
            nameValuePairs.add(new BasicNameValuePair("user",user));
            nameValuePairs.add(new BasicNameValuePair("garageid",garageid));


            DefaultHttpClient httpclient1 = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://garageserver.herobo.com/commentinsert.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient1.execute(httppost);
           
            Intent goMapView1 = new Intent(getApplicationContext(),AboutStation.class);
    		startActivity(goMapView1);
            
   
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection"+e.toString());
        }
       
		
			
       }



}