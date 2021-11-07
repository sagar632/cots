package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cots.R;

import java.util.List;

import model.project;

public class project_adapter extends RecyclerView.Adapter<project_adapter.viewholder> {

private List<model.project> projectList;

    public project_adapter(List<project> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.res_prooject_layout,viewGroup,false);
       return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        String heading=projectList.get(i).getProject_title();


        viewholder.setdata(heading);
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
    }
}
