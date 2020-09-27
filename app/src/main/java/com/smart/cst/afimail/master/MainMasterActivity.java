package com.smart.cst.afimail.master;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.master.emp.MasterEmployeeFragment;
import com.smart.cst.afimail.master.farmer.MasterFarmerFragment;
import com.smart.cst.afimail.master.fgd.MasterFGDFragment;
import com.smart.cst.afimail.master.province.MasterTableFragment;

import java.util.ArrayList;


public class MainMasterActivity extends AppCompatActivity implements MasterClick {

    private ArrayList<MasterItem> reportItemArrayList = new ArrayList<>();
    private MasterItemAdapter mRecyclerAdapterMaster;
    private RecyclerView mastersList;
    private ProgressDialog progressDialog;

    public static String CURRENT_TAG = "";

    public static final String mypreference = "mypref";
    public static final String authkey = "authkey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        getSupportActionBar().setSubtitle("Masters");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        reportItemArrayList = loadItems(this);
        mastersList = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapterMaster = new MasterItemAdapter(this, reportItemArrayList, this);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this
                , LinearLayoutManager.HORIZONTAL, false);
        mastersList.setLayoutManager(thirdManager);
        mastersList.setAdapter(mRecyclerAdapterMaster);


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(AppConfig.reportTitlekey, reportItemArrayList.get(0).title);
        editor.putString(AppConfig.reportUrlkey, reportItemArrayList.get(0).url);
        editor.commit();

        startFragment(new MasterEmployeeFragment());
    }


    @Override
    public void itemClick(int position) {

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(AppConfig.reportTitlekey, reportItemArrayList.get(position).title);
        editor.putString(AppConfig.reportUrlkey, reportItemArrayList.get(position).url);
        editor.commit();

        if (position == 7) {
            startFragment(new MasterTableFragment());
        } else if (position == 1) {
            startFragment(new MasterEmployeeFragment());
        }else if(position == 0){
            startFragment(new MasterFarmerFragment());
        }else if(position == 3){
            startFragment(new MasterFGDFragment());
        }
    }

    private void startFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public ArrayList<MasterItem> loadItems(Context context) {
        ArrayList<MasterItem> reportItems = new ArrayList<>();

        reportItems.add(new MasterItem("Farmer", R.drawable.farmer, ""));
        reportItems.add(new MasterItem("Staff", R.drawable.staff, ""));
        reportItems.add(new MasterItem("PRA", R.drawable.fgd, ""));
        reportItems.add(new MasterItem("FGD", R.drawable.fgd, ""));
        reportItems.add(new MasterItem("Planning", R.drawable.plan, ""));
        reportItems.add(new MasterItem("Village", R.drawable.province, ""));
        reportItems.add(new MasterItem("District", R.drawable.province, ""));
        reportItems.add(new MasterItem("Province", R.drawable.province, ""));

        return reportItems;
    }


}




