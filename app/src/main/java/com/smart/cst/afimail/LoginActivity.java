package com.smart.cst.afimail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.smart.cst.afimail.pra.MainActivityPra;
import com.smart.cst.afimail.staff.AfiMember;
import com.smart.cst.afimail.staff.DbAfiMember;
import com.smart.cst.afimail.staff.StaffRegister;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.smart.cst.afimail.app.AppConfig.staffIdKey;
import static com.smart.cst.afimail.app.AppConfig.studentIdKey;

public class LoginActivity extends AppCompatActivity {

    public static final String buSurveyerId = "buSurveyerIdKey";
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    private ProgressDialog pDialog;
    DbAfiMember dbAfiMember;
    EditText username;
    EditText password;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<String> provinceList = new ArrayList<String>() {
        {
            add("Badghis");
            add("Badakhshan");
            add("Baghlan");
            add("Balkh");
            add("Bamyan");
            add("Daykundi");
            add("Farah");
            add("Faryab");
            add("Ghor");
            add("Ghazni");
            add("Hilmand");
            add("Hirat");
            add("Jawzjan");
            add("Kabul");
            add("Kandahar");
            add("Kunduz");
            add("Kunar");
            add("Kapisa");
            add("Khost");
            add("Laghman");
            add("Logar");
            add("Nangarhar");
            add("Nimroz");
            add("Nuristan");
            add("Paktika");
            add("Panjsher");
            add("Paktya");
            add("Parwan");
            add("Samangan");
            add("Sari Pul");
            add("Takhar");
            add("Uruzgan");
            add("Maydan Wardak");
            add("Zabul");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        try {
//            jsonObject = new JSONObject(loadJSONFromAsset());
//            jsonArray = jsonObject.getJSONArray("features");
//        } catch (Exception e) {
//
//        }
        dbAfiMember = new DbAfiMember(this);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        final TextView login = (TextView) findViewById(R.id.login);
        final TextView forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        TextView addStaff = (TextView) findViewById(R.id.addfarmer);

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent io = new Intent(LoginActivity.this, StaffRegister.class);
                startActivity(io);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent io = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(io);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() > 0 && password.getText().toString().length() > 0) {
                   loginStaff();
//                    try {
//                        for(int k=0;k<provinceList.size();k++) {
//                            JSONArray array = new JSONArray();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                String provinceName=provinceList.get(k);
//                                JSONObject object = jsonArray.getJSONObject(i);
//                                JSONObject object1 = object.getJSONObject("properties");
//                                if (object1.getString("Prov_Name").toLowerCase()
//                                        .equals(provinceList.get(k).toLowerCase())) {
//                                    array.put(object);
//                                }
//                            }
//                            Log.e("xxxxxxxxxxx", jsonObject.toString());
//                        }
//                    } catch (Exception e) {
//                        Log.e("xxxxxxxxxxx", e.toString());
//                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter username and Password/Mobile", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void loginStaff() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt("success");
                    String msg = jObj.getString("message");
                    if (success == 1) {
                        dbAfiMember.deleteAll();
                        AfiMember afiMember = new AfiMember();
                        String id = jObj.getString("id");
                        afiMember.setState(jObj.getString("state"));
                        afiMember.setDistrict(jObj.getString("district"));
                        afiMember.setProvince(jObj.getString("province"));
                        afiMember.setExtension(jObj.getString("extension"));
                        afiMember.setDesignation(jObj.getString("designation"));
                        afiMember.setName(jObj.getString("name"));
                        afiMember.setContact(jObj.getString("contact"));
                        afiMember.setEmail(jObj.getString("email"));
                        afiMember.setPassword(jObj.getString("password"));
                        afiMember.setAuthtoken(jObj.getString("authtoken"));
                        dbAfiMember.addData(id, new Gson().toJson(afiMember));
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.remove(staffIdKey);
                        editor.remove(studentIdKey);
                        editor.putString(staffIdKey, id);
                        editor.putString(buSurveyerId, username.getText().toString() + "_" + password.getText().toString());
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this, MainActivityPra.class));
                        finish();

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
                localHashMap.put("username", username.getText().toString());
                localHashMap.put("password", password.getText().toString());
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


    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.main_lan, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }


    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("districts.json");
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
