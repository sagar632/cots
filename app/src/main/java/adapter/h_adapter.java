package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cots.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Post;

public class h_adapter extends RecyclerView.Adapter<h_adapter.viewholder>
{private List<model.Post> model_home_list;
private DatabaseReference idb;
private OnItemClickListener listener;

    public h_adapter(List<Post> model_home_list,OnItemClickListener listener) {
        this.model_home_list = model_home_list;
        this.listener=listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(model.Post post);
    }





    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_layoout,viewGroup,false);
           idb= FirebaseDatabase.getInstance().getReference("users_image");
                   return new viewholder(view,listener);





    }



    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {

       // String resource=model_home_list.get(i).();
        String profile_name=model_home_list.get(i).getPostname();
        String date=model_home_list.get(i).getDate();
        String time=model_home_list.get(i).getTime();
        String post_desc=model_home_list.get(i).getDescri();
        String post_image=model_home_list.get(i).getImageuri();
        String profile_image=model_home_list.get(i).getPoster_profile_image();

        viewholder.bind(model_home_list.get(i),listener);
        viewholder.setdata(/*resource,profile_name,date,*/post_desc,post_image,profile_name,date,time,profile_image);







    }

    @Override
    public int getItemCount() {
        return model_home_list.size();
    }


    public class viewholder extends RecyclerView.ViewHolder{




        public ImageView profil_image;
        private TextView profile_name;

       public TextView dateu;
        public TextView post_desc;
       public ImageView post_image;


        public viewholder(@NonNull View itemView, final OnItemClickListener listener) {

            super(itemView);
           profil_image=itemView.findViewById(R.id.profile_image);

            dateu=itemView.findViewById(R.id.date);
            profile_name=itemView.findViewById(R.id.profile_name);
            post_desc=itemView.findViewById(R.id.post_description);
            post_image=itemView.findViewById(R.id.post_image);



        }



        public void setdata(String post_descr, String post_images, String proname, String datefo, String timeo, final String profi_image){
          //  profile_image.setImageResource(pro_image);
           //profi_image.set
           dateu.setText(datefo);
            profile_name.setText(proname);
           post_desc.setText(post_descr);
            Picasso.with(itemView.getContext()).load(post_images).into(post_image);
           // Picasso.with(itemView.getContext()).load(profi_image).into(profil_image);
            idb.child(profi_image).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    model.profile_image profile_image=dataSnapshot.getValue(model.profile_image.class);
                    String profileurl=profile_image.getImageur();
                    Picasso.with(itemView.getContext()).load(profileurl).into(profil_image);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }

        private void bind(final Post post, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.OnItemClick(post);
                }
            });
        }
    }
}