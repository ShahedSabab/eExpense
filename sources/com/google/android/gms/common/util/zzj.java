package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zzj {

    /* renamed from: Gv */
    private static IntentFilter f438Gv = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    /* renamed from: Gw */
    private static long f439Gw;

    /* renamed from: Gx */
    private static float f440Gx = Float.NaN;

    @TargetApi(20)
    public static boolean zzb(PowerManager powerManager) {
        return zzs.zzayv() ? powerManager.isInteractive() : powerManager.isScreenOn();
    }

    @TargetApi(20)
    public static int zzck(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, f438Gv);
        boolean z = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int i2 = (zzb(powerManager) ? 1 : 0) << 1;
        if (!z) {
            i = 0;
        }
        return i2 | i;
    }

    public static synchronized float zzcl(Context context) {
        float f;
        synchronized (zzj.class) {
            if (SystemClock.elapsedRealtime() - f439Gw >= 60000 || Float.isNaN(f440Gx)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver(null, f438Gv);
                if (registerReceiver != null) {
                    f440Gx = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                f439Gw = SystemClock.elapsedRealtime();
                f = f440Gx;
            } else {
                f = f440Gx;
            }
        }
        return f;
    }
}
