package com.smart.cst.afimail.master.emp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateEmployee extends AppCompatActivity {

    private String[] HISTORY = new String[]{
            "New Employee","Transfered","Promoted"
    };

    EditText fullName;
    EditText empId;
    EditText mobile;
    EditText email;
    MaterialBetterSpinner empHistory;
    EditText pastDesignation;
    EditText pastDutyStation;
    EditText presentDesignation;
    EditText presentDutyStation;
    EditText password;
    EditText confirmPass;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_register);
        getSupportActionBar().setSubtitle("Add Employee");

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        fullName = findViewById(R.id.fullName);
        empId = findViewById(R.id.empId);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        empHistory = findViewById(R.id.empHistory);
        ArrayAdapter<String> historyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, HISTORY);
        empHistory.setAdapter(historyAdapter);

        pastDesignation = findViewById(R.id.pastDesignation);
        pastDutyStation = findViewById(R.id.pastDutyStation);
        presentDesignation = findViewById(R.id.presentDesignation);
        presentDutyStation = findViewById(R.id.presentDutyStation);
        password = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPass);


        TextView submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().length() > 0 &&
                        mobile.getText().toString().length() > 0 &&
                        password.getText().toString().length() > 0 &&
                        confirmPass.getText().toString().length() > 0) {

                    if (!password.getText().toString().equalsIgnoreCase(confirmPass.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Password & Confirm password not matched", Toast.LENGTH_SHORT).show();
                    } else if (mobile.getText().toString().length() != 10 || mobile.getText().toString().matches(".*[a-zA-Z]+.*")) {
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
    }

    private void registerUser() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Processing ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_CREATE_EMP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response.substring(response.indexOf("{"), response.length()));
                    boolean success = jObj.getBoolean("success");
                    String msg = jObj.getString("message");
                    if (success) {
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
                localHashMap.put("fullName", fullName.getText().toString());
                localHashMap.put("empId", empId.getText().toString());
                localHashMap.put("mobile", mobile.getText().toString());
                localHashMap.put("email", email.getText().toString());
                localHashMap.put("empHistory", empHistory.getText().toString());
                localHashMap.put("pastDesignation", pastDesignation.getText().toString());
                localHashMap.put("pastDutyStation", pastDutyStation.getText().toString());
                localHashMap.put("presentDesignation", presentDesignation.getText().toString());
                localHashMap.put("presentDutyStation", presentDutyStation.getText().toString());
                localHashMap.put("password", password.getText().toString());

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


}
