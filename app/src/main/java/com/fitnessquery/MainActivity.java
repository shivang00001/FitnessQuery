package com.fitnessquery;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitnessquery.adapters.FitnessFeedRecyclerViewAdapter;
import com.fitnessquery.classes.CommonMethods;
import com.fitnessquery.classes.Constants;
import com.fitnessquery.models.fitness_data.FitnessData;
import com.fitnessquery.models.fitness_data.Fitness_feed;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //region "Binding Controls"
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerViewFitnessFeed) RecyclerView recyclerViewFitnessFeed;
    @BindView(R.id.linearLayoutAskQuestion) LinearLayout linearLayoutAskQuestion;
    @BindView(R.id.nestedScrollViewTop) NestedScrollView nestedScrollViewTop;
    //endregion

    //region "Binding Variables"
    private Context context;
    //endregion

    //region "Overrides"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fitness App");

        recyclerViewFitnessFeed.setNestedScrollingEnabled(false);
        getFitnessFeed();
        onClickLinearLayoutAskQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_forum) {
            Intent intent = new Intent(MainActivity.this,ForumActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region "Webservice to get fitness feed data"
    private String FEED_URL_Get_Data = Constants.hostName + "webservice_get_fitness_forum_feed.php";

    private void getFitnessFeed() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, FEED_URL_Get_Data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseResultFeedData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError instanceof NoConnectionError) {
                            Toast.makeText(context, "Internet not found!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Some error occurs in get data. Please try again.", Toast.LENGTH_SHORT).show();
                        }

                    }


                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("app_version", CommonMethods.getVersionNameOfApp(context));
                params.put("device_id", CommonMethods.getAndroidDeviceIdOfHardwareDevice(context));
                return params;
            }


        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);

    }

    ArrayList<Fitness_feed> fitness_feedArrayList;
    private void parseResultFeedData(String result) {
        try {

            //read response from assets
            String mJsonStringsToParse = CommonMethods.readJsonFromFile("fitness_feed.json", context);
            result = mJsonStringsToParse;
            JSONObject objectJson = new JSONObject(result);
            if (objectJson.has("status")) {
                if (objectJson.get("status").toString().equalsIgnoreCase("success")) {
                    FitnessData fitnessData = new Gson().fromJson(result, FitnessData.class);
                    fitness_feedArrayList = new ArrayList<>(Arrays.asList(fitnessData.getFitness_feed()));
                    fillRecyclerView(fitness_feedArrayList);

                } else {
                    String message = objectJson.get("message").toString();
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Some exceptions occur 1!", Toast.LENGTH_SHORT).show();

        }


    }
    //endregion

    //region "Custom Methods"
    FitnessFeedRecyclerViewAdapter fitnessFeedRecyclerViewAdapter;
    private void fillRecyclerView(ArrayList<Fitness_feed> fitness_feedArrayList) {
        fitnessFeedRecyclerViewAdapter = new FitnessFeedRecyclerViewAdapter(context, fitness_feedArrayList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerViewFitnessFeed.setLayoutManager(mLayoutManager);
        recyclerViewFitnessFeed.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFitnessFeed.setAdapter(fitnessFeedRecyclerViewAdapter);
    }


    //endregion

    //region "Custom Events"
    private void onClickLinearLayoutAskQuestion()
    {
        linearLayoutAskQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AskQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
    //endregion

}
