package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.zzd;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class zza {

    /* renamed from: El */
    private static final Object f400El = new Object();

    /* renamed from: FF */
    private static Integer f401FF;

    /* renamed from: Fz */
    private static zza f402Fz;

    /* renamed from: FA */
    private final List<String> f403FA;

    /* renamed from: FB */
    private final List<String> f404FB;

    /* renamed from: FC */
    private final List<String> f405FC;

    /* renamed from: FD */
    private final List<String> f406FD;

    /* renamed from: FE */
    private zzd f407FE;

    /* renamed from: FG */
    private zzd f408FG;

    private zza() {
        if (zzaxs()) {
            this.f403FA = Collections.EMPTY_LIST;
            this.f404FB = Collections.EMPTY_LIST;
            this.f405FC = Collections.EMPTY_LIST;
            this.f406FD = Collections.EMPTY_LIST;
            return;
        }
        String str = (String) com.google.android.gms.common.stats.zzb.zza.f412FK.get();
        this.f403FA = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        String str2 = (String) com.google.android.gms.common.stats.zzb.zza.f413FL.get();
        this.f404FB = str2 == null ? Collections.EMPTY_LIST : Arrays.asList(str2.split(","));
        String str3 = (String) com.google.android.gms.common.stats.zzb.zza.f414FM.get();
        this.f405FC = str3 == null ? Collections.EMPTY_LIST : Arrays.asList(str3.split(","));
        String str4 = (String) com.google.android.gms.common.stats.zzb.zza.f415FN.get();
        this.f406FD = str4 == null ? Collections.EMPTY_LIST : Arrays.asList(str4.split(","));
        this.f407FE = new zzd(1024, ((Long) com.google.android.gms.common.stats.zzb.zza.f416FO.get()).longValue());
        this.f408FG = new zzd(1024, ((Long) com.google.android.gms.common.stats.zzb.zza.f416FO.get()).longValue());
    }

    private static int getLogLevel() {
        if (f401FF == null) {
            try {
                f401FF = Integer.valueOf(zzd.zzayi() ? ((Integer) com.google.android.gms.common.stats.zzb.zza.f411FJ.get()).intValue() : zzc.LOG_LEVEL_OFF);
            } catch (SecurityException e) {
                f401FF = Integer.valueOf(zzc.LOG_LEVEL_OFF);
            }
        }
        return f401FF.intValue();
    }

    public static zza zzaxr() {
        synchronized (f400El) {
            if (f402Fz == null) {
                f402Fz = new zza();
            }
        }
        return f402Fz;
    }

    private boolean zzaxs() {
        return getLogLevel() == zzc.LOG_LEVEL_OFF;
    }

    private boolean zzc(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            return false;
        }
        return zzd.zzx(context, component.getPackageName());
    }

    @SuppressLint({"UntrackedBindService"})
    public void zza(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    public void zza(Context context, ServiceConnection serviceConnection, String str, Intent intent) {
    }

    public boolean zza(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        return zza(context, context.getClass().getName(), intent, serviceConnection, i);
    }

    @SuppressLint({"UntrackedBindService"})
    public boolean zza(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i) {
        if (!zzc(context, intent)) {
            return context.bindService(intent, serviceConnection, i);
        }
        Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
        return false;
    }

    public void zzb(Context context, ServiceConnection serviceConnection) {
    }
}
