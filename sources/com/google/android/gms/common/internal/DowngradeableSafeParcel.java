package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {

    /* renamed from: DQ */
    private static final Object f243DQ = new Object();

    /* renamed from: DR */
    private static ClassLoader f244DR = null;

    /* renamed from: DS */
    private static Integer f245DS = null;

    /* renamed from: DT */
    private boolean f246DT = false;

    protected static ClassLoader zzavy() {
        synchronized (f243DQ) {
        }
        return null;
    }

    protected static Integer zzavz() {
        synchronized (f243DQ) {
        }
        return null;
    }

    private static boolean zzd(Class<?> cls) {
        boolean z = false;
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get(null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return z;
        }
    }

    protected static boolean zzhu(String str) {
        ClassLoader zzavy = zzavy();
        if (zzavy == null) {
            return true;
        }
        try {
            return zzd(zzavy.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzawa() {
        return false;
    }
}
