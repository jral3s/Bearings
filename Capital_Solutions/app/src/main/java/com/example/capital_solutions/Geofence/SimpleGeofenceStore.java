package com.example.capital_solutions.Geofence;
import java.util.HashMap;

import android.text.format.DateUtils;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;

public class SimpleGeofenceStore {
    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = GEOFENCE_EXPIRATION_IN_HOURS
            * DateUtils.HOUR_IN_MILLIS;
    protected HashMap<String, SimpleGeofence> geofences = new HashMap<>();
    private static SimpleGeofenceStore instance = new SimpleGeofenceStore();

    public static SimpleGeofenceStore getInstance() {
        return instance;
    }

    private SimpleGeofenceStore() {
        geofences.put("Simmons Hall", new SimpleGeofence("Trader Joe's", 42.3418, -71.1203,
                50, GEOFENCE_EXPIRATION_IN_MILLISECONDS,
                Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_DWELL
                        | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("La Verde's", new SimpleGeofence("La Verde's", 42.3590, -71.0947, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Cafe Four", new SimpleGeofence("Cafe Four",35.9650, -83.9192, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Flour Bakery", new SimpleGeofence("Flour Bakery",42.3512, -71.0487,50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Target", new SimpleGeofence("Target",42.3886, -71.1186, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("H Mart", new SimpleGeofence("H Mart",42.3650, -71.1027, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("TJ Maxx", new SimpleGeofence("TJ Maxx",42.3897, -71.1410, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Gap", new SimpleGeofence("Gap",42.3681, -71.0762, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Macy's", new SimpleGeofence("Macy's",42.3682, -71.0762, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("MUJI", new SimpleGeofence("MUJI",42.3484, -71.0878, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Primark", new SimpleGeofence("Primark",42.3555,-71.0601, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("LegalSeaFoods", new SimpleGeofence("LegalSeaFoods", 42.3594,-71.0514, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Champions", new SimpleGeofence("Champions", 42.3470,-71.0793, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("CAVA", new SimpleGeofence("CAVA", 42.3555,-71.0601, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Area Four", new SimpleGeofence("Area Four", 42.3631,-71.0924, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Catalyst", new SimpleGeofence("The Friendly Toast",42.3663,-71.0906, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
        geofences.put("Brewer's Fork", new SimpleGeofence("Brewer's Fork", 42.3767,-71.0565, 50,
                GEOFENCE_EXPIRATION_IN_MILLISECONDS, Geofence.GEOFENCE_TRANSITION_ENTER
                | Geofence.GEOFENCE_TRANSITION_DWELL
                | Geofence.GEOFENCE_TRANSITION_EXIT));
    }

    public HashMap<String, SimpleGeofence> getSimpleGeofences() {
        return this.geofences;
    }
}