package com.betaturtle.panic.app;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.test.RenamingDelegatingContext;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.betaturtle.panic.app.mysc.MyLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GoogleMap googleMap;
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        new MyLocation().getLocation(this, new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                double la1 = location.getLatitude();
                double lo1 = location.getLongitude();

                final LatLng locnow = new LatLng(la1 , lo1);
                Marker TP = googleMap.addMarker(new MarkerOptions().position(locnow).title("Current Location\n"+la1+","+lo1));


                CircleOptions circleOptions = new CircleOptions()
                        .center(new LatLng(location.getLatitude(), location.getLongitude()));
                circleOptions.radius(20.0); // In meters
                circleOptions.strokeWidth(1);
                circleOptions.strokeColor(Color.BLUE);
                circleOptions.fillColor(Color.parseColor("#500084d3"));
                // Move the camera instantly to location with a zoom of 15.
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locnow, 0));

                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);


                googleMap.addCircle(circleOptions);
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
