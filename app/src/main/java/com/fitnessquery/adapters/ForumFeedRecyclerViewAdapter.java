package com.fitnessquery.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fitnessquery.R;
import com.fitnessquery.models.fitness_forum_data.Fitness_question_answer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ForumFeedRecyclerViewAdapter extends RecyclerView.Adapter<ForumFeedRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Fitness_question_answer> home_feedArrayList;
    private Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linearLayoutQuestionPart) LinearLayout linearLayoutQuestionPart;
        @BindView(R.id.linearLayoutAnswerPart) LinearLayout linearLayoutAnswerPart;
        @BindView(R.id.circleImageViewUser) CircleImageView circleImageViewUser;
        @BindView(R.id.textViewUserName) TextView textViewUserName;
        @BindView(R.id.textViewTime) TextView textViewTime;
        @BindView(R.id.textViewQuestionText) TextView textViewQuestionText;
        @BindView(R.id.textViewAnswerText) TextView textViewAnswerText;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ForumFeedRecyclerViewAdapter(Context mContext, ArrayList<Fitness_question_answer> fitness_question_answerArrayList, Activity activity) {
        this.mContext = mContext;
        this.home_feedArrayList = fitness_question_answerArrayList;
        this.activity = activity;

    }

    //region "overrides"
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_forum_feed_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Fitness_question_answer fitness_feed = home_feedArrayList.get(position);
        holder.textViewUserName.setText(fitness_feed.getUser_name());
        holder.textViewTime.setText(fitness_feed.getAnswer_time());
        holder.textViewAnswerText.setText("Answer: "+fitness_feed.getAnswer_text());
        holder.textViewQuestionText.setText("Question: "+fitness_feed.getQuestion_text());


    }

    @Override
    public int getItemCount() {
        return home_feedArrayList.size();
    }

    //endregion


}