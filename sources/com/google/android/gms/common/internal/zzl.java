package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzl {

    /* renamed from: El */
    private static final Object f334El = new Object();

    /* renamed from: Em */
    private static zzl f335Em;

    public static zzl zzcc(Context context) {
        synchronized (f334El) {
            if (f335Em == null) {
                f335Em = new zzm(context.getApplicationContext());
            }
        }
        return f335Em;
    }

    public abstract boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3);

    public abstract void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract void zzb(String str, String str2, ServiceConnection serviceConnection, String str3);
}
