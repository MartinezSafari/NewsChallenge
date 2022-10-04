package org.thereachtrust.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OnboadingAdapter onboadingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators= findViewById(R.id.layoutOnboardingIndicators);
        buttonAction= findViewById(R.id.buttonAction);

        setupOnboardingItems();

        ViewPager2 onboardindViewPager= findViewById(R.id.onboardingViewPager);
        onboardindViewPager.setAdapter(onboadingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardindViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });

    }
    private void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems= new ArrayList<>();

        OnboardingItem itemNewsOnline= new OnboardingItem();
        itemNewsOnline.setTitle("Get Your News Online");
        itemNewsOnline.setDescription("Get the world wide news from  your phone by just a click..." +
                "This app allow you to stay up to date with the latest news and features you want...");
        itemNewsOnline.setImage(R.drawable.img3);

        OnboardingItem itemNewsPaper= new OnboardingItem();
        itemNewsPaper.setTitle("News Paper");
        itemNewsPaper.setDescription("Get updated without moving from your confort zone" +
                "we provide up to date news with the latest versions and features you desire");
        itemNewsPaper.setImage(R.drawable.img2);

        OnboardingItem itemNewsWorld= new OnboardingItem();
        itemNewsWorld.setTitle("Global News");
        itemNewsWorld.setDescription("Let get start with our news word by clicking on the button, news.com!");
        itemNewsWorld.setImage(R.drawable.img1);

        onboardingItems.add(itemNewsOnline);
        onboardingItems.add(itemNewsPaper);
        onboardingItems.add(itemNewsWorld);

        onboadingAdapter= new OnboadingAdapter(onboardingItems);
    }
    private void setupOnboardingIndicators(){
        ImageView[] indicators= new ImageView[onboadingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
            indicators[i]= new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));

            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicator(int index){
        int childCount= layoutOnboardingIndicators.getChildCount();
        for(int i=0; i<childCount; i++){
            ImageView imageView= (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onboarding_indicator_active)
                );
            }
            else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onboarding_indicator_inactive)
                );


            }
        }
        buttonAction.setText("Skip");

    }
}