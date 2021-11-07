package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cots.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.h_adapter;
import model.Post;


public class home extends Fragment  {

private RecyclerView recyclerView;
private DatabaseReference db;
private ProgressDialog progressDialog;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View  v=  inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView= v.findViewById(R.id.upcoming_revycler);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getBaseContext()));
        db= FirebaseDatabase.getInstance().getReference("post");


        final List<model.Post>homelist =new ArrayList<>();
       //homelist.add(new model.home_mosel(/*R.drawable.already1,"sagar aryal","20190516",*/"this is my first post","https://firebasestorage.googleapis.com/v0/b/cots-1dff4.appspot.com/o/post%2Fimages%2FGTGh5ziNvTWvLfh3FzEXnH2v8yh1_bc433011-7ec8-4344-aab2-a87d7dba99cbimage%3A42?alt=media&token=06622185-2f74-4d96-a127-8758d42f7882"));
       // homelist.add(new model.home_mosel(/*R.drawable.already1,"sagar aryal","20190516",*/"this is my first post","https://firebasestorage.googleapis.com/v0/b/cots-1dff4.appspot.com/o/post%2Fimages%2FGTGh5ziNvTWvLfh3FzEXnH2v8yh1_bc433011-7ec8-4344-aab2-a87d7dba99cbimage%3A42?alt=media&token=06622185-2f74-4d96-a127-8758d42f7882"));
       //homelist.add(new model.home_mosel(/*R.drawable.already1,"sagar aryal","20190516",*/"this is my first post","https://firebasestorage.googleapis.com/v0/b/cots-1dff4.appspot.com/o/post%2Fimages%2FGTGh5ziNvTWvLfh3FzEXnH2v8yh1_bc433011-7ec8-4344-aab2-a87d7dba99cbimage%3A42?alt=media&token=06622185-2f74-4d96-a127-8758d42f7882"));

       // homelist.add(new model.home_mosel(/*R.drawable.already1,"sagar aryal","20190516",*/"this is my first post","https://firebasestorage.googleapis.com/v0/b/cots-1dff4.appspot.com/o/post%2Fimages%2FGTGh5ziNvTWvLfh3FzEXnH2v8yh1_bc433011-7ec8-4344-aab2-a87d7dba99cbimage%3A42?alt=media&token=06622185-2f74-4d96-a127-8758d42f7882"));


       /*db.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot content : dataSnapshot.getChildren()) {
                  if (content.exists()) {
                      model.home_mosel read_art = content.getValue(model.home_mosel.class);
                      homelist.add(read_art);
                  }
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
              Toast.makeText(getContext(),"SORRY",Toast.LENGTH_SHORT).show();
          }
      });*/
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("");
        progressDialog.setMessage("Loading");
        progressDialog.show();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    for (DataSnapshot content : dataSnapshot.getChildren()) {
                        if(content.exists()) {
                            model.Post read_art= content.getValue(model.Post.class);
                            homelist.add(read_art);

                          //  Toast.makeText(getContext(),"ok",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
                        }

                    }
                   // adapter.h_adapter adar = new adapter.h_adapter(homelist);
                    recyclerView.setAdapter(new h_adapter(homelist, new h_adapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(Post post) {
                            Toast.makeText(getContext(),post.getTime(),Toast.LENGTH_SHORT).show();
                         // LayoutInflater  mInflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                           /* Intent intent=new Intent(getContext(),post_full_image.class);
                            intent.putExtra("imageuri",post.getImageuri());
                            startActivity(intent);*/

                            startActivity(new Intent(getContext(),post_full_image.class).putExtra("image", post.getImageuri()));
                          //  full_image.load(post,getContext());
                        }


                    }));
                    progressDialog.dismiss();


                }else
                {
                    Toast.makeText(getContext(),"sorry",Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




















































        /*h_adapter  adapter=new h_adapter(homelist);
        recyclerView.setAdapter(adapter);
*/











        return v;


    }



}
