package ir.instapack.android.Base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.instapack.android.Exceptions.ErrorDialogException;
import ir.instapack.android.Exceptions.UnAuthorizedException;
import ir.instapack.android.LoginSignUp.Intro.IntroActivity;
import ir.instapack.android.LoginSignUp.LoginToApp.LoginToAppActivity;
import ir.instapack.android.Model.SharedPrefManager;
import ir.instapack.android.R;
import ir.mhd.ninjadialog.NinjaAlertDialog;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void handleUnAuthorizedException(UnAuthorizedException unAuthorizedException) {

        //clear Tokens from sharedPref
        new SharedPrefManager(this).saveInSharedPref(SharedPrefManager.KEY_API_TOKEN, "");
        new SharedPrefManager(this).saveInSharedPref(SharedPrefManager.KEY_COOKIE, "");
        new SharedPrefManager(this).saveInSharedPref(SharedPrefManager.KEY_USER_ID, "");

        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Subscribe
    public void showErrorDialog(ErrorDialogException errorDialogException) {

        NinjaAlertDialog alertDialog = new NinjaAlertDialog.Builder()
                .setTitle("خطا")
                .setSubtitle(errorDialogException.getErrorMessage())
                .setPositiveText("باشه")
                .setCancellable(true)
                .build();

        alertDialog.setPositiveButtonClickListener(v -> alertDialog.dismiss());

        alertDialog.show(getSupportFragmentManager(), null);
    }

    @Override //to Change Font
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void showToast(String message) {

        if (message != null) {
            carbon.widget.TextView toastView = new carbon.widget.TextView(this);
            toastView.setBackgroundColor(getResources().getColor(R.color.colorToastBackground));
            toastView.setCornerRadius(40);
            toastView.setStrokeWidth(5);
            toastView.setStroke(getResources().getColor(R.color.colorToastStroke));
            toastView.setPadding(60, 35, 60, 35);
            toastView.setTextColor(getResources().getColor(R.color.colorToastText));
            toastView.setTypeface(Typeface.createFromAsset(getAssets(), "IRANSansMobileFonts/IRANSansMobile(FaNum)_Bold.ttf"));
            toastView.setText(message);

            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastView);
            toast.show();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void closeKeyboard(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && view.getWindowToken() != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
