package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.zza;

public class ConverterWrapper extends AbstractSafeParcelable {
    public static final Creator<ConverterWrapper> CREATOR = new zza();

    /* renamed from: Fa */
    private final StringToIntConverter f361Fa;
    final int mVersionCode;

    ConverterWrapper(int i, StringToIntConverter stringToIntConverter) {
        this.mVersionCode = i;
        this.f361Fa = stringToIntConverter;
    }

    private ConverterWrapper(StringToIntConverter stringToIntConverter) {
        this.mVersionCode = 1;
        this.f361Fa = stringToIntConverter;
    }

    public static ConverterWrapper zza(zza<?, ?> zza) {
        if (zza instanceof StringToIntConverter) {
            return new ConverterWrapper((StringToIntConverter) zza);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    /* access modifiers changed from: 0000 */
    public StringToIntConverter zzaww() {
        return this.f361Fa;
    }

    public zza<?, ?> zzawx() {
        if (this.f361Fa != null) {
            return this.f361Fa;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
