package com.example.mad.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    RV_ParentAdapter myAdapter;
    ArrayList<ParentSection> parentSectionArrayList;
    ArrayList<ChildLecture> childLectureArrayList;
    RecyclerView RVparent;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

    RVparent=root.findViewById(R.id.rvparent);

    String[] SecName= {"Section 1","Section 2"};
    parentSectionArrayList = new ArrayList<>();
    childLectureArrayList = new ArrayList<>();

    for(int i=0; i<SecName.length;i++){

        ParentSection parentSection=new ParentSection(SecName[i]);
        parentSectionArrayList.add(parentSection);



    }

    myAdapter= new RV_ParentAdapter(parentSectionArrayList,childLectureArrayList,getParentFragment());
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
    RVparent.setLayoutManager(linearLayoutManager);
    RVparent.setAdapter(myAdapter);


      return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}