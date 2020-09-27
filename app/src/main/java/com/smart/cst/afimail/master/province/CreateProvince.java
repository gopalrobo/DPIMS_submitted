package com.smart.cst.afimail.master.province;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.smart.cst.afimail.staff.StaffRegister;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateProvince extends AppCompatActivity {

    EditText ProvinceCode;
    EditText RegionCode;
    EditText ProvinceName;
    EditText ProvinceNameDari;
    EditText ProvinceNamePashtu;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_register);
        getSupportActionBar().setSubtitle("Add Province");

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        ProvinceCode = findViewById(R.id.ProvinceCode);
        RegionCode = findViewById(R.id.RegionCode);
        ProvinceName = findViewById(R.id.ProvinceName);
        ProvinceNameDari = findViewById(R.id.ProvinceNameDari);
        ProvinceNamePashtu = findViewById(R.id.ProvinceNamePashtu);

        TextView submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Processing ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_CREATE_PRO, new Response.Listener<String>() {
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
                localHashMap.put("ProvinceCode", ProvinceCode.getText().toString());
                localHashMap.put("RegionCode", RegionCode.getText().toString());
                localHashMap.put("ProvinceName", ProvinceName.getText().toString());
                localHashMap.put("ProvinceNameDari", ProvinceNameDari.getText().toString());
                localHashMap.put("ProvinceNamePashtu", ProvinceNamePashtu.getText().toString());

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
