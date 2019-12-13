package com.marvel_explorer.data.services;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.marvel_explorer.utils.APIRequestInterceptor;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple class to handle calling the Retrofit HTTP client
 *
 * @author koodun
 */
public class MarvelRetrofitService {

    private static String BASE_URL = "http://gateway.marvel.com/v1/public/";

    private static Retrofit retrofit;

    private static MarvelRetrofitService sMarvelRetrofitService;

    private APIRequestInterceptor apiRequestInterceptor;

    private Gson gson;

    @Inject
    public MarvelRetrofitService(APIRequestInterceptor apiRequestInterceptor, Gson gson) {

        apiRequestInterceptor = apiRequestInterceptor;
        gson = gson;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(apiRequestInterceptor)
                .addInterceptor(logging)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }



    public static MarvelService getMarvelService(APIRequestInterceptor interceptor, Gson gson) {
        if (sMarvelRetrofitService == null) {
            sMarvelRetrofitService = new MarvelRetrofitService(interceptor, gson);
        }
        return retrofit.create(MarvelService.class);
    }

}
