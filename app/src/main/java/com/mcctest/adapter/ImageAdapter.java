package com.mcctest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcctest.R;
import com.mcctest.serverclient.response.ImageDetail;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by sanjoy on 8/29/18.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    OnFingerClickListener mClickListener;
    private final ArrayList<ImageDetail> imageDetails;
    Context context;
    boolean isFPRequired = false;
    public int selectedPosition = -1;

    public ImageAdapter(Context context, ArrayList<ImageDetail> items, boolean isFPRequired) {
        this.imageDetails = items;
        this.context = context;
        this.isFPRequired = isFPRequired;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final ImageDetail imageDetail = imageDetails.get(position);
        if(imageDetail.getIMG()!=null) {
            //holder.imageView.setImageURI(Uri.parse(imageDetail.getIMG()));
           // new DownloadImageTask(holder.imageView).execute(imageDetail.getIMG());
            Picasso.get().load(imageDetail.getIMG()).into(holder.imageView);
        }
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mClickListener != null) {
//                    mClickListener.onItemClick(view, position);
//                }
//            }
//        });
    }

    //private Bitmap getBitMapByString(String encodedImage){
       // byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        //return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    //}



    @Override
    public int getItemCount() {
        return imageDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnFingerClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnFingerClickListener(
            final OnFingerClickListener mItemClickListener) {
        this.mClickListener = mItemClickListener;
    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
}
