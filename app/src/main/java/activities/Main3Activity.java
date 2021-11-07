package activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import activities.MainActivity;


import com.example.cots.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private RecyclerView recyclerView;
private FirebaseAuth mauth;

private FirebaseUser muser;
private Uri imageuri;
private MenuInflater meen;
private final int request=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     /*   FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/





        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);


        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
       if(savedInstanceState==null)
        {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new home()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
      getSupportActionBar().setTitle("HOME");


        }

    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         /* if(id==R.id.imageView)
        {

          AlertDialog.Builder alert=new AlertDialog.Builder(Main3Activity.this);
          View mviewof=getLayoutInflater().inflate(R.layout.profile_image,null);
            Button change=mviewof.findViewById(R.id.button4);
            Button vieiew=mviewof.findViewById(R.id.button5);

          alert.setView(mviewof);

            AlertDialog alertDialog=alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
              alertDialog.show();




        }*/

         if(id==R.id.action_settings)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new fragment_profile()).commit();
            getSupportActionBar().setTitle("CHANGE PASSWORD");

        }
          else if(id==R.id.signout)
          {
              if(mauth!=null && muser!=null)
              {
                  mauth.signOut();
                  startActivity(new Intent(Main3Activity.this, Main2Activity.class));
                  Toast.makeText(getApplicationContext(),"SUCCESSFULLY SIGNED OUT",Toast.LENGTH_SHORT).show();
                  finish();

              }


          }

        return super.onOptionsItemSelected(item);
    }








    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_home) {
           // Handle the camera action

          getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new home()).commit();
           getSupportActionBar().setTitle("HOME");


           // startActivity(new Intent(Main3Activity.this,home.class));
       }



      else if(id==R.id.eastern)
       { Intent browser=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ioepc.edu.np/"));
           startActivity(browser);
         //  getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new eastern()).commit();
         //  getSupportActionBar().setTitle("PURWANCHAL CAMPUS");

       }




       else if(id==R.id.paschimanchal)
       { Intent browser=new Intent(Intent.ACTION_VIEW, Uri.parse("http://wrc.edu.np"));
           startActivity(browser);
        //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new paschimanchal()).commit();
          // getSupportActionBar().setTitle("PASCHIMANCHAL CAMPUS");
       }


       else if(id==R.id.event_registeration)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new event_registeration()).commit();
           getSupportActionBar().setTitle("EVENT REGISTERATION");
       }



       else if(id==R.id.view_profile)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_profile()).commit();
           getSupportActionBar().setTitle("PROFILE");
       }


       else if(id==R.id.changepassword)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_profile()).commit();
           getSupportActionBar().setTitle("CHANGE PASSWORD");
       }



       else if(id==R.id.pulchowk)
       {//startActivity(new Intent(Main3Activity.this,pulchowk.class));
         //  getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new pulchowk()).commit();
         /*  WebView webView=new WebView(this);
           setContentView(webView);
           webView.loadUrl("www.google.com");*/
           Intent browser=new Intent(Intent.ACTION_VIEW, Uri.parse("http://pcampus.edu.np"));
           startActivity(browser);
          // getSupportActionBar().setTitle("PULCHOWK CAMPUS");
       }



       else if(id==R.id.thapathali)
       { Intent browser=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tcioe.edu.np/"));
           startActivity(browser);
          // getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new thapathali()).commit();
           //getSupportActionBar().setTitle("THAPATHALI CAMPUS");
       }











       else if(id==R.id.about_us)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_about_us()).commit();
           getSupportActionBar().setTitle("ABOUT US");
       }



      else if(id==R.id.post)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_add_post()).commit();
           getSupportActionBar().setTitle("ADD POST");
       }




       else if(id==R.id.contact)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_contact_us()).commit();
           getSupportActionBar().setTitle("CONTACT US");
       }




      /* else if(id==R.id.events_organized)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_event_organized()).commit();
           getSupportActionBar().setTitle("EVENTS ORGANIZED");
       }*/




       else if(id==R.id.internship)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_internship_offers()).commit();
           getSupportActionBar().setTitle("INTERNSHIP OFFERS");
       }




       else if(id==R.id.major_projects)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_major_projects()).commit();
           getSupportActionBar().setTitle("MAJOR PROJECTS");
       }




       else if(id==R.id.minor_projects)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_minor_projects()).commit();
           getSupportActionBar().setTitle("MINOR PROJECTS");
       }




       else if(id==R.id.notes)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_notes()).commit();
           getSupportActionBar().setTitle( "NOTES");
       }




       else if(id==R.id.publish)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_publish_articles()).commit();
           getSupportActionBar().setTitle( "PUBLISH ARTICLES");
       }



       else if(id==R.id.read)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_read_articles()).commit();
           getSupportActionBar().setTitle( "READ ARTICLES");
       }



       else if(id==R.id.upcoming_events)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new fragment_upcoming_events()).commit();
           getSupportActionBar().setTitle( "UPCOMING EVENTS");

       }



       else if(id==R.id.feedback)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new fragment_feedback()).commit();
           getSupportActionBar().setTitle("FEEDBACK");

       }




       else if(id==R.id.signout)
       {
           if(mauth!=null && muser!=null)
           {
               mauth.signOut();
               startActivity(new Intent(Main3Activity.this, Main2Activity.class));
               Toast.makeText(getApplicationContext(),"SUCCESSFULLY SIGNED OUT",Toast.LENGTH_SHORT).show();
               finish();

           }


       }






















        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
