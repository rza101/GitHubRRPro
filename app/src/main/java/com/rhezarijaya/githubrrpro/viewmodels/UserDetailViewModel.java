package com.rhezarijaya.githubrrpro.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rhezarijaya.githubrrpro.BuildConfig;
import com.rhezarijaya.githubrrpro.models.UserDetail;
import com.rhezarijaya.githubrrpro.utils.APIUtil;
import com.rhezarijaya.githubrrpro.utils.SingleEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailViewModel extends ViewModel {
    private final MutableLiveData<UserDetail> userDetail = new MutableLiveData<>();
    private final MutableLiveData<UserDetail> userSearchData = new MutableLiveData<>();
    private final MutableLiveData<List<UserDetail>> followersList = new MutableLiveData<>();
    private final MutableLiveData<List<UserDetail>> followingsList = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isUserDetailLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFollowersLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFollowingsLoading = new MutableLiveData<>();

    private final MutableLiveData<SingleEvent<String>> userDetailsError = new MutableLiveData<>();
    private final MutableLiveData<SingleEvent<String>> followersError = new MutableLiveData<>();
    private final MutableLiveData<SingleEvent<String>> followingsError = new MutableLiveData<>();

    public LiveData<UserDetail> getUserDetail() {
        return userDetail;
    }

    public LiveData<UserDetail> getUserSearchData() {
        return userSearchData;
    }

    public LiveData<List<UserDetail>> getFollowersList() {
        return followersList;
    }

    public LiveData<List<UserDetail>> getFollowingsList() {
        return followingsList;
    }

    public LiveData<Boolean> getIsUserDetailLoading() {
        return isUserDetailLoading;
    }

    public LiveData<Boolean> getIsFollowersLoading() {
        return isFollowersLoading;
    }

    public LiveData<Boolean> getIsFollowingsLoading() {
        return isFollowingsLoading;
    }

    public LiveData<SingleEvent<String>> getUserDetailsError() {
        return userDetailsError;
    }

    public LiveData<SingleEvent<String>> getFollowersError() {
        return followersError;
    }

    public LiveData<SingleEvent<String>> getFollowingsError() {
        return followingsError;
    }

    public void initUserSearchData(UserDetail userDetail) {
        userSearchData.setValue(userDetail);
    }

    public void fetchUserDetail(String username) {
        isUserDetailLoading.setValue(true);

        APIUtil.getAPIService()
                .getUserDetail(BuildConfig.GITHUB_PERSONAL_TOKEN, username)
                .enqueue(new Callback<UserDetail>() {
                    @Override
                    public void onResponse(@NonNull Call<UserDetail> call, @NonNull Response<UserDetail> response) {
                        isUserDetailLoading.setValue(false);

                        if (response.isSuccessful()) {
                            userDetail.setValue(response.body());
                        } else {
                            userDetailsError.setValue(new SingleEvent<>(APIUtil.responseCodeFormatter(response.code())));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserDetail> call, @NonNull Throwable t) {
                        isUserDetailLoading.setValue(false);
                        userDetailsError.setValue(new SingleEvent<>(t.getMessage()));
                    }
                });
    }

    public void fetchFollowers(String username) {
        isFollowersLoading.setValue(true);

        APIUtil.getAPIService()
                .getUserFollowers(BuildConfig.GITHUB_PERSONAL_TOKEN, username)
                .enqueue(new Callback<List<UserDetail>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserDetail>> call, @NonNull Response<List<UserDetail>> response) {
                        isFollowersLoading.setValue(false);

                        if (response.isSuccessful()) {
                            followersList.setValue(response.body());
                        } else {
                            followersError.setValue(new SingleEvent<>(APIUtil.responseCodeFormatter(response.code())));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserDetail>> call, @NonNull Throwable t) {
                        isFollowersLoading.setValue(false);
                        followersError.setValue(new SingleEvent<>(t.getMessage()));
                    }
                });
    }

    public void fetchFollowings(String username) {
        isFollowingsLoading.setValue(true);

        APIUtil.getAPIService()
                .getUserFollowing(BuildConfig.GITHUB_PERSONAL_TOKEN, username)
                .enqueue(new Callback<List<UserDetail>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserDetail>> call, @NonNull Response<List<UserDetail>> response) {
                        isFollowingsLoading.setValue(false);

                        if (response.isSuccessful()) {
                            followingsList.setValue(response.body());
                        } else {
                            followingsError.setValue(new SingleEvent<>(APIUtil.responseCodeFormatter(response.code())));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserDetail>> call, @NonNull Throwable t) {
                        isFollowingsLoading.setValue(false);
                        followingsError.setValue(new SingleEvent<>(t.getMessage()));
                    }
                });
    }
}
