package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzvv;
import java.util.concurrent.Callable;

public class zzb {

    /* renamed from: WM */
    private static SharedPreferences f503WM = null;

    public static SharedPreferences zzm(final Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (f503WM == null) {
                f503WM = (SharedPreferences) zzvv.zzb(new Callable<SharedPreferences>() {
                    /* renamed from: zzbhi */
                    public SharedPreferences call() {
                        return context.getSharedPreferences("google_sdk_flags", 1);
                    }
                });
            }
            sharedPreferences = f503WM;
        }
        return sharedPreferences;
    }
}
