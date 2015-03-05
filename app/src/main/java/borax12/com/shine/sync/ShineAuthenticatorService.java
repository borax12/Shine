package borax12.com.shine.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by borax12 on 3/5/2015.
 */
public class ShineAuthenticatorService extends Service {

    private ShineAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new ShineAuthenticator(this);
    }

    /*
 * When the system binds to this Service to make the RPC call
 * return the authenticator's IBinder.
 */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
