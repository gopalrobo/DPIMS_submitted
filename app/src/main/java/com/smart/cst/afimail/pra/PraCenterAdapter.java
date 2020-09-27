package com.smart.cst.afimail.pra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.smart.cst.afimail.MainActivityAllFGDSurvey;
import com.smart.cst.afimail.R;
import com.smart.cst.afimail.plan.MainActivityPlan;
import com.smart.cst.afimail.web.MainActivityWeb;

import java.util.List;


public class PraCenterAdapter extends RecyclerView.Adapter<PraCenterAdapter.MyViewHolder> {


    private List<Pra> moviesList;
    private AppCompatActivity mContext;
    YouTubePlayer player;
    private VideoClick videoClick;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String tittle = "tittleKey";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout vidolin;
        private final LinearLayout infolin;
        private final ImageView imagevideo, arrowImg;
        private final LinearLayout descriptionlin;
        public TextView title, count;
        private final LinearLayout addimagelin, toolsLin;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            vidolin = (LinearLayout) view.findViewById(R.id.vidolin);
            infolin = (LinearLayout) view.findViewById(R.id.infolin);
            addimagelin = (LinearLayout) view.findViewById(R.id.addimagelin);
            descriptionlin = (LinearLayout) view.findViewById(R.id.descriptionlin);
            imagevideo = (ImageView) view.findViewById(R.id.imagevideo);
            toolsLin = (LinearLayout) view.findViewById(R.id.toolsLin);
            arrowImg = (ImageView) view.findViewById(R.id.arrowImg);

        }
    }


    public PraCenterAdapter(List<Pra> moviesList, AppCompatActivity mContext, VideoClick videoClick) {
        this.moviesList = moviesList;
        this.mContext = mContext;
        this.videoClick = videoClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pra_center_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Pra pra = moviesList.get(position);

        holder.count.setText(String.valueOf(position + 1) + " ");
        holder.title.setText(pra.getTitle());
        holder.infolin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClick.webClick(position);
            }
        });
        holder.vidolin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClick.videoClick(position);

            }
        });

        holder.addimagelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClick.imageClick(position);
            }
        });
        holder.descriptionlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClick.reportClick(position);
            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleClick(pra, holder);
            }
        });
        holder.arrowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleClick(pra, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


   private void titleClick(Pra pra, MyViewHolder holder) {

        if (pra.getTitle().equals("MAIL Website")) {
            holder.toolsLin.setVisibility(View.GONE);
            Intent io = new Intent(mContext, MainActivityWeb.class);
            io.putExtra("link", "https://www.mail.gov.af/en");
            mContext.startActivity(io);
        }
//        else if (pra.getTitle().equals("UBA News & Events")) {
//            holder.toolsLin.setVisibility(View.GONE);
//            Intent io = new Intent(mContext, MainActivityWeb.class);
//            io.putExtra("link", "http://unnatbharatabhiyan.gov.in/index#newsslider");
//            mContext.startActivity(io);
//        } else if (pra.getTitle().equals("UBA Photos & Videos")) {
//            holder.toolsLin.setVisibility(View.GONE);
//            Intent io = new Intent(mContext, MainActivityWeb.class);
//            io.putExtra("link", "http://unnatbharatabhiyan.gov.in/blog/");
//            mContext.startActivity(io);
//        }
        else if (pra.getTitle().equals("UBA Website")) {
            holder.toolsLin.setVisibility(View.GONE);
            Intent io = new Intent(mContext, MainActivityWeb.class);
            mContext.startActivity(io);
        }

//        else if (pra.getTitle().equals("UBA Village Secondary Info")) {
//            Intent io = new Intent(mContext, VillageIntroActivity.class);
//            mContext.startActivity(io);
//        } else if (pra.getTitle().equals("UBA 2.0 Project Guidelines")) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://unnatbharatabhiyan.gov.in/app/webroot/files/brochure.pdf"));
//            mContext.startActivity(browserIntent);
//        } else if (pra.getTitle().equals("Gram shaba/Village Meeting")) {
//            Intent io = new Intent(mContext, VillageMeetingActivity.class);
//            mContext.startActivity(io);
//        }
        else if (pra.getTitle().equals("GEO FGD")) {
            Intent io = new Intent(mContext, MainActivityAllFGDSurvey.class);
            mContext.startActivity(io);
        }
       else if (pra.getTitle().equals("Participatory Planning")) {
           Intent io = new Intent(mContext, MainActivityPlan.class);
           mContext.startActivity(io);
       }
        else if (pra.getTitle().equals("Smart Geo PRA Tools")) {
            Intent io = new Intent(mContext, MainActivityPraWork.class);
            mContext.startActivity(io);
        }
//        else if (pra.getTitle().equals("Gram Panchayat Development Plan")) {
//            Intent io = new Intent(mContext, MainActivityGpdp.class);
//            mContext.startActivity(io);
//        } else if (pra.getTitle().equals("UBA 2.0 Village Survey")) {
//            Intent io = new Intent(mContext, MainActivityAllVillage.class);
//            mContext.startActivity(io);
//        } else if (pra.getTitle().equals("UBA Household survey")) {
//            Intent io = new Intent(mContext, MainActivityAllSurvey.class);
//            mContext.startActivity(io);
//        } else if (pra.getTitle().equals("Plan of Action")) {
//            Intent io = new Intent(mContext, MainActivityAllSurveyAction.class);
//            mContext.startActivity(io);
//        } else if ( pra.getTitle().equals("Other Activities")
//                || pra.getTitle().equals("Financial Aids")) {
//            Toast.makeText(mContext, "Coming Soon", Toast.LENGTH_SHORT).show();
//        }
        else if (!pra.getYear().equalsIgnoreCase("true")) {
            sharedpreferences = mContext.getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(tittle, pra.getTitle());
            editor.commit();
//            Intent io = new Intent(mContext, AttendancePage.class);
//            mContext.startActivity(io);
        } else if (pra.getYear().equalsIgnoreCase("true")) {
            if (pra.getGenre().equals("true")) {
//                Intent io = new Intent(mContext, TeamMember.class);
//                mContext.startActivity(io);
            }
        } else {
            Toast.makeText(mContext, pra.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
        }
   }


}
