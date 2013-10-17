//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import org.apache.http.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.apache.http.impl.client.DefaultHttpClient;
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

public class QueryCodeGarages extends ListActivity {
	
	
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
            DefaultHttpClient httpclient = new DefaultHttpClient();

            HttpGet httppost = new HttpGet("http://garageserver.herobo.com/select.php");
            
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
        String name = "";
        String address = "";
        String phone = "";
        String postal = "";
        int rating = 0;
        String lati = "";
        String longi = "";
        try{
        JSONArray jArray = new JSONArray(result);
        JSONObject json_data=null;
       
        DataHolder x = new DataHolder();

        for(int i=0;i<jArray.length();i++){
           x.rowAmount++;
   }

        x.stationName = new String[x.rowAmount];
        x.area = new String[x.rowAmount];
        x.id = new int[x.rowAmount];
        x.address = new String[x.rowAmount];
        x.phone = new String[x.rowAmount];
        x.lati = new String[x.rowAmount];
        x.longi = new String[x.rowAmount];

        
        for(int i=0;i<jArray.length();i++){
                json_data = jArray.getJSONObject(i);
                id = json_data.getInt("ID");
                name = json_data.getString("NAME");
                address = json_data.getString("ADDRESS");
                phone = json_data.getString("PHONE");
                postal = json_data.getString("POSTAL");
                rating = json_data.getInt("RATING");
                lati = json_data.getString("LATI");
                longi = json_data.getString("LONGI");
                x.stationName[i] = name;
                x.area[i] = postal;
                x.id[i] = id;
                x.address[i] = address;
                x.phone[i] = phone;
                x.lati[i] = lati;
                x.longi[i] = longi;
        }

        }catch(JSONException e1){
            Toast.makeText(getBaseContext(), "Nothing Found", Toast.LENGTH_LONG).show();
        }catch (ParseException e1){
            e1.printStackTrace();
        }
       
        
    	Intent enterDisplay = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(enterDisplay);
		
			
       }

private static void setProxy (DefaultHttpClient httpClient,
                String proxy,int port,String username,String password)
{
    HttpParams params = httpClient.getParams();
    params.setParameter(ConnRoutePNames.DEFAULT_PROXY,
            new HttpHost( proxy,port));
    if(username!=null&&password!=null){
    httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(proxy, port),
                new UsernamePasswordCredentials(username, password)
        );
    }
    httpClient.setParams(params);
}


}