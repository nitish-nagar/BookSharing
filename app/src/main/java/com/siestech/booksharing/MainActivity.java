package com.siestech.booksharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iammert.library.readablebottombar.ReadableBottomBar;
import com.siestech.booksharing.Fragment.AddFragment;
import com.siestech.booksharing.Fragment.HomeFragment;
import com.siestech.booksharing.Fragment.NotificationFragment;
import com.siestech.booksharing.Fragment.ProfileFragment;
import com.siestech.booksharing.Fragment.SearchFragment;
import com.siestech.booksharing.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new HomeFragment());
        transaction.commit();

        binding.readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch(i) {
                    case 0:
                        transaction.replace(R.id.container, new HomeFragment());
                        break;

                    case 1:
                        transaction.replace(R.id.container, new NotificationFragment());
                        break;

                    case 2:
                        transaction.replace(R.id.container, new AddFragment());
                        break;

                    case 3:
                        transaction.replace(R.id.container, new SearchFragment());
                        break;

                    case 4:
                        transaction.replace(R.id.container, new ProfileFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }
}