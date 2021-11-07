package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cots.R;

public class article_detaiil extends AppCompatActivity {
    private TextView heading;
    private TextView content;
    private TextView pub;
    private TextView da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detaiil);
        heading=findViewById(R.id.textView12);
        content=findViewById(R.id.textView14);
        pub=findViewById(R.id.textView15);
        da=findViewById(R.id.textView11);
        Intent intent=getIntent();
        String titl=intent.getStringExtra("title").toUpperCase().trim();
        String cont=intent.getStringExtra("content").trim();
        String publish =intent.getStringExtra("publisher");
        String dat=intent.getStringExtra("date");

        heading.setText(titl);
        content.setText(cont);

        pub.setText("by "+publish.toUpperCase()+"    ");

        da.setText("  on: "+dat+"    ");
      //  finish();







    }
}
