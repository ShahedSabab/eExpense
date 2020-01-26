package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Creator<ResolveAccountRequest> CREATOR = new zzab();

    /* renamed from: EH */
    private final int f255EH;

    /* renamed from: EI */
    private final GoogleSignInAccount f256EI;

    /* renamed from: gj */
    private final Account f257gj;
    final int mVersionCode;

    ResolveAccountRequest(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.mVersionCode = i;
        this.f257gj = account;
        this.f255EH = i2;
        this.f256EI = googleSignInAccount;
    }

    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public Account getAccount() {
        return this.f257gj;
    }

    public int getSessionId() {
        return this.f255EH;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzab.zza(this, parcel, i);
    }

    @Nullable
    public GoogleSignInAccount zzawl() {
        return this.f256EI;
    }
}
