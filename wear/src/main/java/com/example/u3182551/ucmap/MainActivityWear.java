package com.example.u3182551.ucmap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wear.widget.SwipeDismissFrameLayout;
import android.support.wearable.activity.WearableActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivityWear extends WearableActivity implements OnMapReadyCallback {

    private Polygon polygon2;
    private GoogleMap nMap;
    private static final LatLng COFFEE = new LatLng(-35.238856, 149.082242);
    private static final LatLng GYM = new LatLng(-35.238453, 149.088204);
    private static final LatLng PARKING = new LatLng(-35.240789, 149.080463);
    private static final LatLng POST = new LatLng(-35.238371, 149.084517);
    private static final LatLng STUDENTCENTRE = new LatLng(-35.238888, 149.084488);
    private static final LatLng THEHUB = new LatLng(-35.238106, 149.084023);
    private static final LatLng LIBRARY = new LatLng(-35.238032, 149.083411);
    private static final LatLng STREETVIEW1 = new LatLng(-35.234442, 149.086851);
    private static final LatLng STREETVIEW2 = new LatLng(-35.238438, 149.089442);
    private static final LatLng NATSEM = new LatLng(-35.240904, 149.085580);
    private LatLngBounds UCCANBERRA = new LatLngBounds(new LatLng(-35.242938, 149.075590), new LatLng(-35.230775, 149.092928));
    private Marker nCoffee;
    private Marker nGym;
    private Marker nParking;
    private Marker nPost;
    private Marker nStudentCentre;
    private Marker nTheHub;
    private Marker nLibrary;
    private Marker nNatsem;
    private Marker nStreetView1;
    private Marker nStreetView2;
    final String StreetViewA = "KEY1";
    final String StreetViewB = "KEY2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);
        setAmbientEnabled();


        final SwipeDismissFrameLayout swipeDismissRootFrameLayout =
                (SwipeDismissFrameLayout) findViewById(R.id.swipe_dismiss_root_container);
        final FrameLayout mapFrameLayout = (FrameLayout) findViewById(R.id.map_container);

        // Enables the Swipe-To-Dismiss Gesture via the root layout (SwipeDismissFrameLayout).
        // Swipe-To-Dismiss is a standard pattern in Wear for closing an app and needs to be
        // manually enabled for any Google Maps Activity. For more information, review our docs:
        // https://developer.android.com/training/wearables/ui/exit.html
        swipeDismissRootFrameLayout.addCallback(new SwipeDismissFrameLayout.Callback() {
            @Override
            public void onDismissed(SwipeDismissFrameLayout layout) {
                // Hides view before exit to avoid stutter.
                layout.setVisibility(View.GONE);
                finish();
            }
        });

        // Adjusts margins to account for the system window insets when they become available.
        swipeDismissRootFrameLayout.setOnApplyWindowInsetsListener(
                new View.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                        insets = swipeDismissRootFrameLayout.onApplyWindowInsets(insets);

                        FrameLayout.LayoutParams params =
                                (FrameLayout.LayoutParams) mapFrameLayout.getLayoutParams();

                        // Sets Wearable insets to FrameLayout container holding map as margins
                        params.setMargins(
                                insets.getSystemWindowInsetLeft(),
                                insets.getSystemWindowInsetTop(),
                                insets.getSystemWindowInsetRight(),
                                insets.getSystemWindowInsetBottom());
                        mapFrameLayout.setLayoutParams(params);

                        return insets;
                    }
                });

        // Obtain the MapFragment and set the async listener to be notified when the map is ready.
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap = googleMap;

        nMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                nMap.moveCamera(CameraUpdateFactory.newLatLngBounds(UCCANBERRA,20));
            }
        });

        nGym = nMap.addMarker(new MarkerOptions()
                .position(GYM)
                .title("Gym")
                .snippet("UC Gym")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gym)));

        nStreetView1 = nMap.addMarker(new MarkerOptions()
                .position(STREETVIEW1)
                .title("University Drive")
                .snippet("University Dr")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_street_view_person)));

        nStreetView2 = nMap.addMarker(new MarkerOptions()
                .position(STREETVIEW2)
                .title("Allawoona Street")
                .snippet("Allawoona St")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_street_view_person)));

        nParking = nMap.addMarker(new MarkerOptions()
                .position(PARKING)
                .title("Parking")
                .snippet("UC Car Parks")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking)));

        nCoffee = nMap.addMarker(new MarkerOptions()
                .position(COFFEE)
                .title("Coffee Grounds")
                .snippet("Best Coffee On Campus")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_coffee)));

        nPost = nMap.addMarker(new MarkerOptions()
                .position(POST)
                .title("Post Office")
                .snippet("University Post Office")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_post)));

        nLibrary = nMap.addMarker(new MarkerOptions()
                .position(LIBRARY)
                .title("Library")
                .snippet("UC Library")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_library)));

        nStudentCentre = nMap.addMarker(new MarkerOptions()
                .position(STUDENTCENTRE)
                .title("Ask UC")
                .snippet("UC Help")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_student_centre)));

        nTheHub = nMap.addMarker(new MarkerOptions()
                .position(THEHUB)
                .title("The Hub")
                .snippet("UC Refactory")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_thehub)));

        nNatsem = nMap.addMarker(new MarkerOptions()
                .position(NATSEM)
                .title("NATSEM Center")
                .snippet("Social & Economic")
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_sc)));

        polygon2 = nMap.addPolygon(new PolygonOptions()
                .strokeColor(0)
                .strokeWidth(0)
                .fillColor(Color.argb(50, 15, 15, 214))
                .add(new LatLng(-35.23511, 149.09162)).add(new LatLng(-35.23683, 149.09103)).add(new LatLng(-35.23848, 149.09039)).add(new LatLng(-35.23896, 149.09019)).add(new LatLng(-35.23948, 149.09005)).add(new LatLng(-35.24026, 149.08994)).add(new LatLng(-35.24075, 149.08995)).add(new LatLng(-35.24147, 149.08998)).add(new LatLng(-35.24179, 149.09006)).add(new LatLng(-35.24187, 149.09004)).add(new LatLng(-35.24198, 149.08989)).add(new LatLng(-35.24222, 149.08886)).add(new LatLng(-35.24226, 149.08803)).add(new LatLng(-35.2423, 149.08668)).add(new LatLng(-35.24232, 149.08386)).add(new LatLng(-35.2423, 149.08291)).add(new LatLng(-35.24231, 149.08204)).add(new LatLng(-35.24231, 149.0809)).add(new LatLng(-35.24229, 149.08022)).add(new LatLng(-35.24229, 149.0799)).add(new LatLng(-35.24231, 149.0795)).add(new LatLng(-35.24233, 149.07932)).add(new LatLng(-35.24232, 149.07879)).add(new LatLng(-35.24229, 149.07841)).add(new LatLng(-35.24229, 149.07802)).add(new LatLng(-35.24234, 149.07763)).add(new LatLng(-35.24078, 149.0752)).add(new LatLng(-35.24017, 149.07563)).add(new LatLng(-35.23987, 149.07585)).add(new LatLng(-35.23957, 149.07603)).add(new LatLng(-35.2384, 149.07682)).add(new LatLng(-35.23781, 149.07714)).add(new LatLng(-35.23731, 149.07727)).add(new LatLng(-35.23703, 149.07732)).add(new LatLng(-35.23674, 149.07737)).add(new LatLng(-35.23655, 149.0774)).add(new LatLng(-35.23628, 149.07751)).add(new LatLng(-35.23593, 149.07764)).add(new LatLng(-35.23559, 149.07777)).add(new LatLng(-35.23524, 149.0779)).add(new LatLng(-35.23481, 149.07808)).add(new LatLng(-35.23455, 149.07825)).add(new LatLng(-35.23442, 149.07831)).add(new LatLng(-35.23437, 149.0784)).add(new LatLng(-35.23419, 149.07852)).add(new LatLng(-35.23406, 149.07861)).add(new LatLng(-35.23391, 149.07872)).add(new LatLng(-35.23377, 149.07882)).add(new LatLng(-35.2336, 149.07892)).add(new LatLng(-35.23337, 149.07909)).add(new LatLng(-35.23303, 149.07934)).add(new LatLng(-35.23273, 149.0796)).add(new LatLng(-35.23257, 149.07977)).add(new LatLng(-35.23241, 149.07993)).add(new LatLng(-35.23222, 149.08006)).add(new LatLng(-35.23197, 149.0802)).add(new LatLng(-35.23173, 149.08031)).add(new LatLng(-35.23146, 149.08042)).add(new LatLng(-35.23133, 149.08047)).add(new LatLng(-35.23129, 149.08051)).add(new LatLng(-35.23127, 149.08058)).add(new LatLng(-35.23126, 149.08071)).add(new LatLng(-35.23127, 149.08093)).add(new LatLng(-35.2313, 149.08128)).add(new LatLng(-35.23133, 149.08159)).add(new LatLng(-35.23144, 149.08219)).add(new LatLng(-35.23165, 149.08301)).add(new LatLng(-35.23192, 149.08379)).add(new LatLng(-35.23229, 149.08467)).add(new LatLng(-35.23314, 149.08612)).add(new LatLng(-35.23355, 149.08682)).add(new LatLng(-35.23364, 149.08697)).add(new LatLng(-35.2337, 149.08699)).add(new LatLng(-35.23379, 149.08718)).add(new LatLng(-35.23387, 149.0873)).add(new LatLng(-35.23394, 149.08736)).add(new LatLng(-35.234, 149.0878)).add(new LatLng(-35.23409, 149.088)).add(new LatLng(-35.23421, 149.08827)).add(new LatLng(-35.23431, 149.08868)).add(new LatLng(-35.23447, 149.08931)).add(new LatLng(-35.23456, 149.08977)).add(new LatLng(-35.23466, 149.09023)).add(new LatLng(-35.23478, 149.09127)).add(new LatLng(-35.23482, 149.09144)).add(new LatLng(-35.23485, 149.09153)).add(new LatLng(-35.23492, 149.09159)).add(new LatLng(-35.23501, 149.09163)));
        polygon2.setTag("alpha");
        polygon2.setClickable(true);

        nMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }


            @Override
            public View getInfoContents(Marker marker) {
                View infoWindow = getLayoutInflater().inflate(R.layout.infowindowwear, null);
                TextView title = (TextView) infoWindow.findViewById(R.id.wtViewTitle);
                TextView snippet = (TextView) infoWindow.findViewById(R.id.wtViewSnippet);
                ImageView image = (ImageView) infoWindow.findViewById(R.id.wimageView);

                if (marker.getId().equals(nCoffee.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_coffee, getTheme()));
                }
                if (marker.getId().equals(nGym.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_gym, getTheme()));
                }
                if (marker.getId().equals(nParking.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_parking, getTheme()));
                }
                if (marker.getId().equals(nPost.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_post, getTheme()));
                }
                if (marker.getId().equals(nStudentCentre.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_student_centre, getTheme()));
                }
                if (marker.getId().equals(nTheHub.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_thehub, getTheme()));
                }

                if (marker.getId().equals(nLibrary.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_library, getTheme()));
                }

                if (marker.getId().equals(nNatsem.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_sc, getTheme()));
                }

                if (marker.getId().equals(nStreetView1.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_street_view_person, getTheme()));

                    Intent intent = new Intent(MainActivityWear.this, StreetView.class);
                    intent.putExtra("KEY", StreetViewA);
                    startActivity(intent);
                }

                if (marker.getId().equals(nStreetView2.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_street_view_person, getTheme()));

                    Intent intent = new Intent(MainActivityWear.this, StreetView.class);
                    intent.putExtra("KEY", StreetViewB);
                    startActivity(intent);
                }

                return infoWindow;
            }
        });

        nMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon2) {
                polygon2.setStrokeColor(Color.rgb(22, 22, 22));
                polygon2.setStrokeWidth(10);

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("University of Canberra");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

    }
}

