package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.server.response.FieldMappingDictionary.Entry;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;
import java.util.ArrayList;

public class zzd implements Creator<Entry> {
    static void zza(Entry entry, Parcel parcel, int i) {
        int zzcs = zzb.zzcs(parcel);
        zzb.zzc(parcel, 1, entry.versionCode);
        zzb.zza(parcel, 2, entry.className, false);
        zzb.zzc(parcel, 3, entry.f380Ft, false);
        zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzda */
    public Entry createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
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
                    arrayList = zza.zzc(parcel, zzcq, FieldMapPair.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzcq);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new Entry(i, str, arrayList);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }

    /* renamed from: zzhd */
    public Entry[] newArray(int i) {
        return new Entry[i];
    }
}