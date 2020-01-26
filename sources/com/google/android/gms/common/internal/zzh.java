package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.internal.zzrp;

public abstract class zzh implements OnClickListener {
    public static zzh zza(final Activity activity, final Intent intent, final int i) {
        return new zzh() {
            public void zzavx() {
                if (intent != null) {
                    activity.startActivityForResult(intent, i);
                }
            }
        };
    }

    public static zzh zza(@NonNull final Fragment fragment, final Intent intent, final int i) {
        return new zzh() {
            public void zzavx() {
                if (intent != null) {
                    fragment.startActivityForResult(intent, i);
                }
            }
        };
    }

    public static zzh zza(@NonNull final zzrp zzrp, final Intent intent, final int i) {
        return new zzh() {
            @TargetApi(11)
            public void zzavx() {
                if (intent != null) {
                    zzrp.startActivityForResult(intent, i);
                }
            }
        };
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zzavx();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzavx();
}
