package ir.instapack.android.Exceptions;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.HttpException;

public class ExceptionMessageFactory {

    public static String getError(Throwable throwable) {

        if (throwable instanceof HttpException) {
            switch (((HttpException) throwable).code()) {
                case 401:
                    EventBus.getDefault().post(new UnAuthorizedException(401));
                    return null;
                case 403:
                    EventBus.getDefault().post(new UnAuthorizedException(403));
                    return null;
                case 404:
                    return "درخواست مورد نظر یافت نشد!";
                case 422:
                    return "تعداد درخواست ها بیش از حد مجاز است!";
                case 423: {

                    //to get server error message
                    String message = "Null";
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                        message = jsonObject.getString("message");

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                    return message;
                }

                case 424: {

                    //to get server error message
                    String message = "Null";
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                        message = jsonObject.getString("message");

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                    EventBus.getDefault().post(new ErrorDialogException(message));

                    return null;
                }

                case 500:
                    return "خطای سمت سرور!";
                default:
                    return "خطایی رخ داده لطفاً بعداً تلاش کنید!";
            }
        } else {
            return "خطای ناشناخته!";
        }

    }

}
