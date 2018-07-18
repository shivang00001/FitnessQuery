package com.fitnessquery.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fitnessquery.R;
import com.fitnessquery.models.fitness_data.Fitness_feed;
import com.fitnessquery.models.fitness_data.Post_images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FitnessFeedRecyclerViewAdapter extends RecyclerView.Adapter<FitnessFeedRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Fitness_feed> home_feedArrayList;
    private Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.linearLayoutOuter) LinearLayout linearLayoutOuter;
        @BindView(R.id.circleImageViewUser) CircleImageView circleImageViewUser;
        @BindView(R.id.textViewUserName) TextView textViewUserName;
        @BindView(R.id.textViewTime) TextView textViewTime;
        @BindView(R.id.textViewPostText) TextView textViewPostText;

        //images layout for images 1
        @BindView(R.id.linearLayoutSingleImageView) LinearLayout linearLayoutSingleImageView;
        @BindView(R.id.imageViewPostImageSingleImage1) ImageView imageViewPostImageSingleImage1;

        //images layout for images 2
        @BindView(R.id.linearLayoutDoubleImageView) LinearLayout linearLayoutDoubleImageView;
        @BindView(R.id.imageViewPostImageDoubleImage1) ImageView imageViewPostImageDoubleImage1;
        @BindView(R.id.imageViewPostImageDoubleImage2) ImageView imageViewPostImageDoubleImage2;

        //images layout for images 3
        @BindView(R.id.linearLayoutTripleImageView) LinearLayout linearLayoutTripleImageView;
        @BindView(R.id.imageViewPostImageTripleImage1) ImageView imageViewPostImageTripleImage1;
        @BindView(R.id.imageViewPostImageTripleImage2) ImageView imageViewPostImageTripleImage2;
        @BindView(R.id.imageViewPostImageTripleImage3) ImageView imageViewPostImageTripleImage3;



        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public FitnessFeedRecyclerViewAdapter(Context mContext, ArrayList<Fitness_feed> home_feedArrayList, Activity activity) {
        this.mContext = mContext;
        this.home_feedArrayList = home_feedArrayList;
        this.activity = activity;

    }

    //region "overrides"
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_fitness_feed_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Fitness_feed fitness_feed = home_feedArrayList.get(position);
        if (fitness_feed.getUser_image() != "" && fitness_feed.getUser_image() != null) {
            Picasso.with(mContext)
                    .load(fitness_feed.getUser_image())
                    .fit()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.loading_animation)
                    .into(holder.circleImageViewUser);
        }
        holder.textViewUserName.setText(fitness_feed.getUser_name());
        holder.textViewTime.setText(fitness_feed.getPost_time());
        holder.textViewPostText.setText(fitness_feed.getPost_text());

        if(fitness_feed.getPost_images().length>0)
        {
            ArrayList<Post_images> post_imagesArrayList = new ArrayList<>(Arrays.asList(fitness_feed.getPost_images()));

            //region "Image Size 1"
            if(fitness_feed.getPost_images().length == 1)
            {
                holder.linearLayoutSingleImageView.setVisibility(View.VISIBLE);
                if (post_imagesArrayList.get(0).getPost_image_url() != "" && post_imagesArrayList.get(0).getPost_image_url() != null) {
                    Picasso.with(mContext)
                            .load(post_imagesArrayList.get(0).getPost_image_url())
                            .fit()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                            .into(holder.imageViewPostImageSingleImage1);
                }

            }
            //endregion

            //region "Image count 2"
            if(fitness_feed.getPost_images().length == 2)
            {
                holder.linearLayoutDoubleImageView.setVisibility(View.VISIBLE);
                if (post_imagesArrayList.get(0).getPost_image_url() != "" && post_imagesArrayList.get(0).getPost_image_url() != null) {
                    Picasso.with(mContext)
                            .load(post_imagesArrayList.get(0).getPost_image_url())
                            .fit()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                            .into(holder.imageViewPostImageDoubleImage1);
                }
                if (post_imagesArrayList.get(1).getPost_image_url() != "" && post_imagesArrayList.get(1).getPost_image_url() != null) {
                    Picasso.with(mContext)
                            .load(post_imagesArrayList.get(1).getPost_image_url())
                            .fit()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                            .into(holder.imageViewPostImageDoubleImage2);
                }

            }
            //endregion

            //region "Image count 3"
            if(fitness_feed.getPost_images().length == 3)
            {
                holder.linearLayoutTripleImageView.setVisibility(View.VISIBLE);
                if (post_imagesArrayList.get(0).getPost_image_url() != "" && post_imagesArrayList.get(0).getPost_image_url() != null) {
                    Picasso.with(mContext)
                            .load(post_imagesArrayList.get(0).getPost_image_url())
                            .fit()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                            .into(holder.imageViewPostImageTripleImage1);
                }
                if (post_imagesArrayList.get(1).getPost_image_url() != "" && post_imagesArrayList.get(1).getPost_image_url() != null) {
                    Picasso.with(mContext)
                            .load(post_imagesArrayList.get(1).getPost_image_url())
                            .fit()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                            .into(holder.imageViewPostImageTripleImage2);
                }
                if (post_imagesArrayList.get(2).getPost_image_url() != "" && post_imagesArrayList.get(2).getPost_image_url() != null) {
                    Picasso.with(mContext)
                            .load(post_imagesArrayList.get(2).getPost_image_url())
                            .fit()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                            .into(holder.imageViewPostImageTripleImage3);
                }

            }
            //endregion


        }


    }

    @Override
    public int getItemCount() {
        return home_feedArrayList.size();
    }

    //endregion





}