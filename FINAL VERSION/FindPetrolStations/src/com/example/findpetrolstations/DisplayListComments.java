//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import android.R.xml;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DisplayListComments extends ListActivity 
{
	int count = 0;

	 String classes1[];

	 DataHolder x = new DataHolder();
		protected void onCreate(Bundle savedInstanceState) 
		{
			
			 
			setContentView(R.layout.activity_display_list_comments);

			
			 final Button button3 = (Button) findViewById(R.id.backBtnComment);
			 button3.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	Intent goMapView = new Intent(getApplicationContext(),AboutStation.class);
		        		startActivity(goMapView);            }
		        });
			 final Button button4 = (Button) findViewById(R.id.addComment);
			 button4.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	Intent goMapView1 = new Intent(getApplicationContext(),AddComment.class);
		        		startActivity(goMapView1);            }
		        });
		        
			for(int k = 0;k < x.commentRowAmount;k++)
			 {
				
				 if(x.garageid[k] == x.idListChosen)
				{
					 	count++;					
				}
			
			}
			
			 classes1 = new String[count];
			 
			 int h = 0;
			 for(int m = 0;m < x.commentRowAmount ;m++)
			 {

				if(x.garageid[m] == x.idListChosen)
				{
					
					 classes1[h] = x.commentContent[m];
					 classes1[h] = classes1[h] + "\n\nUser: " + x.user[m] + "\n";
					 h++;

				}
			
			}

			super.onCreate(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(DisplayListComments.this, android.R.layout.simple_list_item_1, classes1));
			//Intent clickList = new Intent(getApplicationContext(),DisplayListComments.class);
			//startActivity(clickList);
		}
		
		//@Override
		////protected void onListItemClick(ListView l, View v, int position, long id)
		//{
		
		//	super.onListItemClick(l, v, position, android.R.id.list);
		//	String cheese = classes1[position];
			
		//	x.nameListChosen = classes1[position];
			
		//	for(int p = 0;p < count;p++)
		//	{
			//	if(x.nameListChosen == classes1[p])
			//	{
					//x.idListChosen = id2[p];
					//x.addressListChosen = addresses[p];
					//x.phoneListChosen = phoneNums[p];
					//x.latListChosen = lati[p];
					//x.longListChosen = longi[p];
			//	}
			//}
		
			//Intent clickList = new Intent(getApplicationContext(),DisplayListComments.class);
			//startActivity(clickList);
			}
	//
		
	
	

	
	
	
	
	
//}





