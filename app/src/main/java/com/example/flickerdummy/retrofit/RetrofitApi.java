package com.example.flickerdummy.retrofit;

import com.example.flickerdummy.retrofit.flickrpojo.Root;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {


    @GET("rest/?method=flickr.photos.search&api_key=0789c087897a586d5ebea50283746f0e&format=json&nojsoncallback=1")
    Call<Root> getResult(@Query("tags") String aTags,
                         @Query("page") int aPage,
                         @Query("per_page")int aPerPage);
}
