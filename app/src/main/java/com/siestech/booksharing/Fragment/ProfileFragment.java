package com.siestech.booksharing.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.siestech.booksharing.Adapter.FriendAdapter;
import com.siestech.booksharing.LoginActivity;
import com.siestech.booksharing.MainActivity;
import com.siestech.booksharing.Model.FriendModel;
import com.siestech.booksharing.Model.User;
import com.siestech.booksharing.R;
import com.siestech.booksharing.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FriendModel> list;
    FragmentProfileBinding binding;
    ImageView profile_image;
    Uri uri;
    FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase database;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        database = FirebaseDatabase.getInstance();
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        database.getReference().child("Users").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    Picasso.get().load(user.getImage()).placeholder(R.drawable.ic_profile).into(profile_image);
                    binding.userName.setText(user.getName());
                    binding.profession.setText(user.getProfession());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView = view.findViewById(R.id.friendRV);

        list = new ArrayList<>();
        list.add(new FriendModel(R.drawable.ic_profile));
        list.add(new FriendModel(R.drawable.ic_profile));
        list.add(new FriendModel(R.drawable.ic_profile));
        list.add(new FriendModel(R.drawable.ic_profile));
        list.add(new FriendModel(R.drawable.ic_profile));
        list.add(new FriendModel(R.drawable.ic_profile));
        list.add(new FriendModel(R.drawable.ic_profile));

        FriendAdapter adapter = new FriendAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        profile_image = view.findViewById(R.id.profile_image);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uri = data.getData();
            profile_image.setImageURI(uri);

            final StorageReference reference = storageReference.child("images/")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //Get a url to the uploaded content
                            Toast.makeText(getContext(), "Cover photo saved", Toast.LENGTH_SHORT).show();
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    database.getReference().child("Users").child(mAuth.getUid()).child("image").setValue(uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Handle unsuccessful photos
                            Toast.makeText(getContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

    public void logout(View view) {
//            mAuth.signOut();
//            Intent intent = new Intent(getActivity(), LoginActivity.class);
//            startActivity(intent);
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(getActivity(),
                LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}