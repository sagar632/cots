package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cots.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText memail;
    private EditText password;
    private Button login;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthlist;
    private FirebaseUser muser;
private TextView register;
    private Button forgot;
    private ImageButton showpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth=FirebaseAuth.getInstance();






        register=findViewById(R.id.link_signup);

        memail=findViewById(R.id.editText);
        password=findViewById(R.id.password);
        login=findViewById(R.id.button);

        forgot=findViewById(R.id.button3);
        showpass=findViewById(R.id.imageButton);
        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enter=password.getText().toString();
                password.setInputType(1 );
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),create_account.class));
                finish();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(validate()) {
                    String memailte = memail.getText().toString();
                    String mpasste = password.getText().toString();
                    login(memailte, mpasste);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please fill the fields properly",Toast.LENGTH_SHORT).show();

                }


            }

            private void login(String mail,String pass)
            {
                if(mail!=null && pass!=null)
                {final ProgressDialog progressDialog=new ProgressDialog(login.this);
                    progressDialog.setMessage("AUTHENTICATING...");
                    //progressDialog.setTitle("PLEASE WAIT");
                    progressDialog.show();

                    mauth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(
                            login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(login.this,
                                                "Successfully singned in",Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(login.this,Main3Activity.class);
                                        startActivity(intent);progressDialog.dismiss();



                                    }
                                    else
                                    {
                                        Toast.makeText(login.this,"Sorry you are not registered",Toast.LENGTH_SHORT).show();
                                        memail.setText("");
                                        password.setText("");
                                        progressDialog.dismiss();

                                    }
                                }
                            }
                    );

                }


            }


        });

    }
    public boolean validate() {
        boolean valid = true;

        String email = memail.getText().toString();
        String passwordc =password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            memail.setError("enter a valid email address");
            valid = false;
        } else {
            memail.setError(null);
        }

        if (passwordc.isEmpty() || passwordc.length() < 6 || passwordc.length() > 15) {
            password.setError("between 6 and 15 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
