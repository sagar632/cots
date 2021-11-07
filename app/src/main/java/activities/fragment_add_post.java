


package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cots.R;
//import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import model.Post;

import static android.app.Activity.RESULT_OK;

public class fragment_add_post extends Fragment {
private EditText mpost;
private ImageButton mimage;
private Button msubmit;
    private static final int request = 2;
    private Uri imageUri;
    private StorageReference storageReference;
private String durl;
private DatabaseReference db;
private FirebaseUser muser;
private FirebaseAuth.AuthStateListener mauth;
private ProgressDialog progressDialog;
private DatabaseReference udb;
private model.user_info user_info;
private DatabaseReference imadb;
private String fullname;
private String userid;
private String tokens;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.add_post,container,false);
        mpost=v.findViewById(R.id.editText2);
       mimage =v.findViewById(R.id.imageButton2);
        muser=FirebaseAuth.getInstance().getCurrentUser();
         userid=muser.getUid();

      // Log.d("regd token","g");

         /*FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
             @Override
             public void onComplete(@NonNull Task<InstanceIdResult> task) {
                 if(task.isSuccessful())
                 {
                     tokens=task.getResult().getToken().toString();
                     Log.d("tokkkken",tokens);
                 }
             }
         });*/
        imadb=FirebaseDatabase.getInstance().getReference("users_image");

        udb= FirebaseDatabase.getInstance().getReference("users").child(userid);
       storageReference= FirebaseStorage.getInstance().getReference("post/images");
        db= FirebaseDatabase.getInstance().getReference("post").child(mpost.getText().toString()+UUID.randomUUID().toString());



       msubmit =v.findViewById(R.id.text);
        mimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent, request);

            }
        });

        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadinttodatabase();



            }
        });


        return v;
    }



    private void uploadinttodatabase() {

        if (!TextUtils.isEmpty(mpost.getText().toString()) && !TextUtils.isEmpty(imageUri.toString())) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("uploading");
            progressDialog.show();
            final StorageReference filepath = storageReference.child(muser.getUid() + "_" + UUID.randomUUID() + imageUri.getLastPathSegment());

            final UploadTask upload=filepath.putFile(imageUri);
            upload.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> tasturl=upload.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            durl=filepath.getDownloadUrl().toString();
                            return filepath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull final Task<Uri> task) {
                            // Calendar calendar= Calendar.getInstance();
                            // String datef= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());




                            SimpleDateFormat simple=new SimpleDateFormat("HH:mm a");
                            final String time=simple.format(new Date());

                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                            final String datef=simpleDateFormat.format(new Date());
                            imadb.child(muser.getUid().toString()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    model.profile_image profile_image=dataSnapshot.getValue(model.profile_image.class);
                                   final String proimurl=profile_image.getImageur();
                                    udb.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.exists())
                                            {
                                                for(DataSnapshot conte:dataSnapshot.getChildren()){

                                                    user_info=dataSnapshot.getValue(model.user_info.class);
                                                    fullname=user_info.getFname()+" "+user_info.getLast_name();
                                                    Post po = new Post(datef,mpost.getText().toString(),task.getResult().toString(),time,fullname,muser.getUid().toString());
                                                    db.setValue(po).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                            progressDialog.dismiss();



                                                            mpost.setText("");
                                                            mimage.setImageResource(R.drawable.ic_image_black_250dp);
                                                            Toast.makeText(getContext(), "SUCCESSFULLY POSTED", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(getActivity(), Main3Activity.class));
                                                            progressDialog.dismiss();


                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(getContext(), "SORRY YOUR POST HAS NOT BEEN UPLOADED", Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();
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

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }






                    });










                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "SORRY YOUR POST HAS NOT BEEN UPLOADED", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });



        }

         /*  String link= filepath.getDownloadUrl().toString();
            String email=muser.getEmail().toLowerCase();
*/




        else {
            Toast.makeText(getContext(),"PLEASE FILL THE FIELDS PROPERLY",Toast.LENGTH_SHORT).show();

        }







    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request && resultCode ==RESULT_OK) {
            imageUri = data.getData();

            mimage.setImageURI(imageUri);



        }
    }


}

