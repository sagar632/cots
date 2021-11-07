package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cots.R;
import com.squareup.picasso.Picasso;

public class post_full_image extends AppCompatActivity {
private ImageView imageView;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        progressBar=findViewById(R.id.progressBar2);

        imageView=findViewById(R.id.imageView3);
        Intent intent=getIntent();
        String hello=intent.getStringExtra("image");
       // Toast.makeText(getApplicationContext(),hello,Toast.LENGTH_SHORT).show();
        Picasso.with(getApplicationContext()).load(hello).into(imageView);
        progressBar.setVisibility(View.INVISIBLE);

    }
}
