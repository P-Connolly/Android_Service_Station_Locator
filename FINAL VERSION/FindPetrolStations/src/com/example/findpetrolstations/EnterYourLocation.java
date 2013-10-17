//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;


import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EnterYourLocation extends Activity implements OnItemSelectedListener {
		
DataHolder x = new DataHolder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_your_location);
		
		Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
		spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.station_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
	}

		
	public void enterLocationButton(View view)
	{

		Intent enterDisplay = new Intent(getApplicationContext(),DisplayListGarages.class);
		startActivity(enterDisplay);
		
	}


	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		
		x.areaChosen = (String) parent.getItemAtPosition(pos);
		
	}

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
	
	
}
