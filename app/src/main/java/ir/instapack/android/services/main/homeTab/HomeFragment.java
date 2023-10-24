package ir.instapack.android.services.main.homeTab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import ir.instapack.android.Base.BaseFragment;
import ir.instapack.android.R;
import ir.instapack.android.databinding.FragmentHomeBinding;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void setupViews() {

        binding.setViewModel(viewModel);

        binding.cvProfile.setOnClickListener(view ->
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_searchUserActivity));

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
