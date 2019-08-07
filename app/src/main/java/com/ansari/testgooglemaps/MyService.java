package com.ansari.testgooglemaps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

public class MyService extends Service {

    FusedLocationProviderClient client;
    LocationRequest locationRequest;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        client = new FusedLocationProviderClient(this);

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(2000);
        locationRequest.setInterval(4000);

client.requestLocationUpdates(locationRequest,new LocationCallback(){
    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);

        double lat = locationResult.getLastLocation().getLatitude();
        double lon = locationResult.getLastLocation().getLongitude();

        Toast.makeText(MyService.this, "latitude is :" + lat + " " + "longitude :" + lon , Toast.LENGTH_SHORT).show();

    }
}, getMainLooper());
        return super.onStartCommand(intent, flags, startId);
    }
}
