//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutStation extends Activity {
	DataHolder x = new DataHolder();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_station);
		
		final TextView changeName = (TextView) findViewById(R.id.nameTxt);
		changeName.setText(
		    x.nameListChosen);
		
		final TextView changeAddress = (TextView) findViewById(R.id.addressLine1);
		changeAddress.setText(
		    x.addressListChosen);
		
		final TextView changePhone = (TextView) findViewById(R.id.phoneNumberTxt);
		changePhone.setText(
		    x.phoneListChosen);
		
		final Button button5 = (Button) findViewById(R.id.goBtn);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent goMapView5 = new Intent(getApplicationContext(),MapsActivity.class);
        		startActivity(goMapView5);            }
        });
        final Button button2 = (Button) findViewById(R.id.commentButton);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent goMapView1 = new Intent(getApplicationContext(),QueryCodeComments.class);
        		startActivity(goMapView1);            }
        });
   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_about_station, menu);
		return true;
	}
	
	public void goHome(View view)
	{
		Intent goHome = new Intent(getApplicationContext(),DisplayListGarages.class);
		startActivity(goHome);
	}

	public void goComment(View view)
	{
		Intent goHome = new Intent(getApplicationContext(),QueryCodeComments.class);
		startActivity(goHome);
	}

	
}



