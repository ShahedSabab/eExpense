package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.C0735zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    public static final Creator<SafeParcelResponse> CREATOR = new zze();

    /* renamed from: Fo */
    private final FieldMappingDictionary f382Fo;

    /* renamed from: Fv */
    private final Parcel f383Fv;

    /* renamed from: Fw */
    private final int f384Fw = 2;

    /* renamed from: Fx */
    private int f385Fx;

    /* renamed from: Fy */
    private int f386Fy;
    private final String mClassName;
    private final int mVersionCode;

    SafeParcelResponse(int i, Parcel parcel, FieldMappingDictionary fieldMappingDictionary) {
        this.mVersionCode = i;
        this.f383Fv = (Parcel) zzaa.zzy(parcel);
        this.f382Fo = fieldMappingDictionary;
        if (this.f382Fo == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.f382Fo.zzaxn();
        }
        this.f385Fx = 2;
    }

    private void zza(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(zzp.zzii(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzc.zzq((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzc.zzr((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                zzq.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void zza(StringBuilder sb, Field<?, ?> field, Parcel parcel, int i) {
        switch (field.zzaxc()) {
            case 0:
                zzb(sb, field, zza(field, Integer.valueOf(zza.zzg(parcel, i))));
                return;
            case 1:
                zzb(sb, field, zza(field, zza.zzk(parcel, i)));
                return;
            case 2:
                zzb(sb, field, zza(field, Long.valueOf(zza.zzi(parcel, i))));
                return;
            case 3:
                zzb(sb, field, zza(field, Float.valueOf(zza.zzl(parcel, i))));
                return;
            case 4:
                zzb(sb, field, zza(field, Double.valueOf(zza.zzn(parcel, i))));
                return;
            case 5:
                zzb(sb, field, zza(field, zza.zzp(parcel, i)));
                return;
            case 6:
                zzb(sb, field, zza(field, Boolean.valueOf(zza.zzc(parcel, i))));
                return;
            case 7:
                zzb(sb, field, zza(field, zza.zzq(parcel, i)));
                return;
            case 8:
            case 9:
                zzb(sb, field, zza(field, zza.zzt(parcel, i)));
                return;
            case 10:
                zzb(sb, field, zza(field, zzq(zza.zzs(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.zzaxc());
        }
    }

    private void zza(StringBuilder sb, String str, Field<?, ?> field, Parcel parcel, int i) {
        sb.append("\"").append(str).append("\":");
        if (field.zzaxi()) {
            zza(sb, field, parcel, i);
        } else {
            zzb(sb, field, parcel, i);
        }
    }

    private void zza(StringBuilder sb, Map<String, Field<?, ?>> map, Parcel parcel) {
        SparseArray zzax = zzax(map);
        sb.append('{');
        int zzcr = zza.zzcr(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = zza.zzcq(parcel);
            Entry entry = (Entry) zzax.get(zza.zzgu(zzcq));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                zza(sb, (String) entry.getKey(), (Field) entry.getValue(), parcel, zzcq);
                z = true;
            }
        }
        if (parcel.dataPosition() != zzcr) {
            throw new C0735zza("Overread allowed size end=" + zzcr, parcel);
        }
        sb.append('}');
    }

    private static SparseArray<Entry<String, Field<?, ?>>> zzax(Map<String, Field<?, ?>> map) {
        SparseArray<Entry<String, Field<?, ?>>> sparseArray = new SparseArray<>();
        for (Entry entry : map.entrySet()) {
            sparseArray.put(((Field) entry.getValue()).zzaxf(), entry);
        }
        return sparseArray;
    }

    private void zzb(StringBuilder sb, Field<?, ?> field, Parcel parcel, int i) {
        if (field.zzaxd()) {
            sb.append("[");
            switch (field.zzaxc()) {
                case 0:
                    zzb.zza(sb, zza.zzw(parcel, i));
                    break;
                case 1:
                    zzb.zza(sb, (T[]) zza.zzy(parcel, i));
                    break;
                case 2:
                    zzb.zza(sb, zza.zzx(parcel, i));
                    break;
                case 3:
                    zzb.zza(sb, zza.zzz(parcel, i));
                    break;
                case 4:
                    zzb.zza(sb, zza.zzaa(parcel, i));
                    break;
                case 5:
                    zzb.zza(sb, (T[]) zza.zzab(parcel, i));
                    break;
                case 6:
                    zzb.zza(sb, zza.zzv(parcel, i));
                    break;
                case 7:
                    zzb.zza(sb, zza.zzac(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] zzag = zza.zzag(parcel, i);
                    int length = zzag.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        zzag[i2].setDataPosition(0);
                        zza(sb, field.zzaxk(), zzag[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (field.zzaxc()) {
            case 0:
                sb.append(zza.zzg(parcel, i));
                return;
            case 1:
                sb.append(zza.zzk(parcel, i));
                return;
            case 2:
                sb.append(zza.zzi(parcel, i));
                return;
            case 3:
                sb.append(zza.zzl(parcel, i));
                return;
            case 4:
                sb.append(zza.zzn(parcel, i));
                return;
            case 5:
                sb.append(zza.zzp(parcel, i));
                return;
            case 6:
                sb.append(zza.zzc(parcel, i));
                return;
            case 7:
                sb.append("\"").append(zzp.zzii(zza.zzq(parcel, i))).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzc.zzq(zza.zzt(parcel, i))).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzc.zzr(zza.zzt(parcel, i)));
                sb.append("\"");
                return;
            case 10:
                Bundle zzs = zza.zzs(parcel, i);
                Set<String> keySet = zzs.keySet();
                keySet.size();
                sb.append("{");
                boolean z = true;
                for (String str : keySet) {
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"").append(str).append("\"");
                    sb.append(":");
                    sb.append("\"").append(zzp.zzii(zzs.getString(str))).append("\"");
                    z = false;
                }
                sb.append("}");
                return;
            case 11:
                Parcel zzaf = zza.zzaf(parcel, i);
                zzaf.setDataPosition(0);
                zza(sb, field.zzaxk(), zzaf);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void zzb(StringBuilder sb, Field<?, ?> field, Object obj) {
        if (field.zzaxb()) {
            zzb(sb, field, (ArrayList) obj);
        } else {
            zza(sb, field.zzaxa(), obj);
        }
    }

    private void zzb(StringBuilder sb, Field<?, ?> field, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            zza(sb, field.zzaxa(), arrayList.get(i));
        }
        sb.append("]");
    }

    public static HashMap<String, String> zzq(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        zzaa.zzb(this.f382Fo, (Object) "Cannot convert to JSON on client side.");
        Parcel zzaxp = zzaxp();
        zzaxp.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.f382Fo.zzig(this.mClassName), zzaxp);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public Map<String, Field<?, ?>> zzawz() {
        if (this.f382Fo == null) {
            return null;
        }
        return this.f382Fo.zzig(this.mClassName);
    }

    public Parcel zzaxp() {
        switch (this.f385Fx) {
            case 0:
                this.f386Fy = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(this.f383Fv);
                com.google.android.gms.common.internal.safeparcel.zzb.zzaj(this.f383Fv, this.f386Fy);
                this.f385Fx = 2;
                break;
            case 1:
                com.google.android.gms.common.internal.safeparcel.zzb.zzaj(this.f383Fv, this.f386Fy);
                this.f385Fx = 2;
                break;
        }
        return this.f383Fv;
    }

    /* access modifiers changed from: 0000 */
    public FieldMappingDictionary zzaxq() {
        switch (this.f384Fw) {
            case 0:
                return null;
            case 1:
                return this.f382Fo;
            case 2:
                return this.f382Fo;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.f384Fw);
        }
    }

    public Object zzic(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public boolean zzid(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
}
