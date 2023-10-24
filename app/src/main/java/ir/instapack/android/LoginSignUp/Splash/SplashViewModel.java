package ir.instapack.android.LoginSignUp.Splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import ir.instapack.android.Model.Api.ApiService;
import ir.instapack.android.Model.DataModels.CheckValidTokenModel;
import ir.instapack.android.Model.DataModels.CheckVersionModel;
import ir.instapack.android.Model.SharedPrefManager;

class SplashViewModel {

    //private ApiService apiService = APIServiceProvider.provideApiService();

    private SharedPrefManager sharedPrefManager;


    String getFromSharedPref(String TYPE, Context context) {

        //to make sharedPrefManager object
        if (sharedPrefManager == null)
            sharedPrefManager = new SharedPrefManager(context);

        return sharedPrefManager.getFromSharedPref(TYPE);
    }


    Single<CheckVersionModel> checkVersionRequest(ApiService apiService) {

        JsonObject object = new JsonObject();

        object.addProperty("os", "android");

        return apiService.checkVersionRequest(object);

    }

    Single<CheckValidTokenModel> checkValidTokenRequest(ApiService apiService, Context context) {


        //to get Android ID
        @SuppressLint("HardwareIds")
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        JsonObject object = new JsonObject();

        object.addProperty("api_token", getFromSharedPref(SharedPrefManager.KEY_API_TOKEN, context));
        object.addProperty("uuid", androidId);

        return apiService.checkValidTokenRequest(object);

    }

}
