package com.siestech.booksharing.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siestech.booksharing.Adapter.NotificationAdapter;
import com.siestech.booksharing.Model.NotificationModel;
import com.siestech.booksharing.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationModel> list;

    public NotificationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.notification2RV);

        list = new ArrayList<>();
        list.add(new NotificationModel(R.drawable.ic_profile,"<b>Nitish Nagar mentioned you in a comment</b>","just now"));
        list.add(new NotificationModel(R.drawable.ic_profile,"<b>Pradeepraj Nadar mentioned you in a comment</b>","just now"));
        list.add(new NotificationModel(R.drawable.ic_profile,"<b>Suriaraja Nadar mentioned you in a comment</b>","just now"));

        NotificationAdapter adapter = new NotificationAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}