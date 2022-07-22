package com.rhezarijaya.githubrrpro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayoutMediator;
import com.rhezarijaya.githubrrpro.adapters.UserDetailFragmentAdapter;
import com.rhezarijaya.githubrrpro.databinding.ActivityUserDetailBinding;
import com.rhezarijaya.githubrrpro.models.UserDetail;
import com.rhezarijaya.githubrrpro.utils.Constants;
import com.rhezarijaya.githubrrpro.viewmodels.UserDetailViewModel;

public class UserDetailActivity extends AppCompatActivity {

    private final String[] pageTitles = {
            "User Info",
            "Followers",
            "Followings"
    };

    private ActivityUserDetailBinding binding;
    private UserDetailFragmentAdapter userDetailFragmentAdapter;

    private UserDetail userDetail;
    private UserDetailViewModel userDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getParcelableExtra(Constants.INTENT_MAIN_TO_USER_DETAIL) == null) {
            Toast.makeText(this, "Illegal Access!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            userDetail = getIntent().getParcelableExtra(Constants.INTENT_MAIN_TO_USER_DETAIL);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("@" + userDetail.getLogin());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        userDetailViewModel = new ViewModelProvider(this).get(UserDetailViewModel.class);
        userDetailViewModel.initUserSearchData(userDetail);
        userDetailViewModel.fetchUserDetail(userDetail.getLogin());
        userDetailViewModel.fetchFollowers(userDetail.getLogin());
        userDetailViewModel.fetchFollowings(userDetail.getLogin());

        userDetailFragmentAdapter = new UserDetailFragmentAdapter(this);

        binding.userDetailViewPager.setAdapter(userDetailFragmentAdapter);
        new TabLayoutMediator(
                binding.userDetailTabLayout,
                binding.userDetailViewPager,
                (tab, position) -> tab.setText(pageTitles[position])
        ).attach();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}