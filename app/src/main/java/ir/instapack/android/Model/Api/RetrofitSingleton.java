package ir.instapack.android.Model.Api;


import ir.instapack.android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit mainRetrofit;
    private static Retrofit instaRetrofit;

    public static Retrofit getMainInstance() {

        if (mainRetrofit == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {

                        Request oldRequest = chain.request();
                        Request.Builder newRequest = oldRequest.newBuilder();

                        newRequest.addHeader("Content-Type", "application/json");
                        newRequest.addHeader("Accept", "application/json");

                        return chain.proceed(newRequest.build());
                    });


            if (!BuildConfig.BUILD_TYPE.equals("release")) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(loggingInterceptor);
            }


            mainRetrofit = new Retrofit.Builder()
                    .baseUrl("http://instapackapp.ir/api/v1/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder.build())
                    .build();
        }

        return mainRetrofit;
    }


    public static Retrofit getInstaInstance() {

        if (instaRetrofit == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {

                        Request oldRequest = chain.request();
                        Request.Builder newRequest = oldRequest.newBuilder();

                        //TODO send from shared pref
                        newRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
                        newRequest.addHeader("User-Agent", "Instagram 22.0.0.15.68 Android (23/6.0.1; 640dpi; 1440x2560; samsung; SM-G935F; hero2lte; samsungexynos8890; en_US)");
                        newRequest.addHeader("Cookie", "mid=XaRjcQABAAFLv5VVwoQ_ibiT5so8; csrftoken=Qo45jjBkK0L6z1cTxtYQd5E5WgsVnIda; shbid=3422; shbts=1571054471.1237211; ds_user_id=16387523953; sessionid=16387523953%3ATBSPDj8GP2O5JP%3A1; rur=ATN; urlgen=\"{\\\"217.66.220.16\\\": 24631}:1iJz2F:oBwC7DEFNeTiOkD1FAAE4sUfi2E\"");

                        return chain.proceed(newRequest.build());
                    });


            if (!BuildConfig.BUILD_TYPE.equals("release")) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(loggingInterceptor);
            }


            instaRetrofit = new Retrofit.Builder()
                    .baseUrl("https://i.instagram.com/api/v1/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder.build())
                    .build();
        }

        return instaRetrofit;
    }

}
