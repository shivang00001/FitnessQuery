package com.fitnessquery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitnessquery.adapters.FitnessFeedRecyclerViewAdapter;
import com.fitnessquery.adapters.ForumFeedRecyclerViewAdapter;
import com.fitnessquery.classes.CommonMethods;
import com.fitnessquery.classes.Constants;
import com.fitnessquery.models.fitness_data.FitnessData;
import com.fitnessquery.models.fitness_data.Fitness_feed;
import com.fitnessquery.models.fitness_forum_data.FitnessForumData;
import com.fitnessquery.models.fitness_forum_data.Fitness_question_answer;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumActivity extends AppCompatActivity {

    //region "Binding Controls"
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerViewForum) RecyclerView recyclerViewForum;
    //endregion

    //region "Binding Variables"
    private Context context;
    //endregion

    //region "Overrides"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        context = getApplicationContext();
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forum");
        getForumFeed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region "Webservice to get home data"
    private String FEED_URL_Get_Data = Constants.hostName + "webservice_get_fitness_forum_feed.php";

    private void getForumFeed() {
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

    ArrayList<Fitness_question_answer> fitness_feedArrayList;
    private void parseResultFeedData(String result) {
        try {

            //read response from assets
            //String mJsonStringsToParse = CommonMethods.readJsonFromFile("fitness_question_answers.json", context);
            //result = mJsonStringsToParse;
            JSONObject objectJson = new JSONObject(result);
            if (objectJson.has("status")) {
                if (objectJson.get("status").toString().equalsIgnoreCase("success")) {
                    FitnessForumData fitnessData = new Gson().fromJson(result, FitnessForumData.class);
                    fitness_feedArrayList = new ArrayList<>(Arrays.asList(fitnessData.getFitness_question_answer()));
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
    ForumFeedRecyclerViewAdapter fitnessFeedRecyclerViewAdapter;
    private void fillRecyclerView(ArrayList<Fitness_question_answer> fitness_feedArrayList) {
        fitnessFeedRecyclerViewAdapter = new ForumFeedRecyclerViewAdapter(context, fitness_feedArrayList, ForumActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerViewForum.setLayoutManager(mLayoutManager);
        recyclerViewForum.setItemAnimator(new DefaultItemAnimator());
        recyclerViewForum.setAdapter(fitnessFeedRecyclerViewAdapter);
    }
    //endregion

}
