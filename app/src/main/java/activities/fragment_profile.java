package activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cots.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import model.profile_image;

import static android.app.Activity.RESULT_OK;

public class fragment_profile extends Fragment {
    private TextView mname;
    private TextView mnamedetail;
    private TextView mphone;
    private TextView mphhondetail;
    private TextView email;
    private TextView emaildetail;
    private TextView rollnodetail;
   // private ImageView profile_image;
    private DatabaseReference db;
    public String fnam;
    private CircleImageView profileimage;
    public String last_name;
    private FirebaseAuth mauth;
    private FirebaseUser muser;
    private ProgressBar progressBar;
private ProgressDialog progressDialog;
private Uri imageurii;
private ProgressBar imageprogress;
private DatabaseReference ffdb;
private StorageReference mstorage;
private final int request=2;
private DatabaseReference idb;
private String durl;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request&& resultCode==RESULT_OK)
        {
            imageurii=data.getData();
            profileimage.setImageURI(imageurii);

            uploadimagetodatabase();

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.profile_info, container, false);
        mnamedetail=v.findViewById(R.id.name_detail);
        emaildetail=v.findViewById(R.id.email_detail);
       mphhondetail =v.findViewById(R.id.phone_detail);
       rollnodetail =v.findViewById(R.id.rollno_detail);
        profileimage=v.findViewById(R.id.imageView2);
        progressBar=v.findViewById(R.id.progressBar3);
        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        imageprogress=v.findViewById(R.id.progressBar4);
       // imageprogress.setVisibility(View.VISIBLE);
      // profile_image=v.findViewById(R.id.imageView2);
        ffdb=FirebaseDatabase.getInstance().getReference("users_image");

        String userid=muser.getUid();
        idb=FirebaseDatabase.getInstance().getReference("users_image");

        mstorage= FirebaseStorage.getInstance().getReference("profile_images");




        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view=getLayoutInflater().inflate(R.layout.profile_image,null);
                builder.setView(view);
                final AlertDialog alertDialog=builder.create();
                alertDialog.show();
                Button change=view.findViewById(R.id.button4);
                Button  ew=view.findViewById(R.id.button5);

                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent,request);


                    }
                });
                ew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                       /* AlertDialog.Builder builderr=new AlertDialog.Builder(getContext());
                        View vieww=getLayoutInflater().inflate(R.layout.activity_full_image,null);
                        builderr.setView(vieww);
                        final AlertDialog alertDialogg=builderr.create();
                        alertDialogg.show();*/
                       startActivity(new Intent(getActivity(),full_image.class));



                    }
                });







            }
        });


        idb.child(muser.getUid().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageprogress.setVisibility(View.VISIBLE);
                model.profile_image profile_image=dataSnapshot.getValue(model.profile_image.class);
                String prourl=profile_image.getImageur();
                Picasso.with(getContext()).load(prourl).into(profileimage);
                imageprogress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        db= FirebaseDatabase.getInstance().getReference("users");
      /*  progressDialog.setMessage("Loading");
        progressDialog.setTitle("");
        progressDialog.show();*/

        db.child(userid).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.VISIBLE);


               model.user_info userInfo =dataSnapshot.getValue(model.user_info.class);


               assert userInfo != null;
               fnam=userInfo.getFname().toUpperCase();
                last_name=userInfo.getLast_name().toUpperCase();
               String email=userInfo.getEmail().toLowerCase();
               String phoneno=userInfo.getPhone_number();
               String rollno=userInfo.getRoll_no().toUpperCase();
            //   progressDialog.dismiss();



               mnamedetail.setText(fnam+" "+last_name);
               emaildetail.setText(email);
               mphhondetail.setText(phoneno);
               rollnodetail.setText(rollno);
progressBar.setVisibility(View.INVISIBLE);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }

       });




        return v;





    }




    public void uploadimagetodatabase(){
         final StorageReference filepath = (StorageReference) mstorage.child(UUID.randomUUID().toString());

        final UploadTask uploadi;
        uploadi = filepath.putFile(imageurii);
        uploadi.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> tasturl = uploadi.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        durl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        model.profile_image profile_image = new profile_image(task.getResult().toString());
                        ffdb.child(muser.getUid().toString()).setValue(profile_image).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "SUCCESSFULLY UPLOADED", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
            }
        });
    }
}



