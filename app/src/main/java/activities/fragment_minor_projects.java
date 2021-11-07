package activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cots.R;

import java.util.ArrayList;
import java.util.List;

import adapter.project_adapter;

public class fragment_minor_projects extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.minorprojects, container, false);
        recyclerView = v.findViewById(R.id.minor_project_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));


        List<model.project> projectList=new ArrayList<>();

        projectList.add(new model.project("this is my first project"));
        projectList.add(new model.project("this is my second project"));
        projectList.add(new model.project("this is my third project"));
        projectList.add(new model.project("this is my forth project"));




        adapter.project_adapter pro_adapter=new project_adapter(projectList);
        recyclerView.setAdapter(pro_adapter);






        return v;


    }
}
