package activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cots.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.major_project_adapter;
import adapter.project_adapter;
import model.project;

public class fragment_major_projects extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference pdb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.major_projects,container,false);



        recyclerView = v.findViewById(R.id.major_project_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        final List<project> projectList=new ArrayList<>();

      /*  projectList.add(new model.project("this is my first major project"));
        projectList.add(new model.project("this is my second major project"));
        projectList.add(new model.project("this is my third majorproject"));
        projectList.add(new model.project("this is my forth major project"));*/

      pdb= FirebaseDatabase.getInstance().getReference("major_projects");
      pdb.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              if(dataSnapshot.exists()){
                  for(DataSnapshot conten:dataSnapshot.getChildren()){
                      if(conten.exists()){
                          model.project proj=dataSnapshot.getValue(model.project.class);
                          projectList.add(proj);

                      }
                  }
                  adapter.major_project_adapter pro_adapter=new major_project_adapter(projectList);
                  recyclerView.setAdapter(pro_adapter);
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });






        return v;
    }
}