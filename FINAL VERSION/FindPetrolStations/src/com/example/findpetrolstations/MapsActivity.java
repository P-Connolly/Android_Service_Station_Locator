//X00081023 PAUL CONNOLLY
//X00082450 KEVIN DEEGAN

package com.example.findpetrolstations;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;





import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity implements LocationListener

{
	DataHolder i = new DataHolder();
	
	MapView map;
	long start;
	long stop;
	MyLocationOverlay compass;
	MapController controller;
	int x, y;
	GeoPoint touchedPoint;
	GeoPoint startPoint;

	List<Overlay> overlayList;
	List<Overlay> overlayList2;
	Drawable d;
	Drawable e;
	LocationManager lm;
	String towers;
	int lat = 0;
	int longi = 0;
	
	private double[] getGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        List<String> providers = lm.getProviders(true);

        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
        Location l = null;
        
        for (int i=providers.size()-1; i>=0; i--) {
                l = lm.getLastKnownLocation(providers.get(i));
                if (l != null) break;
        }
        
        double[] gps = new double[2];
        if (l != null) {
                gps[0] = l.getLatitude();
                gps[1] = l.getLongitude();
        }
        return gps;
}
	
	public void onCreate(Bundle savedInstanceState)
	{
	
		
		
		
		double gpsCo[] = new double[2];
		gpsCo = getGPS();
		
		
		
		
		double lati =  Double.parseDouble(i.latListChosen);
		double long1 =  Double.parseDouble(i.longListChosen);
		GeoPoint startPoint = new GeoPoint((int)(lati * 1E6), (int)(long1 * 1E6)); 
		GeoPoint startPoint2 = new GeoPoint((int)(gpsCo[0] * 1E6), (int)(gpsCo[1] * 1E6)); 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		map = (MapView)findViewById(R.id.mvMain);
		map.setBuiltInZoomControls(true);
		d = getResources().getDrawable(R.drawable.servicestation);
		e = getResources().getDrawable(R.drawable.person);

		GeoPoint point = new GeoPoint((int)(53.352901 * 1E6), (int)(-6.271477 * 1E6));
		//User Location Code

		//Service Station Location Code
		Touchy t = new Touchy();
		 overlayList = map.getOverlays();
		overlayList.add(t);
		
		compass = new MyLocationOverlay(MapsActivity.this, map);
		controller = map.getController();
		controller.animateTo(point);
		controller.setZoom(12);
		OverlayItem overlayItemStart = new OverlayItem(startPoint, "Service Station Location", "");
		CustomPinpoint custom1 = new CustomPinpoint(d, MapsActivity.this);		
		custom1.insertPinPoint(overlayItemStart);
		overlayList.add(custom1);
		
		OverlayItem overlayItemStart2 = new OverlayItem(startPoint2, "Service Station Location", "");
		CustomPinpoint custom2 = new CustomPinpoint(e, MapsActivity.this);		
		custom2.insertPinPoint(overlayItemStart2);
		overlayList.add(custom2);
		
		//Back Button
		final Button button1 = (Button) findViewById(R.id.backMapBtn);
		button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent goMapView = new Intent(getApplicationContext(),AboutStation.class);
        		startActivity(goMapView);            }
        });
        Toast.makeText(getBaseContext(), "To view more options, simply touch the screen for 2 seconds.", Toast.LENGTH_LONG).show();
        Toast.makeText(getBaseContext(), "To view more options, simply touch the screen for 2 seconds.", Toast.LENGTH_LONG).show();

	}
	
	class Touchy extends Overlay
	{
		
		@SuppressWarnings("deprecation")
		public boolean onTouchEvent(MotionEvent e, MapView m)
		{
			if(e.getAction()==MotionEvent.ACTION_DOWN)
			{
			start = e.getEventTime();	
			x = (int) e.getX();
			y = (int) e.getY();
			 touchedPoint = map.getProjection().fromPixels(x, y);
			}
			if(e.getAction()==MotionEvent.ACTION_UP)
			{
				stop = e.getEventTime();
			}
			if(stop - start > 1500)
			{
				AlertDialog alert = new AlertDialog.Builder(MapsActivity.this).create();
				alert.setTitle("Pick an Option");
				alert.setMessage("Please Pick An Option");
				
				alert.setButton("Place a pin", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						OverlayItem overlayItem = new OverlayItem(touchedPoint, "", "");
						CustomPinpoint custom = new CustomPinpoint(d, MapsActivity.this);		
						custom.insertPinPoint(overlayItem);
						overlayList.add(custom);
					}
				});
					alert.setButton2("Get address", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
						try
						{
							List<Address> address = geocoder.getFromLocation(touchedPoint.getLatitudeE6() / 1E6, touchedPoint.getLongitudeE6() / 1E6, 1);
							if(address.size() > 0)
							{
								String display = "";

								for (int i = 0; i <= address.get(0).getMaxAddressLineIndex();i++)
								{
								display += address.get(0).getAddressLine(i) + "\n";
								}
								Toast t = Toast.makeText(getBaseContext(), display, Toast.LENGTH_LONG);
								t.show();
							}
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
							finally
							{
								
							}
							
						
					}
				});
					alert.setButton3("Toggle View", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(map.isSatellite())
							{
							map.setSatellite(false);
							map.setStreetView(true);
							}
							else
							{
							map.setStreetView(false);
							map.setSatellite(true);
							}
							
						}
					});
					alert.show();
					return true;
			}
			
			
			return false;
			
		}
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location != null)
		{
			lat = (int) (location.getLatitude() * 1E6);
			longi = (int) (location.getLongitude() * 1E6);
			GeoPoint ourLocation = new GeoPoint(lat, longi);
			OverlayItem overlayItem = new OverlayItem(ourLocation, "Whats up", "Second String");
			CustomPinpoint custom = new CustomPinpoint(e, MapsActivity.this);		
			custom.insertPinPoint(overlayItem);
			overlayList.add(custom);
		}
		else
		{
			Toast.makeText(MapsActivity.this,"Couldn't find provider.", Toast.LENGTH_SHORT);
			
		}
	
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
