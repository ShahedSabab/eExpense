package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder.zza;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {

    /* renamed from: BW */
    private static final String[] f204BW = {"data"};

    /* renamed from: BX */
    private final Creator<T> f205BX;

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.f205BX = creator;
    }

    public static <T extends SafeParcelable> void zza(zza zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }

    public static zza zzaum() {
        return DataHolder.zzc(f204BW);
    }

    /* renamed from: zzfz */
    public T get(int i) {
        byte[] zzg = this.f178zy.zzg("data", i, this.f178zy.zzga(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) this.f205BX.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}
