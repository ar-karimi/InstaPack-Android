package ir.instapack.android.services.main.tutorialTab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import ir.instapack.android.Base.BaseFragment;
import ir.instapack.android.R;
import ir.instapack.android.databinding.FragmentTutorialBinding;


public class TutorialFragment extends BaseFragment<FragmentTutorialBinding> {

    private TutorialViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TutorialViewModel.class);
    }

    @Override
    public void setupViews() {

        binding.setViewModel(viewModel);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tutorial;
    }


    public static TutorialFragment newInstance() {

        Bundle args = new Bundle();

        TutorialFragment fragment = new TutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
