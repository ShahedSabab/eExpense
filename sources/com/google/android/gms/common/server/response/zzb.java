package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;

public class zzb implements Creator<FieldMapPair> {
    static void zza(FieldMapPair fieldMapPair, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, fieldMapPair.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, fieldMapPair.zzcb, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable) fieldMapPair.f381Fu, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzcy */
    public FieldMapPair createFromParcel(Parcel parcel) {
        Field field = null;
        int zzcr = zza.zzcr(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = zza.zzcq(parcel);
            switch (zza.zzgu(zzcq)) {
                case 1:
                    i = zza.zzg(parcel, zzcq);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcq);
                    break;
                case 3:
                    field = (Field) zza.zza(parcel, zzcq, (Creator<T>) Field.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzcq);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new FieldMapPair(i, str, field);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }

    /* renamed from: zzhb */
    public FieldMapPair[] newArray(int i) {
        return new FieldMapPair[i];
    }
}
