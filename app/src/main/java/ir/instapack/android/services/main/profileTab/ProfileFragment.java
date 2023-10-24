package ir.instapack.android.services.main.profileTab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import ir.instapack.android.Base.BaseFragment;
import ir.instapack.android.R;
import ir.instapack.android.databinding.FragmentProfileBinding;


public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    private ProfileViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

    @Override
    public void setupViews() {

        binding.setViewModel(viewModel);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_profile;
    }


    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
