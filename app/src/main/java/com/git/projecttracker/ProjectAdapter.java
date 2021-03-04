package com.git.projecttracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Anurag on 4/3/21.
 */
class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {


    ArrayList<ProjectModel> projectList;

    public ProjectAdapter(ArrayList<ProjectModel> list) {
        projectList = list;
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        holder.txtPName.setText(projectList.get(position).getProjectName());
        holder.txtPdesc.setText(projectList.get(position).getProjectDesc());
        holder.txtPdate.setText(projectList.get(position).getProjectdate());

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPName, txtPdesc, txtPdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPName = itemView.findViewById(R.id.txtProjectName);
            txtPdesc = itemView.findViewById(R.id.txtProjectDesc);
            txtPdate = itemView.findViewById(R.id.txtProjetDate);

        }
    }
}
