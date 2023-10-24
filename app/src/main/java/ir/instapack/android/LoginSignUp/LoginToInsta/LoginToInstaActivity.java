package ir.instapack.android.LoginSignUp.LoginToInsta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.Model.SharedPrefManager;
import ir.instapack.android.R;
import ir.instapack.android.services.main.MainActivity;

public class LoginToInstaActivity extends BaseActivity {

    private LoginToInstaViewModel viewModel = new LoginToInstaViewModel();

    private boolean pageFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_insta);


        ProgressBar progressBar = findViewById(R.id.progress_bar);
        WebView webView = findViewById(R.id.web_view);
        webView.clearCache(true);

        webView.setWebViewClient(new CustomWebViewClient(progressBar, webView));
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webSetting.setLoadsImagesAutomatically(true);
        webView.loadUrl("https://www.instagram.com/accounts/login/");

    }


    class CustomWebViewClient extends WebViewClient {
        private ProgressBar progressBar;


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        CustomWebViewClient(ProgressBar progressBar, WebView webView) {
            this.progressBar = progressBar;
            webView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {


            if (url.equals("https://www.instagram.com/") || url.equals("https://www.instagram.com") || url.contains("instagram.com/accounts/onetap/")) {

                //to avoid of call onPageFinished twice (maybe webView Bug)
                if (pageFinished)
                    return;

                pageFinished = true;


                showToast("خوش آمدید");

                String cookie = CookieManager.getInstance().getCookie(url);
                Map parsedCookies = parseCookies(cookie);

                viewModel.saveInSharedPref(SharedPrefManager.KEY_COOKIE, cookie, LoginToInstaActivity.this);
                viewModel.saveInSharedPref(SharedPrefManager.KEY_USER_ID
                        , parsedCookies.get("ds_user_id").toString(), LoginToInstaActivity.this);

                //set UserAgent Manually
                viewModel.saveInSharedPref(SharedPrefManager.KEY_USER_AGENT
                        , getResources().getString(R.string.default_user_agent), LoginToInstaActivity.this);

                startActivity(new Intent(LoginToInstaActivity.this, MainActivity.class));
                finish();


            } else //unSuccessful Login
            {
                view.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }

        private Map parseCookies(String str) {
            Map hashMap = new HashMap();
            String[] split = str.split("; ");
            if (split.length >= 2) {
                for (String s : split) {
                    String[] split2 = s.split("=");
                    hashMap.put(split2[0], split2[1]);
                }
            }
            return hashMap;
        }
    }
}
