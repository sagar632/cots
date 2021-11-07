package activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.cots.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import adapter.h_adapter;
import model.Post;

public class full_image extends AppCompatActivity {
private static ImageView fullimage;

private FirebaseAuth mauth;
private ProgressBar progressBar;
private FirebaseUser muser;
private DatabaseReference db;
private ProgressDialog progressDialog;

    public static void load(Post post, Context context) {
       Picasso.with(context).load(post.getImageuri()).into(fullimage);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_full_image);
        fullimage=findViewById(R.id.imageView3);
        mauth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar2);
        muser=mauth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference("users_image");
        db.child(muser.getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    progressBar.setVisibility(View.VISIBLE);
                    for (DataSnapshot conte:dataSnapshot.getChildren())
                    {
                        model.profile_image profile_imagee=dataSnapshot.getValue(model.profile_image.class);
                        String imageurl=profile_imagee.getImageur();
                        Picasso.with(getApplicationContext()).load(imageurl).into(fullimage);
                       // progressDialog.dismiss();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
