package com.smart.cst.afimail.staff;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.smart.cst.afimail.MainActivityNewFGD;
import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.AndroidMultiPartEntity;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.smart.cst.afimail.app.GlideApp;
import com.smart.cst.afimail.app.Imageutils;
import com.smart.cst.afimail.app.LocationTrack;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.smart.cst.afimail.LoginActivity.mypreference;


/**
 * Created by user_1 on 11-07-2018.
 */

public class StaffRegister extends AppCompatActivity implements Imageutils.ImageAttachmentListener {


    private int FASTEST_INTERVAL = 8 * 1000; // 8 SECOND
    private int UPDATE_INTERVAL = 2000; // 2 SECOND
    private int FINE_LOCATION_REQUEST = 888;
    private LocationRequest locationRequest;


    private String TAG = getClass().getSimpleName();

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();


    EditText contact, memberName;
    EditText password;
    EditText confirmPass;
    EditText geotags;
    DbAfiMember dbFarmer;


    private TextView submit;

    private ProgressDialog pDialog;

    SharedPreferences sharedpreferences;

    Imageutils imageutils;
    private CircleImageView studentImage;
    private String imageUrl = "";
    ImageView frontview;
    private String frontviewUrl = "";
    private int typeImg = 0;
    ImageView backview;
    private String backviewUrl = "";


    private ArrayAdapter<String> blockAdapter;
    private ArrayAdapter<String> chcAdapter;


    private final static int ALL_PERMISSIONS_RESULT = 101;
    LocationTrack locationTrack;


    public Spinner village;
    public Spinner province;
    public Spinner district;

