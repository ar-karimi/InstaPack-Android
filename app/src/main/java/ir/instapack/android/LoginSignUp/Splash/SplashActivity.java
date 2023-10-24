package ir.instapack.android.LoginSignUp.Splash;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.LoginSignUp.Intro.IntroActivity;
import ir.instapack.android.LoginSignUp.LoginToInsta.LoginToInstaActivity;
import ir.instapack.android.Model.Api.MySingleObserver;
import ir.instapack.android.Model.DataModels.CheckValidTokenModel;
import ir.instapack.android.Model.DataModels.CheckVersionModel;
import ir.instapack.android.Model.SharedPrefManager;
import ir.instapack.android.Providers.APIServiceProvider;
import ir.instapack.android.R;
import ir.instapack.android.services.main.MainActivity;
import ir.mhd.ninjadialog.NinjaAlertDialog;
import ir.mhd.ninjadialog.OnClickListener;

public class SplashActivity extends BaseActivity {

    private SplashViewModel viewModel = new SplashViewModel();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private NinjaAlertDialog openDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!isNetworkAvailable())
            showCheckInternetDialog();
        else {
            observeCheckVersion();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (compositeDisposable.size() != 0) //to check it not null
            compositeDisposable.clear();

        if (openDialog != null)
            openDialog.dismiss(); //to dismiss any open dialog
    }


    private void observeCheckVersion() {

        viewModel.checkVersionRequest(APIServiceProvider.provideMainApiService())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<CheckVersionModel>(this) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(CheckVersionModel checkVersionModel) {

                        //to remove .0.0 and get int version
                        int index;
                        for (index = 0; checkVersionModel.getData().getLastVersion().charAt(index) != '.'; index++)
                            ;
                        checkVersionModel.getData()
                                .setLastVersion(checkVersionModel.getData().getLastVersion().substring(0, index));

                        for (index = 0; checkVersionModel.getData().getStableVersion().charAt(index) != '.'; index++)
                            ;
                        checkVersionModel.getData()
                                .setStableVersion(checkVersionModel.getData().getStableVersion().substring(0, index));


                        //to get Current App Version
                        int appVersion = 0;
                        try {
                            appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }


                        //to calculate need update
                        if (appVersion < Integer.parseInt(checkVersionModel.getData().getLastVersion())) {
                            if (appVersion < Integer.parseInt(checkVersionModel.getData().getStableVersion())) {

                                //to show CriticalUpdate Dialog
                                showCriticalUpdateDialog();

                            } else {

                                //to show OptionalUpdate Dialog
                                showOptionalUpdateDialog();
                            }
                        } else {
                            if (viewModel.getFromSharedPref(SharedPrefManager.KEY_API_TOKEN
                                    , SplashActivity.this).equals("")) {

                                Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                                startActivity(intent);
                                finish();

                            } else observeCheckValidToken();
                        }

                    }
                });

    }

    private void observeCheckValidToken() {

        viewModel.checkValidTokenRequest(APIServiceProvider.provideMainApiService(), this)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<CheckValidTokenModel>(this) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(CheckValidTokenModel checkValidTokenModel) {

                        //if token isn't valid gets 401 error and is handled

                        //check insta cookie is empty or not
                        if (viewModel.getFromSharedPref(SharedPrefManager.KEY_COOKIE
                                , SplashActivity.this).equals("")) {

                            Intent intent = new Intent(SplashActivity.this, LoginToInstaActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            //Everything is OK
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    }
                });

    }


    public void showCheckInternetDialog() {

        openDialog = new NinjaAlertDialog.Builder()
                .setTitle("اتصال اینترنت موجود نیست !")
                .setSubtitle("لطفا اتصال اینترنت خود را بررسی و دوباره امتحان کنید.")
                .setPositiveText("تلاش مجدد")
                .setCancellable(false)
                .build();

        openDialog.setNegativeButtonClickListener(v -> openDialog.dismiss());

        openDialog.setPositiveButtonClickListener(v -> {

            openDialog.dismiss();

            if (!isNetworkAvailable())
                showCheckInternetDialog();
            else
                observeCheckVersion();

        });

        openDialog.show(getSupportFragmentManager(), null);
    }

    private void showOptionalUpdateDialog() {

        openDialog = new NinjaAlertDialog.Builder()
                .setTitle("آپدیت جدید رسید")
                .setSubtitle("لطفا آخرین نسخه را نصب نمایید.")
                .setPositiveText("به روزرسانی")
                .setNegativeText("بعدا")
                .setCancellable(false)
                .build();

        openDialog.setNegativeButtonClickListener(new OnClickListener.OnNegativeButtonClickListener() {
            @Override
            public void onClick(View v) {

                //dismissed
                if (viewModel.getFromSharedPref(SharedPrefManager.KEY_API_TOKEN
                        , SplashActivity.this).equals("")) {

                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);
                    finish();

                } else observeCheckValidToken();
            }
        });

        openDialog.setPositiveButtonClickListener(new OnClickListener.OnPositiveButtonClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/"));
                startActivity(browserIntent);
                finish();

            }
        });

        openDialog.show(getSupportFragmentManager(), null);
    }

    private void showCriticalUpdateDialog() {

        openDialog = new NinjaAlertDialog.Builder()
                .setTitle("آپدیت جدید رسید")
                .setSubtitle("لطفا آخرین نسخه را نصب نمایید.")
                .setPositiveText("به روزرسانی")
                .setNegativeText("خروج")
                .setCancellable(false)
                .build();

        openDialog.setNegativeButtonClickListener(v -> finish());

        openDialog.setPositiveButtonClickListener(v -> {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/"));
            startActivity(browserIntent);
            finish();

        });

        openDialog.show(getSupportFragmentManager(), null);
    }
}
