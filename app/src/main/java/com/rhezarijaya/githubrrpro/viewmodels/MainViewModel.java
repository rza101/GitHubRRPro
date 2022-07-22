package com.rhezarijaya.githubrrpro.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rhezarijaya.githubrrpro.BuildConfig;
import com.rhezarijaya.githubrrpro.models.SearchResponse;
import com.rhezarijaya.githubrrpro.utils.APIUtil;
import com.rhezarijaya.githubrrpro.utils.SingleEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    // TODO boleh di declare final jika hanys sekali assign
    private MutableLiveData<SearchResponse> searchResponse = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<SingleEvent<String>> error = new MutableLiveData<>();

    public LiveData<SearchResponse> getSearchResponse() {
        return searchResponse;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<SingleEvent<String>> getError() {
        return error;
    }

    public void search(String query) {
        isLoading.setValue(true);

        APIUtil.getAPIService()
                .getSearch(BuildConfig.GITHUB_PERSONAL_TOKEN, query)
                .enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                        isLoading.setValue(false);

                        if (response.isSuccessful()) {
                            searchResponse.setValue(response.body());
                        } else {
                            error.setValue(new SingleEvent<>(APIUtil.responseCodeFormatter(response.code())));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                        isLoading.setValue(false);
                        error.setValue(new SingleEvent<>(t.getMessage()));
                    }
                });
    }
}
