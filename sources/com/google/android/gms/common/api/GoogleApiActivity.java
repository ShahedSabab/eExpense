package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.zzrh;

public class GoogleApiActivity extends Activity implements OnCancelListener {

    /* renamed from: xD */
    protected int f135xD = 0;

    public static PendingIntent zza(Context context, PendingIntent pendingIntent, int i) {
        return zza(context, pendingIntent, i, true);
    }

    public static PendingIntent zza(Context context, PendingIntent pendingIntent, int i, boolean z) {
        return PendingIntent.getActivity(context, 0, zzb(context, pendingIntent, i, z), 134217728);
    }

    private void zza(int i, zzrh zzrh) {
        switch (i) {
            case -1:
                zzrh.zzarm();
                return;
            case 0:
                zzrh.zza(new ConnectionResult(13, null), getIntent().getIntExtra("failing_client_id", -1));
                return;
            default:
                return;
        }
    }

    private void zzarb() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e("GoogleApiActivity", "Activity started without extras");
            finish();
            return;
        }
        PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
        Integer num = (Integer) extras.get("error_code");
        if (pendingIntent == null && num == null) {
            Log.e("GoogleApiActivity", "Activity started without resolution");
            finish();
        } else if (pendingIntent != null) {
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                this.f135xD = 1;
            } catch (SendIntentException e) {
                Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e);
                finish();
            }
        } else {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(this, num.intValue(), 2, this);
            this.f135xD = 1;
        }
    }

    public static Intent zzb(Context context, PendingIntent pendingIntent, int i, boolean z) {
        Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", z);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.f135xD = 0;
            setResultCode(i2);
            if (booleanExtra) {
                zza(i2, zzrh.zzbx(this));
            }
        } else if (i == 2) {
            this.f135xD = 0;
            setResultCode(i2);
        }
        finish();
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.f135xD = 0;
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f135xD = bundle.getInt("resolution");
        }
        if (this.f135xD != 1) {
            zzarb();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("resolution", this.f135xD);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void setResultCode(int i) {
        setResult(i);
    }
}