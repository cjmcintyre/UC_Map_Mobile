package com.example.u3182551.ucmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import static com.google.android.gms.internal.zzbgp.NULL;

public class StreetView extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    String Street = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);

        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        Bundle extras = getIntent().getExtras();
        if (Street != NULL) {
            Street = extras.getString( "KEY" );
        }
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {

        LatLng StreetViewA = new LatLng(-35.234276, 149.086920);
        LatLng StreetViewB = new LatLng(-35.238447, 149.089441);

        final float ZOOM_BY = 10.0f;
        long duration = 10000;
        float tilt = 0;
        final int PAN_BY = -179;

        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .zoom(panorama.getPanoramaCamera().zoom + ZOOM_BY)
                .bearing(panorama.getPanoramaCamera().bearing - PAN_BY)
                .tilt(tilt)
                .build();

        panorama.animateTo(camera, duration);

        if (Street.equals("KEY1")) {
            panorama.setPosition(StreetViewA);
        } else if (Street.equals("KEY2")) {
            panorama.setPosition(StreetViewB);
        }
    }
}
