package borax12.com.shine.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by borax12 on 3/5/2015.
 */
public class ShineSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static ShineSyncAdapter sSunshineSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("SunshineSyncService", "onCreate - SunshineSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSunshineSyncAdapter == null) {
                sSunshineSyncAdapter = new ShineSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent){
            return sSunshineSyncAdapter.getSyncAdapterBinder();
    }
}
