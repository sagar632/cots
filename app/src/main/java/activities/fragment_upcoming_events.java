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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.upcomming_events_adapter;
import model.events_list_model;

public class fragment_upcoming_events extends Fragment {
    private DatabaseReference fdb;


   private List<model.events> eventslis;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.upcomingevents,container,false);
        recyclerView=v.findViewById(R.id.upcoming_revycler);
        fdb= FirebaseDatabase.getInstance().getReference("events_registeratio");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        eventslis=new ArrayList<model.events>();
        fdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    for (DataSnapshot content : dataSnapshot.getChildren()) {
                        if(content.exists()) {
                            model.events eventsupcom= content.getValue(model.events.class);
                             eventslis.add(eventsupcom);

                        }
                        else
                        {
                            Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
                        }

                    }

                }else
                {
                    Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
                }
                adapter.upcomming_events_adapter adaa=new upcomming_events_adapter(eventslis);
                recyclerView.setAdapter(adaa);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return v;
               }

    }



     /*   fdb.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             if(dataSnapshot.exists()) {
                    for (DataSnapshot content : dataSnapshot.getChildren()) {
                        if (content.exists()) {
                            events_list_model eve = content.getValue(events_list_model.class);
                            eventsl.add(eve);
                        } else {
                            Toast.makeText(getContext(),"sorry", Toast.LENGTH_SHORT).show();
                        }
                    }
                    adapter.upcomming_events_adapter adara=new upcomming_events_adapter(eventsl);
                    recyclerView.setAdapter(adara);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



