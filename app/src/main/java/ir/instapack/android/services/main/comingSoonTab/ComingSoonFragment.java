package ir.instapack.android.services.main.comingSoonTab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import ir.instapack.android.Base.BaseFragment;
import ir.instapack.android.R;
import ir.instapack.android.databinding.FragmentComingSoonBinding;


public class ComingSoonFragment extends BaseFragment<FragmentComingSoonBinding> {

    private ComingSoonViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ComingSoonViewModel.class);
    }

    @Override
    public void setupViews() {

        binding.setViewModel(viewModel);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_coming_soon;
    }


    public static ComingSoonFragment newInstance() {

        Bundle args = new Bundle();

        ComingSoonFragment fragment = new ComingSoonFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
