package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cots.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthlist;
    private FirebaseUser muser;


    ProgressDialog mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       timer=new Timer();
        mauth=FirebaseAuth.getInstance();




        mauthlist=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                muser=firebaseAuth.getCurrentUser();
                if(muser!=null) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this, Main3Activity.class));

                        }
                    }, 0000);
                }
                   // Toast.makeText(getApplicationContext(),"ALREADY LOG IN",Toast.LENGTH_SHORT).show();



                else
                {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startActivity(new Intent(MainActivity.this,Main2Activity.class));

                        }
                    },2500);
                    Toast.makeText(getApplicationContext(),"PLEASE LOG IN",Toast.LENGTH_SHORT).show();

                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mauthlist);
    }


    @Override
    protected void onStop() {
        super.onStop();

        mauth.removeAuthStateListener(mauthlist);

    }
}
