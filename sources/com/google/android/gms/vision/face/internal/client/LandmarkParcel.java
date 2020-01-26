package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class LandmarkParcel extends AbstractSafeParcelable {
    public static final Creator<LandmarkParcel> CREATOR = new zzf();
    public final int type;
    public final int versionCode;

    /* renamed from: x */
    public final float f850x;

    /* renamed from: y */
    public final float f851y;

    public LandmarkParcel(int i, float f, float f2, int i2) {
        this.versionCode = i;
        this.f850x = f;
        this.f851y = f2;
        this.type = i2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}
