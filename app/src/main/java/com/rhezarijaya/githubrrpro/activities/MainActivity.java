package com.rhezarijaya.githubrrpro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rhezarijaya.githubrrpro.adapters.UserListAdapter;
import com.rhezarijaya.githubrrpro.databinding.ActivityMainBinding;
import com.rhezarijaya.githubrrpro.utils.Constants;
import com.rhezarijaya.githubrrpro.viewmodels.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getSearchResponse().observe(this,
                searchResponse -> userListAdapter.setData(searchResponse.getItems()));

        mainViewModel.getError().observe(this, errors -> {
            if (!errors.hasBeenDone()) {
                Toast.makeText(this, errors.getData(), Toast.LENGTH_SHORT).show();
            }
        });

        mainViewModel.isLoading().observe(this,
                isLoading -> binding.mainProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        userListAdapter = new UserListAdapter(data -> {
            Intent intent = new Intent(this, UserDetailActivity.class);
            intent.putExtra(Constants.INTENT_MAIN_TO_USER_DETAIL, data);
            startActivity(intent);
        });

        binding.mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.mainSearchView.clearFocus();
                userListAdapter.setData(new ArrayList<>());
                mainViewModel.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.mainRvSearch.setAdapter(userListAdapter);
        binding.mainRvSearch.setLayoutManager(new LinearLayoutManager(this));
    }
}