package com.example.kousalya.swiggy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kousalya.swiggy.R;
import com.example.kousalya.swiggy.models.NewsFeedsModel;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kousalya on 15/10/16.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.VievHolder> {

    Context mContext;
    ArrayList<NewsFeedsModel> feedsModels;
    LayoutInflater inflater;

    public NewsFeedAdapter(Context context, ArrayList<NewsFeedsModel> models) {
        this.mContext = context;
        this.feedsModels = models;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsFeedAdapter.VievHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VievHolder vievHolder = null;
        View contentView;
        contentView = inflater.inflate(R.layout.row_item_post, parent, false);
        vievHolder = new VievHolder(contentView);
        return vievHolder;
    }

    @Override
    public void onBindViewHolder(VievHolder holder, int position) {
        if(feedsModels.get(position).getPhoto().length() > 0) {
            Picasso.with(mContext)
                    .load(feedsModels.get(position).getPhoto()).fit()
                    .into(holder.postImage);
        }
        holder.header.setText(feedsModels.get(position).getPost().getType());
        holder.postText.setText(feedsModels.get(position).getPost().getText());
    }

    @Override
    public int getItemCount() {
        return feedsModels.size();
    }

    static class VievHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.postImage)
        CircularImageView postImage;
        @Bind(R.id.header)
        TextView header;
        @Bind(R.id.innerImage)
        CircularImageView innerImage;
        @Bind(R.id.cancel_id)
        TextView cancelId;
        @Bind(R.id.ok_id)
        TextView okId;
        @Bind(R.id.postText)
        TextView postText;

        public VievHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
