package com.siestech.booksharing.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siestech.booksharing.Adapter.DashboardAdapter;
import com.siestech.booksharing.Adapter.FriendAdapter;
import com.siestech.booksharing.Model.DashboardModel;
import com.siestech.booksharing.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView dashboardRV;
    ArrayList<DashboardModel> dashboardList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        dashboardRV = view.findViewById(R.id.dashboardRv);

        dashboardList = new ArrayList<>();
        dashboardList.add(new DashboardModel(R.drawable.ic_profile,R.drawable.book1,R.drawable.ic_bookmarked,"Nitish Nagar","MCA 2021-23","354"));
        dashboardList.add(new DashboardModel(R.drawable.ic_profile,R.drawable.book2,R.drawable.ic_bookmarked,"Pradeepraj Nadar","MCA 2021-23","354"));
        dashboardList.add(new DashboardModel(R.drawable.ic_profile,R.drawable.book3,R.drawable.ic_bookmarked,"Suriaraja Nadar","MCA 2021-23","354"));
        dashboardList.add(new DashboardModel(R.drawable.ic_profile,R.drawable.book4,R.drawable.ic_bookmarked,"Nitish Nagar","MCA 2021-23","354"));

        DashboardAdapter adapter = new DashboardAdapter(dashboardList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        dashboardRV.setLayoutManager(linearLayoutManager);
//        dashboardRV.setNestedScrollingEnabled(false);
        dashboardRV.setAdapter(adapter);
        return view;
    }
}