package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cots.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class create_account extends AppCompatActivity {


    private EditText mfirstname;
    private EditText mlastname;
    private EditText memail;
    private EditText mpassword;
    private EditText mrollno;
    private EditText phoneno;
    private Button msignup;
    private FirebaseAuth mauth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference db;
    private ImageButton passwordimage;
private String tokens;
private DatabaseReference tdb;
private Button already_account;

private DatabaseReference idb;

private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {







        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);




        mfirstname=findViewById(R.id.first_name);
        mlastname=findViewById(R.id.last_name);
        memail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mrollno=findViewById(R.id.rollno);
        phoneno=findViewById(R.id.phoneno);
        msignup=findViewById(R.id.signup);
        passwordimage=findViewById(R.id.imageButton);
        mdatabase=FirebaseDatabase.getInstance();
        tdb=FirebaseDatabase.getInstance().getReference("fm-tokens");
        already_account=findViewById(R.id.already);

        idb=FirebaseDatabase.getInstance().getReference("users_image");


        mauth=FirebaseAuth.getInstance();
       // FirebaseUser muser=mauth.getCurrentUser().getT


        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()) {
                    createnewuser();
                }
                else
                {
                    onfailure();
                }

            }
        });


        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });
            passwordimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String enter=mpassword.getText().toString();
                    mpassword.setInputType(1 );
                }
            });



        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful())
                {
                    token=task.getResult().getToken().toString();
                  //  Log.d("tikken",token);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"YOUR DEVICE HAS NOOT BEEN REGISTERED",Toast.LENGTH_SHORT).show();

            }
        });







    }
    public void onfailure()
    {
        Toast.makeText(getBaseContext(), "Please fill the fields properly", Toast.LENGTH_LONG).show();

        already_account.setEnabled(true);
    }

    private void createnewuser() {

        final String fname=mfirstname.getText().toString().trim();
        final String lname=mlastname.getText().toString().trim();
        final String email=memail.getText().toString().trim();
        final String password=mpassword.getText().toString().trim();
        final String mobile=phoneno.getText().toString().trim();
        final String tollno=mrollno.getText().toString().trim();


        if(!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname) && !TextUtils.isEmpty(email)  && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(tollno))
        {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("CREATING ACCOUNT");
            progressDialog.setTitle("SETTING TO DATABASE");
            progressDialog.show();

            mauth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    if(authResult!=null)
                    {
                        tdb.child(token).child("device").setValue("active");


                       String userid=mauth.getCurrentUser().getUid();
                        db=mdatabase.getReference("users").child(userid);

                        db.child("fname").setValue(fname);
                        db.child("last_name").setValue(lname);
                        db.child("email").setValue(email);

                        db.child("password").setValue(password);
                        db.child("roll_no").setValue(tollno);
                        db.child("phone_number").setValue(mobile);
                        idb.child(userid).child("imageur").setValue("https://firebasestorage.googleapis.com/v0/b/cots-1dff4.appspot.com/o/permanent_profile%2Falready1.png?alt=media&token=17b5ce4d-1a7d-4ea2-9e03-9048dcccc03b");




                        mfirstname.setText("");
                        mlastname.setText("");
                        memail.setText("");
                        mrollno.setText("");
                        mpassword.setText("");
                        phoneno.setText("");

                        Toast.makeText(getApplicationContext(),"ACCOUNT CREATED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(create_account.this,login.class));

                    }
                    else
                    {


                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    mfirstname.setText("");
                    mlastname.setText("");
                    memail.setText("");
                    mrollno.setText("");
                    mpassword.setText("");
                    phoneno.setText("");
                    Toast.makeText(getApplicationContext(),"PLEASE FILL THE FIELDS PROPERLY",Toast.LENGTH_SHORT).show();
                }
            });



        }







    }

    public boolean validate() {
        boolean valid = true;

        String firstname = mfirstname.getText().toString();
        String lastname = mlastname.getText().toString();
        String password = mpassword.getText().toString();
        String email=memail.getText().toString();
        String rollno=mrollno.getText().toString();
        String phone=phoneno.getText().toString();


        if (firstname.isEmpty() || firstname.length() < 3) {
            mfirstname.setError("at least 3 characters");
            valid = false;
        } else {
            mfirstname.setError(null);
        }

        if (lastname.isEmpty() || lastname.length() < 3) {
            mlastname.setError("at least 3 characters");
            valid = false;
        } else {
            mlastname.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            memail.setError("enter a valid email address");
            valid = false;
        } else {
            memail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 15) {
            mpassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mpassword.setError(null);
        }

        if (rollno.isEmpty() || rollno.length() != 12) {
            mrollno.setError("enter valid rollno");
            valid = false;
        } else {
            mrollno.setError(null);
        }


        if (phone.isEmpty() || phone.length() >10) {
            phoneno.setError("enter valid phone number");
            valid = false;
        } else {
            phoneno.setError(null);
        }

        return valid;
    }

}
