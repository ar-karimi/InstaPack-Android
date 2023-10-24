package ir.instapack.android.services.main;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.R;
import ir.instapack.android.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setupViews();

    }

    public void setupViews() {

        binding.setViewModel(viewModel);


        FloatingActionButton fabProfile = binding.fabProfile;

        int START_DELAY_LENGTH = 500;
        new Handler().postDelayed(() -> {

            fabProfile.setVisibility(View.VISIBLE);
            ViewAnimator
                    .animate(fabProfile)
                    .slideTopIn()
                    .duration(600)
                    .start();

        }, START_DELAY_LENGTH);


        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewAnimator
                        .animate(fab)
                        .zoomOut()
                        .duration(700)
                        .start();

                new Handler().postDelayed(() -> {

                    fab.setVisibility(View.INVISIBLE);

                }, 1000);

            }
        });*/


        setupBottomAppBar();

    }


    private void setupBottomAppBar() {

        ImageView icHomeTab = binding.icHomeTab;
        ImageView icTutorialTab = binding.icTutorialTab;
        ImageView icGetCreditTab = binding.icGetCreditTab;
        ImageView icComingSoonTab = binding.icComingSoonTab;
        TextView titleHomeTab = binding.titleHomeTab;
        TextView titleTutorialTab = binding.titleTutorialTab;
        TextView titleGetCreditTab = binding.titleGetCreditTab;
        TextView titleComingSoonTab = binding.titleComingSoonTab;


        //set homeTab (Start Destination) enable
        ImageViewCompat.setImageTintList(icHomeTab,
                ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
        titleHomeTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        //homeTab
        binding.homeTab.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() != R.id.homeFragment) {

                navController.navigate(R.id.action_to_homeFragment);

                //set homeTab enable
                ImageViewCompat.setImageTintList(icHomeTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
                titleHomeTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

                //set other tabs disable
                ImageViewCompat.setImageTintList(icTutorialTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleTutorialTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icGetCreditTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleGetCreditTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icComingSoonTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleComingSoonTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));

            }

        });


        //tutorialTab
        binding.tutorialTab.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() != R.id.tutorialFragment) {

                navController.navigate(R.id.action_to_tutorialFragment);

                //set tutorialTab enable
                ImageViewCompat.setImageTintList(icTutorialTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
                titleTutorialTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

                //set other tabs disable
                ImageViewCompat.setImageTintList(icHomeTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleHomeTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icGetCreditTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleGetCreditTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icComingSoonTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleComingSoonTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));

            }

        });


        //fabProfile
        binding.fabProfile.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() != R.id.profileFragment) {

                navController.navigate(R.id.action_to_profileFragment);

                //set other tabs disable
                ImageViewCompat.setImageTintList(icHomeTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleHomeTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icTutorialTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleTutorialTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icGetCreditTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleGetCreditTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icComingSoonTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleComingSoonTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));

            }

        });


        //getCreditTab
        binding.getCreditTab.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() != R.id.getCreditFragment) {

                navController.navigate(R.id.action_to_getCreditFragment);

                //set getCreditTab enable
                ImageViewCompat.setImageTintList(icGetCreditTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
                titleGetCreditTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

                //set other tabs disable
                ImageViewCompat.setImageTintList(icHomeTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleHomeTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icTutorialTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleTutorialTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icComingSoonTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleComingSoonTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));

            }

        });


        //comingSoonTab
        binding.comingSoonTab.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() != R.id.comingSoonFragment) {

                navController.navigate(R.id.action_to_comingSoonFragment);

                //set comingSoonTab enable
                ImageViewCompat.setImageTintList(icComingSoonTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
                titleComingSoonTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

                //set other tabs disable
                ImageViewCompat.setImageTintList(icHomeTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleHomeTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icTutorialTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleTutorialTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));
                ImageViewCompat.setImageTintList(icGetCreditTab,
                        ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency)));
                titleGetCreditTab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryWithTransparency));

            }

        });

    }


}
