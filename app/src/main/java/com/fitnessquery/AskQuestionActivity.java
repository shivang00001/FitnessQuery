package com.fitnessquery;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitnessquery.classes.CommonMethods;
import com.fitnessquery.classes.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AskQuestionActivity extends AppCompatActivity {

    //region "Binding Controls"
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.editTextQuestion) EditText editTextQuestion;
    @BindView(R.id.buttonDone) Button buttonDone;
    @BindView(R.id.buttonCancel) Button buttonCancel;
    //endregion

    //region "Variables"
    private Context context;
    //endregion

    //region "Overrides"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        context = getApplicationContext();
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ask Question");

        buttonDoneOnClick();
        buttonCancelOnClick();
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

    //region "Custom Events"
    private void buttonDoneOnClick() {
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonMethods.isInternetConnected(context)) {
                    if (validateFormBeforeSubmit()) {
                        webserviceToSaveQuestion();
                    }
                }
            }
        });
    }

    private void buttonCancelOnClick() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AskQuestionActivity.this.finish();
            }
        });
    }
    //endregion

    //region "Custom Methods"
    private boolean validateFormBeforeSubmit() {

        boolean returnValue = true;
        if (editTextQuestion.getText().toString().trim().length() == 0) {
            editTextQuestion.setError(null);
            editTextQuestion.setError("required!");
            returnValue = false;
        }

        return returnValue;
    }
    //endregion

    //region "Webservice to submit question"
    private ProgressDialog dialogFeedback;
    private String FEED_URL_Login = Constants.hostName + "webservice_submit_question.php";

    private void webserviceToSaveQuestion() {
        dialogFeedback = new ProgressDialog(AskQuestionActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        dialogFeedback.setIndeterminate(true);
        dialogFeedback.setCancelable(false);
        dialogFeedback.setMessage("Please wait while submitting question...");
        dialogFeedback.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, FEED_URL_Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseResultFeedback(response);
                        dialogFeedback.cancel();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        dialogFeedback.cancel();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("question_text", editTextQuestion.getText().toString());
                params.put("device_unique_id", CommonMethods.getAndroidDeviceIdOfHardwareDevice(context));
                params.put("app_version", CommonMethods.getVersionNameOfApp(context));

                return params;
            }


        };
        CommonMethods.callWebserviceForResponse(stringRequest, AskQuestionActivity.this);

    }

    private void parseResultFeedback(String result) {
        try {
            JSONObject objectJson = new JSONObject(result);
            if (objectJson.has("status")) {
                if (objectJson.get("status").toString().equalsIgnoreCase("success")) {
                    editTextQuestion.setText("");
                    Toast.makeText(context, objectJson.get("message").toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, objectJson.get("message").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {

        }
    }
    //endregion
}
