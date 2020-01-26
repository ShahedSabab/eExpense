package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcl;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient {
    private final Context mContext;
    com.google.android.gms.common.zza zzalf;
    zzcl zzalg;
    boolean zzalh;
    Object zzali;
    zza zzalj;
    final long zzalk;

    public static final class Info {
        private final String zzalr;
        private final boolean zzals;

        public Info(String str, boolean z) {
            this.zzalr = str;
            this.zzals = z;
        }

        public String getId() {
            return this.zzalr;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzals;
        }

        public String toString() {
            String str = this.zzalr;
            return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append("}").append(this.zzals).toString();
        }
    }

    static class zza extends Thread {
        private WeakReference<AdvertisingIdClient> zzaln;
        private long zzalo;
        CountDownLatch zzalp = new CountDownLatch(1);
        boolean zzalq = false;

        public zza(AdvertisingIdClient advertisingIdClient, long j) {
            this.zzaln = new WeakReference<>(advertisingIdClient);
            this.zzalo = j;
            start();
        }

        private void disconnect() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.zzaln.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzalq = true;
            }
        }

        public void cancel() {
            this.zzalp.countDown();
        }

        public void run() {
            try {
                if (!this.zzalp.await(this.zzalo, TimeUnit.MILLISECONDS)) {
                    disconnect();
                }
            } catch (InterruptedException e) {
                disconnect();
            }
        }

        public boolean zzeb() {
            return this.zzalq;
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, 30000, false);
    }

    public AdvertisingIdClient(Context context, long j, boolean z) {
        this.zzali = new Object();
        zzaa.zzy(context);
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            this.mContext = context;
        } else {
            this.mContext = context;
        }
        this.zzalh = false;
        this.zzalk = j;
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        float f = 0.0f;
        boolean z = false;
        try {
            Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext != null) {
                SharedPreferences sharedPreferences = remoteContext.getSharedPreferences("google_ads_flags", 1);
                z = sharedPreferences.getBoolean("gads:ad_id_app_context:enabled", false);
                f = sharedPreferences.getFloat("gads:ad_id_app_context:ping_ratio", 0.0f);
            }
        } catch (Exception e) {
            Log.w("AdvertisingIdClient", "Error while reading from SharedPreferences ", e);
        }
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1, z);
        try {
            advertisingIdClient.zze(false);
            Info info = advertisingIdClient.getInfo();
            advertisingIdClient.zza(info, z, f, null);
            advertisingIdClient.finish();
            return info;
        } catch (Throwable th) {
            advertisingIdClient.finish();
            throw th;
        }
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    static zzcl zza(Context context, com.google.android.gms.common.zza zza2) throws IOException {
        try {
            return com.google.android.gms.internal.zzcl.zza.zzf(zza2.zza(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    private void zza(Info info, boolean z, float f, Throwable th) {
        if (Math.random() <= ((double) f)) {
            final String uri = zza(info, z, th).toString();
            new Thread() {
                public void run() {
                    new zza().zzv(uri);
                }
            }.start();
        }
    }

    private void zzea() {
        synchronized (this.zzali) {
            if (this.zzalj != null) {
                this.zzalj.cancel();
                try {
                    this.zzalj.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.zzalk > 0) {
                this.zzalj = new zza(this, this.zzalk);
            }
        }
    }

    static com.google.android.gms.common.zza zzf(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (zzc.zzaql().isGooglePlayServicesAvailable(context)) {
                case 0:
                case 2:
                    com.google.android.gms.common.zza zza2 = new com.google.android.gms.common.zza();
                    Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                    intent.setPackage("com.google.android.gms");
                    try {
                        if (com.google.android.gms.common.stats.zza.zzaxr().zza(context, intent, (ServiceConnection) zza2, 1)) {
                            return zza2;
                        }
                        throw new IOException("Connection failure");
                    } catch (Throwable th) {
                        throw new IOException(th);
                    }
                default:
                    throw new IOException("Google Play services not available");
            }
        } catch (NameNotFoundException e) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finish() {
        /*
            r3 = this;
            java.lang.String r0 = "Calling this from your main thread can lead to deadlock"
            com.google.android.gms.common.internal.zzaa.zzht(r0)
            monitor-enter(r3)
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x000e
            com.google.android.gms.common.zza r0 = r3.zzalf     // Catch:{ all -> 0x002a }
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
        L_0x000f:
            return
        L_0x0010:
            boolean r0 = r3.zzalh     // Catch:{ IllegalArgumentException -> 0x002d, Throwable -> 0x0036 }
            if (r0 == 0) goto L_0x001f
            com.google.android.gms.common.stats.zza r0 = com.google.android.gms.common.stats.zza.zzaxr()     // Catch:{ IllegalArgumentException -> 0x002d, Throwable -> 0x0036 }
            android.content.Context r1 = r3.mContext     // Catch:{ IllegalArgumentException -> 0x002d, Throwable -> 0x0036 }
            com.google.android.gms.common.zza r2 = r3.zzalf     // Catch:{ IllegalArgumentException -> 0x002d, Throwable -> 0x0036 }
            r0.zza(r1, r2)     // Catch:{ IllegalArgumentException -> 0x002d, Throwable -> 0x0036 }
        L_0x001f:
            r0 = 0
            r3.zzalh = r0     // Catch:{ all -> 0x002a }
            r0 = 0
            r3.zzalg = r0     // Catch:{ all -> 0x002a }
            r0 = 0
            r3.zzalf = r0     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            goto L_0x000f
        L_0x002a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            throw r0
        L_0x002d:
            r0 = move-exception
            java.lang.String r1 = "AdvertisingIdClient"
            java.lang.String r2 = "AdvertisingIdClient unbindService failed."
            android.util.Log.i(r1, r2, r0)     // Catch:{ all -> 0x002a }
            goto L_0x001f
        L_0x0036:
            r0 = move-exception
            java.lang.String r1 = "AdvertisingIdClient"
            java.lang.String r2 = "AdvertisingIdClient unbindService failed."
            android.util.Log.i(r1, r2, r0)     // Catch:{ all -> 0x002a }
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.finish():void");
    }

    public Info getInfo() throws IOException {
        Info info;
        zzaa.zzht("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.zzalh) {
                synchronized (this.zzali) {
                    if (this.zzalj == null || !this.zzalj.zzeb()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    zze(false);
                    if (!this.zzalh) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (RemoteException e) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                    throw new IOException("Remote exception");
                } catch (Exception e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            zzaa.zzy(this.zzalf);
            zzaa.zzy(this.zzalg);
            info = new Info(this.zzalg.getId(), this.zzalg.zzf(true));
        }
        zzea();
        return info;
    }

    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zze(true);
    }

    /* access modifiers changed from: 0000 */
    public Uri zza(Info info, boolean z, Throwable th) {
        Bundle bundle = new Bundle();
        bundle.putString("app_context", z ? "1" : "0");
        if (info != null) {
            bundle.putString("limit_ad_tracking", info.isLimitAdTrackingEnabled() ? "1" : "0");
        }
        if (!(info == null || info.getId() == null)) {
            bundle.putString("ad_id_size", Integer.toString(info.getId().length()));
        }
        if (th != null) {
            bundle.putString("error", th.getClass().getName());
        }
        Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for (String str : bundle.keySet()) {
            buildUpon.appendQueryParameter(str, bundle.getString(str));
        }
        return buildUpon.build();
    }

    /* access modifiers changed from: protected */
    public void zze(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzaa.zzht("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzalh) {
                finish();
            }
            this.zzalf = zzf(this.mContext);
            this.zzalg = zza(this.mContext, this.zzalf);
            this.zzalh = true;
            if (z) {
                zzea();
            }
        }
    }
}
