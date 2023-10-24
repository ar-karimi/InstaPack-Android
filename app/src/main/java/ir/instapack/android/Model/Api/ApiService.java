package ir.instapack.android.Model.Api;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import ir.instapack.android.Model.DataModels.CheckValidTokenModel;
import ir.instapack.android.Model.DataModels.CheckVersionModel;
import ir.instapack.android.Model.DataModels.LoginModel;
import ir.instapack.android.Model.DataModels.VerifyCodeModel;
import ir.instapack.android.services.searchUser.dataModels.SearchUserModel;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /*@POST
    Single<UserInformationModel> userInformationRequest(@Url String URL, @Header("Accept") String accept);*/

    //Main APIs
    @POST("checkVersion")
    Single<CheckVersionModel> checkVersionRequest(@Body JsonObject jsonObject);

    @POST("checkValidToken")
    Single<CheckValidTokenModel> checkValidTokenRequest(@Body JsonObject jsonObject);

    @POST("login")
    Single<LoginModel> loginRequest(@Body JsonObject jsonObject);

    @POST("attemptToLogin")
    Single<VerifyCodeModel> verifyCodeRequest(@Body JsonObject jsonObject);

    //Insta APIs
    @POST("fbsearch/topsearch_flat/")
    Single<SearchUserModel> searchUserRequest(@Query("query") String searchPhrase
            , @Query("context") String searchType, @Query("timezone_offset") int timezone);


}
