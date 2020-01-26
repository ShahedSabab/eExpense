package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR = new zza();

    /* renamed from: jf */
    public static zze f74jf = zzh.zzayl();

    /* renamed from: jm */
    private static Comparator<Scope> f75jm = new Comparator<Scope>() {
        /* renamed from: zza */
        public int compare(Scope scope, Scope scope2) {
            return scope.zzari().compareTo(scope2.zzari());
        }
    };

    /* renamed from: hR */
    List<Scope> f76hR;

    /* renamed from: iF */
    private String f77iF;

    /* renamed from: is */
    private String f78is;

    /* renamed from: it */
    private String f79it;

    /* renamed from: jg */
    private String f80jg;

    /* renamed from: jh */
    private String f81jh;

    /* renamed from: ji */
    private Uri f82ji;

    /* renamed from: jj */
    private String f83jj;

    /* renamed from: jk */
    private long f84jk;

    /* renamed from: jl */
    private String f85jl;
    final int versionCode;
    private String zzboa;

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzboa = str;
        this.f77iF = str2;
        this.f80jg = str3;
        this.f81jh = str4;
        this.f82ji = uri;
        this.f83jj = str5;
        this.f84jk = j;
        this.f85jl = str6;
        this.f76hR = list;
        this.f78is = str7;
        this.f79it = str8;
    }

    public static GoogleSignInAccount zza(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Uri uri, @Nullable Long l, @NonNull String str7, @NonNull Set<Scope> set) {
        if (l == null) {
            l = Long.valueOf(f74jf.currentTimeMillis() / 1000);
        }
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l.longValue(), zzaa.zzib(str7), new ArrayList((Collection) zzaa.zzy(set)), str5, str6);
    }

    private JSONObject zzais() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.f84jk);
            jSONObject.put("obfuscatedIdentifier", zzaip());
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.f76hR, f75jm);
            for (Scope zzari : this.f76hR) {
                jSONArray.put(zzari.zzari());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInAccount zzfz(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl", null);
        Uri uri = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        return zza(jSONObject.optString("id"), jSONObject.optString("tokenId", null), jSONObject.optString("email", null), jSONObject.optString("displayName", null), jSONObject.optString("givenName", null), jSONObject.optString("familyName", null), uri, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet).zzga(jSONObject.optString("serverAuthCode", null));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        return ((GoogleSignInAccount) obj).zzaiq().equals(zzaiq());
    }

    @Nullable
    public String getDisplayName() {
        return this.f81jh;
    }

    @Nullable
    public String getEmail() {
        return this.f80jg;
    }

    @Nullable
    public String getFamilyName() {
        return this.f79it;
    }

    @Nullable
    public String getGivenName() {
        return this.f78is;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.f76hR);
    }

    @Nullable
    public String getId() {
        return this.zzboa;
    }

    @Nullable
    public String getIdToken() {
        return this.f77iF;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.f82ji;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.f83jj;
    }

    public int hashCode() {
        return zzaiq().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public boolean zza() {
        return f74jf.currentTimeMillis() / 1000 >= this.f84jk - 300;
    }

    public long zzaio() {
        return this.f84jk;
    }

    @NonNull
    public String zzaip() {
        return this.f85jl;
    }

    public String zzaiq() {
        return zzais().toString();
    }

    public String zzair() {
        JSONObject zzais = zzais();
        zzais.remove("serverAuthCode");
        return zzais.toString();
    }

    public GoogleSignInAccount zzga(String str) {
        this.f83jj = str;
        return this;
    }
}
