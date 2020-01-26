package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.C0459R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.internal.zzy;

@Deprecated
public final class zzrk {

    /* renamed from: Ba */
    private static zzrk f720Ba;
    private static final Object zzaox = new Object();

    /* renamed from: Bb */
    private final Status f721Bb;

    /* renamed from: Bc */
    private final boolean f722Bc;

    /* renamed from: Bd */
    private final boolean f723Bd;
    private final String zzctj;

    zzrk(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(C0459R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            if (z2) {
                z = false;
            }
            this.f723Bd = z;
            z = z2;
        } else {
            this.f723Bd = false;
        }
        this.f722Bc = z;
        String zzcd = zzy.zzcd(context);
        if (zzcd == null) {
            zzcd = new zzah(context).getString("google_app_id");
        }
        if (TextUtils.isEmpty(zzcd)) {
            this.f721Bb = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzctj = null;
            return;
        }
        this.zzctj = zzcd;
        this.f721Bb = Status.f158xZ;
    }

    public static String zzatt() {
        return zzhh("getGoogleAppId").zzctj;
    }

    public static boolean zzatu() {
        return zzhh("isMeasurementExplicitlyDisabled").f723Bd;
    }

    public static Status zzby(Context context) {
        Status status;
        zzaa.zzb(context, (Object) "Context must not be null.");
        synchronized (zzaox) {
            if (f720Ba == null) {
                f720Ba = new zzrk(context);
            }
            status = f720Ba.f721Bb;
        }
        return status;
    }

    private static zzrk zzhh(String str) {
        zzrk zzrk;
        synchronized (zzaox) {
            if (f720Ba == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            zzrk = f720Ba;
        }
        return zzrk;
    }
}
