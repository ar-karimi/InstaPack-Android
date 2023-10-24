package ir.instapack.android.LoginSignUp.ValidateMobile;


import android.content.Context;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.subjects.BehaviorSubject;
import ir.instapack.android.Model.Api.ApiService;
import ir.instapack.android.Model.DataModels.LoginModel;
import ir.instapack.android.Model.DataModels.VerifyCodeModel;
import ir.instapack.android.Model.SharedPrefManager;

class ValidateMobileViewModel {

    private SharedPrefManager sharedPrefManager;

    private BehaviorSubject<Boolean> progressBarVisibilitySubject = BehaviorSubject.create();


    void saveInSharedPref(String TYPE, String value, Context context) {

        //to make sharedPrefManager object
        if (sharedPrefManager == null)
            sharedPrefManager = new SharedPrefManager(context);

        sharedPrefManager.saveInSharedPref(TYPE, value);
    }

    Single<VerifyCodeModel> verifyCodeRequest(ApiService apiService
            , String phone, String code, String androidId, String OsVersion) {

        progressBarVisibilitySubject.onNext(true);

        JsonObject object = new JsonObject();

        object.addProperty("phone", phone);
        object.addProperty("secret_key", code);
        object.addProperty("uuid", androidId);
        object.addProperty("device", "android " + OsVersion);


        return apiService.verifyCodeRequest(object)
                .doOnEvent(new BiConsumer<VerifyCodeModel, Throwable>() {
                    @Override
                    public void accept(VerifyCodeModel verifyCodeModel, Throwable throwable){
                        progressBarVisibilitySubject.onNext(false);
                    }
                });

    }

    Single<LoginModel> loginRequest(ApiService apiService, String phone) {

        progressBarVisibilitySubject.onNext(true);

        JsonObject object = new JsonObject();

        object.addProperty("phone", phone);

        return apiService.loginRequest(object)
                .doOnEvent(new BiConsumer<LoginModel, Throwable>() {
                    @Override
                    public void accept(LoginModel loginModel, Throwable throwable){
                        progressBarVisibilitySubject.onNext(false);
                    }
                });

    }


    BehaviorSubject<Boolean> getProgressBarVisibilitySubject() {
        return progressBarVisibilitySubject;
    }

}
