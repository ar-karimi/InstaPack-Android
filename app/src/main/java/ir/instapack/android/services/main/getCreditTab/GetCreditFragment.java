package ir.instapack.android.services.main.getCreditTab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import ir.instapack.android.Base.BaseFragment;
import ir.instapack.android.R;
import ir.instapack.android.databinding.FragmentGetCreditBinding;


public class GetCreditFragment extends BaseFragment<FragmentGetCreditBinding> {

    private GetCreditViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(GetCreditViewModel.class);
    }

    @Override
    public void setupViews() {

        binding.setViewModel(viewModel);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_get_credit;
    }


    public static GetCreditFragment newInstance() {

        Bundle args = new Bundle();

        GetCreditFragment fragment = new GetCreditFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
