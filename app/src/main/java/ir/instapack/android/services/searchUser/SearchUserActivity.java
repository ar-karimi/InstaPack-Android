package ir.instapack.android.services.searchUser;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.Model.Api.MySingleObserver;
import ir.instapack.android.Providers.APIServiceProvider;
import ir.instapack.android.R;
import ir.instapack.android.databinding.ActivitySearchUserBinding;
import ir.instapack.android.services.searchUser.adapters.SearchUserAdapter;
import ir.instapack.android.services.searchUser.dataModels.SearchUserModel;

public class SearchUserActivity extends BaseActivity {

    private ActivitySearchUserBinding binding;
    private SearchUserViewModel viewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private TextView noItemExist;
    private RecyclerView recyclerView;
    private LinearLayout searchLoadingView;
    private TextView searchResultsTitle;
    private ImageView backgroundImage;
    private ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(SearchUserViewModel.class);


        setupViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setupViews() {

        binding.setViewModel(viewModel);


        //to get page Items
        noItemExist = binding.noItemExist;
        recyclerView = binding.recyclerViewSearchUsers;
        searchLoadingView = binding.searchLoadingView;
        searchResultsTitle = binding.searchResultsTitle;
        backgroundImage = binding.backgroundImage;
        searchIcon = binding.icSearch;
        //till here


        //to close keyboard by tap outside
        binding.coordinatorLayout.setOnTouchListener((v, event) -> {
            closeKeyboard(v);
            return true;
        });

        recyclerView.setOnTouchListener((v, event) -> {
            closeKeyboard(v);
            return true;
        });
        //till here


        //to set TextWatcher to editText
        final EditText searchEditText = binding.searchEditText;

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence input, int start, int before, int count) {

                if (count == 0)
                    return;

                if (isNetworkAvailable())
                    observeSearch(input.toString().trim());
                else
                    showToast("اتصال اینترنت را بررسی کنید");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable input) {
            }
        });

        searchEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        //till here

        searchIcon.setOnClickListener(view -> searchEditText.setText(""));


    }

    private void observeSearch(String phone) {

        viewModel.loginRequest(APIServiceProvider.provideInstaApiService(), phone)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<SearchUserModel>(this) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(SearchUserModel searchUserModel) {

                        if (searchUserModel != null && !searchUserModel.getList().isEmpty()) {

                            List<SearchUserModel.ListItem.User> users = new ArrayList<>();

                            for (int i = 0; i < searchUserModel.getList().size(); i++)
                                users.add(searchUserModel.getList().get(i).getUser());

                            setAdapter(users);
                        }
                        else showToast("no result");


                    }
                });

        viewModel.getProgressBarLiveData().observe(this, shouldShow -> {

            if (shouldShow)
                searchLoadingView.setVisibility(View.VISIBLE);
            else
                searchLoadingView.setVisibility(View.GONE);
        });

    }

    private void setAdapter(List<SearchUserModel.ListItem.User> users) {

        SearchUserAdapter adapter = new SearchUserAdapter(users, user -> {
            showToast(user.getUsername());
        });

        binding.recyclerViewSearchUsers.setAdapter(adapter);

    }


    @Override
    protected void onPause() {
        super.onPause();
/*

        //to Ignore Request if is sent
        if (compositeDisposable.size() != 0) //to check it not null
            compositeDisposable.clear();

        progressBar.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);
        //till here

*/
    }


}
