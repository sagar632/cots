package activities;

import android.content.Intent;
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
import android.widget.Toast;

import activities.MainActivity;
import model.feedback;

import com.example.cots.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fragment_feedback extends Fragment {

    private EditText mfeedback;
    private Button msubmit;
    private DatabaseReference db;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.feedback,container,false);

        mfeedback=v.findViewById(R.id.feedback);
            db= FirebaseDatabase.getInstance().getReference("feedback");
        msubmit=v.findViewById(R.id.submit);
        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addfeedback();

            }
        });








        return v;
    }


    public void addfeedback()
    {
        String feddback=mfeedback.getText().toString();
        if(!TextUtils.isEmpty(feddback))
        {

            String id=db.push().getKey();


            final model.feedback feedback=new feedback(id,feddback);
            db.child(id).setValue(feedback).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mfeedback.setText("");
                    Toast.makeText(getContext(),"THANK YOU FOR OUR VALUABLE FEEDBACK",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getActivity(),Main3Activity.class));

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"SORRY PLEASE TRY AGAIN",Toast.LENGTH_SHORT).show();
                    mfeedback.setText("");
                }
            });




        }
        else {
            Toast.makeText(getContext(),"SORRY PLEASE FILL THE FIELDS PROPERLY",Toast.LENGTH_SHORT).show();
           mfeedback .setText("");

        }


    }
}
