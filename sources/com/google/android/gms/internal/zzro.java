package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzro {

    /* renamed from: Bf */
    protected final zzrp f725Bf;

    protected zzro(zzrp zzrp) {
        this.f725Bf = zzrp;
    }

    protected static zzrp zzc(zzrn zzrn) {
        return zzrn.zzatv() ? zzsd.zza(zzrn.zzatx()) : zzrq.zzt(zzrn.zzatw());
    }

    public static zzrp zzs(Activity activity) {
        return zzc(new zzrn(activity));
    }

    @MainThread
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity getActivity() {
        return this.f725Bf.zzaty();
    }

    @MainThread
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @MainThread
    public void onCreate(Bundle bundle) {
    }

    @MainThread
    public void onDestroy() {
    }

    @MainThread
    public void onSaveInstanceState(Bundle bundle) {
    }

    @MainThread
    public void onStart() {
    }

    @MainThread
    public void onStop() {
    }
}
