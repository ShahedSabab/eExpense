package com.google.android.gms.internal;

import com.google.android.gms.internal.zzm.zza;
import com.google.android.gms.internal.zzm.zzb;
import java.io.UnsupportedEncodingException;

public class zzab extends zzk<String> {
    private final zzb<String> zzcg;

    public zzab(int i, String str, zzb<String> zzb, zza zza) {
        super(i, str, zza);
        this.zzcg = zzb;
    }

    /* access modifiers changed from: protected */
    public zzm<String> zza(zzi zzi) {
        String str;
        try {
            str = new String(zzi.data, zzx.zza(zzi.zzz));
        } catch (UnsupportedEncodingException e) {
            str = new String(zzi.data);
        }
        return zzm.zza(str, zzx.zzb(zzi));
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzi */
    public void zza(String str) {
        this.zzcg.zzb(str);
    }
}
