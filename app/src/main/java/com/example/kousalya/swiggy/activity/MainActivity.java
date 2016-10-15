package com.example.kousalya.swiggy.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kousalya.swiggy.R;
import com.example.kousalya.swiggy.adapter.NewsFeedAdapter;
import com.example.kousalya.swiggy.interfaces.NewsFeeds;
import com.example.kousalya.swiggy.models.NewsFeedsModel;
import com.example.kousalya.swiggy.retrofit.APIGenerator;
import com.example.kousalya.swiggy.utilis.CommonUtilis;
import com.example.kousalya.swiggy.utilis.DividerItemDecoration;
import com.example.kousalya.swiggy.utilis.IntentExtra;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.latest)
    TextView latest;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.popular)
    TextView popular;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.topmenubar)
    LinearLayout topmenubar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    NewsFeeds newsFeeds;
    NewsFeedAdapter adapter;
    String cehckedVal;
    StringBuilder team;
    StringBuilder topic;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_black));
        }
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            team = new StringBuilder();
            topic = new StringBuilder();
            newsFeeds = APIGenerator.createService(NewsFeeds.class);
            init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == IntentExtra.RESULT_OK) {
                cehckedVal = data.getStringExtra("CHECKED");
                Log.d("cehckedVal ... ", cehckedVal);

                if(cehckedVal.length() > 0){
                    String[] temp = cehckedVal.split(",");
                    for (int i = 0; i < temp.length ; i++){
                        if(temp[i].contains("Topic")){
                            topic.append(temp[i].split("=")[0]);
                        } else {
                            topic.append(temp[i].split("=")[0]);
                        }

                    }
                    // http://compassites-dev-api.herokuapp.com/apis/filtered-feed?project=SW&filter=team:H R---Topics:Fun
                    newsFeeds.filtered_feed_category("SW", team.toString() + "---" + topic.toString(), new Callback<ArrayList<NewsFeedsModel>>() {
                        @Override
                        public void success(ArrayList<NewsFeedsModel> models, Response response) {
                            Log.d("Question object", models.toString());
                            adapter = new NewsFeedAdapter(MainActivity.this, models);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            error.printStackTrace();
                            CommonUtilis.showMassege(MainActivity.this,"Something went wrong, Please try again");
                        }
                    });

                }
            }
    }

    public void init() {
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Feed");
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.globe_ic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            newsFeedAPI();

            latest.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    latestPost();
                    return false;
                }
            });

            popular.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    popularPost();
                    return false;
                }
            });
            filter.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                    startActivityForResult(intent, IntentExtra.RESULT_OK);
                    return false;
                }
            });

            recyclerView.setNestedScrollingEnabled(true);
    }

    public void latestPost(){
        newsFeeds.filtered_feed("SW", "Latest", new Callback<ArrayList<NewsFeedsModel>>() {
            @Override
            public void success(ArrayList<NewsFeedsModel> models, Response response) {
                Log.d("Latest object", models.toString());
                CommonUtilis.showMassege(MainActivity.this, "Updated Latest Posts");
                adapter = new NewsFeedAdapter(MainActivity.this, models);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void failure(RetrofitError error) {
                CommonUtilis.showMassege(MainActivity.this,"Something went wrong, Please try again");
            }
        });
    }

    public void popularPost(){
        newsFeeds.filtered_feed("SW", "Popular", new Callback<ArrayList<NewsFeedsModel>>() {
            @Override
            public void success(ArrayList<NewsFeedsModel> models, Response response) {
                Log.d("Popular object", models.toString());
                CommonUtilis.showMassege(MainActivity.this, "Updated Popular Posts");
                adapter = new NewsFeedAdapter(MainActivity.this, models);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void failure(RetrofitError error) {
                CommonUtilis.showMassege(MainActivity.this,"Something went wrong, Please try again");

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        else if (item.getItemId() == R.id.action_latest){
            latestPost();
        }
        else if (item.getItemId() == R.id.action_popular){
            popularPost();
        }
        else if (item.getItemId() == R.id.action_refresh){
            newsFeedAPI();
        }
        return super.onOptionsItemSelected(item);
    }

    public void newsFeedAPI(){
        newsFeeds.getfeeds("SW", new Callback<ArrayList<NewsFeedsModel>>() {
            @Override
            public void success(ArrayList<NewsFeedsModel> response, Response response2) {
                Log.d("Question object", response.toString());
                adapter = new NewsFeedAdapter(MainActivity.this, response);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                CommonUtilis.showMassege(MainActivity.this,"Something went wrong, Please try again");
            }
        });
    }
}