    String[] PROVINCE = new String[10];
    String[] DISTRICT = new String[10];
    String[] VILLAGE = new String[10];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_register);

        getSupportActionBar().setTitle("Smart DPIMS");
        getSupportActionBar().setSubtitle("Register Farmer");
        imageutils = new Imageutils(this);
        locationTrack = new LocationTrack(StaffRegister.this);

        if (checkPermissions()) {
            initLocationUpdate();
        }

        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        dbFarmer = new DbAfiMember(this);
        studentImage = (CircleImageView) findViewById(R.id.staffImage);
        studentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeImg = 0;
                imageutils.imagepicker(1);
            }
        });

        frontview = (ImageView) findViewById(R.id.frontview);
        frontview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeImg = 1;
                imageutils.imagepicker(1);
            }
        });
        backview = (ImageView) findViewById(R.id.backview);
        backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeImg = 2;
                imageutils.imagepicker(1);
            }
        });

        memberName = (EditText) findViewById(R.id.name);

        contact = (EditText) findViewById(R.id.contact);
        password = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        geotags = (EditText) findViewById(R.id.geotags);
        village = (Spinner) findViewById(R.id.village);
        province = (Spinner) findViewById(R.id.province);
        district = (Spinner) findViewById(R.id.district);


        submit = (TextView) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memberName.getText().toString().length() > 0 &&
                        contact.getText().toString().length() > 0 &&
                        password.getText().toString().length() > 0 &&
                          confirmPass.getText().toString().length() > 0) {

                    if (!password.getText().toString().equalsIgnoreCase(confirmPass.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Password & Confirm password not matched", Toast.LENGTH_SHORT).show();
                    } else if (contact.getText().toString().length() != 10 || contact.getText().toString().matches(".*[a-zA-Z]+.*")) {
                        Toast.makeText(getApplicationContext(), "Enter a valid Contact", Toast.LENGTH_SHORT).show();
                    }
//                    else if (imageUrl == null) {
//                        Toast.makeText(getApplicationContext(), "Select a Image", Toast.LENGTH_SHORT).show();
//                    }

                    else {
                        registerUser();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter All Fields", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });


        ImageView refreshBtn = (ImageView) findViewById(R.id.refreshBtn);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationTrack = new LocationTrack(StaffRegister.this);
                if (locationTrack.canGetLocation() &&
                        locationTrack.getLatitude() != 0.0 && locationTrack.getLongitude() != 0.0) {

                    double longitude = locationTrack.getLongitude();
                    double latitude = locationTrack.getLatitude();
                    geotags.setText(Double.toString(latitude) + "," + Double.toString(longitude));

                } else {
                    locationTrack.showSettingsAlert();
                }

            }
        });

        locationTrack = new LocationTrack(StaffRegister.this);
        if (locationTrack.canGetLocation() &&
                locationTrack.getLatitude() != 0.0 && locationTrack.getLongitude() != 0.0) {

            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            geotags.setText(Double.toString(latitude) + "," + Double.toString(longitude));

        } else {
            locationTrack.showSettingsAlert();
        }


        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            Iterator<String> keys = jsonObject.keys();
            PROVINCE = toArray(keys);
            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, PROVINCE);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            province.setAdapter(aa);

            province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String item = province.getSelectedItem().toString();
                    try {
                        final JSONObject mandalJson = jsonObject.getJSONObject(item);
                        Iterator<String> keys = mandalJson.keys();
                        DISTRICT = toArray(keys);

                        ArrayAdapter unitAdapter = new ArrayAdapter(StaffRegister.this, android.R.layout.simple_spinner_item, DISTRICT);
                        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        district.setAdapter(unitAdapter);

                        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String mandalItem = district.getSelectedItem().toString();
                                try {
                                    JSONArray subchapterJson = mandalJson.getJSONArray(mandalItem);
                                    VILLAGE = toStringArray(subchapterJson);
                                    if (VILLAGE.length == 0) {
                                        VILLAGE = new String[]{
                                                "No Village"
                                        };
                                    }
                                    ArrayAdapter subchapterAdapter = new ArrayAdapter(StaffRegister.this,
                                            android.R.layout.simple_spinner_item, VILLAGE);
                                    subchapterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    //Setting the ArrayAdapter data on the Spinner
                                    village.setAdapter(subchapterAdapter);

                                } catch (Exception e) {
                                    Log.e("Error", e.toString());
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }

                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            Log.e("Json object", jsonObject.toString());
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }


    }

    private void registerUser() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Processing ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_CREATE_FARMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response.substring(response.indexOf("{"), response.length()));
                    int success = jObj.getInt("success");
                    String msg = jObj.getString("message");
                    if (success == 1) {
                        //AppConfig.sendSMS(contact.getText().toString(), "Namaste! " + memberName.getText().toString() + " Ji Jeevika's SMART DPIMS. Your profile created successfully", StaffRegister.this);
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Some Network Error.Try after some time", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Registration Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Some Network Error.Try after some time", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap localHashMap = new HashMap();
                localHashMap.put("name", memberName.getText().toString());
                localHashMap.put("contact", contact.getText().toString());
                localHashMap.put("geotags", geotags.getText().toString());
                localHashMap.put("province", province.getSelectedItem().toString());
                localHashMap.put("district", district.getSelectedItem().toString());
                localHashMap.put("village", village.getSelectedItem().toString());
                localHashMap.put("frontviewUrl", frontviewUrl);
                localHashMap.put("backviewUrl", backviewUrl);
                localHashMap.put("password", password.getText().toString());
                localHashMap.put("image", imageUrl);
                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ALL_PERMISSIONS_RESULT) {
            for (String perms : permissionsToRequest) {
                if (!hasPermission(perms)) {
                    permissionsRejected.add(perms);
                }
            }

            if (permissionsRejected.size() > 0) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                        showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    }
                                });
                        return;
                    }
                }

            }
        } else if (requestCode == FINE_LOCATION_REQUEST) {
            // Received permission result for Location permission.
            Log.i(TAG, "Received response for Location permission request.");

            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "Location permission has now been granted. Now call initLocationUpdate");
                initLocationUpdate();
            }
        } else {
            imageutils.request_permission_result(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file, filename, path, false);
        pDialog.setMessage("Uploading...");
        showDialog();
        new UploadFileToServer().execute(imageutils.getPath(uri));
    }

    private class UploadFileToServer extends AsyncTask<String, Integer, String> {
        String filepath;
        public long totalSize = 0;

        @Override
        protected void onPreExecute() {
            // setting progress bar to zero

            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            pDialog.setMessage("Uploading..." + (String.valueOf(progress[0])));
        }

        @Override
        protected String doInBackground(String... params) {
            filepath = params[0];
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(AppConfig.URL_IMAGE_UPLOAD);
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(filepath);
                // Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);

                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;

                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("Response from server: ", result);
            try {
                JSONObject jsonObject = new JSONObject(result.toString());
                if (!jsonObject.getBoolean("error")) {
                    if (typeImg == 0) {
                        GlideApp.with(getApplicationContext())
                                .load(filepath)
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(false)
                                .placeholder(R.drawable.profile)
                                .into(studentImage);
                        imageUrl = AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);
                    } else if (typeImg == 1) {
                        GlideApp.with(getApplicationContext())
                                .load(filepath)
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(false)
                                .placeholder(R.drawable.profile)
                                .into(frontview);
                        frontviewUrl = AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);

                    } else if (typeImg == 2) {
                        GlideApp.with(getApplicationContext())
                                .load(filepath)
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(false)
                                .placeholder(R.drawable.profile)
                                .into(backview);
                        backviewUrl = AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);

                    }
                } else {
                    if (typeImg == 0) {
                        imageUrl = "";
                    } else if (typeImg == 1) {
                        frontviewUrl = "";
                    } else if (typeImg == 2) {
                        backviewUrl = "";
                    }
                }
                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Image not uploaded", Toast.LENGTH_SHORT).show();
            }
            hideDialog();
            // showing the server response in an alert dialog
            //showAlert(result);


            super.onPostExecute(result);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        hideDialog();
    }


    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(StaffRegister.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationTrack.stopListener();
    }




    private void initLocationUpdate() {

        // Check API revision for New Location Update
        //https://developers.google.com/android/guides/releases#june_2017_-_version_110

        //init location request to start retrieving location update
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        //Create LocationSettingRequest object using locationRequest
        LocationSettingsRequest.Builder locationSettingBuilder = new LocationSettingsRequest.Builder();
        locationSettingBuilder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSetting = locationSettingBuilder.build();

        //Need to check whether location settings are satisfied
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSetting);
        //More info :  // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient


        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        //super.onLocationResult(locationResult);
                        if (locationResult != null) {
                            onLocationChanged(locationResult.getLastLocation());
                        }
                    }

                    @Override
                    public void onLocationAvailability(LocationAvailability locationAvailability) {
                        super.onLocationAvailability(locationAvailability);
                    }
                },
                Looper.myLooper());

    }

    private void onLocationChanged(Location location) {
        geotags.setText(String.valueOf(location.getLatitude())
                + "," + String.valueOf(location.getLongitude()));
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                FINE_LOCATION_REQUEST);
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

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }

}
