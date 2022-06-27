package com.example.capital_solutions.ui.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.capital_solutions.R;
import com.example.capital_solutions.ui.Settings.WeekSettingsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageButton settingsButton;
    Button monthButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        settingsButton = (ImageButton) root.findViewById(R.id.settingsButton);
        monthButton = (Button) root.findViewById(R.id.monthButton);

        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new WeekSettingsFragment());
                fr.commit();
            }
        });

        monthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new MonthViewFragment());
                fr.commit();
            }
        });

        final Spinner spn = root.findViewById(R.id.weekChoice);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String text = spn.getSelectedItem().toString();
                if (text.compareTo("Week One") == 0) {
                    AnimationDrawable frameAnimation;
                    ImageView iView;
                    iView = root.findViewById(R.id.imageView);

//                            int gifResource = getResources().getIdentifier("@drawable/sad", "drawable", getPackageName());
//                            Drawable res = getResources().getDrawable(gifResource);


                    // Load the ImageView that will host the animation and
                    // set its background to our AnimationDrawable XML resource.

                    iView.setImageDrawable(getResources().getDrawable(R.drawable.sadanimation));
//                            iView.setBackgroundResource(R.drawable.animation );

                    // Get the background, which has been compiled to an AnimationDrawable object.
                    frameAnimation = (AnimationDrawable) iView.getDrawable();
//                            frameAnimation = (AnimationDrawable) iView.getBackground();

                    // Start the animation (looped playback by default).
//                            frameAnimation.setVisible(true,true);
                    frameAnimation.start();



                }
                else if (text.compareTo("Week Two") == 0) {
                    AnimationDrawable frameAnimation;
                    ImageView iView;
                    iView = root.findViewById(R.id.imageView);
                    iView.setImageDrawable(getResources().getDrawable(R.drawable.neutralanimation));
                    frameAnimation = (AnimationDrawable) iView.getDrawable();
                    frameAnimation.start();

                }
                else if (text.compareTo("Week Three") == 0) {
                    AnimationDrawable frameAnimation;
                    ImageView iView;
                    iView = root.findViewById(R.id.imageView);
                    iView.setImageDrawable(getResources().getDrawable(R.drawable.happyanimation));
                    frameAnimation = (AnimationDrawable) iView.getDrawable();
                    frameAnimation.start();
                }
                else {
                    AnimationDrawable frameAnimation;
                    ImageView iView;
                    iView = root.findViewById(R.id.imageView);
                    iView.setImageDrawable(getResources().getDrawable(R.drawable.cominganimation));
                    frameAnimation = (AnimationDrawable) iView.getDrawable();
                    frameAnimation.start();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return root;
    }
}

// GIF Sources:
// https://www.pinterest.com/pin/785385622495023750/
// https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjLzvay5qTnAhV5lXIEHVOBBykQjRx6BAgBEAQ&url=https%3A%2F%2Fgiphy.com%2Fexplore%2Fcute-transparents&psig=AOvVaw2faavao1-VMjUXHTlI-rkp&ust=1580249534550472
// https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwic--KQ5qTnAhVbgXIEHVOpAF0QjRx6BAgBEAQ&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F385198574375940174%2F&psig=AOvVaw2yZs5l3ij_oKIog1DsfPUt&ust=1580249462962884
// https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjFmpj65aTnAhVFhHIEHeDqC3UQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.famudrs.org%2Fapps%2Fpages%2Findex.jsp%3FuREC_ID%3D1545527%26type%3Dd&psig=AOvVaw3zORr4IYZ0WkUcqq2AfyLr&ust=1580249415838061