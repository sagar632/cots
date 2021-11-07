package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cots.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Post;
import model.Publish_article;

public class fragment_publish_articles extends Fragment {
private EditText title;
private EditText content;
private Button publish;
private model.user_info user_info;
private DatabaseReference db;
    private DatabaseReference udb;
    private String datef,time,fullname,desc ,topic,id;
private FirebaseUser muser;
 private String userid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.publish_article,container,false);
        title=v.findViewById(R.id.headingpart);
        content=v.findViewById(R.id.article);
        publish=v.findViewById(R.id.submit);
        muser= FirebaseAuth.getInstance().getCurrentUser();
        userid=muser.getUid();

        udb= FirebaseDatabase.getInstance().getReference("users").child(userid);

        db= FirebaseDatabase.getInstance().getReference("articles");

           publish.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   addarticles();

               }

           });




        return v;


    }


    public void addarticles(){



         topic=title.getText().toString();
         desc=content.getText().toString();
        if(!TextUtils.isEmpty(topic) && !TextUtils.isEmpty(desc))
        {
             id=db.push().getKey();
            SimpleDateFormat simple=new SimpleDateFormat("HH:mm a");
             time=simple.format(new Date());

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
              datef=simpleDateFormat.format(new Date());
            udb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        for(DataSnapshot conte:dataSnapshot.getChildren()){

                            user_info=dataSnapshot.getValue(model.user_info.class);
                            fullname=user_info.getFname()+" "+user_info.getLast_name();
                            model.Publish_article article=new Publish_article(id,topic,desc,fullname,datef,time);

                            db.child(topic).setValue(article).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    title.setText("");
                                    content.setText("");
                                    Toast.makeText(getContext(),"YOUR ARTICLE HAS BEEN PUBLISHED",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(),Main3Activity.class));

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),"PLEASE TRY AGAIN",Toast.LENGTH_SHORT).show();

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
        else {
            Toast.makeText(getContext(),"SORRY PLEASE FILL THE FIELDS PROPERLY",Toast.LENGTH_SHORT).show();
            title.setText("");
            content.setText("");
        }
    }

}
