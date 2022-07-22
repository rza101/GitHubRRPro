package com.rhezarijaya.githubrrpro.utils;

import com.rhezarijaya.githubrrpro.services.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtil {
    public static APIService getAPIService(){
        return new Retrofit.Builder()
                .baseUrl(Constants.GITHUB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);
    }

    public static String responseCodeFormatter(int code){
        if(code >= 500){
            return code + " - Server Error";
        }else if(code >= 400){
            return code + " - Client Error";
        }else{
            return "Unexpected Error";
        }
    }
}
