package ir.instapack.android.LoginSignUp.LoginToInsta;

import android.content.Context;

import ir.instapack.android.Model.SharedPrefManager;

class LoginToInstaViewModel {

    private SharedPrefManager sharedPrefManager;

    void saveInSharedPref(String TYPE, String value, Context context) {

        //to make sharedPrefManager object
        if (sharedPrefManager == null)
            sharedPrefManager = new SharedPrefManager(context);

        sharedPrefManager.saveInSharedPref(TYPE, value);
    }

}
