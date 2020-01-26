package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<Scope> CREATOR = new zzf();
    final int mVersionCode;

    /* renamed from: xY */
    private final String f157xY;

    Scope(int i, String str) {
        zzaa.zzh(str, "scopeUri must not be null or empty");
        this.mVersionCode = i;
        this.f157xY = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.f157xY.equals(((Scope) obj).f157xY);
    }

    public int hashCode() {
        return this.f157xY.hashCode();
    }

    public String toString() {
        return this.f157xY;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public String zzari() {
        return this.f157xY;
    }
}
