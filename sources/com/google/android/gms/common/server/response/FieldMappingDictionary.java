package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FieldMappingDictionary extends AbstractSafeParcelable {
    public static final Creator<FieldMappingDictionary> CREATOR = new zzc();

    /* renamed from: Fq */
    private final HashMap<String, Map<String, Field<?, ?>>> f377Fq;

    /* renamed from: Fr */
    private final ArrayList<Entry> f378Fr = null;

    /* renamed from: Fs */
    private final String f379Fs;
    final int mVersionCode;

    public static class Entry extends AbstractSafeParcelable {
        public static final Creator<Entry> CREATOR = new zzd();

        /* renamed from: Ft */
        final ArrayList<FieldMapPair> f380Ft;
        final String className;
        final int versionCode;

        Entry(int i, String str, ArrayList<FieldMapPair> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.f380Ft = arrayList;
        }

        Entry(String str, Map<String, Field<?, ?>> map) {
            this.versionCode = 1;
            this.className = str;
            this.f380Ft = zzaw(map);
        }

        private static ArrayList<FieldMapPair> zzaw(Map<String, Field<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (String str : map.keySet()) {
                arrayList.add(new FieldMapPair(str, (Field) map.get(str)));
            }
            return arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzd.zza(this, parcel, i);
        }

        /* access modifiers changed from: 0000 */
        public HashMap<String, Field<?, ?>> zzaxo() {
            HashMap<String, Field<?, ?>> hashMap = new HashMap<>();
            int size = this.f380Ft.size();
            for (int i = 0; i < size; i++) {
                FieldMapPair fieldMapPair = (FieldMapPair) this.f380Ft.get(i);
                hashMap.put(fieldMapPair.zzcb, fieldMapPair.f381Fu);
            }
            return hashMap;
        }
    }

    public static class FieldMapPair extends AbstractSafeParcelable {
        public static final Creator<FieldMapPair> CREATOR = new zzb();

        /* renamed from: Fu */
        final Field<?, ?> f381Fu;
        final int versionCode;
        final String zzcb;

        FieldMapPair(int i, String str, Field<?, ?> field) {
            this.versionCode = i;
            this.zzcb = str;
            this.f381Fu = field;
        }

        FieldMapPair(String str, Field<?, ?> field) {
            this.versionCode = 1;
            this.zzcb = str;
            this.f381Fu = field;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzb.zza(this, parcel, i);
        }
    }

    FieldMappingDictionary(int i, ArrayList<Entry> arrayList, String str) {
        this.mVersionCode = i;
        this.f377Fq = zzi(arrayList);
        this.f379Fs = (String) zzaa.zzy(str);
        zzaxl();
    }

    private static HashMap<String, Map<String, Field<?, ?>>> zzi(ArrayList<Entry> arrayList) {
        HashMap<String, Map<String, Field<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Entry entry = (Entry) arrayList.get(i);
            hashMap.put(entry.className, entry.zzaxo());
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.f377Fq.keySet()) {
            sb.append(str).append(":\n");
            Map map = (Map) this.f377Fq.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ").append(str2).append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    public void zzaxl() {
        for (String str : this.f377Fq.keySet()) {
            Map map = (Map) this.f377Fq.get(str);
            for (String str2 : map.keySet()) {
                ((Field) map.get(str2)).zza(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<Entry> zzaxm() {
        ArrayList<Entry> arrayList = new ArrayList<>();
        for (String str : this.f377Fq.keySet()) {
            arrayList.add(new Entry(str, (Map) this.f377Fq.get(str)));
        }
        return arrayList;
    }

    public String zzaxn() {
        return this.f379Fs;
    }

    public Map<String, Field<?, ?>> zzig(String str) {
        return (Map) this.f377Fq.get(str);
    }
}
