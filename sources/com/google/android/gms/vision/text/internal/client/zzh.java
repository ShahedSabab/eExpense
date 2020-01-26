package com.google.android.gms.vision.text.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<TextRecognizerOptions> {
    static void zza(TextRecognizerOptions textRecognizerOptions, Parcel parcel, int i) {
        int zzcs = zzb.zzcs(parcel);
        zzb.zzc(parcel, 1, textRecognizerOptions.versionCode);
        zzb.zzaj(parcel, zzcs);
    }

    /* renamed from: zzabr */
    public TextRecognizerOptions[] newArray(int i) {
        return new TextRecognizerOptions[i];
    }

    /* renamed from: zzta */
    public TextRecognizerOptions createFromParcel(Parcel parcel) {
        int zzcr = zza.zzcr(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = zza.zzcq(parcel);
            switch (zza.zzgu(zzcq)) {
                case 1:
                    i = zza.zzg(parcel, zzcq);
                    break;
                default:
                    zza.zzb(parcel, zzcq);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new TextRecognizerOptions(i);
        }
        throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
    }
}
