//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomPinpoint extends ItemizedOverlay<OverlayItem> 
{
	
	private ArrayList<OverlayItem> pinpoints = new ArrayList<OverlayItem>();
	private Context c;

	public CustomPinpoint(Drawable defaultMarker) 
	{
		super(boundCenter(defaultMarker));
		
	}
	
	public CustomPinpoint(Drawable m, Context x) 
	{
		this(m);		
		c = x;
	}
	@Override
	protected OverlayItem createItem(int i) 
	{
		return pinpoints.get(i);
		
	}

	@Override
	public int size() 
	{
		return pinpoints.size();
		
	}
	public void insertPinPoint(OverlayItem item)
	{
		
		pinpoints.add(item);
		this.populate();
	}
	
	
}
