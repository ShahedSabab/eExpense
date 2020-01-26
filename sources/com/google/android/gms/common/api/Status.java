package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Creator<Status> CREATOR = new zzg();

    /* renamed from: xZ */
    public static final Status f158xZ = new Status(0);

    /* renamed from: ya */
    public static final Status f159ya = new Status(14);

    /* renamed from: yb */
    public static final Status f160yb = new Status(8);

    /* renamed from: yc */
    public static final Status f161yc = new Status(15);

    /* renamed from: yd */
    public static final Status f162yd = new Status(16);

    /* renamed from: ye */
    public static final Status f163ye = new Status(17);

    /* renamed from: yf */
    public static final Status f164yf = new Status(18);
    private final PendingIntent mPendingIntent;
    final int mVersionCode;

    /* renamed from: uo */
    private final int f165uo;

    /* renamed from: wP */
    private final String f166wP;

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.f165uo = i2;
        this.f166wP = str;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.mVersionCode == status.mVersionCode && this.f165uo == status.f165uo && zzz.equal(this.f166wP, status.f166wP) && zzz.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.f165uo;
    }

    @Nullable
    public String getStatusMessage() {
        return this.f166wP;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.f165uo), this.f166wP, this.mPendingIntent);
    }

    public boolean isCanceled() {
        return this.f165uo == 16;
    }

    public boolean isInterrupted() {
        return this.f165uo == 14;
    }

    public boolean isSuccess() {
        return this.f165uo <= 0;
    }

    public void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public String toString() {
        return zzz.zzx(this).zzg("statusCode", zzark()).zzg("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    /* access modifiers changed from: 0000 */
    public PendingIntent zzarj() {
        return this.mPendingIntent;
    }

    public String zzark() {
        return this.f166wP != null ? this.f166wP : CommonStatusCodes.getStatusCodeString(this.f165uo);
    }
}
