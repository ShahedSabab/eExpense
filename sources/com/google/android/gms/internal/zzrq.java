package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@TargetApi(11)
public final class zzrq extends Fragment implements zzrp {

    /* renamed from: Bg */
    private static WeakHashMap<Activity, WeakReference<zzrq>> f726Bg = new WeakHashMap<>();

    /* renamed from: Bh */
    private Map<String, zzro> f727Bh = new ArrayMap();
    /* access modifiers changed from: private */

    /* renamed from: Bi */
    public Bundle f728Bi;
    /* access modifiers changed from: private */
    public int zzbtt = 0;

    private void zzb(final String str, @NonNull final zzro zzro) {
        if (this.zzbtt > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (zzrq.this.zzbtt >= 1) {
                        zzro.onCreate(zzrq.this.f728Bi != null ? zzrq.this.f728Bi.getBundle(str) : null);
                    }
                    if (zzrq.this.zzbtt >= 2) {
                        zzro.onStart();
                    }
                    if (zzrq.this.zzbtt >= 3) {
                        zzro.onStop();
                    }
                    if (zzrq.this.zzbtt >= 4) {
                        zzro.onDestroy();
                    }
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzrq zzt(android.app.Activity r3) {
        /*
            java.util.WeakHashMap<android.app.Activity, java.lang.ref.WeakReference<com.google.android.gms.internal.zzrq>> r0 = f726Bg
            java.lang.Object r0 = r0.get(r3)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r0.get()
            com.google.android.gms.internal.zzrq r0 = (com.google.android.gms.internal.zzrq) r0
            if (r0 == 0) goto L_0x0013
        L_0x0012:
            return r0
        L_0x0013:
            android.app.FragmentManager r0 = r3.getFragmentManager()     // Catch:{ ClassCastException -> 0x0048 }
            java.lang.String r1 = "LifecycleFragmentImpl"
            android.app.Fragment r0 = r0.findFragmentByTag(r1)     // Catch:{ ClassCastException -> 0x0048 }
            com.google.android.gms.internal.zzrq r0 = (com.google.android.gms.internal.zzrq) r0     // Catch:{ ClassCastException -> 0x0048 }
            if (r0 == 0) goto L_0x0027
            boolean r1 = r0.isRemoving()
            if (r1 == 0) goto L_0x003d
        L_0x0027:
            com.google.android.gms.internal.zzrq r0 = new com.google.android.gms.internal.zzrq
            r0.<init>()
            android.app.FragmentManager r1 = r3.getFragmentManager()
            android.app.FragmentTransaction r1 = r1.beginTransaction()
            java.lang.String r2 = "LifecycleFragmentImpl"
            android.app.FragmentTransaction r1 = r1.add(r0, r2)
            r1.commitAllowingStateLoss()
        L_0x003d:
            java.util.WeakHashMap<android.app.Activity, java.lang.ref.WeakReference<com.google.android.gms.internal.zzrq>> r1 = f726Bg
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r0)
            r1.put(r3, r2)
            goto L_0x0012
        L_0x0048:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzrq.zzt(android.app.Activity):com.google.android.gms.internal.zzrq");
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzro dump : this.f727Bh.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzro onActivityResult : this.f727Bh.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbtt = 1;
        this.f728Bi = bundle;
        for (Entry entry : this.f727Bh.entrySet()) {
            ((zzro) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.zzbtt = 4;
        for (zzro onDestroy : this.f727Bh.values()) {
            onDestroy.onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.f727Bh.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzro) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        this.zzbtt = 2;
        for (zzro onStart : this.f727Bh.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        this.zzbtt = 3;
        for (zzro onStop : this.f727Bh.values()) {
            onStop.onStop();
        }
    }

    public <T extends zzro> T zza(String str, Class<T> cls) {
        return (zzro) cls.cast(this.f727Bh.get(str));
    }

    public void zza(String str, @NonNull zzro zzro) {
        if (!this.f727Bh.containsKey(str)) {
            this.f727Bh.put(str, zzro);
            zzb(str, zzro);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
    }

    public Activity zzaty() {
        return getActivity();
    }
}
