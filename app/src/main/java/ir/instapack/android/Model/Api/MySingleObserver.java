package ir.instapack.android.Model.Api;

import io.reactivex.SingleObserver;
import ir.instapack.android.Base.BaseActivity;
import ir.instapack.android.Exceptions.ExceptionMessageFactory;

public abstract class MySingleObserver<T> implements SingleObserver<T> {

    private BaseActivity baseActivity;

    protected MySingleObserver(BaseActivity baseActivity) {

        this.baseActivity = baseActivity;
    }

    @Override
    public void onError(Throwable e) {

        baseActivity.showToast(ExceptionMessageFactory.getError(e));
    }
}
