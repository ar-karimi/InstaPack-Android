package ir.instapack.android.LoginSignUp.LoginToApp;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.subjects.BehaviorSubject;
import ir.instapack.android.Model.Api.ApiService;
import ir.instapack.android.Model.DataModels.LoginModel;

class LoginToAppViewModel {

    private BehaviorSubject<Boolean> progressBarVisibilitySubject = BehaviorSubject.create();


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
