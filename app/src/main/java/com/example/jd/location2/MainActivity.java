package com.example.jd.location2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    protected static final String TAG = "location2";
    protected GoogleApiClient googleApiClient;
    protected Location lastlocation;
    protected TextView lati;
    protected TextView longi;
    LocationRequest mLocationRequest;
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        it's textview
        lati = (TextView) findViewById(R.id.latitude_text);
        longi = (TextView) findViewById(R.id.longitude_text);
//        it's button for next activity
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), Main2Activity.class);
                startActivity(i);
            }
        });
//        calling function BuildgoogleAPIclient
        buildGoogleApiClient();
    }

    private synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    //then after create connect and stop method
    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */

    @Override
    public void onConnected(Bundle bundle) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
     lastlocation=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
     if(lastlocation!=null)
     {
      lati.setText(String.valueOf(lastlocation.getLatitude()));
      longi.setText(String.valueOf(lastlocation.getLongitude()));

     }

    }

    @Override
    public void onConnectionSuspended(int i) {
     // The connection to Google Play services was lost for some reason. We call connect() to
     // attempt to re-establish the connection.
     Toast.makeText(getApplicationContext(), "Connection suspended",Toast.LENGTH_SHORT).show();
     googleApiClient.connect();

    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(),"onchaged "+ location.toString(),Toast.LENGTH_LONG).show();
        //txtOutput.setText(location.toString());

        lati.setText(String.valueOf(location.getLatitude()));
        longi.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
     // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
     // onConnectionFailed.
     Toast.makeText(getApplicationContext(), "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode(),Toast.LENGTH_SHORT).show();

    }
     /*
     * Called by Google Play services if the connection to GoogleApiClient drops because of an
     * error.
     */
     public void onDisconnected() {
      Log.i(TAG, "Disconnected");
     }



}
