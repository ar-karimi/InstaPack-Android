package ir.instapack.android.LoginSignUp.ValidateMobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import carbon.widget.FrameLayout;
import cn.iwgang.countdownview.CountdownView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.LoginSignUp.LoginToApp.LoginToAppActivity;
import ir.instapack.android.LoginSignUp.LoginToInsta.LoginToInstaActivity;
import ir.instapack.android.Model.Api.MySingleObserver;
import ir.instapack.android.Model.DataModels.LoginModel;
import ir.instapack.android.Model.DataModels.VerifyCodeModel;
import ir.instapack.android.Model.SharedPrefManager;
import ir.instapack.android.Providers.APIServiceProvider;
import ir.instapack.android.R;

public class ValidateMobileActivity extends BaseActivity {

    private ValidateMobileViewModel viewModel = new ValidateMobileViewModel();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable progressBarDisposable;

    public static final String KEY_PHONE = "phone";
    private String enteredPhone;

    private CountdownView countDownView;
    private Button submitButton;
    private FrameLayout resendCodeButton;
    private TextView resendCodeTitle;
    private ProgressBar submitProgressBar;
    private ProgressBar resendCodeProgressBar;
    private EditText codeEditText;
    private String enteredCode;

    private boolean responseReceived = true;
    private boolean countDownEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_mobile);


        //to get enteredPhone from LoginToAppActivity
        enteredPhone = getIntent().getStringExtra(KEY_PHONE);


//to close keyboard by tap outside
        findViewById(R.id.constraint_layout).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                closeKeyboard(v);
                return true;
            }
        });


        EditText phoneEditText = findViewById(R.id.phone_edit_text);
        phoneEditText.setText(enteredPhone);

        setupCountDownView();

        submitButton = findViewById(R.id.submit_button);
        resendCodeButton = findViewById(R.id.resend_code_button);
        resendCodeTitle = findViewById(R.id.resend_code_title);
        submitProgressBar = findViewById(R.id.submit_progress_bar);
        resendCodeProgressBar = findViewById(R.id.resend_code_progress_bar);
        codeEditText = findViewById(R.id.code_edit_text);


        submitButton.setOnClickListener(view -> {

            if (!responseReceived)   //for Block requests till Response not received
                return;

            closeKeyboard(view);

            enteredCode = codeEditText.getText().toString();

            if (!enteredCode.isEmpty()) {

                if (isNetworkAvailable())
                    observeValidateCode();
                else
                    showToast("اتصال اینترنت را بررسی کنید");
            } else
                showToast("لطفا کد تایید را وارد نمایید");

        });


        //to set TextWatcher to editText
        codeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable code) {

                if (code.toString().length() == 6)
                    submitButton.callOnClick();  //to Call submitButton OnClickListener manually
            }
        });
        //till here


        resendCodeButton.setOnClickListener(view -> {

            if (!responseReceived || !countDownEnded)  //for Block requests till Response not received
                return;

            closeKeyboard(view);

            if (isNetworkAvailable())
                observeLogin(enteredPhone);
            else
                showToast("اتصال اینترنت را بررسی کنید");


        });


        findViewById(R.id.change_phone_button).setOnClickListener(view -> {

            startActivity(new Intent(ValidateMobileActivity.this, LoginToAppActivity.class));
            finish();

        });


    }


    @Override
    protected void onPause() {
        super.onPause();

        //to Ignore Request if is sent
        if (compositeDisposable.size() != 0) //to check it not null
            compositeDisposable.clear();

        submitProgressBar.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);

        resendCodeProgressBar.setVisibility(View.GONE);
        countDownEnded = false;
        disableResendCodeButton();
        countDownView.start(2 * 60 * 1000); // Millisecond

        setResponseReceived(true);
        //till here

    }

    private void setupCountDownView() {

        countDownView = findViewById(R.id.countdown_view);
        countDownView.start(2 * 60 * 1000); // Millisecond

        countDownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                enableResendCodeButton();
                countDownEnded = true;
            }
        });
    }

    public void enableResendCodeButton() {

        resendCodeButton.setStroke(getResources().getColor(R.color.colorPrimary));
        countDownView.setVisibility(View.GONE);
        resendCodeTitle.setVisibility(View.VISIBLE);
    }

    public void disableResendCodeButton() {

        resendCodeButton.setStroke(getResources().getColor(R.color.colorPrimaryWithTransparency));
        countDownView.setVisibility(View.VISIBLE);
        resendCodeTitle.setVisibility(View.GONE);
    }


    private void setResponseReceived(boolean isReceived) {
        responseReceived = isReceived;
    }


    private void observeValidateCode() {

        //to get Android ID
        @SuppressLint("HardwareIds")
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);


        //to get OS Version
        String OsVersion = android.os.Build.VERSION.RELEASE;

        viewModel.verifyCodeRequest(APIServiceProvider.provideMainApiService(), enteredPhone, enteredCode, androidId, OsVersion)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<VerifyCodeModel>(this) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(VerifyCodeModel verifyCodeModel) {

                        viewModel.saveInSharedPref(SharedPrefManager.KEY_API_TOKEN
                                , verifyCodeModel.getData().getApiToken(), ValidateMobileActivity.this);

                        startActivity(new Intent(ValidateMobileActivity.this, LoginToInstaActivity.class));
                        finish();

                    }
                });


        progressBarDisposable = viewModel.getProgressBarVisibilitySubject().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {

                        setResponseReceived(!aBoolean);

                        submitProgressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                        submitButton.setVisibility(aBoolean ? View.INVISIBLE : View.VISIBLE);

                        if (!aBoolean)
                            compositeDisposable.remove(progressBarDisposable); //to remove last progressBarDisposable
                    }
                });

        compositeDisposable.add(progressBarDisposable);

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

                        countDownEnded = false;
                        showToast("کد تایید مجدد ارسال شد");
                        disableResendCodeButton();
                        countDownView.start(2 * 60 * 1000); // Millisecond

                    }
                });


        progressBarDisposable = viewModel.getProgressBarVisibilitySubject().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {

                        setResponseReceived(!aBoolean);

                        resendCodeProgressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                        resendCodeTitle.setVisibility(aBoolean ? View.INVISIBLE : View.VISIBLE);

                        if (!aBoolean)
                            compositeDisposable.remove(progressBarDisposable); //to remove last progressBarDisposable
                    }
                });

        compositeDisposable.add(progressBarDisposable);

    }


}
