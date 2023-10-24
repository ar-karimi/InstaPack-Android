package ir.instapack.android.services.searchUser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import ir.instapack.android.Model.Api.ApiService;
import ir.instapack.android.services.searchUser.dataModels.SearchUserModel;

public class SearchUserViewModel extends ViewModel {

    private MutableLiveData<Boolean> progressBarLiveData = new MutableLiveData<>();

    private static final String searchType = "user";
    private static final int timezone = 12600; //Tehran TimeZone Offset In seconds (GMT +03:30)


    Single<SearchUserModel> loginRequest(ApiService apiService, String searchPhrase) {

        progressBarLiveData.postValue(true);

        return apiService.searchUserRequest(searchPhrase, searchType, timezone)
                .doOnEvent(new BiConsumer<SearchUserModel, Throwable>() {
                    @Override
                    public void accept(SearchUserModel loginModel, Throwable throwable){
                        progressBarLiveData.postValue(false); //setValue not work because is in background thread
                    }
                });

    }


    LiveData<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

}
