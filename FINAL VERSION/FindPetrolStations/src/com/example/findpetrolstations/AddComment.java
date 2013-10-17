package com.example.findpetrolstations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddComment extends Activity {
	DataHolder x = new DataHolder();
	
	EditText content;
	EditText name;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_comment);
		
	    content   = (EditText)findViewById(R.id.contentEntered);
	    name   = (EditText)findViewById(R.id.nameEntered);


	    
		final Button button5 = (Button) findViewById(R.id.actualAdd);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            	if(content.getText().toString().equals("") || name.getText().toString().equals(""))
            	{
                    Toast.makeText(getBaseContext(), "Both fields must not be empty.", Toast.LENGTH_LONG).show();

            		
            	}
            	else
            	{
            
            	x.commentEntered = content.getText().toString();
            	x.nameEntered  = name.getText().toString(); 
            	
            	Intent goMapView5 = new Intent(getApplicationContext(),QueryInsert.class);
        		startActivity(goMapView5); 
        		
        		
        		
            	}
            
            }
        });
        
        final Button button2 = (Button) findViewById(R.id.backAddButton);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent goMapView1 = new Intent(getApplicationContext(),DisplayListComments.class);
        		startActivity(goMapView1);            }
        });
		
        
        
	}

	

	
}

