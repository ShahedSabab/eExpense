package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzrt extends zzqp {

    /* renamed from: yg */
    private TaskCompletionSource<Void> f736yg = new TaskCompletionSource<>();

    private zzrt(zzrp zzrp) {
        super(zzrp);
        this.f725Bf.zza("GmsAvailabilityHelper", (zzro) this);
    }

    public static zzrt zzu(Activity activity) {
        zzrp zzs = zzs(activity);
        zzrt zzrt = (zzrt) zzs.zza("GmsAvailabilityHelper", zzrt.class);
        if (zzrt == null) {
            return new zzrt(zzs);
        }
        if (!zzrt.f736yg.getTask().isComplete()) {
            return zzrt;
        }
        zzrt.f736yg = new TaskCompletionSource<>();
        return zzrt;
    }

    public Task<Void> getTask() {
        return this.f736yg.getTask();
    }

    public void onDestroy() {
        super.onDestroy();
        this.f736yg.setException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        this.f736yg.setException(zzb.zzk(connectionResult));
    }

    /* access modifiers changed from: protected */
    public void zzarm() {
        int isGooglePlayServicesAvailable = this.f530xP.isGooglePlayServicesAvailable(this.f725Bf.zzaty());
        if (isGooglePlayServicesAvailable == 0) {
            this.f736yg.setResult(null);
        } else {
            zzj(new ConnectionResult(isGooglePlayServicesAvailable, null));
        }
    }

    public void zzj(ConnectionResult connectionResult) {
        zzb(connectionResult, 0);
    }
}
