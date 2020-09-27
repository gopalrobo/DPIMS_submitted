package com.smart.cst.afimail.master.fgd;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class CreateFGD extends AppCompatActivity {

    private static final String[] SECTION = new String[]{
            "Section 1", "Section 2", "Section 3", "Section 4", "Section 5", "Section 6", "Section 7", "Section 8", "Section 9", "Section 10"
    };

    private static final String[] QUESTIONTYPE = new String[]{
            "Single Select", "Multiple Select", "Range", "Image", "Rating"
    };

    MaterialBetterSpinner section;
    MaterialBetterSpinner questionType;
    EditText question;
    EditText options;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgd_register);
        getSupportActionBar().setSubtitle("Add FGD Questions");

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        section = findViewById(R.id.section);
        questionType = findViewById(R.id.questionType);
        question = findViewById(R.id.question);
        options = findViewById(R.id.options);

        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SECTION);
        section.setAdapter(sectionAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, QUESTIONTYPE);
        questionType.setAdapter(typeAdapter);

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
                AppConfig.URL_GET_CREATE_FGD, new Response.Listener<String>() {
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
                localHashMap.put("section", section.getText().toString());
                localHashMap.put("question", question.getText().toString());
                localHashMap.put("questionType", questionType.getText().toString());
                localHashMap.put("options", options.getText().toString());

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
