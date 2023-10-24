package ir.instapack.android.Model;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {

    private static final String SHARED_PREF = "shared_pref";

    public static final String KEY_API_TOKEN = "api_token";
    public static final String KEY_COOKIE = "cookie";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_AGENT = "user_agent";

    private SharedPreferences sharedPreferences;


    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    }


    public void saveInSharedPref(String TYPE, String value) {
        if (value != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TYPE, value);
            editor.apply();
        }
    }


    public String getFromSharedPref(String TYPE) {
        return sharedPreferences.getString(TYPE, "");
    }

}

