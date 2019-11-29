package com.marvel_explorer.utils;

import com.marvel_explorer.BuildConfig;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple class to handle authorizing and signing requests before making calls to the Marvel Comics API
 *
 * @author koodun
 */
public class APIRequestInterceptor implements Interceptor {

    String marvelPublicKey  = BuildConfig.MARVEL_PUBLIC_KEY;
    String marvelPrivateKey = BuildConfig.MARVEL_PRIVATE_KEY;

    private static final String PARAM_TIMESTAMP = "ts";
    private static final String PARAM_APIKEY    = "apikey";
    private static final String PARAM_HASH      = "hash";


    /**
     * Modifies the request url to include the additional query parameters required by the Marvel Comics API
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = generateHash(timestamp + marvelPrivateKey + marvelPublicKey);

        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(PARAM_TIMESTAMP, timestamp)
                .addQueryParameter(PARAM_APIKEY, marvelPublicKey)
                .addQueryParameter(PARAM_HASH, hash)
                .build();

        Request newRequest = request.newBuilder()
                .url(url)
                .build();

        return chain.proceed(newRequest);
    }

    private String generateHash(String s) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(s.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte value: digest) {
                stringBuilder.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
