package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException {

    /* renamed from: hM */
    private final int f116hM;

    GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.f116hM = i;
    }

    public int getConnectionStatusCode() {
        return this.f116hM;
    }
}
