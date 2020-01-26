package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzk;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.internal.zzxp;
import com.google.android.gms.internal.zzxq;
import com.google.android.gms.signin.internal.zze.zza;

public class zzg extends zzj<zze> implements zzxp {

    /* renamed from: DM */
    private Integer f844DM;
    private final Bundle aDk;
    private final boolean aDv;

    /* renamed from: zP */
    private final zzf f845zP;

    public zzg(Context context, Looper looper, boolean z, zzf zzf, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzf, connectionCallbacks, onConnectionFailedListener);
        this.aDv = z;
        this.f845zP = zzf;
        this.aDk = bundle;
        this.f844DM = zzf.zzavw();
    }

    public zzg(Context context, Looper looper, boolean z, zzf zzf, zzxq zzxq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, zzf, zza(zzf), connectionCallbacks, onConnectionFailedListener);
    }

    public static Bundle zza(zzf zzf) {
        zzxq zzavv = zzf.zzavv();
        Integer zzavw = zzf.zzavw();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzf.getAccount());
        if (zzavw != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zzavw.intValue());
        }
        if (zzavv != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzavv.zzcdd());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzavv.zzaiu());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzavv.zzaix());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzavv.zzaiw());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzavv.zzaiy());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzavv.zzcde());
            if (zzavv.zzcdf() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzavv.zzcdf().longValue());
            }
            if (zzavv.zzcdg() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzavv.zzcdg().longValue());
            }
        }
        return bundle;
    }

    private ResolveAccountRequest zzcdl() {
        Account zzave = this.f845zP.zzave();
        GoogleSignInAccount googleSignInAccount = null;
        if ("<<default account>>".equals(zzave.name)) {
            googleSignInAccount = zzk.zzba(getContext()).zzajm();
        }
        return new ResolveAccountRequest(zzave, this.f844DM.intValue(), googleSignInAccount);
    }

    public void connect() {
        zza((zze.zzf) new zzi());
    }

    public void zza(zzp zzp, boolean z) {
        try {
            ((zze) zzavg()).zza(zzp, this.f844DM.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzd zzd) {
        zzaa.zzb(zzd, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zze) zzavg()).zza(new SignInRequest(zzcdl()), zzd);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzd.zzb(new SignInResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Bundle zzahv() {
        if (!getContext().getPackageName().equals(this.f845zP.zzavs())) {
            this.aDk.putString("com.google.android.gms.signin.internal.realClientPackageName", this.f845zP.zzavs());
        }
        return this.aDk;
    }

    public boolean zzain() {
        return this.aDv;
    }

    public void zzcdc() {
        try {
            ((zze) zzavg()).zzzv(this.f844DM.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    /* access modifiers changed from: protected */
    public String zzjx() {
        return "com.google.android.gms.signin.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzjy() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzkx */
    public zze zzh(IBinder iBinder) {
        return zza.zzkw(iBinder);
    }
}
