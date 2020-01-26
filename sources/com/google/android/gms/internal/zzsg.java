package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class zzsg {

    /* renamed from: BE */
    private static final zzqq<?>[] f763BE = new zzqq[0];

    /* renamed from: ym */
    public static final Status f764ym = new Status(8, "The connection to Google Play services was lost");

    /* renamed from: Aj */
    private final Map<zzc<?>, zze> f765Aj;

    /* renamed from: BF */
    final Set<zzqq<?>> f766BF = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));

    /* renamed from: BG */
    private final zzb f767BG = new zzb() {
        public void zzc(zzqq<?> zzqq) {
            zzsg.this.f766BF.remove(zzqq);
            if (zzqq.zzarh() != null && zzsg.zza(zzsg.this) != null) {
                zzsg.zza(zzsg.this).remove(zzqq.zzarh().intValue());
            }
        }
    };

    private static class zza implements DeathRecipient, zzb {

        /* renamed from: BI */
        private final WeakReference<zzqq<?>> f769BI;

        /* renamed from: BJ */
        private final WeakReference<com.google.android.gms.common.api.zze> f770BJ;

        /* renamed from: BK */
        private final WeakReference<IBinder> f771BK;

        private zza(zzqq<?> zzqq, com.google.android.gms.common.api.zze zze, IBinder iBinder) {
            this.f770BJ = new WeakReference<>(zze);
            this.f769BI = new WeakReference<>(zzqq);
            this.f771BK = new WeakReference<>(iBinder);
        }

        private void zzaug() {
            zzqq zzqq = (zzqq) this.f769BI.get();
            com.google.android.gms.common.api.zze zze = (com.google.android.gms.common.api.zze) this.f770BJ.get();
            if (!(zze == null || zzqq == null)) {
                zze.remove(zzqq.zzarh().intValue());
            }
            IBinder iBinder = (IBinder) this.f771BK.get();
            if (iBinder != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void binderDied() {
            zzaug();
        }

        public void zzc(zzqq<?> zzqq) {
            zzaug();
        }
    }

    interface zzb {
        void zzc(zzqq<?> zzqq);
    }

    public zzsg(Map<zzc<?>, zze> map) {
        this.f765Aj = map;
    }

    static /* synthetic */ com.google.android.gms.common.api.zze zza(zzsg zzsg) {
        return null;
    }

    private static void zza(zzqq<?> zzqq, com.google.android.gms.common.api.zze zze, IBinder iBinder) {
        if (zzqq.isReady()) {
            zzqq.zza((zzb) new zza(zzqq, zze, iBinder));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            zzqq.zza((zzb) null);
            zzqq.cancel();
            zze.remove(zzqq.zzarh().intValue());
        } else {
            zza zza2 = new zza(zzqq, zze, iBinder);
            zzqq.zza((zzb) zza2);
            try {
                iBinder.linkToDeath(zza2, 0);
            } catch (RemoteException e) {
                zzqq.cancel();
                zze.remove(zzqq.zzarh().intValue());
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.f766BF.size());
    }

    public void release() {
        zzqq[] zzqqArr;
        for (zzqq zzqq : (zzqq[]) this.f766BF.toArray(f763BE)) {
            zzqq.zza((zzb) null);
            if (zzqq.zzarh() != null) {
                zzqq.zzaru();
                zza(zzqq, null, ((zze) this.f765Aj.get(((com.google.android.gms.internal.zzqo.zza) zzqq).zzaqv())).zzaqy());
                this.f766BF.remove(zzqq);
            } else if (zzqq.zzars()) {
                this.f766BF.remove(zzqq);
            }
        }
    }

    public void zzauf() {
        for (zzqq zzab : (zzqq[]) this.f766BF.toArray(f763BE)) {
            zzab.zzab(f764ym);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzb(zzqq<? extends Result> zzqq) {
        this.f766BF.add(zzqq);
        zzqq.zza(this.f767BG);
    }
}
