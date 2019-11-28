package com.marvel_explorer;

import android.app.Application;

public class MarvelApplication extends Application {

    String marvelPublicKey = BuildConfig.MARVEL_PUBLIC_KEY;
    String marvelPrivateKey = BuildConfig.MARVEL_PRIVATE_KEY;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
