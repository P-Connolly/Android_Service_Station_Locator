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
import android.widget.ListView;

public class DisplayListGarages extends ListActivity 
{
	int count = 0;

	 String classes[];
	 int id2[];
	 String addresses[];
	 String phoneNums[];
	 String lati[];
	 String longi[];
	 DataHolder x = new DataHolder();
		protected void onCreate(Bundle savedInstanceState) 
		{
			
			 
			setContentView(R.layout.activity_display_list);

			
			
			for(int k = 0;k < x.rowAmount;k++)
			 {
				 if(x.area[k].equals(x.areaChosen))
				{
					 	count++;					
				}
			
			}
			
			 classes = new String[count];
			 id2 = new int[count];
			 addresses = new String[count];
			 phoneNums = new String[count];
			 lati = new String[count];
			 longi = new String[count];
			 int h = 0;
			 for(int m = 0;m < x.rowAmount ;m++)
			 {
				if(x.area[m].equals(x.areaChosen))
				{
					 classes[h] = x.stationName[m];
					 id2[h] = x.id[m];
					 addresses[h] = x.address[m];
					 phoneNums[h] = x.phone[m];
					 lati[h] = x.lati[m];
					 longi[h] = x.longi[m];
							 
					 h++;

				}
			
			}

			super.onCreate(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(DisplayListGarages.this, android.R.layout.simple_list_item_1, classes));
		}
		
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id)
		{
		
			super.onListItemClick(l, v, position, android.R.id.list);
			String cheese = classes[position];
			
			x.nameListChosen = classes[position];
			
			for(int p = 0;p < count;p++)
			{
				if(x.nameListChosen == classes[p])
				{
					x.idListChosen = id2[p];
					x.addressListChosen = addresses[p];
					x.phoneListChosen = phoneNums[p];
					x.latListChosen = lati[p];
					x.longListChosen = longi[p];
					
				}
			}
		
			Intent clickList = new Intent(getApplicationContext(),AboutStation.class);
			startActivity(clickList);
			}
	
		
	
	
	public void backBtn(View view)
	{
		
		Intent backBtn = new Intent(getApplicationContext(),EnterYourLocation.class);
		startActivity(backBtn);
	}
	
	
	
	
	
}





