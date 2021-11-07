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
import android.widget.Toast;

import com.example.cots.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.major_project_adapter;
import adapter.notes_adapter;
import model.notes;
import model.project;

public class fragment_notes extends Fragment {
    private RecyclerView recyclerView;

    private DatabaseReference ndb;
private DatabaseReference ad;
    List<notes> projectList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.otes,container,false);
ad=FirebaseDatabase.getInstance().getReference("note");


        recyclerView = v.findViewById(R.id.notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        projectList=new ArrayList<>();


        ndb=FirebaseDatabase.getInstance().getReference("note");
       ndb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot conte :dataSnapshot.getChildren()){
                        if(conte.exists()) {
                            model.notes notesli = conte.getValue(model.notes.class);


                            projectList.add(notesli);
                        }
                    }

                }
               // adapter.notes_adapter pro_adapter =
              /*  pro_adapter.setOnItemClickListener(new notes_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                       Toast.makeText(getContext(),position,Toast.LENGTH_SHORT).show();
                    }
                });*/
               recyclerView.setAdapter(new notes_adapter(projectList, new notes_adapter.OnItemClickListener() {
                   @Override
                   public void OnItemClick(notes note) {
                       Toast.makeText(getContext(),note.getFaculty_name().toUpperCase(),Toast.LENGTH_SHORT).show();
                   }
               }));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
            }
        });


      /*  projectList.add(new model.notes("this is my second major project"));
        projectList.add(new model.notes("this is my third majorproject"));
        projectList.add(new model.notes("this is my forth major project"));*/






        return v;
    }
}
