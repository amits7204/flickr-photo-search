package com.example.flickerdummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.flickerdummy.retrofit.RetrofitApi;
import com.example.flickerdummy.retrofit.RetrofitClient;
import com.example.flickerdummy.retrofit.flickrpojo.Root;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SearchView mSearchView;
    RecyclerView mRecyclerView;
    private String mQuery;
    private static final String TAG = "MainActivity";
    private ProgressBar mProgressbar;
    private PhotosAdapter mPhotoAdapter;
    private boolean isLoading = true;
    private int mPastVisibleItems, mVisibleItemsCount, mTotallItemCount, mPreviousTotal = 0;
    private int mPerPage = 10;
    private int mPageNumber = 1;
    LinearLayoutManager mLlm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = findViewById(R.id.search_view);
        mRecyclerView = findViewById(R.id.flickr_recyclerview);
        mProgressbar = findViewById(R.id.progress_bar);
        getSearResult();


    }

    private void getSearResult(){
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mQuery = query;
                Log.w(TAG, "Get Query: "+query);
                getData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void getData(){
        RetrofitApi lRetrofitApi = RetrofitClient.getFlickrApi();
        Call<Root> lJson = lRetrofitApi.getResult(mQuery, mPageNumber, mPerPage);
        lJson.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, final Response<Root> response) {
                if (response.body() != null ) {
                    if (response.body().getPhotos().getTotal().equals("0")) {
                        Log.w(TAG, "Data Not found: ");
                        Toast.makeText(getApplicationContext(), "data not found",Toast.LENGTH_SHORT).show();

                    }else {
                        Log.w("MainActivity", "onResponse: " + response.body().getPhotos().getTotal());
                        Root lRoot = response.body();
                        mPhotoAdapter = new PhotosAdapter(getApplicationContext(), lRoot);
                        mLlm = new LinearLayoutManager(getApplicationContext());
                        mRecyclerView.setLayoutManager(mLlm);
                        mRecyclerView.setAdapter(mPhotoAdapter);
                        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                mVisibleItemsCount = mLlm.getChildCount();
                                mTotallItemCount = mLlm.getItemCount();
                                mPastVisibleItems = mLlm.findFirstVisibleItemPosition();

                                if (dy>0){
                                    if (isLoading){
                                        if (mTotallItemCount>mPreviousTotal){
                                            isLoading = false;
                                            mPreviousTotal = mTotallItemCount;
                                        }
                                    }

                                    if (!isLoading&&(mTotallItemCount-mVisibleItemsCount)<=(mPastVisibleItems)){
                                        mPageNumber++;
                                        fetchNextPage();
                                        isLoading = true;
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.w("MainActivity", "onFailed: "+t.getMessage());
            }
        });
    }


    private void fetchNextPage(){
        mProgressbar.setVisibility(View.VISIBLE);
        RetrofitApi lRetrofitApi = RetrofitClient.getFlickrApi();
        Call<Root> lJson = lRetrofitApi.getResult(mQuery, mPageNumber, mPerPage);
        lJson.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.body() != null) {
                    if (response.body().getPhotos().getPhoto().isEmpty()){
                        Toast.makeText(getApplicationContext(),"No More Data",Toast.LENGTH_SHORT).show();
                    }else{
                        mPhotoAdapter.addData(response.body().getPhotos().getPhoto());
                        Toast.makeText(getApplicationContext(),"Page Number: "+mPageNumber+" ",Toast.LENGTH_SHORT).show();
                    }
                }
                mProgressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.w("MainActivity", "onFailed: "+t.getMessage());
            }
        });
    }
}
