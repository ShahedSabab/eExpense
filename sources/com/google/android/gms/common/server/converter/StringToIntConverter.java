package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class StringToIntConverter extends AbstractSafeParcelable implements zza<String, Integer> {
    public static final Creator<StringToIntConverter> CREATOR = new zzb();

    /* renamed from: Fb */
    private final HashMap<String, Integer> f362Fb;

    /* renamed from: Fc */
    private final SparseArray<String> f363Fc;

    /* renamed from: Fd */
    private final ArrayList<Entry> f364Fd;
    final int mVersionCode;

    public static final class Entry extends AbstractSafeParcelable {
        public static final Creator<Entry> CREATOR = new zzc();

        /* renamed from: Fe */
        final String f365Fe;

        /* renamed from: Ff */
        final int f366Ff;
        final int versionCode;

        Entry(int i, String str, int i2) {
            this.versionCode = i;
            this.f365Fe = str;
            this.f366Ff = i2;
        }

        Entry(String str, int i) {
            this.versionCode = 1;
            this.f365Fe = str;
            this.f366Ff = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzc.zza(this, parcel, i);
        }
    }

    public StringToIntConverter() {
        this.mVersionCode = 1;
        this.f362Fb = new HashMap<>();
        this.f363Fc = new SparseArray<>();
        this.f364Fd = null;
    }

    StringToIntConverter(int i, ArrayList<Entry> arrayList) {
        this.mVersionCode = i;
        this.f362Fb = new HashMap<>();
        this.f363Fc = new SparseArray<>();
        this.f364Fd = null;
        zzh(arrayList);
    }

    private void zzh(ArrayList<Entry> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzj(entry.f365Fe, entry.f366Ff);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<Entry> zzawy() {
        ArrayList<Entry> arrayList = new ArrayList<>();
        for (String str : this.f362Fb.keySet()) {
            arrayList.add(new Entry(str, ((Integer) this.f362Fb.get(str)).intValue()));
        }
        return arrayList;
    }

    /* renamed from: zzd */
    public String convertBack(Integer num) {
        String str = (String) this.f363Fc.get(num.intValue());
        return (str != null || !this.f362Fb.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public StringToIntConverter zzj(String str, int i) {
        this.f362Fb.put(str, Integer.valueOf(i));
        this.f363Fc.put(i, str);
        return this;
    }
}
