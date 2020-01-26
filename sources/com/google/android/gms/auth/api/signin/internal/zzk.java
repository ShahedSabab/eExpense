package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzk {

    /* renamed from: jS */
    private static final Lock f106jS = new ReentrantLock();

    /* renamed from: jT */
    private static zzk f107jT;

    /* renamed from: jU */
    private final Lock f108jU = new ReentrantLock();

    /* renamed from: jV */
    private final SharedPreferences f109jV;

    zzk(Context context) {
        this.f109jV = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzk zzba(Context context) {
        zzaa.zzy(context);
        f106jS.lock();
        try {
            if (f107jT == null) {
                f107jT = new zzk(context.getApplicationContext());
            }
            return f107jT;
        } finally {
            f106jS.unlock();
        }
    }

    private String zzx(String str, String str2) {
        String valueOf = String.valueOf(":");
        return new StringBuilder(String.valueOf(str).length() + 0 + String.valueOf(valueOf).length() + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    /* access modifiers changed from: 0000 */
    public void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzaa.zzy(googleSignInAccount);
        zzaa.zzy(googleSignInOptions);
        String zzaip = googleSignInAccount.zzaip();
        zzw(zzx("googleSignInAccount", zzaip), googleSignInAccount.zzair());
        zzw(zzx("googleSignInOptions", zzaip), googleSignInOptions.zzaiq());
    }

    public GoogleSignInAccount zzajm() {
        return zzgd(zzgf("defaultGoogleSignInAccount"));
    }

    public GoogleSignInOptions zzajn() {
        return zzge(zzgf("defaultGoogleSignInAccount"));
    }

    public void zzajo() {
        String zzgf = zzgf("defaultGoogleSignInAccount");
        zzgh("defaultGoogleSignInAccount");
        zzgg(zzgf);
    }

    public void zzb(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzaa.zzy(googleSignInAccount);
        zzaa.zzy(googleSignInOptions);
        zzw("defaultGoogleSignInAccount", googleSignInAccount.zzaip());
        zza(googleSignInAccount, googleSignInOptions);
    }

    /* access modifiers changed from: 0000 */
    public GoogleSignInAccount zzgd(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (TextUtils.isEmpty(str)) {
            return googleSignInAccount;
        }
        String zzgf = zzgf(zzx("googleSignInAccount", str));
        if (zzgf == null) {
            return googleSignInAccount;
        }
        try {
            return GoogleSignInAccount.zzfz(zzgf);
        } catch (JSONException e) {
            return googleSignInAccount;
        }
    }

    /* access modifiers changed from: 0000 */
    public GoogleSignInOptions zzge(String str) {
        GoogleSignInOptions googleSignInOptions = null;
        if (TextUtils.isEmpty(str)) {
            return googleSignInOptions;
        }
        String zzgf = zzgf(zzx("googleSignInOptions", str));
        if (zzgf == null) {
            return googleSignInOptions;
        }
        try {
            return GoogleSignInOptions.zzgb(zzgf);
        } catch (JSONException e) {
            return googleSignInOptions;
        }
    }

    /* access modifiers changed from: protected */
    public String zzgf(String str) {
        this.f108jU.lock();
        try {
            return this.f109jV.getString(str, null);
        } finally {
            this.f108jU.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzgg(String str) {
        if (!TextUtils.isEmpty(str)) {
            zzgh(zzx("googleSignInAccount", str));
            zzgh(zzx("googleSignInOptions", str));
        }
    }

    /* access modifiers changed from: protected */
    public void zzgh(String str) {
        this.f108jU.lock();
        try {
            this.f109jV.edit().remove(str).apply();
        } finally {
            this.f108jU.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void zzw(String str, String str2) {
        this.f108jU.lock();
        try {
            this.f109jV.edit().putString(str, str2).apply();
        } finally {
            this.f108jU.unlock();
        }
    }
}
