package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cots.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import model.events;

import static android.app.Activity.RESULT_OK;

public class event_registeration extends Fragment {


    private ImageButton flex;
    private EditText eve_title;
    private EditText eve_objective;
    private EditText eve_organized;
    private EditText eve_faculty;
    private EditText eve_date;
    private Button register;

    private EditText eve_time;
    private EditText eve_duration;
    private EditText eve_venue;
    private EditText eve_traainer;
    private EditText eve_coordinator;
    private EditText eve_sub_coordinator;
    private EditText contactnumber;
    private EditText fee;
    private ProgressDialog progressDialog;
    private EditText eve_descri;
    private DatabaseReference fdb;
    private Uri mimage;
    private String durl;
    private StorageReference mstorage;
    private static final int request = 2;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request && resultCode == RESULT_OK) {
            mimage = data.getData();
            flex.setImageURI(mimage);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_registeration, container, false);
        flex = v.findViewById(R.id.imageButton3);
        eve_title = v.findViewById(R.id.event_title);
        eve_objective = v.findViewById(R.id.event_objective);
        eve_organized = v.findViewById(R.id.organizing);
        eve_faculty = v.findViewById(R.id.targetted_faculty);
        eve_date = v.findViewById(R.id.event_date);
        eve_time = v.findViewById(R.id.event_time);
        eve_duration = v.findViewById(R.id.duration);
        eve_venue = v.findViewById(R.id.place);
        eve_coordinator = v.findViewById(R.id.coordinator);
        eve_sub_coordinator = v.findViewById(R.id.subcoor);
        contactnumber = v.findViewById(R.id.eventt_contact);
        eve_traainer = v.findViewById(R.id.trainer);

        fee = v.findViewById(R.id.entry_fee);
        eve_descri = v.findViewById(R.id.desription);
        register = v.findViewById(R.id.button2);
        flex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent, request);


            }
        });

        fdb = FirebaseDatabase.getInstance().getReference("events_registeratio");
        mstorage = FirebaseStorage.getInstance().getReference("events_flex");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    upload();
                } else {
                    Toast.makeText(getContext(), "Please enter the field properly", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;


    }

    public void upload() {
        if (!TextUtils.isEmpty(eve_title.getText().toString()) && !TextUtils.isEmpty(eve_objective.getText().toString()) &&
                !TextUtils.isEmpty(eve_organized.getText().toString()) && !TextUtils.isEmpty(eve_faculty.getText().toString())
                && !TextUtils.isEmpty(eve_date.getText().toString()) && !TextUtils.isEmpty(eve_time.getText().toString())
                && !TextUtils.isEmpty(eve_duration.getText().toString()) && !TextUtils.isEmpty(eve_venue.getText().toString()) &&
                !TextUtils.isEmpty(eve_coordinator.getText().toString()) && !TextUtils.isEmpty(eve_sub_coordinator.getText().toString())
                && !TextUtils.isEmpty(eve_traainer.getText().toString()) && !TextUtils.isEmpty(fee.getText().toString()) &&
                !TextUtils.isEmpty(eve_descri.getText().toString()) && mimage != null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("REGISTERING");
            progressDialog.show();
            final StorageReference filepath = mstorage.child(eve_title.getText().toString());
            final UploadTask uploadTask = filepath.putFile(mimage);


            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> tasturl = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            durl = filepath.getDownloadUrl().toString();
                            return filepath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {

                            String rowtitle = eve_title.getText().toString();
                            events eve = new events(task.getResult().toString(), eve_title.getText().toString(), eve_objective.getText().toString(), eve_organized.getText().toString(), eve_faculty.getText().toString(), eve_date.getText().toString(), eve_time.getText().toString(), eve_duration.getText().toString(), eve_venue.getText().toString(), eve_traainer.getText().toString(), eve_coordinator.getText().toString(), eve_sub_coordinator.getText().toString(), fee.getText().toString(), eve_descri.getText().toString(), contactnumber.getText().toString());
                            fdb.child(rowtitle).setValue(eve).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    eve_title.setText("");
                                    eve_objective.setText("");
                                    eve_organized.setText("");
                                    eve_faculty.setText("");
                                    eve_date.setText("");


                                    eve_time.setText("");
                                    eve_duration.setText("");
                                    eve_venue.setText("");
                                    eve_traainer.setText("");
                                    eve_coordinator.setText("");
                                    eve_sub_coordinator.setText("");
                                    contactnumber.setText("");
                                    fee.setText("");
                                    eve_descri.setText("");


                                    Toast.makeText(getContext(), "SUCCESSFULLY REGISTRED", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(), Main3Activity.class));
                                    progressDialog.dismiss();


                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
                                }
                            });


                        }

                    });
                }
            });


        }


    }

    public boolean validate() {
        boolean valid = true;
        String even_title = eve_title.getText().toString();

        String even_objective = eve_objective.getText().toString();
        String even_organized = eve_organized.getText().toString();
        String even_faculty = eve_faculty.getText().toString();
        String even_date = eve_descri.getText().toString();


        String even_time = eve_time.getText().toString();
        String even_duration = eve_duration.getText().toString();
        String even_venue = eve_venue.getText().toString();
        String even_traainer = eve_traainer.getText().toString();
        String even_coordinator = eve_coordinator.getText().toString();
        String even_sub_coordinator = eve_sub_coordinator.getText().toString();
        String even_contactnumber = contactnumber.getText().toString();
        String even_fee = fee.getText().toString();

        if (mimage == null) {
            Toast.makeText(getContext(), "PLEASE SELECT THE IMAGE", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        if (even_title.isEmpty() || even_title.length() < 3) {
            eve_title.setError("please enter the field properly");
            valid = false;
        } else {
            eve_title.setError(null);
        }

        if (even_objective.isEmpty()) {
            eve_objective.setError("please enter the field properly");
            valid = false;
        } else {
            eve_objective.setError(null);
        }

        if (even_organized.isEmpty()) {
            valid = false;
            eve_organized.setError("please enter the field properly");
        } else {
            eve_organized.setError(null);
        }

        if (even_faculty.isEmpty()) {
            eve_faculty.setError("please enter the field properly");
            valid = false;
        } else {
            eve_faculty.setError(null);
        }

        if (even_date.isEmpty()) {
            eve_date.setError("please enter the field properly");
            valid = false;
        } else {
            eve_date.setError(null);
        }
        if (even_time.isEmpty()) {
            eve_time.setError("please enter the field properly");
            valid = false;
        } else {
            eve_time.setError(null);
        }

        if (even_venue.isEmpty()) {
            eve_venue.setError("please enter the field properly");
            valid = false;
        } else {
            eve_venue.setError(null);
        }

        if (even_traainer.isEmpty()) {
            eve_traainer.setError("please enter the field properly");
            valid = false;
        } else {
            eve_traainer.setError(null);
        }


        if (even_duration.isEmpty()) {
            eve_duration.setError("please enter the field properly");
            valid = false;
        } else {
            eve_duration.setError(null);
        }

        if (even_contactnumber.isEmpty()) {
            eve_coordinator.setError("please enter the field properly");
            valid = false;
        } else {
            eve_coordinator.setError(null);
        }

        if (even_sub_coordinator.isEmpty()) {
            eve_sub_coordinator.setError("please enter the field properly");
            valid = false;
        } else {
            eve_sub_coordinator.setError(null);
        }


        if (even_coordinator.isEmpty()) {
            eve_coordinator.setError("please enter the field properly");
            valid = false;
        } else {
            eve_coordinator.setError(null);
        }
        if (even_fee.isEmpty()) {
            fee.setError("please enter the field properly");
            valid = false;
        } else {
            fee.setError(null);
        }


        return valid;

    }

}
