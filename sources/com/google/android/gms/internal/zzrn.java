package com.google.android.gms.internal;

import android.app.Activity;
import android.support.p000v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzs;

public class zzrn {

    /* renamed from: Be */
    private final Object f724Be;

    public zzrn(Activity activity) {
        zzaa.zzb(activity, (Object) "Activity must not be null");
        zzaa.zzb(zzs.zzayn() || (activity instanceof FragmentActivity), (Object) "This Activity is not supported before platform version 11 (3.0 Honeycomb). Please use FragmentActivity instead.");
        this.f724Be = activity;
    }

    public boolean zzatv() {
        return this.f724Be instanceof FragmentActivity;
    }

    public Activity zzatw() {
        return (Activity) this.f724Be;
    }

    public FragmentActivity zzatx() {
        return (FragmentActivity) this.f724Be;
    }
}
