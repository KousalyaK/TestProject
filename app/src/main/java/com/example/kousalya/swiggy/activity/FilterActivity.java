package com.example.kousalya.swiggy.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kousalya.swiggy.R;
import com.example.kousalya.swiggy.interfaces.NewsFeeds;
import com.example.kousalya.swiggy.models.FilterModel;
import com.example.kousalya.swiggy.retrofit.APIGenerator;
import com.example.kousalya.swiggy.utilis.CommonUtilis;
import com.example.kousalya.swiggy.utilis.IntentExtra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FilterActivity extends AppCompatActivity {


   /* @Bind(R.id.toolbar)
    Toolbar toolbar;*/
    @Bind(R.id.team)
    TextView team;
    @Bind(R.id.tag)
    TextView tag;
    @Bind(R.id.teamlayout)
    LinearLayout teamlayout;
    @Bind(R.id.a2)
    CheckBox a2;
    @Bind(R.id.management)
    CheckBox management;
    @Bind(R.id.operations)
    CheckBox operations;
    @Bind(R.id.technology)
    CheckBox technology;
    @Bind(R.id.checkboxlayout)
    LinearLayout checkboxlayout;
    @Bind(R.id.content_filter)
    RelativeLayout contentFilter;
    @Bind(R.id.applyButton)
    Button applyButton;

    String checked;
    NewsFeeds newsFeeds;
    Map<String, String> checkedVlue;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_black));
        }
        ButterKnife.bind(this);
        newsFeeds = APIGenerator.createService(NewsFeeds.class);
        checkedVlue = new HashMap<>();
        init();


    }
    public void init(){
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filter");
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.globe_ic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newsFeeds.filters("SW", new Callback<ArrayList<FilterModel>>() {
            @Override
            public void success(ArrayList<FilterModel> filterModels, Response response) {
                Log.d("filter object", filterModels.toString());
                setupViews(filterModels, 0);

                team.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a2.setText(filterModels.get(0).getValues().get(0));
                        technology.setText(filterModels.get(0).getValues().get(1));
                        operations.setText(filterModels.get(0).getValues().get(2));
                        management.setText(filterModels.get(0).getValues().get(3));
                        //setupViews(filterModels, 0);
                        a2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    checkedVlue.put(filterModels.get(0).getValues().get(0),"team");
                            }
                        });

                        technology.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    checkedVlue.put(filterModels.get(0).getValues().get(1),"team");
                            }
                        });

                        operations.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkedVlue.put(filterModels.get(0).getValues().get(2), "team");
                            }
                        });
                        management.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkedVlue.put(filterModels.get(0).getValues().get(3),"team");
                            }
                        });
                    }
                });

                tag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a2.setChecked(false);
                        technology.setChecked(false);
                        operations.setChecked(false);
                        management.setChecked(false);
                        a2.setText(filterModels.get(1).getValues().get(0));
                        technology.setText(filterModels.get(1).getValues().get(1));
                        operations.setText(filterModels.get(1).getValues().get(2));
                        management.setText(filterModels.get(1).getValues().get(3));
                        //setupViews(filterModels, 0);
                        a2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkedVlue.put(filterModels.get(1).getValues().get(0),"Topic");
                            }
                        });

                        technology.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkedVlue.put(filterModels.get(1).getValues().get(1),"Topic");
                            }
                        });

                        operations.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkedVlue.put(filterModels.get(1).getValues().get(2),"Topic");
                            }
                        });
                        management.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkedVlue.put(filterModels.get(1).getValues().get(3),"Topic");
                            }
                        });
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                CommonUtilis.showMassege(FilterActivity.this,"Something went wrong, Please try again");
            }
        });



        applyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent();
                //intent.putExtra("TAG", typeFlag);
                intent.putExtra("CHECKED", checkedVlue.toString());
                setResult(IntentExtra.RESULT_OK, intent);
                finish();
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }

    public void setupViews(ArrayList<FilterModel>  filterModels, int i){

        a2.setText(filterModels.get(0).getValues().get(0));
        technology.setText(filterModels.get(0).getValues().get(1));
        operations.setText(filterModels.get(0).getValues().get(2));
        management.setText(filterModels.get(0).getValues().get(3));
        //setupViews(filterModels, 0);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedVlue.put(filterModels.get(0).getValues().get(0),filterModels.get(0).getValues().get(0));
            }
        });

        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedVlue.put(filterModels.get(0).getValues().get(1),filterModels.get(0).getValues().get(1));
            }
        });

        operations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedVlue.put(filterModels.get(0).getValues().get(2),filterModels.get(0).getValues().get(2));
            }
        });
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedVlue.put(filterModels.get(0).getValues().get(3),filterModels.get(0).getValues().get(3));
            }
        });
    }



}
