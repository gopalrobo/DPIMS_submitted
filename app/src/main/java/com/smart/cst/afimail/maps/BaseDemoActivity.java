/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smart.cst.afimail.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.smart.cst.afimail.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class BaseDemoActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    GeoJsonLayer layer;
    Marker layerMarker;
    Polyline layerLine;
    Polyline layerLineDistrict;
    private View mCustomMarkerView;
    TextView provinceName;
    TextView districtCount;
    TextView villageCount;
    JSONObject jsonObject;
    private String lastSelectedPro = "";

    protected int getLayoutId() {
        return R.layout.map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUpMap();

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Smart DPIMS");
        toolbar.setSubtitle("Geotags");
        final FloatingActionButton btnSatellite = (FloatingActionButton) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap != null) {
                    mMap.setMapType(mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID ? GoogleMap.MAP_TYPE_NORMAL : GoogleMap.MAP_TYPE_HYBRID);
                    btnSatellite.setImageResource(mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID ? R.drawable.ic_satellite_off : R.drawable.ic_satellite_on);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(34.521237, 69.139524), 5));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                try {
                    View view = ((LayoutInflater)
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                            .inflate(R.layout.view_custom_marker, null);

                    TextView provinceName = view.findViewById(R.id.provinceName);
                    TextView districtCount = view.findViewById(R.id.districtCount);
                    TextView villageCount = view.findViewById(R.id.villageCount);

                    villageCount.setText(arg0.getTitle());

                    districtCount.setText(Html.fromHtml(arg0.getSnippet()));
                    provinceName.setText("");
                    provinceName.setVisibility(View.GONE);
                    return view;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public View getInfoContents(Marker marker) {

                return null;
            }
        });


        mCustomMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        provinceName = mCustomMarkerView.findViewById(R.id.provinceName);
        districtCount = mCustomMarkerView.findViewById(R.id.districtCount);
        villageCount = mCustomMarkerView.findViewById(R.id.villageCount);

        try {
            jsonObject = new JSONObject(loadJSONFromAsset());
            GeoJsonLayer layer1 = new GeoJsonLayer(mMap, R.raw.provinces,
                    getApplicationContext());
            layer1.addLayerToMap();
            final GeoJsonPolygonStyle style1 = layer1.getDefaultPolygonStyle();
            style1.setStrokeColor(Color.parseColor("#4d4d4d"));
            style1.setFillColor(Color.TRANSPARENT);
            style1.setStrokeWidth(1F);
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    try {
                        boolean isFoundFalse = false;
                        for (Iterator i = layer1.getFeatures().iterator(); i.hasNext(); ) {
                            GeoJsonFeature feature = (GeoJsonFeature) i.next();
                            GeoJsonPolygon geometry = (GeoJsonPolygon) feature.getGeometry();
                            List<? extends List<LatLng>> latLngs = geometry.getCoordinates();
                            List<LatLng> latLngs1 = collect(latLngs);
                            boolean isExisted = PolyUtil.containsLocation(latLng,
                                    latLngs1, false);
                            if (isExisted == true) {

                                PolylineOptions options = new PolylineOptions().width(2).color(getResources().
                                        getColor(R.color.colorPrimary))
                                        .geodesic(true);
                                for (int z = 0; z < latLngs1.size(); z++) {
                                    LatLng point = latLngs1.get(z);
                                    options.add(point);
                                }

                                for (Iterator j = feature.getProperties().iterator();
                                     j.hasNext(); ) {
                                    Map.Entry map1 = (Map.Entry)
                                            j.next();

                                    if (map1.getKey().equals("Prov_Name")) {
                                        if (layerLineDistrict != null) {
                                            layerLineDistrict.remove();
                                        }
                                        if (lastSelectedPro.length() > 0
                                                && lastSelectedPro.equals(map1.getValue())) {

                                            selectDistrict(latLng);
                                            break;
                                        } else {
                                            lastSelectedPro = map1.getValue().toString();
                                        }
                                        if (layerLine != null) {
                                            layerLine.remove();
                                        }
                                        layerLine = mMap.addPolyline(options);

                                        if (layer != null) {
                                            layer.removeLayerFromMap();
                                        }
                                        if (layerMarker != null) {
                                            layerMarker.remove();
                                        }
                                        Log.e("xxxxxxxxxx", map1.getValue().toString());
                                        provinceName.setText(map1.getValue().toString() + " (P)");
                                        provinceName.setTextColor(getResources()
                                                .getColor(R.color.colorPrimary));
                                        if (!jsonObject.isNull(map1.getValue().toString())) {
                                            JSONObject disObject = jsonObject.getJSONObject(map1.getValue().toString());
                                            Iterator<String> keys = disObject.keys();
                                            int disCountVal = 0;
                                            int villageCountVal = 0;
                                            while (keys.hasNext()) {
                                                String disName = keys.next();
                                                disCountVal++;
                                                if (!disObject.isNull(disName)) {
                                                    villageCountVal = villageCountVal + disObject.getJSONArray(disName).length();
                                                }
                                            }
                                            villageCount.setText(villageCountVal + " Villages");
                                            districtCount.setText(disCountVal + " Districts");
                                            districtCount.setTextColor(getResources()
                                                    .getColor(R.color.greenafi));
                                        }
                                        int fileId = getResources().getIdentifier(map1.getValue().toString().toLowerCase(),
                                                "raw", getPackageName());
                                        layer = new GeoJsonLayer(mMap, fileId,
                                                getApplicationContext());
                                        layer.addLayerToMap();
                                        final GeoJsonPolygonStyle style = layer.getDefaultPolygonStyle();
                                        style.setStrokeColor(Color.parseColor("#4d4d4d"));
                                        style.setFillColor(Color.TRANSPARENT);
                                        style.setStrokeWidth(0.5F);
                                        isFoundFalse = true;
                                        LatLng latLng1 = computeCentroid(latLngs1);
                                        layerMarker = mMap.addMarker(new MarkerOptions()
                                                .position(latLng1)
                                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView))));
                                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 7));
                                        break;
                                    }
                                }
                                if (isFoundFalse) {
                                    break;
                                }
                            }

                        }
                    } catch (Exception e) {

                    }
                }
            });


        } catch (Exception e) {
            Log.e("xxxxxxxx", e.toString());
        }

    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    /**
     * Run the demo-specific code.
     */
    protected abstract void startDemo(String districtId, String districtname, String proName);

    protected GoogleMap getMap() {
        return mMap;
    }

    List<LatLng> collect(List<? extends List<LatLng>> a1) {
        List<LatLng> collected = new ArrayList<>();
        for (int i = 0; i < a1.size(); i++) {
            collected.addAll(a1.get(i));
        }
        return collected;
    }

    private LatLng computeCentroid(List<LatLng> points) {
        double latitude = 0;
        double longitude = 0;
        int n = points.size();

        for (LatLng point : points) {
            latitude += point.latitude;
            longitude += point.longitude;
        }

        return new LatLng(latitude / n, longitude / n);
    }

    private Bitmap getMarkerBitmapFromView(View view) {

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }

    public void selectDistrict(LatLng latLng) throws Exception {

        for (Iterator i = layer.getFeatures().iterator(); i.hasNext(); ) {
            GeoJsonFeature feature = (GeoJsonFeature) i.next();
            GeoJsonPolygon geometry = (GeoJsonPolygon) feature.getGeometry();
            List<? extends List<LatLng>> latLngs = geometry.getCoordinates();
            List<LatLng> latLngs1 = collect(latLngs);
            boolean isExisted = PolyUtil.containsLocation(latLng,
                    latLngs1, false);
            if (isExisted == true) {
                PolylineOptions options = new PolylineOptions().width(2).color(getResources().
                        getColor(R.color.greenafi))
                        .geodesic(true);
                for (int z = 0; z < latLngs1.size(); z++) {
                    LatLng point = latLngs1.get(z);
                    options.add(point);
                }

                for (Iterator j = feature.getProperties().iterator();
                     j.hasNext(); ) {
                    Map.Entry map1 = (Map.Entry)
                            j.next();

                    if (map1.getKey().equals("Dist_Name")) {
                        if (layerLineDistrict != null) {
                            layerLineDistrict.remove();
                        }
                        layerLineDistrict = mMap.addPolyline(options);
                        if (layerMarker != null) {
                            layerMarker.remove();
                        }

                        JSONArray disArray = jsonObject.getJSONObject(lastSelectedPro).getJSONArray(map1.getValue().toString());
                        villageCount.setText(disArray.length() + " Villages");
                        districtCount.setText(map1.getValue().toString()+" (D)");
                        districtCount.setTextColor(getResources()
                                .getColor(R.color.greenafi));

                        LatLng latLng1 = computeCentroid(latLngs1);
                        layerMarker = mMap.addMarker(new MarkerOptions()
                                .position(latLng1)
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView))));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 7));
                    } else if (map1.getKey().equals("Dist_ID")) {
                        startDemo(map1.getValue().toString(), districtCount.getText().toString(), lastSelectedPro);
                    }
                }
            }
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cites.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String[] toArray(Iterator<String> itr) {
        ArrayList<String> ret = new ArrayList<>();
        while (itr.hasNext()) {
            ret.add(itr.next());
        }
        return ret.toArray(new String[ret.size()]);
    }

    public static String[] toStringArray(JSONArray array) {
        if (array == null)
            return null;

        String[] arr = new String[array.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.optString(i);
        }
        return arr;
    }
}
