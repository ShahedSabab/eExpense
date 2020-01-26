package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zza CREATOR = new zza();

        /* renamed from: Fg */
        protected final int f367Fg;

        /* renamed from: Fh */
        protected final boolean f368Fh;

        /* renamed from: Fi */
        protected final int f369Fi;

        /* renamed from: Fj */
        protected final boolean f370Fj;

        /* renamed from: Fk */
        protected final String f371Fk;

        /* renamed from: Fl */
        protected final int f372Fl;

        /* renamed from: Fm */
        protected final Class<? extends FastJsonResponse> f373Fm;

        /* renamed from: Fn */
        protected final String f374Fn;

        /* renamed from: Fo */
        private FieldMappingDictionary f375Fo;
        /* access modifiers changed from: private */

        /* renamed from: Fp */
        public zza<I, O> f376Fp;
        private final int mVersionCode;

        Field(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, ConverterWrapper converterWrapper) {
            this.mVersionCode = i;
            this.f367Fg = i2;
            this.f368Fh = z;
            this.f369Fi = i3;
            this.f370Fj = z2;
            this.f371Fk = str;
            this.f372Fl = i4;
            if (str2 == null) {
                this.f373Fm = null;
                this.f374Fn = null;
            } else {
                this.f373Fm = SafeParcelResponse.class;
                this.f374Fn = str2;
            }
            if (converterWrapper == null) {
                this.f376Fp = null;
            } else {
                this.f376Fp = converterWrapper.zzawx();
            }
        }

        protected Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, zza<I, O> zza) {
            this.mVersionCode = 1;
            this.f367Fg = i;
            this.f368Fh = z;
            this.f369Fi = i2;
            this.f370Fj = z2;
            this.f371Fk = str;
            this.f372Fl = i3;
            this.f373Fm = cls;
            if (cls == null) {
                this.f374Fn = null;
            } else {
                this.f374Fn = cls.getCanonicalName();
            }
            this.f376Fp = zza;
        }

        public static Field zza(String str, int i, zza<?, ?> zza, boolean z) {
            return new Field(7, z, 0, false, str, i, null, zza);
        }

        public static <T extends FastJsonResponse> Field<T, T> zza(String str, int i, Class<T> cls) {
            return new Field<>(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new Field<>(11, true, 11, true, str, i, cls, null);
        }

        public static Field<Integer, Integer> zzk(String str, int i) {
            return new Field<>(0, false, 0, false, str, i, null, null);
        }

        public static Field<Boolean, Boolean> zzl(String str, int i) {
            return new Field<>(6, false, 6, false, str, i, null, null);
        }

        public static Field<String, String> zzm(String str, int i) {
            return new Field<>(7, false, 7, false, str, i, null, null);
        }

        public I convertBack(O o) {
            return this.f376Fp.convertBack(o);
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            com.google.android.gms.common.internal.zzz.zza zzg = zzz.zzx(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("typeIn", Integer.valueOf(this.f367Fg)).zzg("typeInArray", Boolean.valueOf(this.f368Fh)).zzg("typeOut", Integer.valueOf(this.f369Fi)).zzg("typeOutArray", Boolean.valueOf(this.f370Fj)).zzg("outputFieldName", this.f371Fk).zzg("safeParcelFieldId", Integer.valueOf(this.f372Fl)).zzg("concreteTypeName", zzaxh());
            Class zzaxg = zzaxg();
            if (zzaxg != null) {
                zzg.zzg("concreteType.class", zzaxg.getCanonicalName());
            }
            if (this.f376Fp != null) {
                zzg.zzg("converterName", this.f376Fp.getClass().getCanonicalName());
            }
            return zzg.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zza.zza(this, parcel, i);
        }

        public void zza(FieldMappingDictionary fieldMappingDictionary) {
            this.f375Fo = fieldMappingDictionary;
        }

        public int zzaxa() {
            return this.f367Fg;
        }

        public boolean zzaxb() {
            return this.f368Fh;
        }

        public int zzaxc() {
            return this.f369Fi;
        }

        public boolean zzaxd() {
            return this.f370Fj;
        }

        public String zzaxe() {
            return this.f371Fk;
        }

        public int zzaxf() {
            return this.f372Fl;
        }

        public Class<? extends FastJsonResponse> zzaxg() {
            return this.f373Fm;
        }

        /* access modifiers changed from: 0000 */
        public String zzaxh() {
            if (this.f374Fn == null) {
                return null;
            }
            return this.f374Fn;
        }

        public boolean zzaxi() {
            return this.f376Fp != null;
        }

        /* access modifiers changed from: 0000 */
        public ConverterWrapper zzaxj() {
            if (this.f376Fp == null) {
                return null;
            }
            return ConverterWrapper.zza(this.f376Fp);
        }

        public Map<String, Field<?, ?>> zzaxk() {
            zzaa.zzy(this.f374Fn);
            zzaa.zzy(this.f375Fo);
            return this.f375Fo.zzig(this.f374Fn);
        }
    }

    public interface zza<I, O> {
        I convertBack(O o);
    }

    private void zza(StringBuilder sb, Field field, Object obj) {
        if (field.zzaxa() == 11) {
            sb.append(((FastJsonResponse) field.zzaxg().cast(obj)).toString());
        } else if (field.zzaxa() == 7) {
            sb.append("\"");
            sb.append(zzp.zzii((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private void zza(StringBuilder sb, Field field, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, field, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map zzawz = zzawz();
        StringBuilder sb = new StringBuilder(100);
        for (String str : zzawz.keySet()) {
            Field field = (Field) zzawz.get(str);
            if (zza(field)) {
                Object zza2 = zza(field, zzb(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(str).append("\":");
                if (zza2 != null) {
                    switch (field.zzaxc()) {
                        case 8:
                            sb.append("\"").append(zzc.zzq((byte[]) zza2)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(zzc.zzr((byte[]) zza2)).append("\"");
                            break;
                        case 10:
                            zzq.zza(sb, (HashMap) zza2);
                            break;
                        default:
                            if (!field.zzaxb()) {
                                zza(sb, field, zza2);
                                break;
                            } else {
                                zza(sb, field, (ArrayList) zza2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public <O, I> I zza(Field<I, O> field, Object obj) {
        return field.f376Fp != null ? field.convertBack(obj) : obj;
    }

    /* access modifiers changed from: protected */
    public boolean zza(Field field) {
        return field.zzaxc() == 11 ? field.zzaxd() ? zzif(field.zzaxe()) : zzie(field.zzaxe()) : zzid(field.zzaxe());
    }

    public abstract Map<String, Field<?, ?>> zzawz();

    /* access modifiers changed from: protected */
    public Object zzb(Field field) {
        String zzaxe = field.zzaxe();
        if (field.zzaxg() == null) {
            return zzic(field.zzaxe());
        }
        zzaa.zza(zzic(field.zzaxe()) == null, "Concrete field shouldn't be value object: %s", field.zzaxe());
        if (field.zzaxd()) {
        }
        try {
            char upperCase = Character.toUpperCase(zzaxe.charAt(0));
            String valueOf = String.valueOf(zzaxe.substring(1));
            return getClass().getMethod(new StringBuilder(String.valueOf(valueOf).length() + 4).append("get").append(upperCase).append(valueOf).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zzic(String str);

    /* access modifiers changed from: protected */
    public abstract boolean zzid(String str);

    /* access modifiers changed from: protected */
    public boolean zzie(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public boolean zzif(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
}
