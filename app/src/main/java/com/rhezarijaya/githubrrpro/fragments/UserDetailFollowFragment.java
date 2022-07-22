package com.rhezarijaya.githubrrpro.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rhezarijaya.githubrrpro.activities.UserDetailActivity;
import com.rhezarijaya.githubrrpro.adapters.UserListAdapter;
import com.rhezarijaya.githubrrpro.databinding.FragmentUserDetailFollowBinding;
import com.rhezarijaya.githubrrpro.utils.Constants;
import com.rhezarijaya.githubrrpro.viewmodels.UserDetailViewModel;

public class UserDetailFollowFragment extends Fragment {

    public static final String USER_DETAIL_FOLLOW_TYPE = "USER_DETAIL_FOLLOW_TYPE";
    public static final int USER_DETAIL_FOLLOWERS_FRAGMENT = 0;
    public static final int USER_DETAIL_FOLLOWING_FRAGMENT = 1;

    private FragmentUserDetailFollowBinding binding;

    private int fragmentType;
    private UserListAdapter userListAdapter;
    private UserDetailViewModel userDetailViewModel;

    public UserDetailFollowFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserDetailFollowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            int fragmentType = getArguments().getInt(USER_DETAIL_FOLLOW_TYPE);

            if (fragmentType == USER_DETAIL_FOLLOWERS_FRAGMENT ||
                    fragmentType == USER_DETAIL_FOLLOWING_FRAGMENT) {
                this.fragmentType = fragmentType;

                userDetailViewModel = new ViewModelProvider(requireActivity()).get(UserDetailViewModel.class);

                userListAdapter = new UserListAdapter(data -> {
                    Intent intent = new Intent(requireActivity(), UserDetailActivity.class);
                    intent.putExtra(Constants.INTENT_MAIN_TO_USER_DETAIL, data);
                    startActivity(intent);
                });

                // followers
                if (this.fragmentType == USER_DETAIL_FOLLOWERS_FRAGMENT) {
                    userDetailViewModel.getFollowersList().observe(requireActivity(),
                            userDetails -> userListAdapter.setData(userDetails));
                    userDetailViewModel.getFollowersError().observe(requireActivity(), error -> {
                        if (!error.hasBeenDone()) {
                            Toast.makeText(requireActivity(), error.getData(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    userDetailViewModel.getIsFollowersLoading().observe(requireActivity(),
                            isLoading -> binding.fragmentUserDetailFollowProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
                }

                // followings
                if (this.fragmentType == USER_DETAIL_FOLLOWING_FRAGMENT) {
                    userDetailViewModel.getFollowingsList().observe(requireActivity(),
                            userDetails -> userListAdapter.setData(userDetails));
                    userDetailViewModel.getFollowingsError().observe(requireActivity(), error -> {
                        if (!error.hasBeenDone()) {
                            Toast.makeText(requireActivity(), error.getData(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    userDetailViewModel.getIsFollowingsLoading().observe(requireActivity(),
                            isLoading -> binding.fragmentUserDetailFollowProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
                }

                binding.fragmentUserDetailFollowRv.setAdapter(userListAdapter);
                binding.fragmentUserDetailFollowRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
            }
        }
    }
}