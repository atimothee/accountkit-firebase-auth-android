package xyz.atimothee.firebasefbaccountkitandroidauth;

import com.facebook.accountkit.AccountKit;

/**
 * Created by Timo on 17/09/16.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize(getApplicationContext());
    }
}
