package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cots.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import adapter.major_project_adapter;
import adapter.project_adapter;
import adapter.read_articles;
import model.Publish_article;
import model.project;
import model.read_article;


public class fragment_read_articles extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference db;
    List<model.read_article> projectList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.read_articles,container,false);
        db=FirebaseDatabase.getInstance().getReference("articles");


        recyclerView = v.findViewById(R.id.read_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
         projectList=new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    for (DataSnapshot conte : dataSnapshot.getChildren()) {
                        if(conte.exists()) {
                            model.read_article read_art =conte.getValue(read_article.class);
                            projectList.add(read_art);
                        }
                        else
                        {
                            Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
                        }

                    }
                    //adapter.read_articles adar = new adapter.read_articles(projectList);
                  //  recyclerView.setAdapter(adar);
                    recyclerView.setAdapter(new read_articles(projectList, new read_articles.OnItemCLickListener() {
                        @Override
                        public void OnItemClick(read_article read_article) {
                            Toast.makeText(getContext(),read_article.getDate(),Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getContext(),article_detaiil.class);
                            intent.putExtra("title",read_article.getTitle());
                            intent.putExtra("content",read_article.getContent());
                            intent.putExtra("publisher",read_article.getPublisher());
                            intent.putExtra("date",read_article.getDate());

                            startActivity(intent);

                            // getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new fragment_profile()).commit();
                        }
                    }));
                }else
                {
                    Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        return v;
    }











    }

