package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.gms.common.zze;

public final class zzi {

    /* renamed from: Gr */
    private static Boolean f434Gr;

    /* renamed from: Gs */
    private static Boolean f435Gs;

    /* renamed from: Gt */
    private static Boolean f436Gt;

    /* renamed from: Gu */
    private static Boolean f437Gu;

    public static boolean zzaym() {
        boolean z = zze.f465xb;
        return "user".equals(Build.TYPE);
    }

    public static boolean zzb(Resources resources) {
        boolean z = false;
        if (resources == null) {
            return false;
        }
        if (f434Gr == null) {
            boolean z2 = (resources.getConfiguration().screenLayout & 15) > 3;
            if ((zzs.zzayn() && z2) || zzc(resources)) {
                z = true;
            }
            f434Gr = Boolean.valueOf(z);
        }
        return f434Gr.booleanValue();
    }

    @TargetApi(13)
    private static boolean zzc(Resources resources) {
        if (f435Gs == null) {
            Configuration configuration = resources.getConfiguration();
            f435Gs = Boolean.valueOf(zzs.zzayp() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600);
        }
        return f435Gs.booleanValue();
    }

    @TargetApi(20)
    public static boolean zzci(Context context) {
        if (f436Gt == null) {
            f436Gt = Boolean.valueOf(zzs.zzayv() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return f436Gt.booleanValue();
    }

    @TargetApi(21)
    public static boolean zzcj(Context context) {
        if (f437Gu == null) {
            f437Gu = Boolean.valueOf(zzs.zzayx() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return f437Gu.booleanValue();
    }
}
