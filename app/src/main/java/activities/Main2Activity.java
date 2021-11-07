package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cots.R;

public class Main2Activity extends AppCompatActivity {
private ImageButton login;
private ImageButton new_user;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        login=(ImageButton) findViewById(R.id.login);
        new_user=(ImageButton) findViewById(R.id.new_user);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Main2Activity.this,login.class));


            }
        });
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,create_account.class));


            }
        });




    }
}
