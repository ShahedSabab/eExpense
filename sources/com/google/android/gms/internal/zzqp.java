package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public abstract class zzqp extends zzro implements OnCancelListener {
    protected boolean mStarted;

    /* renamed from: xP */
    protected final GoogleApiAvailability f530xP;
    /* access modifiers changed from: private */

    /* renamed from: yA */
    public ConnectionResult f531yA;
    /* access modifiers changed from: private */

    /* renamed from: yB */
    public int f532yB;

    /* renamed from: yC */
    private final Handler f533yC;

    /* renamed from: yz */
    protected boolean f534yz;

    private class zza implements Runnable {
        private zza() {
        }

        @MainThread
        public void run() {
            if (zzqp.this.mStarted) {
                if (zzqp.this.f531yA.hasResolution()) {
                    zzqp.this.f725Bf.startActivityForResult(GoogleApiActivity.zzb(zzqp.this.getActivity(), zzqp.this.f531yA.getResolution(), zzqp.this.f532yB, false), 1);
                } else if (zzqp.this.f530xP.isUserResolvableError(zzqp.this.f531yA.getErrorCode())) {
                    zzqp.this.f530xP.zza(zzqp.this.getActivity(), zzqp.this.f725Bf, zzqp.this.f531yA.getErrorCode(), 2, zzqp.this);
                } else if (zzqp.this.f531yA.getErrorCode() == 18) {
                    final Dialog zza = zzqp.this.f530xP.zza(zzqp.this.getActivity(), (OnCancelListener) zzqp.this);
                    zzqp.this.f530xP.zza(zzqp.this.getActivity().getApplicationContext(), (com.google.android.gms.internal.zzrj.zza) new com.google.android.gms.internal.zzrj.zza() {
                        public void zzarr() {
                            zzqp.this.zzarq();
                            if (zza.isShowing()) {
                                zza.dismiss();
                            }
                        }
                    });
                } else {
                    zzqp.this.zza(zzqp.this.f531yA, zzqp.this.f532yB);
                }
            }
        }
    }

    protected zzqp(zzrp zzrp) {
        this(zzrp, GoogleApiAvailability.getInstance());
    }

    zzqp(zzrp zzrp, GoogleApiAvailability googleApiAvailability) {
        super(zzrp);
        this.f532yB = -1;
        this.f533yC = new Handler(Looper.getMainLooper());
        this.f530xP = googleApiAvailability;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r6, int r7, android.content.Intent r8) {
        /*
            r5 = this;
            r4 = 18
            r2 = 13
            r0 = 1
            r1 = 0
            switch(r6) {
                case 1: goto L_0x0027;
                case 2: goto L_0x0010;
                default: goto L_0x0009;
            }
        L_0x0009:
            r0 = r1
        L_0x000a:
            if (r0 == 0) goto L_0x003d
            r5.zzarq()
        L_0x000f:
            return
        L_0x0010:
            com.google.android.gms.common.GoogleApiAvailability r2 = r5.f530xP
            android.app.Activity r3 = r5.getActivity()
            int r2 = r2.isGooglePlayServicesAvailable(r3)
            if (r2 != 0) goto L_0x0047
        L_0x001c:
            com.google.android.gms.common.ConnectionResult r1 = r5.f531yA
            int r1 = r1.getErrorCode()
            if (r1 != r4) goto L_0x000a
            if (r2 != r4) goto L_0x000a
            goto L_0x000f
        L_0x0027:
            r3 = -1
            if (r7 == r3) goto L_0x000a
            if (r7 != 0) goto L_0x0009
            if (r8 == 0) goto L_0x0045
            java.lang.String r0 = "<<ResolutionFailureErrorDetail>>"
            int r0 = r8.getIntExtra(r0, r2)
        L_0x0034:
            com.google.android.gms.common.ConnectionResult r2 = new com.google.android.gms.common.ConnectionResult
            r3 = 0
            r2.<init>(r0, r3)
            r5.f531yA = r2
            goto L_0x0009
        L_0x003d:
            com.google.android.gms.common.ConnectionResult r0 = r5.f531yA
            int r1 = r5.f532yB
            r5.zza(r0, r1)
            goto L_0x000f
        L_0x0045:
            r0 = r2
            goto L_0x0034
        L_0x0047:
            r0 = r1
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqp.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), this.f532yB);
        zzarq();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f534yz = bundle.getBoolean("resolving_error", false);
            if (this.f534yz) {
                this.f532yB = bundle.getInt("failed_client_id", -1);
                this.f531yA = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.f534yz);
        if (this.f534yz) {
            bundle.putInt("failed_client_id", this.f532yB);
            bundle.putInt("failed_status", this.f531yA.getErrorCode());
            bundle.putParcelable("failed_resolution", this.f531yA.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ConnectionResult connectionResult, int i);

    /* access modifiers changed from: protected */
    public abstract void zzarm();

    /* access modifiers changed from: protected */
    public void zzarq() {
        this.f532yB = -1;
        this.f534yz = false;
        this.f531yA = null;
        zzarm();
    }

    public void zzb(ConnectionResult connectionResult, int i) {
        if (!this.f534yz) {
            this.f534yz = true;
            this.f532yB = i;
            this.f531yA = connectionResult;
            this.f533yC.post(new zza());
        }
    }
}
