package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzqm extends zzqp {

    /* renamed from: yq */
    private final SparseArray<zza> f519yq = new SparseArray<>();

    private class zza implements OnConnectionFailedListener {

        /* renamed from: yr */
        public final int f520yr;

        /* renamed from: ys */
        public final GoogleApiClient f521ys;

        /* renamed from: yt */
        public final OnConnectionFailedListener f522yt;

        public zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.f520yr = i;
            this.f521ys = googleApiClient;
            this.f522yt = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.f520yr);
            printWriter.println(":");
            this.f521ys.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            zzqm.this.zzb(connectionResult, this.f520yr);
        }

        public void zzarn() {
            this.f521ys.unregisterConnectionFailedListener(this);
            this.f521ys.disconnect();
        }
    }

    private zzqm(zzrp zzrp) {
        super(zzrp);
        this.f725Bf.zza("AutoManageHelper", (zzro) this);
    }

    public static zzqm zza(zzrn zzrn) {
        zzrp zzc = zzc(zzrn);
        zzqm zzqm = (zzqm) zzc.zza("AutoManageHelper", zzqm.class);
        return zzqm != null ? zzqm : new zzqm(zzc);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f519yq.size()) {
                ((zza) this.f519yq.valueAt(i2)).dump(str, fileDescriptor, printWriter, strArr);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.f519yq);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (!this.f534yz) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.f519yq.size()) {
                    ((zza) this.f519yq.valueAt(i2)).f521ys.connect();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f519yq.size()) {
                ((zza) this.f519yq.valueAt(i2)).f521ys.disconnect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzb(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzaa.zza(this.f519yq.indexOfKey(i) < 0, (Object) "Already managing a GoogleApiClient with id " + i);
        Log.d("AutoManageHelper", "starting AutoManage for client " + i + " " + this.mStarted + " " + this.f534yz);
        this.f519yq.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.f534yz) {
            String valueOf = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 11).append("connecting ").append(valueOf).toString());
            googleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza2 = (zza) this.f519yq.get(i);
        if (zza2 != null) {
            zzfs(i);
            OnConnectionFailedListener onConnectionFailedListener = zza2.f522yt;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzarm() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f519yq.size()) {
                ((zza) this.f519yq.valueAt(i2)).f521ys.connect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zzfs(int i) {
        zza zza2 = (zza) this.f519yq.get(i);
        this.f519yq.remove(i);
        if (zza2 != null) {
            zza2.zzarn();
        }
    }
}
