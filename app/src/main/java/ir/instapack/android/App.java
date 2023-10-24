package ir.instapack.android;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class App extends Application {


    @Override
    public void onCreate() {
        //super.onTerminate(); //alan appet biad bala mire paiin
        super.onCreate();

        Fresco.initialize(this);

        //to Change Font
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("IRANSansMobileFonts/IRANSansMobile(FaNum).ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }
}
