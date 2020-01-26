package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzvq.zzd;
import java.util.ArrayList;
import java.util.Collection;

public class zzvr {
    private final Collection<zzvq> zzbcp = new ArrayList();
    private final Collection<zzd> zzbcq = new ArrayList();
    private final Collection<zzd> zzbcr = new ArrayList();

    public static void initialize(Context context) {
        zzvu.zzbhf().initialize(context);
    }

    public void zza(zzvq zzvq) {
        this.zzbcp.add(zzvq);
    }
}
