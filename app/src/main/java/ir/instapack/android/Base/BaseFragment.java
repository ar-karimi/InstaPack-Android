package ir.instapack.android.Base;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import ir.instapack.android.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {


    protected T binding;

    private ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
            binding.setLifecycleOwner(getViewLifecycleOwner());
            rootView = (ViewGroup) binding.getRoot();
            setupViews();
        }

        return rootView;
    }

    public abstract void setupViews();

    @LayoutRes
    public abstract int getLayoutRes();



    public void showToast(String message) {

        if (message != null) {
            carbon.widget.TextView toastView = new carbon.widget.TextView(getContext());
            toastView.setBackgroundColor(getResources().getColor(R.color.colorToastBackground));
            toastView.setCornerRadius(40);
            toastView.setStrokeWidth(5);
            toastView.setStroke(getResources().getColor(R.color.colorToastStroke));
            toastView.setPadding(60, 35, 60, 35);
            toastView.setTextColor(getResources().getColor(R.color.colorToastText));
            toastView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "IRANSansMobileFonts/IRANSansMobile(FaNum)_Bold.ttf"));
            toastView.setText(message);

            Toast toast = new Toast(getContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastView);
            toast.show();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void closeKeyboard(View view) {

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && view.getWindowToken() != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



}
