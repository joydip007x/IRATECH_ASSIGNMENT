package com.example.mad.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;

import java.util.ArrayList;

public class RV_ChildAdapter extends RecyclerView.Adapter<RV_ChildAdapter.ViewHolder> {


    ArrayList<ChildLecture> childLectureArrayList;

    public RV_ChildAdapter(ArrayList<ChildLecture> childLectureArrayList) {

        this.childLectureArrayList = childLectureArrayList;
    }

    @NonNull
    @Override
    public RV_ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lecture,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RV_ChildAdapter.ViewHolder holder, int position) {

        ChildLecture childLecture=childLectureArrayList.get(position);

        System.out.println(" JOY CHILDADAPTER -> "+ childLecture.lectureid);
        holder.lectureName.setText(childLecture.lectureid);

    }
    @Override
    public int getItemCount() {
        return childLectureArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView lectureName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lectureName= itemView.findViewById(R.id.lectureiD);
        }
    }
}
