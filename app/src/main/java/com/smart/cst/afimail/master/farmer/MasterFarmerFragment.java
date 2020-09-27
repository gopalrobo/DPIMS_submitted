package com.smart.cst.afimail.master.farmer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.smart.cst.afimail.master.MasterClick;
import com.smart.cst.afimail.master.emp.CreateEmployee;
import com.smart.cst.afimail.master.emp.Employee;
import com.smart.cst.afimail.master.emp.MasterEmployeeAdapter;
import com.smart.cst.afimail.staff.Farmer;
import com.smart.cst.afimail.staff.StaffRegister;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MasterFarmerFragment extends Fragment implements MasterClick {


    private ArrayList<Farmer> reportTables = new ArrayList<>();


    private RecyclerView mastersList;
    private MasterFarmerAdapter mRecyclerAdapterMaster;

    private ProgressDialog pDialog;
    public static final String mypreference = "mypref";
    public static final String authkey = "authkey";
    SharedPreferences sharedpreferences;


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_master, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        mastersList = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerAdapterMaster = new MasterFarmerAdapter(getActivity(), reportTables, this);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mastersList.setLayoutManager(thirdManager);
        mastersList.setAdapter(mRecyclerAdapterMaster);

        FloatingActionButton addActivity = view.findViewById(R.id.addActivity);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StaffRegister.class);
                startActivity(intent);
            }
        });

        return view;
    }

    protected void getReports() {
        pDialog.setMessage("Fetching...");
        showDialog();
        // Post params to be sent to the server
        JSONArray jsonObject = new JSONArray();
        StringRequest req = new StringRequest(Request.Method.GET,
                AppConfig.URL_GET_ALL_FARMER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        hideDialog();
                        Log.e("xxxxxxxxxxxxx", responseString.toString());
                        try {
                            JSONObject response = new JSONObject(responseString);
                            boolean success = response.getBoolean("success");
                            if (success) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                reportTables = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    Farmer reportTable = new Farmer(
                                            object.getString("id"),
                                            object.getString("name"),
                                            object.getString("contact"),
                                            object.getString("geotags"),
                                            object.getString("province"),
                                            object.getString("district"),
                                            object.getString("village"),
                                            object.getString("frontviewUrl"),
                                            object.getString("backviewUrl"),
                                            object.getString("password"),
                                            object.getString("image"),
                                            ""
                                            );

                                    reportTables.add(reportTable);

                                }
                            }
                            mRecyclerAdapterMaster.notifyData(reportTables);
                        } catch (Exception e) {
                            Log.e("error", e.toString());
                        }
                        hideDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),
                        "Network Error", Toast.LENGTH_LONG).show();
                hideDialog();
            }

        }) {
            public Map<String, String> getParams() {
                HashMap localHashMap = new HashMap();
                return localHashMap;

            }
        };
        // add the request object to the queue to be executed
        AppController.getInstance().addToRequestQueue(req);
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
        getReports();
    }

    @Override
    public void itemClick(int position) {

    }
}
