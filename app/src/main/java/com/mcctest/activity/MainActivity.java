package com.mcctest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mcctest.R;
import com.mcctest.adapter.ImageAdapter;
import com.mcctest.serverclient.RestCallback;
import com.mcctest.serverclient.RestClientBuilder;
import com.mcctest.serverclient.request.ImageRequest;
import com.mcctest.serverclient.response.ErrorResponse;
import com.mcctest.serverclient.response.ImageDetail;
import com.mcctest.serverclient.response.ImageResponse;
import com.mcctest.serverclient.serviceInterface.ImageService;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    ArrayList<ImageDetail> imageDetailList;
    ImageAdapter imageAdapter;


    RecyclerView imageRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this.context;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageRecycleView=(RecyclerView)findViewById(R.id.recycler_view);
        getImageList();
    }

    private void getImageList(){
        ImageService service = RestClientBuilder.createService(context,ImageService.class);
        service.getImage(new ImageRequest("9","35")).enqueue(new RestCallback<ImageResponse>() {
            @Override
            public void onSuccess(ImageResponse response) {
                imageDetailList=(ArrayList<ImageDetail>)removeNullIMG(response.getContentfilelist());

                imageRecycleView.setLayoutManager(new GridLayoutManager(context, 2));
                imageAdapter = new ImageAdapter(context, imageDetailList, true);
                imageRecycleView.setAdapter(imageAdapter);
                imageAdapter.SetOnFingerClickListener(new ImageAdapter.OnFingerClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        Log.d("Position",String.valueOf(position));
                        Intent intent = new Intent(getBaseContext(), ImageDetailActivity.class);
                        intent.putExtra("IMAGE_URL", imageDetailList.get(position).getIMG());
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onError(ErrorResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<ImageDetail> removeNullIMG(List<ImageDetail> imageDetails){
        List<ImageDetail> withoutNullImage =new ArrayList<ImageDetail>();
        for(ImageDetail imageDetail:imageDetails){
            if(imageDetail.getIMG()!=null&&imageDetail.getIMG().isEmpty()==false){
                withoutNullImage.add(imageDetail);
            }
        }
        return withoutNullImage;
    }
}
