//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import org.apache.http.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

public class QueryCodeComments extends ListActivity {
	
	
       @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String result = null;
        InputStream is = null;
        StringBuilder sb=null;
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = 
        	new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        
        try{
        	DataHolder x = new DataHolder();
        	int id = x.idListChosen;
        	
        	
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
           // nameValuePairs.add(new BasicNameValuePair("content",content));
            //nameValuePairs.add(new BasicNameValuePair("user",user));
            nameValuePairs.add(new BasicNameValuePair("garageid", Integer.toString(id)));


        	
            DefaultHttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("http://garageserver.herobo.com/selectComments.php");
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpclient.execute(httppost);
          
            HttpEntity entity = response.getEntity();
            
            is = entity.getContent();
            
            
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection"+e.toString());
        }
        try{
        	
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line="0";

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
           
            is.close();
            result=sb.toString();

        }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }
        System.out.println(result);
        //paring data
        int id = 0;
        int id1 = 0;
        String content = "";
        String user = "";
        int garageid = 0;
   
        try{
        JSONArray jArray = new JSONArray(result);
        JSONObject json_data=null;
       
        DataHolder x = new DataHolder();

        for(int i=0;i<jArray.length();i++){
           x.commentRowAmount++;
   }

        x.commentsID = new int[x.rowAmount];
        x.commentContent = new String[x.rowAmount];
        x.user = new String[x.rowAmount];
        x.garageid = new int[x.rowAmount];


        
        for(int i=0;i<jArray.length();i++){
                json_data = jArray.getJSONObject(i);
                
                id1 = json_data.getInt("ID");
                content = json_data.getString("CONTENT");
                user = json_data.getString("USER");
                garageid = json_data.getInt("GARAGEID");
               
                x.commentsID[i] = id1;
                x.commentContent[i] = content;
                x.user[i] = user;
                x.garageid[i] = garageid;
            
        }

        }catch(JSONException e1){
            Toast.makeText(getBaseContext(), "Nothing Found", Toast.LENGTH_LONG).show();
        }catch (ParseException e1){
            e1.printStackTrace();
        }
       
        
    	Intent enterDisplay = new Intent(getApplicationContext(),DisplayListComments.class);
		startActivity(enterDisplay);
		
			
       }




}