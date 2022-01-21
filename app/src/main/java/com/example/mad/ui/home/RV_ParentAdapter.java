package com.example.mad.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mad.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class RV_ParentAdapter extends RecyclerView.Adapter<RV_ParentAdapter.ViewHolder> {


    ArrayList<ParentSection> parentSectionArrayList;
    ArrayList<ChildLecture> childLectureArrayList;
    Fragment fragment;


    public RV_ParentAdapter(ArrayList<ParentSection> parentSectionArrayList, ArrayList<ChildLecture> childLectureArrayList, Fragment fragment) {
        this.parentSectionArrayList = parentSectionArrayList;
        this.childLectureArrayList = childLectureArrayList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from( parent.getContext()).inflate(R.layout.list_section,
                parent,false);

        fragment.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

         ParentSection parentSection= parentSectionArrayList.get(position);
         holder.sectionName.setText(parentSection.Section);

        AtomicReference<RV_ChildAdapter> childAdapter= new AtomicReference<>(new RV_ChildAdapter(childLectureArrayList));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(fragment.requireActivity());
        holder.nested_RV.setLayoutManager(linearLayoutManager);
        holder.nested_RV.setAdapter(childAdapter.get());



        holder.newLecSave.setOnClickListener(view -> {
            String lecName=holder.newLecName.getText().toString();
            if(lecName.isEmpty()){

                Toast.makeText(fragment.getActivity(), "Give a Name " , Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(fragment.getActivity(), "Lecture : "+lecName +" was Added " , Toast.LENGTH_LONG).show();


            holder.newLecSave.setVisibility(View.GONE);
            holder.newLecName.setVisibility(View.GONE);

            childLectureArrayList.add(new ChildLecture(lecName));
            childAdapter.set(new RV_ChildAdapter(childLectureArrayList));
           /// System.out.println("JOY "+ childLectureArrayList.get(1).lectureid +"    "+ lecName);
            holder.nested_RV.setAdapter(childAdapter.get());


        });
    }

    @Override
    public int getItemCount() {
        return parentSectionArrayList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        TextView sectionName;
        RecyclerView nested_RV;
        EditText newLecName;
        Button newLecSave;
        ImageButton addLecture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sectionName = itemView.findViewById(R.id.sectioniD);
            nested_RV = itemView.findViewById(R.id.nested_rv);
            addLecture= itemView.findViewById(R.id.addSection);
            newLecName=itemView.findViewById(R.id.newLecture_text);
            newLecSave= itemView.findViewById(R.id.newLecture_save);

            newLecSave.setVisibility(View.GONE);
            newLecName.setVisibility(View.GONE);

            addLecture.setOnClickListener(view -> {

                newLecSave.setVisibility(View.VISIBLE);
                newLecName.setVisibility(View.VISIBLE);

            });
            newLecName.setOnFocusChangeListener((v, hasFocus) -> {
                if(!hasFocus && newLecName.getText().toString().isEmpty() ) {

                    newLecSave.setVisibility(View.GONE);
                    newLecName.setVisibility(View.GONE);
                }
            });


        }
    }

}
