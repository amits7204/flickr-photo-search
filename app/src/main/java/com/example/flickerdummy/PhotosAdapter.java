package com.example.flickerdummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flickerdummy.retrofit.flickrpojo.Photo;
import com.example.flickerdummy.retrofit.flickrpojo.Root;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    Context mContext;
    Root mRoot;
    public PhotosAdapter(Context aContext, Root aRoot){
        mContext = aContext;
        mRoot = aRoot;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lLayoutInflater = LayoutInflater.from(mContext);
        View lView = lLayoutInflater.inflate(R.layout.adapter_view, parent, false);
        return new ViewHolder(lView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int lFarm = mRoot.getPhotos().getPhoto().get(position).getFarm();
        String lServer = mRoot.getPhotos().getPhoto().get(position).getServer();
        String lId = mRoot.getPhotos().getPhoto().get(position).getId();
        String lSecretId = mRoot.getPhotos().getPhoto().get(position).getSecret();
        String lImageUrl = "http://farm"+lFarm+".staticflickr.com/"+lServer+"/"+lId+"_"+lSecretId+".jpg";
        Glide.with(mContext)
                .load(lImageUrl)
                .into(holder.mImageView);
    }

    public void addData(List<Photo> aPhotoList){
        for (Photo ph : aPhotoList){
            mRoot.getPhotos().getPhoto().add(ph);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRoot.getPhotos().getPhoto().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
