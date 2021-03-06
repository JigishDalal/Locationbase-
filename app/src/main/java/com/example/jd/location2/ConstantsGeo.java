package com.example.jd.location2;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by JD on 3/9/2017.
 */

public class ConstantsGeo {
    private ConstantsGeo() {
    }
    public static final String PACKAGE_NAME = "com.example.jd.location2";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    //public static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km
    public static final float GEOFENCE_RADIUS_IN_METERS = 1; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // San Francisco International Airport.
        BAY_AREA_LANDMARKS.put("Home", new LatLng(22.322620, 73.241410));

        // Googleplex.
        BAY_AREA_LANDMARKS.put("Rajdeep", new LatLng(22.322630, 73.241444));

        // Test
        BAY_AREA_LANDMARKS.put("hemdeep ", new LatLng(22.322603, 73.241400));
    }
}
