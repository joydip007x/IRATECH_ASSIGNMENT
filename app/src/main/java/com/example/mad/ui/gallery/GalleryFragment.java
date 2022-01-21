package com.example.mad.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mad.R;
import com.example.mad.databinding.FragmentGalleryBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextInputLayout userInput = root.findViewById(R.id.assignment2_textfield);
        final MaterialButton mButton=root.findViewById(R.id.assignment2_submit);
        final MaterialButton ResetButton=root.findViewById(R.id.assignment2_reset);

        final TextView answerOutput= root.findViewById(R.id.assignment2_result);

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                   answerOutput.setVisibility(View.INVISIBLE);
            }
        });


        mButton.setOnClickListener(view -> {

            String text = userInput.getEditText().getText().toString();
            System.out.println(text);

            if(text.equals("")){

                Toast.makeText(getActivity(), "Input a Number" , Toast.LENGTH_LONG).show();
                return;
            }
            long x= Long.parseLong(text);
            long answer= 2;
            if(x>0) answer=answer+upSum(x)+downSum(x);

            System.out.println("Answer is = "+answer);
            answerOutput.setText( String.valueOf(answer));
            answerOutput.setVisibility(View.VISIBLE);

        });

        ResetButton.setOnClickListener(view -> {

            answerOutput.setVisibility(View.INVISIBLE);
            userInput.getEditText().getText().clear();

        });
        return root;

    }
    public long upSum(long x){

        double one=1.0000000000000000000;
        long n= (long) Math.floor( (x*one )/(2L*one) );
       /// System.out.println("Upsum is = "+ (n+(2*n*(n-1))));

        return  (1L*n)+(2*n*(n-1));
    }

    public long downSum(long x){

        double one=1.0000000000000000000;
        long n= (long) Math.floor( ((x-1)*one )/(2L*one) );
        return  (7*n) + (2*n*(n-1));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}