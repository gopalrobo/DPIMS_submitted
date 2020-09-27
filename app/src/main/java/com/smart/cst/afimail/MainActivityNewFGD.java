package com.smart.cst.afimail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.smart.cst.afimail.app.AndroidMultiPartEntity;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.app.AppController;
import com.smart.cst.afimail.app.GPSTracker;
import com.smart.cst.afimail.app.GlideApp;
import com.smart.cst.afimail.app.Imageutils;
import com.smart.cst.afimail.attachment.AttachmentBaseAdapter;
import com.smart.cst.afimail.attachment.Base;
import com.smart.cst.afimail.attachment.BaseClick;
import com.smart.cst.afimail.media.ActivityAudioRecord;
import com.smart.cst.afimail.media.ActivityMediaOnline;
import com.smart.cst.afimail.media.MainActivityAudio;
import com.smart.cst.afimail.member.Member;
import com.smart.cst.afimail.member.MemberAdapter;
import com.smart.cst.afimail.member.MemberClick;
import com.smart.cst.afimail.village.Village;
import com.smart.cst.afimail.village.VillageAdapter;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.dimorinny.floatingtextbutton.FloatingTextButton;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivityNewFGD extends AppCompatActivity implements BaseClick,
        Imageutils.ImageAttachmentListener, MemberClick {

    private int lastMemberPosition = -1;
    private int lastVillagePosition = -1;
    private static final int FINE_LOCATION_CODE = 199;

    public TextView gpTxt;
    public TextView districtTxt;
    public TextView mandalTxt;

    public EditText nameOfSurveyor;
    public EditText dateOfFGD;
    public EditText geoTag;
    public EditText address;
    public EditText timeStarted;
    public EditText village;
    public Spinner villageSelect;
    public Spinner province;
    public Spinner district;
    public CircleImageView memberImage;
    public EditText memberName;
    public EditText sex;
    public EditText mobile;
    public EditText idType;
    public EditText farmSize;
    public EditText webLink1;
    public EditText webLink2;
    public EditText webLink3;
    public EditText nameVillagesHamlets;
    public EditText numberOfHouseholds;
    public EditText averageLandHoldingSize;
    public EditText largeFarmers;
    public EditText mediumFarmers;
    public EditText marginalFarmers;
    public EditText traditionalLandTitleHolders;
    public EditText ownLandCultivators;
    public EditText leasedLandCultivators;
    public EditText usingDigitalLandRecords;
    public EditText irrigatedLand;
    public EditText rainFedLand;
    public EditText colourOfSoil;
    public EditText typeOfSoil;
    public EditText depthOfSoil;
    public EditText qualityOfSoil;
    public EditText knowledgeAboutSoilTesting;
    public EditText soilTested;
    public EditText soilHealthCard;
    public EditText Frequency;
    public EditText rainfall;
    public EditText monsoons;
    public EditText irrigationWaterAvailable;
    public EditText riverLiftIrrigation;
    public EditText canalIrrigation;
    public EditText tankIrrigation;
    public EditText wellIrrigation;
    public EditText shallowBorewell;
    public EditText mediumBoreWell;
    public EditText deepBoreBoreWell;
    public EditText dieselKerosenePumps;
    public EditText electricPumps;
    public EditText solarPumps;
    public EditText floodIrrigation;
    public EditText dripIrrigation;
    public EditText sprinklerIrrigation;
    public EditText rainGunIrrigation;
    public EditText majorCropsRabi;
    public EditText majorCropsSummer;
    public EditText majorCropsKharif;
    public EditText rankValuableCrops;
    public EditText rankLargeVolumeCrops;
    public EditText tractor;
    public EditText ifHireCostTractor;
    public EditText tiller;
    public EditText ifHireCostTiller;
    public EditText irrigation;
    public EditText ifHireCostIrrigation;
    public EditText harvester;
    public EditText ifHireCostHarvester;
    public EditText trasher;
    public EditText ifHireCostTrasher;
    public EditText dryer;
    public EditText ifHireCostDryer;
    public EditText coldStorageMonthlyRental;
    public EditText mandalLevelLicensedShop;
    public EditText districtLevelLicensedShop;
    public EditText localLicensedShop;
    public EditText localUnLicensedShop;
    public EditText researchUniversity;
    public EditText othersSource;
    public EditText isLaborEasilyAccessible;
    public EditText isLaborEasilyAccessibleSpecify;
    public EditText wageMalePerDay;
    public EditText wageFeMalePerDay;
    public EditText informationAgriBestPractices;
    public EditText whoProvidesGuidance;
    public EditText trainingsParticipated;
    public EditText useWeatherInformation;
    public EditText ifYesWhatSource;
    public EditText investmentForCropping;
    public EditText ownKisanCreditCard;
    public EditText localMoneyLender;
    public EditText sHGGroups;
    public EditText inputShops;
    public EditText traderAggregator;
    public EditText mFI;
    public EditText kCC;
    public EditText localLandCooperative;
    public EditText normalBank;
    public EditText othersSourceFinance;
    public EditText loanRepayment;
    public EditText dailyMarketNos;
    public EditText dailyMarketKms;
    public EditText weeklyHaatNos;
    public EditText weeklyHaatKms;
    public EditText blockMandiNos;
    public EditText blockMandiKms;
    public EditText districtAPMCNos;
    public EditText districtAPMCKms;
    public EditText specialMarketsNos;
    public EditText specialMarketsKms;
    public EditText sourceInformationCurrent;
    public EditText timeOfSale;
    public EditText storageSystems;
    public EditText storageItems;
    public EditText sellCropsFruitsLocalTrader;
    public EditText sellCropsFruitsMandi;
    public EditText sellCropsFruitsOthers;
    public EditText sellCropsFruitsPrice;
    public EditText sellCropsFruitsPayment;
    public EditText sellCropsCerealsLocalTrader;
    public EditText sellCropsCerealsMandi;
    public EditText sellCropsCerealsPACS;
    public EditText sellCropsCerealsFPCPG;
    public EditText sellCropsCerealscompaniesITC;
    public EditText sellCropsCerealsOthers;
    public EditText sellCropsCerealsPrice;
    public EditText sellCropsCerealsPayment;
    public EditText sellCropsVegetablesLocalTrader;
    public EditText sellCropsVegetablesTrader;
    public EditText sellCropsVegetablesProcessingCompanies;
    public EditText sellCropsVegetablesOthers;
    public EditText sellCropsVegetablesPrice;
    public EditText sellCropsVegetablesPayment;

    public EditText incomeFromCrops;
    public EditText incomeFromLivestock;
    public EditText incomeFromAgriLabour;
    public EditText incomeFromMigrationLabour;
    public EditText incomeFromService;
    public EditText incomeFromShopTrade;
    public EditText threeMealsOfFood;
    public EditText landPreparation;
    public EditText seeds;
    public EditText fertilizer;
    public EditText pesticides;
    public EditText irrigationExpan;
    public EditText weeding;
    public EditText harvesting;
    public EditText cleaningSortingPacking;
    public EditText transportation;
    public EditText storage;
    public EditText milkingCow;
    public EditText sheepGoats;
    public EditText backyardPoultry;
    public EditText aquaculturePond;
    public EditText ifYesCountAquaculturePond;
    public EditText tractors;
    public EditText ifYesCountTractors;
    public EditText tillers;
    public EditText ifYesCountTillers;
    public EditText harvesters;
    public EditText ifYesCountHarvesters;
    ProgressDialog pDialog;

    TextView submit;

    FGDNewBean mainFGD = null;
    private String TAG = getClass().getSimpleName();

    public static final String mypreference = "mypref";
    public static final String buSurveyerId = "buSurveyerIdKey";
    SharedPreferences sharedpreferences;

    GPSTracker gps;

    String audioUrl1 = null;
    String audioUrl2 = null;
    String audioUrl3 = null;
    String audioUrl4 = null;
    String audioUrl5 = null;
    String audioUrl6 = null;
    String audioUrl7 = null;
    String audioUrl8 = null;
    String audioUrl9 = null;
    String audioUrl10 = null;

    FloatingTextButton record1;
    FloatingTextButton play1;
    FloatingTextButton record2;
    FloatingTextButton play2;
    FloatingTextButton record3;
    FloatingTextButton play3;
    FloatingTextButton record4;
    FloatingTextButton play4;
    FloatingTextButton record5;
    FloatingTextButton play5;
    FloatingTextButton record6;
    FloatingTextButton play6;
    FloatingTextButton record7;
    FloatingTextButton play7;
    FloatingTextButton record8;
    FloatingTextButton play8;
    FloatingTextButton record9;
    FloatingTextButton play9;
    FloatingTextButton record10;
    FloatingTextButton play10;

    AttachmentBaseAdapter attachmentBase1Adapter;
    ArrayList<Base> bases1 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase2Adapter;
    ArrayList<Base> bases2 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase3Adapter;
    ArrayList<Base> bases3 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase4Adapter;
    ArrayList<Base> bases4 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase5Adapter;
    ArrayList<Base> bases5 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase6Adapter;
    ArrayList<Base> bases6 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase7Adapter;
    ArrayList<Base> bases7 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase8Adapter;
    ArrayList<Base> bases8 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase9Adapter;
    ArrayList<Base> bases9 = new ArrayList<>();
    AttachmentBaseAdapter attachmentBase10Adapter;
    ArrayList<Base> bases10 = new ArrayList<>();


    ArrayList<Member> memberArrayList = new ArrayList<>();
    MemberAdapter memberAdapter;
    RecyclerView memberList;


    ArrayList<Village> villageArrayList = new ArrayList<>();
    VillageAdapter villageAdapter;
    RecyclerView villageList;

    Imageutils imageutils;


    DbHelperSurvey dbHelperSurvey;


    String[] DISTRICTS = new String[10];
    String[] MANDALS = new String[10];
    String[] GPS = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_newfgd);


        dbHelperSurvey = new DbHelperSurvey(this);
