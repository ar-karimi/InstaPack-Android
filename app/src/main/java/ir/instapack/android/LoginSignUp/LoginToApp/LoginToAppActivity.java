package ir.instapack.android.LoginSignUp.LoginToApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.LoginSignUp.ValidateMobile.ValidateMobileActivity;
import ir.instapack.android.Model.Api.MySingleObserver;
import ir.instapack.android.Model.DataModels.LoginModel;
import ir.instapack.android.Providers.APIServiceProvider;
import ir.instapack.android.R;

public class LoginToAppActivity extends BaseActivity {

    private LoginToAppViewModel viewModel = new LoginToAppViewModel();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable progressBarDisposable;

    private Button submitButton;
    private ProgressBar progressBar;
    private EditText phoneEditText;
    private String enteredPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_app);


        submitButton = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.progress_bar);
        phoneEditText = findViewById(R.id.phone_edit_text);


        submitButton.setOnClickListener(view -> {

            closeKeyboard(view);

            enteredPhone = phoneEditText.getText().toString();

            //Regex Checking
            if (mobileRegexChecking(enteredPhone)) {

                if (isNetworkAvailable())
                    observeLogin(enteredPhone);
                else
                    showToast("اتصال اینترنت را بررسی کنید");
            }

        });


//to open keyboard automatically
        phoneEditText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

//to close keyboard by tap outside
        findViewById(R.id.constraint_layout).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                closeKeyboard(v);
                return true;
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

        //to Ignore Request if is sent
        if (compositeDisposable.size() != 0) //to check it not null
            compositeDisposable.clear();

        progressBar.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);
        //till here

    }

    private boolean mobileRegexChecking(String phone) {

        if (phone.length() == 0) {
            showToast("لطفا شماره موبایل را وارد کنید");
            return false;
        }

        if (!Pattern.matches("^09[0-9]{9}$", phone)) {
            showToast("لطفا شماره موبایل را به صورت صحیح وارد کنید");
            return false;
        }
        return true;

    }

    private void observeLogin(String phone) {

        viewModel.loginRequest(APIServiceProvider.provideMainApiService(), phone)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<LoginModel>(this) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(LoginModel loginModel) {

                        startActivity(new Intent(LoginToAppActivity.this, ValidateMobileActivity.class)
                                .putExtra(ValidateMobileActivity.KEY_PHONE, enteredPhone));
                        finish();

                    }
                });


        progressBarDisposable = viewModel.getProgressBarVisibilitySubject().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {

                        progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                        submitButton.setVisibility(aBoolean ? View.INVISIBLE : View.VISIBLE);

                        if (!aBoolean)
                            compositeDisposable.remove(progressBarDisposable); //to remove last progressBarDisposable

                    }
                });

        compositeDisposable.add(progressBarDisposable);

    }


}
