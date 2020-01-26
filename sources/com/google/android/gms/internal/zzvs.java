package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;
import com.google.android.gms.internal.zzvt.zza;

public class zzvs {

    /* renamed from: WC */
    private zzvt f824WC = null;
    private boolean zzaoz = false;

    public void initialize(Context context) {
        synchronized (this) {
            if (!this.zzaoz) {
                try {
                    this.f824WC = zza.asInterface(zztl.zza(context, zztl.f814Qm, ModuleDescriptor.MODULE_ID).zzjd("com.google.android.gms.flags.impl.FlagProviderImpl"));
                    this.f824WC.init(zze.zzac(context));
                    this.zzaoz = true;
                } catch (RemoteException | zztl.zza e) {
                    Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
                }
                return;
            }
            return;
        }
    }

    public <T> T zzb(zzvq<T> zzvq) {
        synchronized (this) {
            if (this.zzaoz) {
                return zzvq.zza(this.f824WC);
            }
            T zzlp = zzvq.zzlp();
            return zzlp;
        }
    }
}