getSupportActionBar().setSubtitle("GEO FGD");
        imageutils = new Imageutils(this);
        TextView expand_button = (TextView) findViewById(R.id.expand_button);
        final ImageView rotation = (ImageView) findViewById(R.id.rotation);
        final ExpandableLayout expandLayout = (ExpandableLayout) findViewById(R.id.expandable_layout_0);
        final LinearLayout expandText = (LinearLayout) findViewById(R.id.expandText);
        expand_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandLayout.isExpanded()) {
                    expandLayout.collapse();
                    rotation.setRotation(180);
                } else {
                    expandLayout.expand();
                    rotation.setRotation(0);
                }
            }
        });


        pDialog = new ProgressDialog(MainActivityNewFGD.this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        record1 = (FloatingTextButton) findViewById(R.id.record);
        play1 = (FloatingTextButton) findViewById(R.id.play);
        record2 = (FloatingTextButton) findViewById(R.id.record2);
        play2 = (FloatingTextButton) findViewById(R.id.play2);
        record3 = (FloatingTextButton) findViewById(R.id.record3);
        play3 = (FloatingTextButton) findViewById(R.id.play3);
        record4 = (FloatingTextButton) findViewById(R.id.record4);
        play4 = (FloatingTextButton) findViewById(R.id.play4);
        record5 = (FloatingTextButton) findViewById(R.id.record5);
        play5 = (FloatingTextButton) findViewById(R.id.play5);
        record6 = (FloatingTextButton) findViewById(R.id.record6);
        play6 = (FloatingTextButton) findViewById(R.id.play6);
        record7 = (FloatingTextButton) findViewById(R.id.record7);
        play7 = (FloatingTextButton) findViewById(R.id.play7);
        record8 = (FloatingTextButton) findViewById(R.id.record8);
        play8 = (FloatingTextButton) findViewById(R.id.play8);
        record9 = (FloatingTextButton) findViewById(R.id.record9);
        play9 = (FloatingTextButton) findViewById(R.id.play9);
        record10 = (FloatingTextButton) findViewById(R.id.record10);
        play10 = (FloatingTextButton) findViewById(R.id.play10);

        play1.setVisibility(View.INVISIBLE);
        play2.setVisibility(View.INVISIBLE);
        play3.setVisibility(View.INVISIBLE);
        play4.setVisibility(View.INVISIBLE);
        play5.setVisibility(View.INVISIBLE);
        play6.setVisibility(View.INVISIBLE);
        play7.setVisibility(View.INVISIBLE);
        play8.setVisibility(View.INVISIBLE);
        play9.setVisibility(View.INVISIBLE);
        play10.setVisibility(View.INVISIBLE);

        record1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl1 != null) {
                    intent.putExtra("url", audioUrl1);
                }
                intent.putExtra("name", "Panchayat Details");
                startActivityForResult(intent, 12);
            }
        });
        record2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl2 != null) {
                    intent.putExtra("url", audioUrl2);
                }
                intent.putExtra("name", "Land Details");
                startActivityForResult(intent, 13);
            }
        });
        record3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl3 != null) {
                    intent.putExtra("url", audioUrl3);
                }
                intent.putExtra("name", "Soil Details");
                startActivityForResult(intent, 14);
            }
        });
        record4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl4 != null) {
                    intent.putExtra("url", audioUrl4);
                }
                intent.putExtra("name", "Water Details");
                startActivityForResult(intent, 15);
            }
        });
        record5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl5 != null) {
                    intent.putExtra("url", audioUrl5);
                }
                intent.putExtra("name", "Cropping Pattern");
                startActivityForResult(intent, 5);
            }
        });
        record6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl6 != null) {
                    intent.putExtra("url", audioUrl6);
                }
                intent.putExtra("name", "Agro services");
                startActivityForResult(intent, 6);
            }
        });
        record7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl7 != null) {
                    intent.putExtra("url", audioUrl7);
                }
                intent.putExtra("name", "Knowledge");
                startActivityForResult(intent, 7);
            }
        });
        record8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl8 != null) {
                    intent.putExtra("url", audioUrl8);
                }
                intent.putExtra("name", "Access to Finance");
                startActivityForResult(intent, 8);
            }
        });
        record9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl9 != null) {
                    intent.putExtra("url", audioUrl9);
                }
                intent.putExtra("name", "Market");
                startActivityForResult(intent, 9);
            }
        });
        record10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNewFGD.this, ActivityAudioRecord.class);
                if (audioUrl10 != null) {
                    intent.putExtra("url", audioUrl10);
                }
                intent.putExtra("name", "Livelihoods cash income");
                startActivityForResult(intent, 10);
            }
        });

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl1, "Panchayat Details");
            }
        });
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl2, "Land Details");
            }
        });
        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl3, "Soil Details");
            }
        });
        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl4, "Water Details");
            }
        });
        play5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl5, "Cropping Pattern");
            }
        });
        play6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl6, "Agro services");
            }
        });
        play7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl7, "Knowledge");
            }
        });
        play8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl8, "Access to Finance");
            }
        });
        play9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl9, "Market");
            }
        });
        play10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlay(audioUrl10, "Livelihoods cash income");
            }
        });

        districtTxt = (TextView) findViewById(R.id.districtTxt);
        mandalTxt = (TextView) findViewById(R.id.mandalTxt);
        gpTxt = (TextView) findViewById(R.id.gpTxt);
        nameOfSurveyor = (EditText) findViewById(R.id.nameOfSurveyor);
        dateOfFGD = (EditText) findViewById(R.id.dateOfFGD);
        geoTag = (EditText) findViewById(R.id.geoTag);
        address = (EditText) findViewById(R.id.address);
        timeStarted = (EditText) findViewById(R.id.timeStarted);
        village = (EditText) findViewById(R.id.village);
        villageSelect = (Spinner) findViewById(R.id.villageSelect);
        province = (Spinner) findViewById(R.id.province);
        district = (Spinner) findViewById(R.id.district);
        memberImage = (CircleImageView) findViewById(R.id.imageview);
        memberName = (EditText) findViewById(R.id.memberName);
        sex = (EditText) findViewById(R.id.sex);
        mobile = (EditText) findViewById(R.id.mobile);
        idType = (EditText) findViewById(R.id.idType);
        farmSize = (EditText) findViewById(R.id.farmSize);
        webLink1 = (EditText) findViewById(R.id.webLink1);
        webLink2 = (EditText) findViewById(R.id.webLink2);
        webLink3 = (EditText) findViewById(R.id.webLink3);
        nameVillagesHamlets = (EditText) findViewById(R.id.nameVillagesHamlets);
        numberOfHouseholds = (EditText) findViewById(R.id.numberOfHouseholds);
        averageLandHoldingSize = (EditText) findViewById(R.id.averageLandHoldingSize);
        largeFarmers = (EditText) findViewById(R.id.largeFarmers);
        mediumFarmers = (EditText) findViewById(R.id.mediumFarmers);
        marginalFarmers = (EditText) findViewById(R.id.marginalFarmers);
        traditionalLandTitleHolders = (EditText) findViewById(R.id.traditionalLandTitleHolders);
        ownLandCultivators = (EditText) findViewById(R.id.ownLandCultivators);
        leasedLandCultivators = (EditText) findViewById(R.id.leasedLandCultivators);
        usingDigitalLandRecords = (EditText) findViewById(R.id.usingDigitalLandRecords);
        irrigatedLand = (EditText) findViewById(R.id.irrigatedLand);
        rainFedLand = (EditText) findViewById(R.id.rainFedLand);
        colourOfSoil = (EditText) findViewById(R.id.colourOfSoil);
        typeOfSoil = (EditText) findViewById(R.id.typeOfSoil);
        depthOfSoil = (EditText) findViewById(R.id.depthOfSoil);
        qualityOfSoil = (EditText) findViewById(R.id.qualityOfSoil);
        knowledgeAboutSoilTesting = (EditText) findViewById(R.id.knowledgeAboutSoilTesting);
        soilTested = (EditText) findViewById(R.id.soilTested);
        soilHealthCard = (EditText) findViewById(R.id.soilHealthCard);
        Frequency = (EditText) findViewById(R.id.Frequency);
        rainfall = (EditText) findViewById(R.id.rainfall);
        monsoons = (EditText) findViewById(R.id.monsoons);
        irrigationWaterAvailable = (EditText) findViewById(R.id.irrigationWaterAvailable);
        riverLiftIrrigation = (EditText) findViewById(R.id.riverLiftIrrigation);
        canalIrrigation = (EditText) findViewById(R.id.canalIrrigation);
        tankIrrigation = (EditText) findViewById(R.id.tankIrrigation);
        wellIrrigation = (EditText) findViewById(R.id.wellIrrigation);
        shallowBorewell = (EditText) findViewById(R.id.shallowBorewell);
        mediumBoreWell = (EditText) findViewById(R.id.mediumBoreWell);
        deepBoreBoreWell = (EditText) findViewById(R.id.deepBoreBoreWell);
        dieselKerosenePumps = (EditText) findViewById(R.id.dieselKerosenePumps);
        electricPumps = (EditText) findViewById(R.id.electricPumps);
        solarPumps = (EditText) findViewById(R.id.solarPumps);
        floodIrrigation = (EditText) findViewById(R.id.floodIrrigation);
        dripIrrigation = (EditText) findViewById(R.id.dripIrrigation);
        sprinklerIrrigation = (EditText) findViewById(R.id.sprinklerIrrigation);
        rainGunIrrigation = (EditText) findViewById(R.id.rainGunIrrigation);
        majorCropsRabi = (EditText) findViewById(R.id.majorCropsRabi);
        majorCropsSummer = (EditText) findViewById(R.id.majorCropsSummer);
        majorCropsKharif = (EditText) findViewById(R.id.majorCropsKharif);
        rankValuableCrops = (EditText) findViewById(R.id.rankValuableCrops);
        rankLargeVolumeCrops = (EditText) findViewById(R.id.rankLargeVolumeCrops);
        tractor = (EditText) findViewById(R.id.tractor);
        ifHireCostTractor = (EditText) findViewById(R.id.ifHireCostTractor);
        tiller = (EditText) findViewById(R.id.tiller);
        ifHireCostTiller = (EditText) findViewById(R.id.ifHireCostTiller);
        irrigation = (EditText) findViewById(R.id.irrigation);
        ifHireCostIrrigation = (EditText) findViewById(R.id.ifHireCostIrrigation);
        harvester = (EditText) findViewById(R.id.harvester);
        ifHireCostHarvester = (EditText) findViewById(R.id.ifHireCostHarvester);
        trasher = (EditText) findViewById(R.id.trasher);
        ifHireCostTrasher = (EditText) findViewById(R.id.ifHireCostTrasher);
        dryer = (EditText) findViewById(R.id.dryer);
        ifHireCostDryer = (EditText) findViewById(R.id.ifHireCostDryer);
        coldStorageMonthlyRental = (EditText) findViewById(R.id.coldStorageMonthlyRental);
        mandalLevelLicensedShop = (EditText) findViewById(R.id.mandalLevelLicensedShop);
        districtLevelLicensedShop = (EditText) findViewById(R.id.districtLevelLicensedShop);
        localLicensedShop = (EditText) findViewById(R.id.localLicensedShop);
        localUnLicensedShop = (EditText) findViewById(R.id.localUnLicensedShop);
        researchUniversity = (EditText) findViewById(R.id.researchUniversity);
        othersSource = (EditText) findViewById(R.id.othersSource);
        isLaborEasilyAccessible = (EditText) findViewById(R.id.isLaborEasilyAccessible);
        isLaborEasilyAccessibleSpecify = (EditText) findViewById(R.id.isLaborEasilyAccessibleSpecify);
        wageMalePerDay = (EditText) findViewById(R.id.wageMalePerDay);
        wageFeMalePerDay = (EditText) findViewById(R.id.wageFeMalePerDay);
        informationAgriBestPractices = (EditText) findViewById(R.id.informationAgriBestPractices);
        whoProvidesGuidance = (EditText) findViewById(R.id.whoProvidesGuidance);
        trainingsParticipated = (EditText) findViewById(R.id.trainingsParticipated);
        useWeatherInformation = (EditText) findViewById(R.id.useWeatherInformation);
        ifYesWhatSource = (EditText) findViewById(R.id.ifYesWhatSource);
        investmentForCropping = (EditText) findViewById(R.id.investmentForCropping);
        ownKisanCreditCard = (EditText) findViewById(R.id.ownKisanCreditCard);
        localMoneyLender = (EditText) findViewById(R.id.localMoneyLender);
        sHGGroups = (EditText) findViewById(R.id.sHGGroups);
        inputShops = (EditText) findViewById(R.id.inputShops);
        traderAggregator = (EditText) findViewById(R.id.traderAggregator);
        mFI = (EditText) findViewById(R.id.mFI);
        kCC = (EditText) findViewById(R.id.kCC);
        localLandCooperative = (EditText) findViewById(R.id.localLandCooperative);
        normalBank = (EditText) findViewById(R.id.normalBank);
        othersSourceFinance = (EditText) findViewById(R.id.othersSourceFinance);
        loanRepayment = (EditText) findViewById(R.id.loanRepayment);
        dailyMarketNos = (EditText) findViewById(R.id.dailyMarketNos);
        dailyMarketKms = (EditText) findViewById(R.id.dailyMarketKms);
        weeklyHaatNos = (EditText) findViewById(R.id.weeklyHaatNos);
        weeklyHaatKms = (EditText) findViewById(R.id.weeklyHaatKms);
        blockMandiNos = (EditText) findViewById(R.id.blockMandiNos);
        blockMandiKms = (EditText) findViewById(R.id.blockMandiKms);
        districtAPMCNos = (EditText) findViewById(R.id.districtAPMCNos);
        districtAPMCKms = (EditText) findViewById(R.id.districtAPMCKms);
        specialMarketsNos = (EditText) findViewById(R.id.specialMarketsNos);
        specialMarketsKms = (EditText) findViewById(R.id.specialMarketsKms);
        sourceInformationCurrent = (EditText) findViewById(R.id.sourceInformationCurrent);
        timeOfSale = (EditText) findViewById(R.id.timeOfSale);
        storageSystems = (EditText) findViewById(R.id.storageSystems);
        storageItems = (EditText) findViewById(R.id.storageItems);
        sellCropsFruitsLocalTrader = (EditText) findViewById(R.id.sellCropsFruitsLocalTrader);
        sellCropsFruitsMandi = (EditText) findViewById(R.id.sellCropsFruitsMandi);
        sellCropsFruitsOthers = (EditText) findViewById(R.id.sellCropsFruitsOthers);
        sellCropsFruitsPrice = (EditText) findViewById(R.id.sellCropsFruitsPrice);
        sellCropsFruitsPayment = (EditText) findViewById(R.id.sellCropsFruitsPayment);
        sellCropsCerealsLocalTrader = (EditText) findViewById(R.id.sellCropsCerealsLocalTrader);
        sellCropsCerealsMandi = (EditText) findViewById(R.id.sellCropsCerealsMandi);
        sellCropsCerealsPACS = (EditText) findViewById(R.id.sellCropsCerealsPACS);
        sellCropsCerealsFPCPG = (EditText) findViewById(R.id.sellCropsCerealsFPCPG);
        sellCropsCerealscompaniesITC = (EditText) findViewById(R.id.sellCropsCerealscompaniesITC);
        sellCropsCerealsOthers = (EditText) findViewById(R.id.sellCropsCerealsOthers);
        sellCropsCerealsPrice = (EditText) findViewById(R.id.sellCropsCerealsPrice);
        sellCropsCerealsPayment = (EditText) findViewById(R.id.sellCropsCerealsPayment);
        sellCropsVegetablesLocalTrader = (EditText) findViewById(R.id.sellCropsVegetablesLocalTrader);
        sellCropsVegetablesTrader = (EditText) findViewById(R.id.sellCropsVegetablesTrader);
        sellCropsVegetablesProcessingCompanies = (EditText) findViewById(R.id.sellCropsVegetablesProcessingCompanies);
        sellCropsVegetablesOthers = (EditText) findViewById(R.id.sellCropsVegetablesOthers);
        sellCropsVegetablesPrice = (EditText) findViewById(R.id.sellCropsVegetablesPrice);
        sellCropsVegetablesPayment = (EditText) findViewById(R.id.sellCropsVegetablesPayment);
        incomeFromCrops = (EditText) findViewById(R.id.incomeFromCrops);
        incomeFromLivestock = (EditText) findViewById(R.id.incomeFromLivestock);
        incomeFromAgriLabour = (EditText) findViewById(R.id.incomeFromAgriLabour);
        incomeFromMigrationLabour = (EditText) findViewById(R.id.incomeFromMigrationLabour);
        incomeFromService = (EditText) findViewById(R.id.incomeFromService);
        incomeFromShopTrade = (EditText) findViewById(R.id.incomeFromShopTrade);
        threeMealsOfFood = (EditText) findViewById(R.id.threeMealsOfFood);
        landPreparation = (EditText) findViewById(R.id.landPreparation);
        seeds = (EditText) findViewById(R.id.seeds);
        fertilizer = (EditText) findViewById(R.id.fertilizer);
        pesticides = (EditText) findViewById(R.id.pesticides);
        irrigationExpan = (EditText) findViewById(R.id.irrigationExpan);
        weeding = (EditText) findViewById(R.id.weeding);
        harvesting = (EditText) findViewById(R.id.harvesting);
        cleaningSortingPacking = (EditText) findViewById(R.id.cleaningSortingPacking);
        transportation = (EditText) findViewById(R.id.transportation);
        storage = (EditText) findViewById(R.id.storage);
        milkingCow = (EditText) findViewById(R.id.milkingCow);
        sheepGoats = (EditText) findViewById(R.id.sheepGoats);
        backyardPoultry = (EditText) findViewById(R.id.backyardPoultry);
        aquaculturePond = (EditText) findViewById(R.id.aquaculturePond);
        ifYesCountAquaculturePond = (EditText) findViewById(R.id.ifYesCountAquaculturePond);
        tractors = (EditText) findViewById(R.id.tractors);
        ifYesCountTractors = (EditText) findViewById(R.id.ifYesCountTractors);
        tillers = (EditText) findViewById(R.id.tillers);
        ifYesCountTillers = (EditText) findViewById(R.id.ifYesCountTillers);
        harvesters = (EditText) findViewById(R.id.harvesters);
        ifYesCountHarvesters = (EditText) findViewById(R.id.ifYesCountHarvesters);
        submit = (TextView) findViewById(R.id.submit);

        bases1.add(new Base(null, null));
        RecyclerView attachemnet1List = (RecyclerView) findViewById(R.id.attachemnet1List);
        attachmentBase1Adapter = new AttachmentBaseAdapter(this, bases1, this, 12);
        attachemnet1List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet1List.setAdapter(attachmentBase1Adapter);
        bases2.add(new Base(null, null));
        RecyclerView attachemnet2List = (RecyclerView) findViewById(R.id.attachemnet2List);
        attachmentBase2Adapter = new AttachmentBaseAdapter(this, bases2, this, 13);
        attachemnet2List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet2List.setAdapter(attachmentBase2Adapter);
        bases3.add(new Base(null, null));
        RecyclerView attachemnet3List = (RecyclerView) findViewById(R.id.attachemnet3List);
        attachmentBase3Adapter = new AttachmentBaseAdapter(this, bases3, this, 14);
        attachemnet3List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet3List.setAdapter(attachmentBase3Adapter);
        bases4.add(new Base(null, null));
        RecyclerView attachemnet4List = (RecyclerView) findViewById(R.id.attachemnet4List);
        attachmentBase4Adapter = new AttachmentBaseAdapter(this, bases4, this, 15);
        attachemnet4List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet4List.setAdapter(attachmentBase4Adapter);
        bases5.add(new Base(null, null));
        RecyclerView attachemnet5List = (RecyclerView) findViewById(R.id.attachemnet5List);
        attachmentBase5Adapter = new AttachmentBaseAdapter(this, bases5, this, 5);
        attachemnet5List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet5List.setAdapter(attachmentBase5Adapter);
        bases6.add(new Base(null, null));
        RecyclerView attachemnet6List = (RecyclerView) findViewById(R.id.attachemnet6List);
        attachmentBase6Adapter = new AttachmentBaseAdapter(this, bases6, this, 6);
        attachemnet6List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet6List.setAdapter(attachmentBase6Adapter);
        bases7.add(new Base(null, null));
        RecyclerView attachemnet7List = (RecyclerView) findViewById(R.id.attachemnet7List);
        attachmentBase7Adapter = new AttachmentBaseAdapter(this, bases7, this, 7);
        attachemnet7List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet7List.setAdapter(attachmentBase7Adapter);
        bases8.add(new Base(null, null));
        RecyclerView attachemnet8List = (RecyclerView) findViewById(R.id.attachemnet8List);
        attachmentBase8Adapter = new AttachmentBaseAdapter(this, bases8, this, 8);
        attachemnet8List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet8List.setAdapter(attachmentBase8Adapter);
        bases9.add(new Base(null, null));
        RecyclerView attachemnet9List = (RecyclerView) findViewById(R.id.attachemnet9List);
        attachmentBase9Adapter = new AttachmentBaseAdapter(this, bases9, this, 9);
        attachemnet9List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet9List.setAdapter(attachmentBase9Adapter);
        bases10.add(new Base(null, null));
        RecyclerView attachemnet10List = (RecyclerView) findViewById(R.id.attachemnet10List);
        attachmentBase10Adapter = new AttachmentBaseAdapter(this, bases10, this, 10);
        attachemnet10List.setLayoutManager(new GridLayoutManager(this, 3));
        attachemnet10List.setAdapter(attachmentBase10Adapter);


        FloatingTextButton addMember = (FloatingTextButton) findViewById(R.id.addMember);
        FloatingTextButton cancelMember = (FloatingTextButton) findViewById(R.id.cancelBtn);
        memberList = (RecyclerView) findViewById(R.id.memberList);
        memberAdapter = new MemberAdapter(this, memberArrayList, this);
        final LinearLayoutManager rate = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        memberList.setLayoutManager(rate);
        memberList.setAdapter(memberAdapter);
        memberList.addOnItemTouchListener(new RecyclerTouchListener(MainActivityNewFGD.this,
                memberList, new RecyclerTouchListener.ClickListener() {
            public void onClick(View paramView, int paramInt) {
                lastMemberPosition = paramInt;
                addMember.setTitle("Update");
                memberName.setText(memberArrayList.get(paramInt).memberName);
                sex.setText(memberArrayList.get(paramInt).sex);
                mobile.setText(memberArrayList.get(paramInt).mobile);
                idType.setText(memberArrayList.get(paramInt).idType);
                farmSize.setText(memberArrayList.get(paramInt).farmSize);
                memberImage.setMfilePath(memberArrayList.get(paramInt).memberImage);
                GlideApp.with(MainActivityNewFGD.this).load("http://" + memberArrayList.get(paramInt).memberImage)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(memberImage);
            }

            public void onLongClick(View paramView, int paramInt) {
            }
        }));


        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastMemberPosition != -1) {
                    memberArrayList.remove(lastMemberPosition);
                }
                if (memberName.getText().toString().length() > 0
                        && sex.getText().toString().length() > 0
                        && idType.getText().toString().length() > 0
                        && farmSize.getText().toString().length() > 0
                        && mobile.getText().toString().length() > 0
                        ) {
                    memberArrayList.add(new Member(
                            memberImage.getMfilePath() == null ? "" : memberImage.getMfilePath(),
                            memberName.getText().toString(),
                            sex.getText().toString(),
                            mobile.getText().toString(),
                            idType.getText().toString(),
                            farmSize.getText().toString()
                    ));
                    memberAdapter.notifyData(memberArrayList);
                    lastMemberPosition = -1;
                    memberImage.setMfilePath("");
                    memberImage.setMfilePath("");
                    memberName.setText("");
                    sex.setText("");
                    mobile.setText("");
                    idType.setText("");
                    farmSize.setText("");
                    addMember.setTitle("Add");
                }

            }
        });

        cancelMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastMemberPosition = -1;
                memberImage.setMfilePath("");
                memberName.setText("");
                sex.setText("");
                mobile.setText("");
                idType.setText("");
                farmSize.setText("");
                addMember.setTitle("Add");
            }
        });

        memberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageutils.imagepicker(105);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
                        Base base = new Base();
                        base.setUrl(imageutils.getPath(uri));
                        base.setIsImage("false");
                        if (filename != null) {
                            base.setIsImage("true");
                            imageutils.createImage(file, filename, path, false);
                        }
                        Map<String, String> dataMap = new HashMap<>();
                        dataMap.put("count", String.valueOf(11));
                        dataMap.put("url", imageutils.getPath(uri));
                        dataMap.put("isImage", base.getIsImage());

                        memberImage.setMfilePath(imageutils.getPath(uri));
                        memberImage.setIsImage(dataMap.get("isImage"));
                        GlideApp.with(MainActivityNewFGD.this).load(imageutils.getPath(uri))
                                .dontAnimate()
                                .thumbnail(0.5f)
                                .placeholder(R.drawable.file)
                                .into(memberImage);
                    }
                });
            }
        });

        FloatingTextButton addVillage = (FloatingTextButton) findViewById(R.id.addVillage);
        FloatingTextButton cancelVillage = (FloatingTextButton) findViewById(R.id.cancelVillageBtn);

        villageList = (RecyclerView) findViewById(R.id.villageList);
        villageAdapter = new VillageAdapter(this, villageArrayList, this);
        final LinearLayoutManager r1ate = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        villageList.setLayoutManager(r1ate);
        villageList.setAdapter(villageAdapter);
        villageList.addOnItemTouchListener(new RecyclerTouchListener(MainActivityNewFGD.this,
                villageList, new RecyclerTouchListener.ClickListener() {
            public void onClick(View paramView, int paramInt) {
                lastVillagePosition = paramInt;
                addVillage.setTitle("Update");
                nameVillagesHamlets.setText(villageArrayList.get(paramInt).nameVillagesHamlets);
                numberOfHouseholds.setText(villageArrayList.get(paramInt).numberOfHouseholds);
            }

            public void onLongClick(View paramView, int paramInt) {
            }
        }));


        addVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastVillagePosition != -1) {
                    villageArrayList.remove(lastVillagePosition);
                }

                if (nameVillagesHamlets.getText().toString().length() > 0
                        && numberOfHouseholds.getText().toString().length() > 0

                        ) {
                    villageArrayList.add(new Village(
                            nameVillagesHamlets.getText().toString(),
                            numberOfHouseholds.getText().toString()
                    ));
                    villageAdapter.notifyData(villageArrayList);
                    lastVillagePosition = -1;
                    nameVillagesHamlets.setText("");
                    numberOfHouseholds.setText("");
                }
            }
        });

        cancelVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameVillagesHamlets.setText("");
                numberOfHouseholds.setText("");
                lastVillagePosition = -1;
            }
        });

        geoTag = (EditText) findViewById(R.id.geoTag);
        ImageView georefresh = (ImageView) findViewById(R.id.refresh);

        geoTag.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!checkPermission(new String[]{ACCESS_FINE_LOCATION})) {
                        requestPermission(new String[]{ACCESS_FINE_LOCATION}, FINE_LOCATION_CODE);
                    } else {
                        locationFetcher(geoTag);
                    }
                }
            }
        });

        georefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkPermission(new String[]{ACCESS_FINE_LOCATION})) {
                    requestPermission(new String[]{ACCESS_FINE_LOCATION}, FINE_LOCATION_CODE);
                } else {
                    locationFetcher(geoTag);
                }


            }
        });

        ImageView location = (ImageView) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=" +
                                    String.valueOf(geoTag.getText().toString().split(",")[0]) + "," +
                                    String.valueOf(geoTag.getText().toString().split(",")[1])));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid Geotag", Toast.LENGTH_SHORT).show();
                }


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FGDNewBean tempmainbean = new FGDNewBean(
                        nameOfSurveyor.getText().toString(),
                        dateOfFGD.getText().toString(),
                        geoTag.getText().toString(),
                        address.getText().toString(),
                        timeStarted.getText().toString(),
                        village.getText().toString(),
                        villageSelect.getSelectedItem().toString(),
                        district.getSelectedItem().toString(),
                        province.getSelectedItem().toString(),
                        memberName.getText().toString(),
                        sex.getText().toString(),
                        mobile.getText().toString(),
                        idType.getText().toString(),
                        farmSize.getText().toString(),
                        webLink1.getText().toString(),
                        webLink2.getText().toString(),
                        webLink3.getText().toString(),
                        nameVillagesHamlets.getText().toString(),
                        numberOfHouseholds.getText().toString(),
                        averageLandHoldingSize.getText().toString(),
                        largeFarmers.getText().toString(),
                        mediumFarmers.getText().toString(),
                        marginalFarmers.getText().toString(),
                        traditionalLandTitleHolders.getText().toString(),
                        ownLandCultivators.getText().toString(),
                        leasedLandCultivators.getText().toString(),
                        usingDigitalLandRecords.getText().toString(),
                        irrigatedLand.getText().toString(),
                        rainFedLand.getText().toString(),
                        colourOfSoil.getText().toString(),
                        typeOfSoil.getText().toString(),
                        depthOfSoil.getText().toString(),
                        qualityOfSoil.getText().toString(),
                        knowledgeAboutSoilTesting.getText().toString(),
                        soilTested.getText().toString(),
                        soilHealthCard.getText().toString(),
                        Frequency.getText().toString(),
                        rainfall.getText().toString(),
                        monsoons.getText().toString(),
                        irrigationWaterAvailable.getText().toString(),
                        riverLiftIrrigation.getText().toString(),
                        canalIrrigation.getText().toString(),
                        tankIrrigation.getText().toString(),
                        wellIrrigation.getText().toString(),
                        shallowBorewell.getText().toString(),
                        mediumBoreWell.getText().toString(),
                        deepBoreBoreWell.getText().toString(),
                        dieselKerosenePumps.getText().toString(),
                        electricPumps.getText().toString(),
                        solarPumps.getText().toString(),
                        floodIrrigation.getText().toString(),
                        dripIrrigation.getText().toString(),
                        sprinklerIrrigation.getText().toString(),
                        rainGunIrrigation.getText().toString(),
                        majorCropsRabi.getText().toString(),
                        majorCropsSummer.getText().toString(),
                        majorCropsKharif.getText().toString(),
                        rankValuableCrops.getText().toString(),
                        rankLargeVolumeCrops.getText().toString(),
                        tractor.getText().toString(),
                        ifHireCostTractor.getText().toString(),
                        tiller.getText().toString(),
                        ifHireCostTiller.getText().toString(),
                        irrigation.getText().toString(),
                        ifHireCostIrrigation.getText().toString(),
                        harvester.getText().toString(),
                        ifHireCostHarvester.getText().toString(),
                        trasher.getText().toString(),
                        ifHireCostTrasher.getText().toString(),
                        dryer.getText().toString(),
                        ifHireCostDryer.getText().toString(),
                        coldStorageMonthlyRental.getText().toString(),
                        mandalLevelLicensedShop.getText().toString(),
                        districtLevelLicensedShop.getText().toString(),
                        localLicensedShop.getText().toString(),
                        localUnLicensedShop.getText().toString(),
                        researchUniversity.getText().toString(),
                        othersSource.getText().toString(),
                        isLaborEasilyAccessible.getText().toString(),
                        isLaborEasilyAccessibleSpecify.getText().toString(),
                        wageMalePerDay.getText().toString(),
                        wageFeMalePerDay.getText().toString(),
                        informationAgriBestPractices.getText().toString(),
                        whoProvidesGuidance.getText().toString(),
                        trainingsParticipated.getText().toString(),
                        useWeatherInformation.getText().toString(),
                        ifYesWhatSource.getText().toString(),
                        investmentForCropping.getText().toString(),
                        ownKisanCreditCard.getText().toString(),
                        localMoneyLender.getText().toString(),
                        sHGGroups.getText().toString(),
                        inputShops.getText().toString(),
                        traderAggregator.getText().toString(),
                        mFI.getText().toString(),
                        kCC.getText().toString(),
                        localLandCooperative.getText().toString(),
                        normalBank.getText().toString(),
                        othersSourceFinance.getText().toString(),
                        loanRepayment.getText().toString(),
                        dailyMarketNos.getText().toString(),
                        dailyMarketKms.getText().toString(),
                        weeklyHaatNos.getText().toString(),
                        weeklyHaatKms.getText().toString(),
                        blockMandiNos.getText().toString(),
                        blockMandiKms.getText().toString(),
                        districtAPMCNos.getText().toString(),
                        districtAPMCKms.getText().toString(),
                        specialMarketsNos.getText().toString(),
                        specialMarketsKms.getText().toString(),
                        sourceInformationCurrent.getText().toString(),
                        timeOfSale.getText().toString(),
                        storageSystems.getText().toString(),
                        storageItems.getText().toString(),
                        sellCropsFruitsLocalTrader.getText().toString(),
                        sellCropsFruitsMandi.getText().toString(),
                        sellCropsFruitsOthers.getText().toString(),
                        sellCropsFruitsPrice.getText().toString(),
                        sellCropsFruitsPayment.getText().toString(),
                        sellCropsCerealsLocalTrader.getText().toString(),
                        sellCropsCerealsMandi.getText().toString(),
                        sellCropsCerealsPACS.getText().toString(),
                        sellCropsCerealsFPCPG.getText().toString(),
                        sellCropsCerealscompaniesITC.getText().toString(),
                        sellCropsCerealsOthers.getText().toString(),
                        sellCropsCerealsPrice.getText().toString(),
                        sellCropsCerealsPayment.getText().toString(),
                        sellCropsVegetablesLocalTrader.getText().toString(),
                        sellCropsVegetablesTrader.getText().toString(),
                        sellCropsVegetablesProcessingCompanies.getText().toString(),
                        sellCropsVegetablesOthers.getText().toString(),
                        sellCropsVegetablesPrice.getText().toString(),
                        sellCropsVegetablesPayment.getText().toString(),
                        incomeFromCrops.getText().toString(),
                        incomeFromLivestock.getText().toString(),
                        incomeFromAgriLabour.getText().toString(),
                        incomeFromMigrationLabour.getText().toString(),
                        incomeFromService.getText().toString(),
                        incomeFromShopTrade.getText().toString(),
                        threeMealsOfFood.getText().toString(),
                        landPreparation.getText().toString(),
                        seeds.getText().toString(),
                        fertilizer.getText().toString(),
                        pesticides.getText().toString(),
                        irrigationExpan.getText().toString(),
                        weeding.getText().toString(),
                        harvesting.getText().toString(),
                        cleaningSortingPacking.getText().toString(),
                        transportation.getText().toString(),
                        storage.getText().toString(),
                        milkingCow.getText().toString(),
                        sheepGoats.getText().toString(),
                        backyardPoultry.getText().toString(),
                        aquaculturePond.getText().toString(),
                        ifYesCountAquaculturePond.getText().toString(),
                        tractors.getText().toString(),
                        ifYesCountTractors.getText().toString(),
                        tillers.getText().toString(),
                        ifYesCountTillers.getText().toString(),
                        harvesters.getText().toString(),
                        ifYesCountHarvesters.getText().toString(),
                        audioUrl1,
                        audioUrl2,
                        audioUrl3,
                        audioUrl4,
                        audioUrl5,
                        audioUrl6,
                        audioUrl7,
                        audioUrl8,
                        audioUrl9,
                        audioUrl10,
                        bases1,
                        bases2,
                        bases3,
                        bases4,
                        bases5,
                        bases6,
                        bases7,
                        bases8,
                        bases9,
                        bases10,
                        memberArrayList,
                        villageArrayList
                );

                String jsonVal = new Gson().toJson(tempmainbean);
                Log.e("xxxxxxxxxxxxx", jsonVal);
                if (mainFGD == null) {
                    tempmainbean.setId(String.valueOf(System.currentTimeMillis()));
                    dbHelperSurvey.insertSurvey(String.valueOf(System.currentTimeMillis()),
                            sharedpreferences.getString(buSurveyerId, ""), new Gson().toJson(tempmainbean));
                    Toast.makeText(getApplicationContext(), "Recorded successFully", Toast.LENGTH_SHORT).show();

                } else {
                    tempmainbean.setId(mainFGD.id);

                    int i = dbHelperSurvey.updateNote(mainFGD.id, sharedpreferences.getString(buSurveyerId, "")
                            , new Gson().toJson(tempmainbean));
                    if (i > 0) {
                        Toast.makeText(getApplicationContext(),
                                "Updated successFully", Toast.LENGTH_SHORT).show();
                    } else {
                        dbHelperSurvey.insertSurvey(String.valueOf(System.currentTimeMillis()),
                                sharedpreferences.getString(buSurveyerId, ""), new Gson().toJson(tempmainbean));
                        Toast.makeText(getApplicationContext(), "Recorded successFully", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
                //getCreateTest(tempmainbean.id, sharedpreferences.getString(buSurveyerId, ""), jsonVal);
            }
        });

        try {

            mainFGD = (FGDNewBean) getIntent().getSerializableExtra("object");
            if (mainFGD != null) {
                nameOfSurveyor.setText(mainFGD.nameOfSurveyor);
                dateOfFGD.setText(mainFGD.dateOfFGD);
                geoTag.setText(mainFGD.geoTag);
                address.setText(mainFGD.address);
                timeStarted.setText(mainFGD.timeStarted);
                village.setText(mainFGD.village);
                villageSelect.setPrompt(mainFGD.gramPanchayat);
                district.setPrompt(mainFGD.mandal);
                province.setPrompt(mainFGD.district);
                memberName.setText(mainFGD.memberName);
                sex.setText(mainFGD.sex);
                mobile.setText(mainFGD.mobile);
                idType.setText(mainFGD.idType);
                farmSize.setText(mainFGD.farmSize);
                webLink1.setText(mainFGD.webLink1);
                webLink2.setText(mainFGD.webLink2);
                webLink3.setText(mainFGD.webLink3);
                nameVillagesHamlets.setText(mainFGD.nameVillagesHamlets);
                numberOfHouseholds.setText(mainFGD.numberOfHouseholds);
                averageLandHoldingSize.setText(mainFGD.averageLandHoldingSize);
                largeFarmers.setText(mainFGD.largeFarmers);
                mediumFarmers.setText(mainFGD.mediumFarmers);
                marginalFarmers.setText(mainFGD.marginalFarmers);
                traditionalLandTitleHolders.setText(mainFGD.traditionalLandTitleHolders);
                ownLandCultivators.setText(mainFGD.ownLandCultivators);
                leasedLandCultivators.setText(mainFGD.leasedLandCultivators);
                usingDigitalLandRecords.setText(mainFGD.usingDigitalLandRecords);
                irrigatedLand.setText(mainFGD.irrigatedLand);
                rainFedLand.setText(mainFGD.rainFedLand);
                colourOfSoil.setText(mainFGD.colourOfSoil);
                typeOfSoil.setText(mainFGD.typeOfSoil);
                depthOfSoil.setText(mainFGD.depthOfSoil);
                qualityOfSoil.setText(mainFGD.qualityOfSoil);
                knowledgeAboutSoilTesting.setText(mainFGD.knowledgeAboutSoilTesting);
                soilTested.setText(mainFGD.soilTested);
                soilHealthCard.setText(mainFGD.soilHealthCard);
                Frequency.setText(mainFGD.Frequency);
                rainfall.setText(mainFGD.rainfall);
                monsoons.setText(mainFGD.monsoons);
                irrigationWaterAvailable.setText(mainFGD.irrigationWaterAvailable);
                riverLiftIrrigation.setText(mainFGD.riverLiftIrrigation);
                canalIrrigation.setText(mainFGD.canalIrrigation);
                tankIrrigation.setText(mainFGD.tankIrrigation);
                wellIrrigation.setText(mainFGD.wellIrrigation);
                shallowBorewell.setText(mainFGD.shallowBorewell);
                mediumBoreWell.setText(mainFGD.mediumBoreWell);
                deepBoreBoreWell.setText(mainFGD.deepBoreBoreWell);
                dieselKerosenePumps.setText(mainFGD.dieselKerosenePumps);
                electricPumps.setText(mainFGD.electricPumps);
                solarPumps.setText(mainFGD.solarPumps);
                floodIrrigation.setText(mainFGD.floodIrrigation);
                dripIrrigation.setText(mainFGD.dripIrrigation);
                sprinklerIrrigation.setText(mainFGD.sprinklerIrrigation);
                rainGunIrrigation.setText(mainFGD.rainGunIrrigation);
                majorCropsRabi.setText(mainFGD.majorCropsRabi);
                majorCropsSummer.setText(mainFGD.majorCropsSummer);
                majorCropsKharif.setText(mainFGD.majorCropsKharif);
                rankValuableCrops.setText(mainFGD.rankValuableCrops);
                rankLargeVolumeCrops.setText(mainFGD.rankLargeVolumeCrops);
                tractor.setText(mainFGD.tractor);
                ifHireCostTractor.setText(mainFGD.ifHireCostTractor);
                tiller.setText(mainFGD.tiller);
                ifHireCostTiller.setText(mainFGD.ifHireCostTiller);
                irrigation.setText(mainFGD.irrigation);
                ifHireCostIrrigation.setText(mainFGD.ifHireCostIrrigation);
                harvester.setText(mainFGD.harvester);
                ifHireCostHarvester.setText(mainFGD.ifHireCostHarvester);
                trasher.setText(mainFGD.trasher);
                ifHireCostTrasher.setText(mainFGD.ifHireCostTrasher);
                dryer.setText(mainFGD.dryer);
                ifHireCostDryer.setText(mainFGD.ifHireCostDryer);
                coldStorageMonthlyRental.setText(mainFGD.coldStorageMonthlyRental);
                mandalLevelLicensedShop.setText(mainFGD.mandalLevelLicensedShop);
                districtLevelLicensedShop.setText(mainFGD.districtLevelLicensedShop);
                localLicensedShop.setText(mainFGD.localLicensedShop);
                localUnLicensedShop.setText(mainFGD.localUnLicensedShop);
                researchUniversity.setText(mainFGD.researchUniversity);
                othersSource.setText(mainFGD.othersSource);
                isLaborEasilyAccessible.setText(mainFGD.isLaborEasilyAccessible);
                isLaborEasilyAccessibleSpecify.setText(mainFGD.isLaborEasilyAccessibleSpecify);
                wageMalePerDay.setText(mainFGD.wageMalePerDay);
                wageFeMalePerDay.setText(mainFGD.wageFeMalePerDay);
                informationAgriBestPractices.setText(mainFGD.informationAgriBestPractices);
                whoProvidesGuidance.setText(mainFGD.whoProvidesGuidance);
                trainingsParticipated.setText(mainFGD.trainingsParticipated);
                useWeatherInformation.setText(mainFGD.useWeatherInformation);
                ifYesWhatSource.setText(mainFGD.ifYesWhatSource);
                investmentForCropping.setText(mainFGD.investmentForCropping);
                ownKisanCreditCard.setText(mainFGD.ownKisanCreditCard);
                localMoneyLender.setText(mainFGD.localMoneyLender);
                sHGGroups.setText(mainFGD.sHGGroups);
                inputShops.setText(mainFGD.inputShops);
                traderAggregator.setText(mainFGD.traderAggregator);
                mFI.setText(mainFGD.mFI);
                kCC.setText(mainFGD.kCC);
                localLandCooperative.setText(mainFGD.localLandCooperative);
                normalBank.setText(mainFGD.normalBank);
                othersSourceFinance.setText(mainFGD.othersSourceFinance);
                loanRepayment.setText(mainFGD.loanRepayment);
                dailyMarketNos.setText(mainFGD.dailyMarketNos);
                dailyMarketKms.setText(mainFGD.dailyMarketKms);
                weeklyHaatNos.setText(mainFGD.weeklyHaatNos);
                weeklyHaatKms.setText(mainFGD.weeklyHaatKms);
                blockMandiNos.setText(mainFGD.blockMandiNos);
                blockMandiKms.setText(mainFGD.blockMandiKms);
                districtAPMCNos.setText(mainFGD.districtAPMCNos);
                districtAPMCKms.setText(mainFGD.districtAPMCKms);
                specialMarketsNos.setText(mainFGD.specialMarketsNos);
                specialMarketsKms.setText(mainFGD.specialMarketsKms);
                sourceInformationCurrent.setText(mainFGD.sourceInformationCurrent);
                timeOfSale.setText(mainFGD.timeOfSale);
                storageSystems.setText(mainFGD.storageSystems);
                storageItems.setText(mainFGD.storageItems);
                sellCropsFruitsLocalTrader.setText(mainFGD.sellCropsFruitsLocalTrader);
                sellCropsFruitsMandi.setText(mainFGD.sellCropsFruitsMandi);
                sellCropsFruitsOthers.setText(mainFGD.sellCropsFruitsOthers);
                sellCropsFruitsPrice.setText(mainFGD.sellCropsFruitsPrice);
                sellCropsFruitsPayment.setText(mainFGD.sellCropsFruitsPayment);
                sellCropsCerealsLocalTrader.setText(mainFGD.sellCropsCerealsLocalTrader);
                sellCropsCerealsMandi.setText(mainFGD.sellCropsCerealsMandi);
                sellCropsCerealsPACS.setText(mainFGD.sellCropsCerealsPACS);
                sellCropsCerealsFPCPG.setText(mainFGD.sellCropsCerealsFPCPG);
                sellCropsCerealscompaniesITC.setText(mainFGD.sellCropsCerealscompaniesITC);
                sellCropsCerealsOthers.setText(mainFGD.sellCropsCerealsOthers);
                sellCropsCerealsPrice.setText(mainFGD.sellCropsCerealsPrice);
                sellCropsCerealsPayment.setText(mainFGD.sellCropsCerealsPayment);
                sellCropsVegetablesLocalTrader.setText(mainFGD.sellCropsVegetablesLocalTrader);
                sellCropsVegetablesTrader.setText(mainFGD.sellCropsVegetablesTrader);
                sellCropsVegetablesProcessingCompanies.setText(mainFGD.sellCropsVegetablesProcessingCompanies);
                sellCropsVegetablesOthers.setText(mainFGD.sellCropsVegetablesOthers);
                sellCropsVegetablesPrice.setText(mainFGD.sellCropsVegetablesPrice);
                sellCropsVegetablesPayment.setText(mainFGD.sellCropsVegetablesPayment);
                incomeFromCrops.setText(mainFGD.incomeFromCrops);
                incomeFromLivestock.setText(mainFGD.incomeFromLivestock);
                incomeFromAgriLabour.setText(mainFGD.incomeFromAgriLabour);
                incomeFromMigrationLabour.setText(mainFGD.incomeFromMigrationLabour);
                incomeFromService.setText(mainFGD.incomeFromService);
                incomeFromShopTrade.setText(mainFGD.incomeFromShopTrade);
                threeMealsOfFood.setText(mainFGD.threeMealsOfFood);
                landPreparation.setText(mainFGD.landPreparation);
                seeds.setText(mainFGD.seeds);
                fertilizer.setText(mainFGD.fertilizer);
                pesticides.setText(mainFGD.pesticides);
                irrigationExpan.setText(mainFGD.irrigationExpan);
                weeding.setText(mainFGD.weeding);
                harvesting.setText(mainFGD.harvesting);
                cleaningSortingPacking.setText(mainFGD.cleaningSortingPacking);
                transportation.setText(mainFGD.transportation);
                storage.setText(mainFGD.storage);
                milkingCow.setText(mainFGD.milkingCow);
                sheepGoats.setText(mainFGD.sheepGoats);
                backyardPoultry.setText(mainFGD.backyardPoultry);
                aquaculturePond.setText(mainFGD.aquaculturePond);
                ifYesCountAquaculturePond.setText(mainFGD.ifYesCountAquaculturePond);
                tractors.setText(mainFGD.tractors);
                ifYesCountTractors.setText(mainFGD.ifYesCountTractors);
                tillers.setText(mainFGD.tillers);
                ifYesCountTillers.setText(mainFGD.ifYesCountTillers);
                harvesters.setText(mainFGD.harvesters);
                ifYesCountHarvesters.setText(mainFGD.ifYesCountHarvesters);
                if (mainFGD.uploadAudioUrl1 != null) {
                    audioUrl1 = mainFGD.uploadAudioUrl1;
                    play1.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl2 != null) {
                    audioUrl2 = mainFGD.uploadAudioUrl2;
                    play2.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl3 != null) {
                    audioUrl3 = mainFGD.uploadAudioUrl3;
                    play3.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl4 != null) {
                    audioUrl4 = mainFGD.uploadAudioUrl4;
                    play4.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl5 != null) {
                    audioUrl5 = mainFGD.uploadAudioUrl5;
                    play5.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl6 != null) {
                    audioUrl6 = mainFGD.uploadAudioUrl6;
                    play6.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl7 != null) {
                    audioUrl7 = mainFGD.uploadAudioUrl7;
                    play7.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl8 != null) {
                    audioUrl8 = mainFGD.uploadAudioUrl8;
                    play8.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl9 != null) {
                    audioUrl9 = mainFGD.uploadAudioUrl9;
                    play9.setVisibility(View.VISIBLE);
                }
                if (mainFGD.uploadAudioUrl10 != null) {
                    audioUrl10 = mainFGD.uploadAudioUrl10;
                    play10.setVisibility(View.VISIBLE);
                }

                if (mainFGD.bases1 != null) {
                    bases1 = mainFGD.bases1;
                    attachmentBase1Adapter.notifyData(bases1);
                }
                if (mainFGD.bases2 != null) {
                    bases2 = mainFGD.bases2;
                    attachmentBase2Adapter.notifyData(bases2);
                }
                if (mainFGD.bases3 != null) {
                    bases3 = mainFGD.bases3;
                    attachmentBase3Adapter.notifyData(bases3);
                }
                if (mainFGD.bases4 != null) {
                    bases4 = mainFGD.bases4;
                    attachmentBase4Adapter.notifyData(bases4);
                }
                if (mainFGD.bases5 != null) {
                    bases5 = mainFGD.bases5;
                    attachmentBase5Adapter.notifyData(bases5);
                }
                if (mainFGD.bases6 != null) {
                    bases6 = mainFGD.bases6;
                    attachmentBase6Adapter.notifyData(bases6);
                }
                if (mainFGD.bases7 != null) {
                    bases7 = mainFGD.bases7;
                    attachmentBase7Adapter.notifyData(bases7);
                }
                if (mainFGD.bases8 != null) {
                    bases8 = mainFGD.bases8;
                    attachmentBase8Adapter.notifyData(bases8);
                }
                if (mainFGD.bases9 != null) {
                    bases9 = mainFGD.bases9;
                    attachmentBase9Adapter.notifyData(bases9);
                }
                if (mainFGD.bases10 != null) {
                    bases10 = mainFGD.bases10;
                    attachmentBase10Adapter.notifyData(bases10);
                }
                if (mainFGD.memberArrayList != null) {
                    memberArrayList = mainFGD.memberArrayList;
                    memberAdapter.notifyData(memberArrayList);
                }
                if (mainFGD.villageArrayList != null) {
                    villageArrayList = mainFGD.villageArrayList;
                    villageAdapter.notifyData(villageArrayList);
                }
                georefresh.setVisibility(View.INVISIBLE);

            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }


        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            Iterator<String> keys = jsonObject.keys();
            DISTRICTS = toArray(keys);
            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DISTRICTS);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            province.setAdapter(aa);
            if(mainFGD!=null) {
                districtTxt.setText("DISTRICT : ("+mainFGD.getDistrict()+")");
                setSpinText(province,mainFGD.getDistrict());
            }

            province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String item = province.getSelectedItem().toString();
                    try {
                        final JSONObject mandalJson = jsonObject.getJSONObject(item);
                        Iterator<String> keys = mandalJson.keys();
                        MANDALS = toArray(keys);

                        ArrayAdapter unitAdapter = new ArrayAdapter(MainActivityNewFGD.this, android.R.layout.simple_spinner_item, MANDALS);
                        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        district.setAdapter(unitAdapter);
                        if(mainFGD!=null) {
                            mandalTxt.setText("MANDAL : ("+mainFGD.getMandal()+")");
                            setSpinText(district,mainFGD.getMandal());
                        }
                        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String mandalItem = district.getSelectedItem().toString();
                                try {
                                    JSONArray subchapterJson = mandalJson.getJSONArray(mandalItem);
                                    GPS = toStringArray(subchapterJson);
                                    if (GPS.length == 0) {
                                        GPS = new String[]{
                                                "No Gps"
                                        };
                                    }
                                    ArrayAdapter subchapterAdapter = new ArrayAdapter(MainActivityNewFGD.this,
                                            android.R.layout.simple_spinner_item, GPS);
                                    subchapterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    //Setting the ArrayAdapter data on the Spinner
                                    villageSelect.setAdapter(subchapterAdapter);
                                    if(mainFGD!=null) {
                                        gpTxt.setText("GP : ("+mainFGD.getGramPanchayat()+")");
                                        setSpinText(villageSelect,mainFGD.getGramPanchayat());
                                    }
                                } catch (Exception e) {
                                    Log.e("Error", e.toString());
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }

                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            Log.e("Json object", jsonObject.toString());
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }



    }


    private void getCreateTest(final String mId, final String surveyer, final String data) {
        this.pDialog.setMessage("Creating...");
        showDialog();
        StringRequest local16 = new StringRequest(1, AppConfig.URL_CREATE_PWCFGD, new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    String str = localJSONObject1.getString("message");
                    if (localJSONObject1.getInt("success") == 1) {
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    return;
                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError paramVolleyError) {
                Log.e("tag", "Fetch Error: " + paramVolleyError.getMessage());
                Toast.makeText(getApplicationContext(), paramVolleyError.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> localHashMap = new HashMap<String, String>();
                if (mId != null) {
                    localHashMap.put("id", mId);
                }
                localHashMap.put("formId", mId);
                localHashMap.put("surveyer", surveyer);
                localHashMap.put("data", data);


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
    }

    private void hideDialog() {

        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }

    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }


    private void requestPermission(String[] permissions, int code) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (requestCode == FINE_LOCATION_CODE) {
            if (grantResults.length > 0) {
                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (locationAccepted) {
                    Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access location data.", Toast.LENGTH_LONG).show();
                    locationFetcher(geoTag);
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data", Toast.LENGTH_LONG).show();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("Please Enable this permission to identify" +
                                            "  your Survey on the google map by other Surveyors",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{ACCESS_FINE_LOCATION},
                                                        FINE_LOCATION_CODE);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }
            }
        } else {
            imageutils.request_permission_result(requestCode, permissions, grantResults);
        }
    }

    private boolean checkPermission(String[] permissions) {
        boolean result = false;
        for (int i = 0; i < permissions.length; i++) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), permissions[i]) == 0;
            if (!result) {
                return false;
            }
        }
        return result;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivityNewFGD.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void locationFetcher(EditText gpsLocal) {
        // check if GPS enabled
        gps = new GPSTracker(MainActivityNewFGD.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            gpsLocal.setText(latitude + "," + longitude);
            try {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(MainActivityNewFGD.this, Locale.getDefault());
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String addresstxt = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String stateTemp = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                //   addressLocal.setText(addresstxt + "," + addresses.get(0).getPostalCode());

            } catch (Exception e) {
                Log.d("Error", e.toString());
            }
            // \n is for new line
            //       Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 12) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl1 = data.getStringExtra("result");
                play1.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 13) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl2 = data.getStringExtra("result");
                play2.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 14) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl3 = data.getStringExtra("result");
                play3.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 15) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl4 = data.getStringExtra("result");
                play4.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 5) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl5 = data.getStringExtra("result");
                play5.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 6) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl6 = data.getStringExtra("result");
                play6.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 7) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl7 = data.getStringExtra("result");
                play7.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 8) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl8 = data.getStringExtra("result");
                play8.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 9) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl9 = data.getStringExtra("result");
                play9.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                audioUrl10 = data.getStringExtra("result");
                play10.setVisibility(View.VISIBLE);
            }
        } else {
            imageutils.onActivityResult(requestCode, resultCode, data);
        }
    }//onActivityResult


    private void startPlay(String url, String name) {
        Intent localIntent = new Intent(MainActivityNewFGD.this, MainActivityAudio.class);
        localIntent.putExtra("filePath", "http://" + url);
        localIntent.putExtra("isImage", false);
        localIntent.putExtra("name", name);
        startActivity(localIntent);
    }

    @Override
    public void onBaseClick(int position, final int count) {
        if (position == 0) {
            imageutils.imagepicker(101);
            imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                @Override
                public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                    String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
                    Base base = new Base();
                    base.setUrl(imageutils.getPath(uri));
                    base.setIsImage("false");
                    if (filename != null) {
                        base.setIsImage("true");
                        imageutils.createImage(file, filename, path, false);
                    }
                    Map<String, String> dataMap = new HashMap<>();
                    dataMap.put("count", String.valueOf(count));
                    dataMap.put("url", imageutils.getPath(uri));
                    dataMap.put("isImage", base.getIsImage());
                    if (dataMap.get("count").equals("12")) {
                        bases1.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase1Adapter.notifyDataItem(bases1, bases1.size() + 1);
                    } else if (dataMap.get("count").equals("13")) {
                        bases2.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase2Adapter.notifyDataItem(bases2, bases2.size() + 1);
                    } else if (dataMap.get("count").equals("14")) {
                        bases3.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase3Adapter.notifyDataItem(bases3, bases3.size() + 1);
                    } else if (dataMap.get("count").equals("15")) {
                        bases4.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase4Adapter.notifyDataItem(bases4, bases4.size() + 1);
                    } else if (dataMap.get("count").equals("5")) {
                        bases5.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase5Adapter.notifyDataItem(bases5, bases5.size() + 1);
                    } else if (dataMap.get("count").equals("6")) {
                        bases6.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase6Adapter.notifyDataItem(bases6, bases6.size() + 1);
                    } else if (dataMap.get("count").equals("7")) {
                        bases7.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase7Adapter.notifyDataItem(bases7, bases7.size() + 1);
                    } else if (dataMap.get("count").equals("8")) {
                        bases8.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase8Adapter.notifyDataItem(bases8, bases8.size() + 1);
                    } else if (dataMap.get("count").equals("9")) {
                        bases9.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase9Adapter.notifyDataItem(bases9, bases9.size() + 1);
                    } else if (dataMap.get("count").equals("10")) {
                        bases10.add(new Base(imageutils.getPath(uri), dataMap.get("isImage")));
                        attachmentBase10Adapter.notifyDataItem(bases10, bases10.size() + 1);
                    } else if (dataMap.get("count").equals("11")) {
                        memberImage.setMfilePath(imageutils.getPath(uri));
                        memberImage.setIsImage(dataMap.get("isImage"));
                        GlideApp.with(MainActivityNewFGD.this).load(imageutils.getPath(uri))
                                .dontAnimate()
                                .thumbnail(0.5f)
                                .placeholder(R.drawable.file)
                                .into(memberImage);
                    }

                }
            });
        } else {
            Intent localIntent = new Intent(MainActivityNewFGD.this, ActivityMediaOnline.class);
            if (count == 12) {
                localIntent.putExtra("filePath", bases1.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases1.get(position).getIsImage()));
            } else if (count == 13) {
                localIntent.putExtra("filePath", bases2.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases2.get(position).getIsImage()));
            } else if (count == 14) {
                localIntent.putExtra("filePath", bases3.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases3.get(position).getIsImage()));
            } else if (count == 15) {
                localIntent.putExtra("filePath", bases4.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases4.get(position).getIsImage()));
            } else if (count == 5) {
                localIntent.putExtra("filePath", bases5.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases5.get(position).getIsImage()));
            } else if (count == 6) {
                localIntent.putExtra("filePath", bases6.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases6.get(position).getIsImage()));
            } else if (count == 7) {
                localIntent.putExtra("filePath", bases7.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases7.get(position).getIsImage()));
            } else if (count == 8) {
                localIntent.putExtra("filePath", bases8.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases8.get(position).getIsImage()));
            } else if (count == 9) {
                localIntent.putExtra("filePath", bases9.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases9.get(position).getIsImage()));
            } else if (count == 10) {
                localIntent.putExtra("filePath", bases10.get(position).getUrl());
                localIntent.putExtra("isImage", Boolean.parseBoolean(bases10.get(position).getIsImage()));
            }
            startActivity(localIntent);
        }

    }

    @Override
    public void onDeleteClick(int position, int count) {
        if (count == 1) {
            bases1.remove(position);
            attachmentBase1Adapter.notifyData(bases1);
        }
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {

    }

    @Override
    public void onMemberItemClick(int position) {

    }

    private class UploadImageToServer extends AsyncTask<Map<String, String>, Integer, String> {
        Map<String, String> dataMap;
        public long totalSize = 0;

        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            pDialog.setMessage("Uploading..." + (String.valueOf(progress[0])));
        }

        @Override
        protected String doInBackground(Map<String, String>... params) {
            dataMap = params[0];
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            String url = AppConfig.URL_IMAGE_UPLOAD;
            if (dataMap.get("isImage").equals("false")) {
                url = AppConfig.URL_VIDEO_UPLOAD;
            }
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(dataMap.get("url"));
                // Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));
                totalSize = entity.getContentLength();
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
            try {
                JSONObject jsonObject = new JSONObject(result.toString());
                if (!jsonObject.getBoolean("error")) {
                    String imageUrl = "";
                    if (dataMap.get("isImage").equals("true")) {
                        imageUrl = AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(dataMap.get("url"));
                    } else {
                        imageUrl = AppConfig.ipcloud + "/uploads/video/" + imageutils.getfilename_from_path(dataMap.get("url"));
                    }
                    if (dataMap.get("count").equals("12")) {
                        bases1.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase1Adapter.notifyDataItem(bases1, bases1.size() + 1);
                    } else if (dataMap.get("count").equals("13")) {
                        bases2.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase2Adapter.notifyDataItem(bases2, bases2.size() + 1);
                    } else if (dataMap.get("count").equals("14")) {
                        bases3.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase3Adapter.notifyDataItem(bases3, bases3.size() + 1);
                    } else if (dataMap.get("count").equals("15")) {
                        bases4.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase4Adapter.notifyDataItem(bases4, bases4.size() + 1);
                    } else if (dataMap.get("count").equals("5")) {
                        bases5.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase5Adapter.notifyDataItem(bases5, bases5.size() + 1);
                    } else if (dataMap.get("count").equals("6")) {
                        bases6.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase6Adapter.notifyDataItem(bases6, bases6.size() + 1);
                    } else if (dataMap.get("count").equals("7")) {
                        bases7.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase7Adapter.notifyDataItem(bases7, bases7.size() + 1);
                    } else if (dataMap.get("count").equals("8")) {
                        bases8.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase8Adapter.notifyDataItem(bases8, bases8.size() + 1);
                    } else if (dataMap.get("count").equals("9")) {
                        bases9.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase9Adapter.notifyDataItem(bases9, bases9.size() + 1);
                    } else if (dataMap.get("count").equals("10")) {
                        bases10.add(new Base(imageUrl, dataMap.get("isImage")));
                        attachmentBase10Adapter.notifyDataItem(bases10, bases10.size() + 1);
                    } else if (dataMap.get("count").equals("11")) {
                        memberImage.setMfilePath(imageUrl);
                        memberImage.setIsImage(dataMap.get("isImage"));

                        GlideApp.with(MainActivityNewFGD.this).load("http://" + imageUrl)
                                .dontAnimate()
                                .thumbnail(0.5f)
                                .placeholder(R.drawable.file)
                                .into(memberImage);
                    }
                }
                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Image not uploaded", Toast.LENGTH_SHORT).show();
            }
            hideDialog();
            // showing the server response in an alert dialog
            //showAlert(result);


            super.onPostExecute(result);
        }


    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cites.json");
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

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }
}
