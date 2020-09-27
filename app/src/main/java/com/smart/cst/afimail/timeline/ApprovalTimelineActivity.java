package com.smart.cst.afimail.timeline;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smart.cst.afimail.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.smart.cst.afimail.app.AppConfig.mypreference;

/**
 * Activity with RecyclerView that displays TimelineView
 */

public class ApprovalTimelineActivity extends AppCompatActivity
        implements TimelineListener {

    private static DecimalFormat df = new DecimalFormat("0.00");
    SharedPreferences sharedpreferences;
    ProgressDialog pDialog;

    ArrayList<Timeline> listItems = new ArrayList<>();
    TimelineAdapter adapter;
    RecyclerView recycler;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_timeline);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        getSupportActionBar().setSubtitle("Timeline");
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new TimelineAdapter(LinearLayoutManager.VERTICAL, listItems, ApprovalTimelineActivity.this,
                this);
        recycler.setAdapter(adapter);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        fetchStudentData();


    }


    private void hideDialog() {

        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }

    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }

    private void fetchStudentData() {
        listItems = new ArrayList<>();
        Timeline timeline = new Timeline("");
        listItems.add(timeline);

        timeline = new Timeline("GEO PRA");
        listItems.add(timeline);

        timeline = new Timeline("GEO FGD");
        listItems.add(timeline);

        timeline = new Timeline("Extension Planning Activity");
        listItems.add(timeline);

        timeline = new Timeline("Extension Submission");
        listItems.add(timeline);

        timeline = new Timeline("Extension Review");
        listItems.add(timeline);

        timeline = new Timeline("Extension Approve or Resubmission");
        listItems.add(timeline);

        timeline = new Timeline("District Review");
        listItems.add(timeline);

        timeline = new Timeline("Submission to Province");
        listItems.add(timeline);

        timeline = new Timeline("Province Review");
        listItems.add(timeline);

        timeline = new Timeline("Approve or Resubmission or Reject");
        listItems.add(timeline);

        timeline = new Timeline("Submission to MAIL");
        listItems.add(timeline);

        timeline = new Timeline("MAIL Review");
        listItems.add(timeline);

        timeline = new Timeline("Approve or Resubmission or Reject");
        listItems.add(timeline);

        timeline = new Timeline("Submission to Finance");
        listItems.add(timeline);

        timeline = new Timeline("Finance Review");
        listItems.add(timeline);

        timeline = new Timeline("Finance Approval or Pending or Reject");
        listItems.add(timeline);

        timeline = new Timeline("MAIL Announcement");
        listItems.add(timeline);

        timeline = new Timeline("Project Preparation");
        listItems.add(timeline);

        timeline = new Timeline("Project Submission");
        listItems.add(timeline);

        timeline = new Timeline("Project Approval or Resubmission");
        listItems.add(timeline);

        timeline = new Timeline("");
        listItems.add(timeline);

        timeline = new Timeline("Project Implementation start");
        listItems.add(timeline);

        timeline = new Timeline("Implementation Progress Tracking 1");
        listItems.add(timeline);

        timeline = new Timeline("Monitoring");
        listItems.add(timeline);

        timeline = new Timeline("Implementation Progress Tracking 2");
        listItems.add(timeline);

        timeline = new Timeline("Monitoring");
        listItems.add(timeline);

        timeline = new Timeline("Implementation Progress Tracking 3");
        listItems.add(timeline);

        timeline = new Timeline("Monitoring");
        listItems.add(timeline);

        timeline = new Timeline("Project Completion");
        listItems.add(timeline);

        timeline = new Timeline("Evaluation");
        listItems.add(timeline);

        timeline = new Timeline("Impact Evaluation");
        listItems.add(timeline);

        adapter.notifiData(listItems);

    }


}
