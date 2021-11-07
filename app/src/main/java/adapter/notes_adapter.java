package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cots.R;

import java.util.List;

import model.notes;
import model.project;

public class notes_adapter extends RecyclerView.Adapter<notes_adapter.viewholder> {

    private final List<model.notes> projectList;
 private  final  notes_adapter.OnItemClickListener listener;
    public notes_adapter(List<model.notes> projectList,OnItemClickListener listener) {
        this.projectList = projectList;
        this.listener=listener;
    }
    public interface OnItemClickListener{
        void OnItemClick(model.notes notes);
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.res_prooject_layout,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        String heading=projectList.get(i).getFaculty_name();
        viewholder.setdata(heading);
        viewholder.bindi(projectList.get(i),listener);


    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        public Button project_heading;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            project_heading=itemView.findViewById(R.id.heading);

        }
        public  void setdata(String heading_title){
            project_heading.setText(heading_title);

        }


        public void bindi(final notes notes, final OnItemClickListener listener) {
            project_heading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("jello","hello");
                    listener.OnItemClick(notes);
                }
            });
        }
    }
}
