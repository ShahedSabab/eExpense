package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional, ReflectedParcelable {
    public static final Creator<GoogleSignInOptions> CREATOR = new zzb();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();

    /* renamed from: jm */
    private static Comparator<Scope> f86jm = new Comparator<Scope>() {
        /* renamed from: zza */
        public int compare(Scope scope, Scope scope2) {
            return scope.zzari().compareTo(scope2.zzari());
        }
    };

    /* renamed from: jn */
    public static final Scope f87jn = new Scope(Scopes.PROFILE);

    /* renamed from: jo */
    public static final Scope f88jo = new Scope("email");

    /* renamed from: jp */
    public static final Scope f89jp = new Scope("openid");
    /* access modifiers changed from: private */

    /* renamed from: gj */
    public Account f90gj;
    /* access modifiers changed from: private */

    /* renamed from: jq */
    public final ArrayList<Scope> f91jq;
    /* access modifiers changed from: private */

    /* renamed from: jr */
    public boolean f92jr;
    /* access modifiers changed from: private */

    /* renamed from: js */
    public final boolean f93js;
    /* access modifiers changed from: private */

    /* renamed from: jt */
    public final boolean f94jt;
    /* access modifiers changed from: private */

    /* renamed from: ju */
    public String f95ju;
    /* access modifiers changed from: private */

    /* renamed from: jv */
    public String f96jv;
    final int versionCode;

    public static final class Builder {

        /* renamed from: gj */
        private Account f97gj;

        /* renamed from: jr */
        private boolean f98jr;

        /* renamed from: js */
        private boolean f99js;

        /* renamed from: jt */
        private boolean f100jt;

        /* renamed from: ju */
        private String f101ju;

        /* renamed from: jv */
        private String f102jv;

        /* renamed from: jw */
        private Set<Scope> f103jw = new HashSet();

        public Builder() {
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            zzaa.zzy(googleSignInOptions);
            this.f103jw = new HashSet(googleSignInOptions.f91jq);
            this.f99js = googleSignInOptions.f93js;
            this.f100jt = googleSignInOptions.f94jt;
            this.f98jr = googleSignInOptions.f92jr;
            this.f101ju = googleSignInOptions.f95ju;
            this.f97gj = googleSignInOptions.f90gj;
            this.f102jv = googleSignInOptions.f96jv;
        }

        private String zzgc(String str) {
            zzaa.zzib(str);
            zzaa.zzb(this.f101ju == null || this.f101ju.equals(str), (Object) "two different server client ids provided");
            return str;
        }

        public GoogleSignInOptions build() {
            if (this.f98jr && (this.f97gj == null || !this.f103jw.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions((Set) this.f103jw, this.f97gj, this.f98jr, this.f99js, this.f100jt, this.f101ju, this.f102jv);
        }

        public Builder requestEmail() {
            this.f103jw.add(GoogleSignInOptions.f88jo);
            return this;
        }

        public Builder requestId() {
            this.f103jw.add(GoogleSignInOptions.f89jp);
            return this;
        }

        public Builder requestIdToken(String str) {
            this.f98jr = true;
            this.f101ju = zzgc(str);
            return this;
        }

        public Builder requestProfile() {
            this.f103jw.add(GoogleSignInOptions.f87jn);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.f103jw.add(scope);
            this.f103jw.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public Builder requestServerAuthCode(String str, boolean z) {
            this.f99js = true;
            this.f101ju = zzgc(str);
            this.f100jt = z;
            return this;
        }

        public Builder setAccountName(String str) {
            this.f97gj = new Account(zzaa.zzib(str), "com.google");
            return this;
        }

        public Builder setHostedDomain(String str) {
            this.f102jv = zzaa.zzib(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this.versionCode = i;
        this.f91jq = arrayList;
        this.f90gj = account;
        this.f92jr = z;
        this.f93js = z2;
        this.f94jt = z3;
        this.f95ju = str;
        this.f96jv = str2;
    }

    private GoogleSignInOptions(Set<Scope> set, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this(2, new ArrayList<>(set), account, z, z2, z3, str, str2);
    }

    private JSONObject zzais() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.f91jq, f86jm);
            Iterator it = this.f91jq.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Scope) it.next()).zzari());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.f90gj != null) {
                jSONObject.put("accountName", this.f90gj.name);
            }
            jSONObject.put("idTokenRequested", this.f92jr);
            jSONObject.put("forceCodeForRefreshToken", this.f94jt);
            jSONObject.put("serverAuthRequested", this.f93js);
            if (!TextUtils.isEmpty(this.f95ju)) {
                jSONObject.put("serverClientId", this.f95ju);
            }
            if (!TextUtils.isEmpty(this.f96jv)) {
                jSONObject.put("hostedDomain", this.f96jv);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInOptions zzgb(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(hashSet, !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.f91jq.size() != googleSignInOptions.zzait().size() || !this.f91jq.containsAll(googleSignInOptions.zzait())) {
                return false;
            }
            if (this.f90gj == null) {
                if (googleSignInOptions.getAccount() != null) {
                    return false;
                }
            } else if (!this.f90gj.equals(googleSignInOptions.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.f95ju)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzaix())) {
                    return false;
                }
            } else if (!this.f95ju.equals(googleSignInOptions.zzaix())) {
                return false;
            }
            return this.f94jt == googleSignInOptions.zzaiw() && this.f92jr == googleSignInOptions.zzaiu() && this.f93js == googleSignInOptions.zzaiv();
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.f90gj;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.f91jq.toArray(new Scope[this.f91jq.size()]);
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f91jq.iterator();
        while (it.hasNext()) {
            arrayList.add(((Scope) it.next()).zzari());
        }
        Collections.sort(arrayList);
        return new zze().zzq(arrayList).zzq(this.f90gj).zzq(this.f95ju).zzbe(this.f94jt).zzbe(this.f92jr).zzbe(this.f93js).zzajf();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public String zzaiq() {
        return zzais().toString();
    }

    public ArrayList<Scope> zzait() {
        return new ArrayList<>(this.f91jq);
    }

    public boolean zzaiu() {
        return this.f92jr;
    }

    public boolean zzaiv() {
        return this.f93js;
    }

    public boolean zzaiw() {
        return this.f94jt;
    }

    public String zzaix() {
        return this.f95ju;
    }

    public String zzaiy() {
        return this.f96jv;
    }
}
