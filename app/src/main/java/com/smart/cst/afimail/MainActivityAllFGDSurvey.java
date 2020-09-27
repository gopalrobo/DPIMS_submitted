package com.smart.cst.afimail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.smart.cst.afimail.app.AndroidMultiPartEntity;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.smart.cst.afimail.attachment.Base;
import com.smart.cst.afimail.maps.CustClusterHouseHoldActivity;
import com.smart.cst.afimail.member.Member;
import com.smart.cst.afimail.report.ColumnChartActivity;
import com.smart.cst.afimail.report.LineChartActivity;
import com.smart.cst.afimail.staff.DbStaff;
import com.smart.cst.afimail.staff.Farmer;
import com.smart.cst.afimail.staff.StaffUpdate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.smart.cst.afimail.app.AppConfig.staffIdKey;


public class MainActivityAllFGDSurvey extends AppCompatActivity {


    private ProgressDialog pDialog;
    private RecyclerView mastersList;
    private MasterAllFGDSurveyAdapter mRecyclerAdapterMaster;
    private ArrayList<FGDNewBean> itemArrayList = new ArrayList<>();


    public static final String mypreference = "mypref";
    public static final String buSurveyerId = "buSurveyerIdKey";
    SharedPreferences sharedpreferences;
    DbHelperSurvey dbHelperSurvey;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_all_fgd_survey);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        getSupportActionBar().setTitle("");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        dbHelperSurvey = new DbHelperSurvey(this);


        FloatingActionButton addMaster = (FloatingActionButton) findViewById(R.id.addSurvey);
        addMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityAllFGDSurvey.this, MainActivityNewFGD.class);
                startActivity(intent);
            }
        });
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mastersList = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapterMaster = new MasterAllFGDSurveyAdapter(this, itemArrayList);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mastersList.setLayoutManager(thirdManager);
        mastersList.setAdapter(mRecyclerAdapterMaster);
    }

    private void getAllData() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_PWCFGD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("crop");
                        itemArrayList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject dataObject = jsonArray.getJSONObject(i);
                                FGDNewBean mainFGD = new Gson().fromJson(dataObject.getString("data"), FGDNewBean.class);
                                mainFGD.setId(dataObject.getString("formId"));
                                String surveyId = dataObject.getString("formId");
                                if (surveyId != null) {
                                    int l = dbHelperSurvey.updateNote(surveyId,
                                            sharedpreferences.getString(buSurveyerId, ""),
                                            new Gson().toJson(mainFGD));
                                    if (l > 0) {
                                        Log.e("xxxxxxxx", "Updated");
                                    } else {
                                        dbHelperSurvey.insertSurvey(surveyId,
                                                sharedpreferences.getString(buSurveyerId, ""), new Gson().toJson(mainFGD));
                                        Log.e("xxxxxxxx", "Recorded successFully");
                                    }
                                }

                            } catch (Exception e) {
                                Log.e("xxxxxxxxxxx", e.toString());
                            }
                        }
                        itemArrayList = dbHelperSurvey.getStudentsNotes(sharedpreferences.getString(buSurveyerId, ""));
                        getSupportActionBar().setSubtitle(String.valueOf(itemArrayList.size()) + " Surveys");
                        mRecyclerAdapterMaster.notifyData(itemArrayList);
                        if (itemArrayList.size() == 0) {
                            Intent intent = new Intent(MainActivityAllFGDSurvey.this, MainActivityNewFGD.class);
                            startActivity(intent);
                        }
                    } else {
                        Intent intent = new Intent(MainActivityAllFGDSurvey.this, MainActivityNewFGD.class);
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    Log.e("xxxxxxxxxxx", e.toString());
                    //     Toast.makeText(getApplicationContext(), "Some Network Error.Try after some time", Toast.LENGTH_SHORT).show();

                }
                hideDialog();

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
                localHashMap.put("key", sharedpreferences.getString(buSurveyerId, ""));
                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void hideDialog() {
        if (this.pDialog.isShowing())
            this.pDialog.dismiss();
    }


    private void showDialog() {
        if (!this.pDialog.isShowing())
            this.pDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
//        itemArrayList = dbHelperSurvey.getStudentsNotes(sharedpreferences.getString(buSurveyerId, ""));
//        mRecyclerAdapterMaster.notifyData(itemArrayList);
//
//        if(itemArrayList.size()==0){
        getAllData();
//        }

    }


    private boolean isValidString(String string) {

        if (string == null) {
            return false;
        } else if (string.length() <= 0) {
            return false;
        } else if (string.startsWith("http")) {
            return false;
        } else if (string.startsWith("coconut")) {
            return false;
        }

        return true;
    }

    public String getfilename_from_path(String path) {
        return path.substring(path.lastIndexOf('/') + 1, path.length());
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.main_menu, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            default:
                return super.onOptionsItemSelected(paramMenuItem);
            case R.id.editProfile:
                Intent io = new Intent(MainActivityAllFGDSurvey.this, StaffUpdate.class);
                startActivity(io);
                return true;
            case R.id.exit:
                AlertDialog diaBox = Option();
                diaBox.show();
                return true;
            case R.id.columnChart:
                Intent intentCo = new Intent(MainActivityAllFGDSurvey.this, ColumnChartActivity.class);
                startActivity(intentCo);
                return true;
            case R.id.lineChart:
                Intent intentch = new Intent(MainActivityAllFGDSurvey.this, LineChartActivity.class);
                startActivity(intentch);
                return true;
            case R.id.location:
                Intent loca = new Intent(MainActivityAllFGDSurvey.this, CustClusterHouseHoldActivity.class);
                startActivity(loca);
                return true;
            case R.id.upload:
                itemArrayList.get(0).setPosition("0");
                new UploadImagesToServer().execute(itemArrayList.get(0));
                return true;

        }


    }


    private AlertDialog Option() {

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Alert")
                .setMessage("Are you sure want to exit?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        DbStaff dbStaff = new DbStaff(MainActivityAllFGDSurvey.this);
                        Farmer farmer = new Gson().fromJson(dbStaff.getDataBystaffid(sharedpreferences.getString(staffIdKey, "")).get(1), Farmer.class);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.remove(staffIdKey);
                        editor.commit();
                        Intent io = new Intent(MainActivityAllFGDSurvey.this, LoginActivity.class);
                        startActivity(io);
                        finish();
                    }
                })


                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }


    private class UploadImagesToServer extends AsyncTask<FGDNewBean, Integer, String> {
        FGDNewBean survey;
        Map<String, String> filePosition = new HashMap<>();
        public long totalSize = 0;

        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            showDialog();
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            pDialog.setMessage("Uploading..." + (String.valueOf(progress[0])));
        }

        @Override
        protected String doInBackground(FGDNewBean... params) {
            survey = params[0];
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(AppConfig.URL_UPLOAD_IMAGES);
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                filePosition = new HashMap<>();

                ArrayList<Member> members = survey.getMemberArrayList();
                for (int j = 0; j < members.size(); j++) {
                    String base = members.get(j).getMemberImage();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(0) + "_" + String.valueOf(j));
                    }
                }

                ArrayList<Base> bases = survey.getBases1();
                for (int j = 0; j < bases.size(); j++) {
                    String base = bases.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(1) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases1 = survey.getBases2();
                for (int j = 0; j < bases1.size(); j++) {
                    String base = bases1.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(2) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases2 = survey.getBases3();
                for (int j = 0; j < bases2.size(); j++) {
                    String base = bases2.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(3) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases4 = survey.getBases4();
                for (int j = 0; j < bases4.size(); j++) {
                    String base = bases4.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(4) + "_" + String.valueOf(j));
                    }
                }

                ArrayList<Base> bases5 = survey.getBases5();
                for (int j = 0; j < bases5.size(); j++) {
                    String base = bases5.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(5) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases6 = survey.getBases6();
                for (int j = 0; j < bases6.size(); j++) {
                    String base = bases6.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(6) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases7 = survey.getBases7();
                for (int j = 0; j < bases7.size(); j++) {
                    String base = bases7.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(7) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases8 = survey.getBases8();
                for (int j = 0; j < bases8.size(); j++) {
                    String base = bases8.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(8) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases9 = survey.getBases9();
                for (int j = 0; j < bases9.size(); j++) {
                    String base = bases9.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(9) + "_" + String.valueOf(j));
                    }
                }
                ArrayList<Base> bases10 = survey.getBases10();
                for (int j = 0; j < bases10.size(); j++) {
                    String base = bases10.get(j).getUrl();
                    if (isValidString(base)) {
                        File sourceFile = new File(base);
                        entity.addPart("image[]", new FileBody(sourceFile));
                        filePosition.put(getfilename_from_path(base), String.valueOf(10) + "_" + String.valueOf(j));
                    }
                }

                // Adding file data to http body
                totalSize = entity.getContentLength();
                if (totalSize == 0) {

                }
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
            hideDialog();
            try {
                JSONObject jsonObject = new JSONObject(result.toString());
                Iterator<String> keys = jsonObject.keys();
                if (!jsonObject.getBoolean("error")) {
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (!key.equals("error")) {
                            if (jsonObject.get(key) instanceof Boolean) {
                                boolean value = jsonObject.getBoolean(key);
                                if (value) {
                                    if (filePosition.containsKey(key)) {
                                        String position = filePosition.get(key);
                                        String[] positions = position.split("_");
                                        if (positions[0].equals("0")) {
                                            survey.getMemberArrayList()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setMemberImage(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("1")) {
                                            survey.getBases1()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("2")) {
                                            survey.getBases2()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("3")) {
                                            survey.getBases3()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("4")) {
                                            survey.getBases4()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("5")) {
                                            survey.getBases5()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("6")) {
                                            survey.getBases6()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("7")) {
                                            survey.getBases7()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("8")) {
                                            survey.getBases8()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("9")) {
                                            survey.getBases9()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        } else if (positions[0].equals("10")) {
                                            survey.getBases10()
                                                    .get(Integer.parseInt(positions[1]))
                                                    .setUrl(AppConfig.imagePath + key);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    dbHelperSurvey.updateNote(survey.id, sharedpreferences.getString(buSurveyerId, ""),
                            new Gson().toJson(survey));
                }
                registerUser(new Gson().toJson(survey), survey.id, Integer.parseInt(survey.position));

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Images not uploaded", Toast.LENGTH_SHORT).show();
                registerUser(new Gson().toJson(survey), survey.id, Integer.parseInt(survey.position));
            }

            // showing the server response in an alert dialog
            //showAlert(result);


            super.onPostExecute(result);
        }

    }


    private void registerUser(final String mData, final String id, final int position) {
        String tag_string_req = "req_register";
        pDialog.setMessage("Processing " + String.valueOf(position) + "...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_CREATE_OFFLINE_SURVEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                hideDialog();
                try {
                    if (position + 1 < itemArrayList.size()) {

                        itemArrayList.get(position + 1).setPosition(String.valueOf(position + 1));
                        new UploadImagesToServer().execute(itemArrayList.get(position + 1));

                    } else {
                        Toast.makeText(getApplicationContext(), "All details updated", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "All details updated", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.e("Registration Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Some Network Error.Try after some time", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap localHashMap = new HashMap();
                localHashMap.put("id", id);
                localHashMap.put("data", mData);
                localHashMap.put("studentid", sharedpreferences.getString(buSurveyerId, ""));
                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


}


