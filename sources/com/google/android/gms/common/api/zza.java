package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class zza extends Exception {

    /* renamed from: hv */
    protected final Status f167hv;

    public zza(@NonNull Status status) {
        super(status.getStatusMessage());
        this.f167hv = status;
    }
}
