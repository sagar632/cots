package adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cots.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.List;

import activities.eventdetail;
import activities.fragment_contact_us;

import model.applied;
import model.events;
import model.read_article;

public class upcomming_events_adapter extends RecyclerView.Adapter<upcomming_events_adapter.viewholder> {
    private List<model.events> events_list;


    public upcomming_events_adapter(List<events> events_list) {
        this.events_list = events_list;
    }

    @NonNull
    @Override
    public upcomming_events_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_detail, viewGroup, false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull upcomming_events_adapter.viewholder viewholder, int i) {
        String imageurl = events_list.get(i).getFlex();

        String eventstitle = events_list.get(i).getEvetitle();

        String eventobjective = events_list.get(i).getEveobjective();

        String eveorganized = events_list.get(i).getEveorganized();
        String evefaculty = events_list.get(i).getEvefaculty();
        String evedate = events_list.get(i).getEvedate();
        String evetime = events_list.get(i).getEvetime();
        String eveduration = events_list.get(i).getEveduration();
        String evevenue = events_list.get(i).getEvevenue();
        String evetrainer = events_list.get(i).getEvetrainer();
        String evecoordinator = events_list.get(i).getEvecoordinator();
        String evesubcoordinator = events_list.get(i).getEvesubcoordinator();
        String fee = events_list.get(i).getFee();
        String evedescri = events_list.get(i).getEvedescri();
        String phoneno = events_list.get(i).getPhoneno();


        viewholder.setdata(imageurl, eventstitle, eventobjective, eveorganized, evedate, evetime,
                eveduration, evevenue, evetrainer, evecoordinator, evesubcoordinator, fee, evedescri, phoneno);


    }

    @Override
    public int getItemCount() {
        return events_list.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {
        private ImageView flex;


        private TextView evetitle;

        private TextView organizedy;

        private TextView evedat;

        private TextView evetim;

        private TextView eveduratio;

        private AlertDialog alertDialog;
        private TextView venue;

        private TextView trainer;

        private TextView coordi;

        private TextView subcoor;

        private TextView fee;

        private TextView contact;

        private TextView evedescri;

        public Button gister;
        private FirebaseAuth mauth;
        private FirebaseUser muser;
        private DatabaseReference evedb;
        private DatabaseReference ffdb;
        private applied applied;
        private DatabaseReference userdb;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            flex = itemView.findViewById(R.id.imageView4);
            evedb=FirebaseDatabase.getInstance().getReference("applied");
            userdb=FirebaseDatabase.getInstance().getReference("users");
            evetitle = itemView.findViewById(R.id.textView4);
            organizedy = itemView.findViewById(R.id.textView5);
            evedat = itemView.findViewById(R.id.textView6);
            evetim = itemView.findViewById(R.id.textView13);
            eveduratio = itemView.findViewById(R.id.durationvalue);
            venue = itemView.findViewById(R.id.venuevalue);
            trainer = itemView.findViewById(R.id.trainer_value);
            coordi = itemView.findViewById(R.id.coorvalue);
            subcoor = itemView.findViewById(R.id.subvalue);
            fee = itemView.findViewById(R.id.feevalue);
            contact = itemView.findViewById(R.id.contvalue);
            evedescri = itemView.findViewById(R.id.desriptionvalue);
            gister = itemView.findViewById(R.id.apply);

            mauth = FirebaseAuth.getInstance();
            muser = mauth.getCurrentUser();

            ffdb = FirebaseDatabase.getInstance().getReference("applied");

        }

        public void setdata(String imageurl,
                            final String eventstitle,
                            String eventobjective,
                            String eveorganized,
                /* String evefaculty,*/
                            String evedate,
                            String evetime,
                            String eveduration,
                            String evevenue,
                            String evetrainer,
                            String evecoordinator,
                            String evesubcoordinator,
                            String fe,
                            String evedescr,
                            String phoneno) {
            checkforthedatabase(eventstitle,muser.getEmail());
            Picasso.with(itemView.getContext()).load(imageurl).into(flex);

            evetitle.setText(eventstitle);

            organizedy.setText(eventobjective);
            evedat.setText(evedate);
            evetim.setText(evetime);
            eveduratio.setText(eveduration);

            venue.setText(evevenue);
            trainer.setText(evetrainer);
            coordi.setText(evecoordinator);
            subcoor.setText(evesubcoordinator);
            fee.setText(fe);
            contact.setText(phoneno);
            evedescri.setText(evedescr);



         /* if(x==1)
               Toast.makeText(itemView.getContext(),"ysefjf",Toast.LENGTH_SHORT).show();*/

            gister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  /*  if(gister.getText().toString().toUpperCase()=="REGISTER");
                    {
                        gettodatabase(eventstitle, muser.getEmail().toString());
                        gister.setText("REGISTERED");
                    }
                    else*/
                  /* else
                    {removefromdatabase(eventstitle);
                    gister.setText("REGISTER");}*/
                   if (gister.getText().toString().toUpperCase()=="REGISTER")
                    {
                        gettodatabase(eventstitle,muser.getEmail().toString());
                        gister.setText("REGISTERED");

                    }
                   //// else
                    else
                   {
                       removefromdatabase(eventstitle);
                       gister.setText("REGISTER");
                   }

                }
            });


        }
public void removefromdatabase(String titleevent)
{
   evedb.child(titleevent).child(muser.getUid()).removeValue();


}


        public void gettodatabase(final String titleevent, final String email) {
            userdb.child(muser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        for (DataSnapshot conte : dataSnapshot.getChildren()) {
                            model.user_info userin =dataSnapshot.getValue(model.user_info.class);

                            String fullname=userin.getFname()+" "+userin.getLast_name();
                            String rollno=userin.getRoll_no();
                            String phonenom=userin.getPhone_number();

                            model.applied applied = new applied(email,"REGISTERED",fullname,rollno,phonenom);

                            ffdb.child(titleevent).child(muser.getUid().toString()).setValue(applied).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    gister.setText("REGISTERED");



                                }
                            });


                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        public void checkforthedatabase(String titleofevent,String email) {
            ffdb.child(titleofevent).child(muser.getUid().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot cont : dataSnapshot.getChildren()) {
                        if (cont.exists()) {


                            model.applied appli = dataSnapshot.getValue(model.applied.class);
                            String getstatus = appli.getStatus();
                            gister.setText(getstatus);

                        }
                        else
                            gister.setText("REGISTER");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

}










