package com.smart.cst.afimail.plan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.smart.cst.afimail.staff.AfiMember;
import com.smart.cst.afimail.staff.DbAfiMember;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.smart.cst.afimail.app.AppConfig.mypreference;
import static com.smart.cst.afimail.app.AppConfig.staffIdKey;

public class MainActivityPlan extends AppCompatActivity {

    private static final String[] ACTIVITY = new String[]{
            "Activity 1", "Activity 2", "Activity 3"
    };
    MaterialBetterSpinner activity;
    EditText geoTag;

    AfiMember afiMember = null;
    DbAfiMember dbAfiMember;
    SharedPreferences sharedpreferences;
    ProgressDialog pDialog;
    PlanAdapter planAdapter;
    RecyclerView taskList;
    ArrayList<Plan> itemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbar_main);

        dbAfiMember = new DbAfiMember(this);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        getSupportActionBar().setSubtitle("Kabul");
        taskList = (RecyclerView) findViewById(R.id.recycler_view);
        planAdapter = new PlanAdapter(this, itemArrayList);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        taskList.setLayoutManager(thirdManager);
        taskList.setAdapter(planAdapter);

        pDialog = new ProgressDialog(this);

        try {
            afiMember = new Gson().fromJson(dbAfiMember.getDataBystaffid(sharedpreferences.getString(staffIdKey, "")).get(1), AfiMember.class);
        } catch (Exception e) {
            Log.e("XXXXXXXX", e.toString());
            Toast.makeText(getApplicationContext(), "No Farmer found", Toast.LENGTH_SHORT).show();
            finish();
        }


        FloatingActionButton addActivity = findViewById(R.id.addActivity);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActivity();
            }
        });

        getAllPlan();

    }


    private void getAllPlan() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_ALL_PLAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean success = jObj.getBoolean("success");
                    if (success) {
                        JSONArray jsonArray = jObj.getJSONArray("data");
                        itemArrayList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Plan staff = new Gson().fromJson(jsonArray.get(i).toString(), Plan.class);
                            itemArrayList.add(staff);
                        }
                        planAdapter.notifyData(itemArrayList);
                    }

                } catch (JSONException e) {
                    Log.e("xxxxxxxxxxx", e.toString());
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

                localHashMap.put("email", afiMember.getEmail());
                localHashMap.put("designation", afiMember.getDesignation());

                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void registerPlan(AlertDialog alertDialog) {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER_PLAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt("success");
                    String msg = jObj.getString("message");
                    if (success == 1) {
                        getAllPlan();
                        alertDialog.cancel();
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Log.e("xxxxxxxxxxx", e.toString());
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
                localHashMap.put("sector", "agriculture");
                localHashMap.put("activity", activity.getText().toString());
                localHashMap.put("geoTag", geoTag.getText().toString());
                localHashMap.put("userid", afiMember.getEmail());
                localHashMap.put("extension", afiMember.getExtension());
                localHashMap.put("district", afiMember.getDistrict());
                localHashMap.put("province", afiMember.getProvince());
                localHashMap.put("state", afiMember.getState());
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

    private void addActivity() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.create_activity, null);
        dialogBuilder.setView(dialogView);

        activity = dialogView.findViewById(R.id.activity);
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ACTIVITY);
        activity.setAdapter(departmentAdapter);

        geoTag = dialogView.findViewById(R.id.geoTag);
        TextView submit = dialogView.findViewById(R.id.submit);
        AlertDialog alertDialog = dialogBuilder.create();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPlan(alertDialog);
            }
        });


        alertDialog.show();


    }
}
