package com.smart.cst.afimail.pra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.smart.cst.afimail.R;
import com.smart.cst.afimail.maps.ClusterDistrictActivity;
import com.smart.cst.afimail.master.MainMasterActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivityPra extends AppCompatActivity implements VideoClick {
    private List<Pra> praList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PraCenterAdapter mAdapter;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String tittle = "tittleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pra);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new PraCenterAdapter(praList, this, this);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle("Dashboard");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {

//        Pra pra = new Pra("MAIL Website", "", "true", "");
//        praList.add(pra);
//
//        pra = new Pra("MAIL Agriculture Docs", "false", "false", "");
//        praList.add(pra);
//
//        pra = new Pra("MAIL Team Members", "true", "true", "");
//        praList.add(pra);
//
//        pra = new Pra("MAIL District Info", "true", "true", "");
//        praList.add(pra);

        Pra pra = new Pra("Smart Geo PRA Tools", "-07fOqnyXz8", "", "https://sites.google.com/site/faopratoolkit/historical-tiemline");
        praList.add(pra);

        pra = new Pra("GEO FGD", "", "true", "");
        praList.add(pra);

        pra = new Pra("Participatory Planning", "true", "true", "");
        praList.add(pra);

        pra = new Pra("Implementation Progress", "true", "true", "");
        praList.add(pra);

        pra = new Pra("Monitoring", "false", "false", "");
        praList.add(pra);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void videoClick(int position) {
//        if (praList.get(position).getYear().equals("true")) {
//            Intent io = new Intent(MainActivityPra.this, TeamMember.class);
//            startActivity(io);
//        } else {
//            Intent io = new Intent(MainActivityPra.this, MainActivityVideo.class);
//            io.putExtra("video", praList.get(position).getGenre());
//            startActivity(io);
//        }
    }

    @Override
    public void webClick(int position) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(praList.get(position).getUrl()));
            startActivity(browserIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid Link", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void mapClick(int position) {
//        Intent io = new Intent(MainActivityPra.this, MapsFragActivity.class);
//        io.putExtra("tittle", praList.get(position).getTitle());
//        startActivity(io);
    }

    @Override
    public void imageClick(int position) {
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(tittle, praList.get(position).getTitle());
        editor.commit();
//        if (praList.get(position).getTitle().equals("Household survey")) {
//            Intent io = new Intent(MainActivityPra.this, CustClusterHouseHoldActivity.class);
//            io.putExtra("tittle", praList.get(position).getTitle());
//            startActivity(io);
//        } else {
//            Intent io = new Intent(MainActivityPra.this, CustomMarkerClusteringDemoActivity.class);
//            io.putExtra("tittle", praList.get(position).getTitle());
//            startActivity(io);
//        }
    }

    @Override
    public void reportClick(int position) {
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(tittle, praList.get(position).getTitle());
        editor.commit();
//        if (praList.get(position).getTitle().equals("Household survey")) {
//            Intent io = new Intent(MainActivityPra.this, ColumnChartActivity.class);
//            io.putExtra("tittle", praList.get(position).getTitle());
//            startActivity(io);
//        } else {
//            Intent io = new Intent(MainActivityPra.this, FinalReport.class);
//            startActivity(io);
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.local_english) {
            //setNewLocale(this, LocaleManager.ENGLISH);
            return true;
        }
        if (id == R.id.local_dari) {
            //setNewLocale(this, LocaleManager.HINDI);
            return true;
        }
        if (id == R.id.action_masters) {
            Intent intent = new Intent(MainActivityPra.this, MainMasterActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_plot) {
            Intent intent = new Intent(MainActivityPra.this, ClusterDistrictActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
