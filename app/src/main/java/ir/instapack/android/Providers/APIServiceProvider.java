package ir.instapack.android.Providers;


import ir.instapack.android.Model.Api.ApiService;
import ir.instapack.android.Model.Api.RetrofitSingleton;

public class APIServiceProvider {

    private static ApiService mainApiService;
    private static ApiService instaApiService;

    public static ApiService provideMainApiService() {
        if (mainApiService == null)
            mainApiService = RetrofitSingleton.getMainInstance().create(ApiService.class);

        return mainApiService;

    }

    public static ApiService provideInstaApiService() {
        if (instaApiService == null)
            instaApiService = RetrofitSingleton.getInstaInstance().create(ApiService.class);

        return instaApiService;

    }
}
