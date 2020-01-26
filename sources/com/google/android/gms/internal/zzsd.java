package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzsd extends Fragment implements zzrp {

    /* renamed from: Bg */
    private static WeakHashMap<FragmentActivity, WeakReference<zzsd>> f746Bg = new WeakHashMap<>();

    /* renamed from: Bh */
    private Map<String, zzro> f747Bh = new ArrayMap();
    /* access modifiers changed from: private */

    /* renamed from: Bi */
    public Bundle f748Bi;
    /* access modifiers changed from: private */
    public int zzbtt = 0;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzsd zza(android.support.p000v4.app.FragmentActivity r3) {
        /*
            java.util.WeakHashMap<android.support.v4.app.FragmentActivity, java.lang.ref.WeakReference<com.google.android.gms.internal.zzsd>> r0 = f746Bg
            java.lang.Object r0 = r0.get(r3)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r0.get()
            com.google.android.gms.internal.zzsd r0 = (com.google.android.gms.internal.zzsd) r0
            if (r0 == 0) goto L_0x0013
        L_0x0012:
            return r0
        L_0x0013:
            android.support.v4.app.FragmentManager r0 = r3.getSupportFragmentManager()     // Catch:{ ClassCastException -> 0x0048 }
            java.lang.String r1 = "SupportLifecycleFragmentImpl"
            android.support.v4.app.Fragment r0 = r0.findFragmentByTag(r1)     // Catch:{ ClassCastException -> 0x0048 }
            com.google.android.gms.internal.zzsd r0 = (com.google.android.gms.internal.zzsd) r0     // Catch:{ ClassCastException -> 0x0048 }
            if (r0 == 0) goto L_0x0027
            boolean r1 = r0.isRemoving()
            if (r1 == 0) goto L_0x003d
        L_0x0027:
            com.google.android.gms.internal.zzsd r0 = new com.google.android.gms.internal.zzsd
            r0.<init>()
            android.support.v4.app.FragmentManager r1 = r3.getSupportFragmentManager()
            android.support.v4.app.FragmentTransaction r1 = r1.beginTransaction()
            java.lang.String r2 = "SupportLifecycleFragmentImpl"
            android.support.v4.app.FragmentTransaction r1 = r1.add(r0, r2)
            r1.commitAllowingStateLoss()
        L_0x003d:
            java.util.WeakHashMap<android.support.v4.app.FragmentActivity, java.lang.ref.WeakReference<com.google.android.gms.internal.zzsd>> r1 = f746Bg
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r0)
            r1.put(r3, r2)
            goto L_0x0012
        L_0x0048:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsd.zza(android.support.v4.app.FragmentActivity):com.google.android.gms.internal.zzsd");
    }

    private void zzb(final String str, @NonNull final zzro zzro) {
        if (this.zzbtt > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (zzsd.this.zzbtt >= 1) {
                        zzro.onCreate(zzsd.this.f748Bi != null ? zzsd.this.f748Bi.getBundle(str) : null);
                    }
                    if (zzsd.this.zzbtt >= 2) {
                        zzro.onStart();
                    }
                    if (zzsd.this.zzbtt >= 3) {
                        zzro.onStop();
                    }
                    if (zzsd.this.zzbtt >= 4) {
                        zzro.onDestroy();
                    }
                }
            });
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzro dump : this.f747Bh.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzro onActivityResult : this.f747Bh.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbtt = 1;
        this.f748Bi = bundle;
        for (Entry entry : this.f747Bh.entrySet()) {
            ((zzro) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.zzbtt = 4;
        for (zzro onDestroy : this.f747Bh.values()) {
            onDestroy.onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.f747Bh.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzro) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        this.zzbtt = 2;
        for (zzro onStart : this.f747Bh.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        this.zzbtt = 3;
        for (zzro onStop : this.f747Bh.values()) {
            onStop.onStop();
        }
    }

    public <T extends zzro> T zza(String str, Class<T> cls) {
        return (zzro) cls.cast(this.f747Bh.get(str));
    }

    public void zza(String str, @NonNull zzro zzro) {
        if (!this.f747Bh.containsKey(str)) {
            this.f747Bh.put(str, zzro);
            zzb(str, zzro);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
    }

    /* renamed from: zzaub */
    public FragmentActivity zzaty() {
        return getActivity();
    }
}
