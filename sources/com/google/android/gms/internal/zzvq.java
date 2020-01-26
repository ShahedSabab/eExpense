package com.google.android.gms.internal;

import android.os.RemoteException;

public abstract class zzvq<T> {
    private final int zzbcm;
    private final String zzbcn;
    private final T zzbco;

    public static class zza extends zzvq<Boolean> {
        public zza(int i, String str, Boolean bool) {
            super(i, str, bool);
        }

        /* renamed from: zzb */
        public Boolean zza(zzvt zzvt) {
            try {
                return Boolean.valueOf(zzvt.getBooleanFlagValue(getKey(), ((Boolean) zzlp()).booleanValue(), getSource()));
            } catch (RemoteException e) {
                return (Boolean) zzlp();
            }
        }
    }

    public static class zzb extends zzvq<Integer> {
        public zzb(int i, String str, Integer num) {
            super(i, str, num);
        }

        /* renamed from: zzc */
        public Integer zza(zzvt zzvt) {
            try {
                return Integer.valueOf(zzvt.getIntFlagValue(getKey(), ((Integer) zzlp()).intValue(), getSource()));
            } catch (RemoteException e) {
                return (Integer) zzlp();
            }
        }
    }

    public static class zzc extends zzvq<Long> {
        public zzc(int i, String str, Long l) {
            super(i, str, l);
        }

        /* renamed from: zzd */
        public Long zza(zzvt zzvt) {
            try {
                return Long.valueOf(zzvt.getLongFlagValue(getKey(), ((Long) zzlp()).longValue(), getSource()));
            } catch (RemoteException e) {
                return (Long) zzlp();
            }
        }
    }

    public static class zzd extends zzvq<String> {
        public zzd(int i, String str, String str2) {
            super(i, str, str2);
        }

        /* renamed from: zze */
        public String zza(zzvt zzvt) {
            try {
                return zzvt.getStringFlagValue(getKey(), (String) zzlp(), getSource());
            } catch (RemoteException e) {
                return (String) zzlp();
            }
        }
    }

    private zzvq(int i, String str, T t) {
        this.zzbcm = i;
        this.zzbcn = str;
        this.zzbco = t;
        zzvu.zzbhe().zza(this);
    }

    public static zza zzb(int i, String str, Boolean bool) {
        return new zza(i, str, bool);
    }

    public static zzb zzb(int i, String str, int i2) {
        return new zzb(i, str, Integer.valueOf(i2));
    }

    public static zzc zzb(int i, String str, long j) {
        return new zzc(i, str, Long.valueOf(j));
    }

    public static zzd zzc(int i, String str, String str2) {
        return new zzd(i, str, str2);
    }

    public T get() {
        return zzvu.zzbhf().zzb(this);
    }

    public String getKey() {
        return this.zzbcn;
    }

    public int getSource() {
        return this.zzbcm;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(zzvt zzvt);

    public T zzlp() {
        return this.zzbco;
    }
}
