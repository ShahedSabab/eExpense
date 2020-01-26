package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzsi<T> {

    /* renamed from: BL */
    private static zza f773BL = null;

    /* renamed from: BM */
    private static int f774BM = 0;
    private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static final Object zzaox = new Object();

    /* renamed from: BN */
    private T f775BN = null;
    protected final String zzbcn;
    protected final T zzbco;

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    protected zzsi(String str, T t) {
        this.zzbcn = str;
        this.zzbco = t;
    }

    public static zzsi<Float> zza(String str, Float f) {
        return new zzsi<Float>(str, f) {
            /* access modifiers changed from: protected */
            /* renamed from: zzhm */
            public Float zzhi(String str) {
                return zzsi.zzauh().zzb(this.zzbcn, (Float) this.zzbco);
            }
        };
    }

    public static zzsi<Integer> zza(String str, Integer num) {
        return new zzsi<Integer>(str, num) {
            /* access modifiers changed from: protected */
            /* renamed from: zzhl */
            public Integer zzhi(String str) {
                return zzsi.zzauh().zzb(this.zzbcn, (Integer) this.zzbco);
            }
        };
    }

    public static zzsi<Long> zza(String str, Long l) {
        return new zzsi<Long>(str, l) {
            /* access modifiers changed from: protected */
            /* renamed from: zzhk */
            public Long zzhi(String str) {
                return zzsi.zzauh().getLong(this.zzbcn, (Long) this.zzbco);
            }
        };
    }

    public static zzsi<String> zzaa(String str, String str2) {
        return new zzsi<String>(str, str2) {
            /* access modifiers changed from: protected */
            /* renamed from: zzhn */
            public String zzhi(String str) {
                return zzsi.zzauh().getString(this.zzbcn, (String) this.zzbco);
            }
        };
    }

    static /* synthetic */ zza zzauh() {
        return null;
    }

    public static zzsi<Boolean> zzk(String str, boolean z) {
        return new zzsi<Boolean>(str, Boolean.valueOf(z)) {
            /* access modifiers changed from: protected */
            /* renamed from: zzhj */
            public Boolean zzhi(String str) {
                return zzsi.zzauh().zza(this.zzbcn, (Boolean) this.zzbco);
            }
        };
    }

    public final T get() {
        long clearCallingIdentity;
        try {
            return zzhi(this.zzbcn);
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            T zzhi = zzhi(this.zzbcn);
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzhi;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public abstract T zzhi(String str);
}
