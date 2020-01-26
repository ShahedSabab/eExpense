package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.C0459R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzsz;

public final class zzg {

    /* renamed from: DO */
    private static final SimpleArrayMap<String, String> f320DO = new SimpleArrayMap<>();

    public static String zzcb(Context context) {
        String str = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String packageName = context.getPackageName();
        try {
            return zzsz.zzco(context).zzik(context.getPackageName()).toString();
        } catch (NameNotFoundException e) {
            return packageName;
        }
    }

    @Nullable
    public static String zzg(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(C0459R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(C0459R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(C0459R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zzu(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zzu(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zzu(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zzu(context, "common_google_play_services_restricted_profile_title");
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i);
                return null;
        }
    }

    private static String zzg(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String zzu = zzu(context, str);
        if (zzu == null) {
            zzu = resources.getString(C0459R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, zzu, new Object[]{str2});
    }

    @NonNull
    public static String zzh(Context context, int i) {
        String zzg = i == 6 ? zzu(context, "common_google_play_services_resolution_required_title") : zzg(context, i);
        return zzg == null ? context.getResources().getString(C0459R.string.common_google_play_services_notification_ticker) : zzg;
    }

    @NonNull
    public static String zzi(Context context, int i) {
        Resources resources = context.getResources();
        String zzcb = zzcb(context);
        switch (i) {
            case 1:
                return resources.getString(C0459R.string.common_google_play_services_install_text, new Object[]{zzcb});
            case 2:
                if (zzi.zzci(context)) {
                    return resources.getString(C0459R.string.common_google_play_services_wear_update_text);
                }
                return resources.getString(C0459R.string.common_google_play_services_update_text, new Object[]{zzcb});
            case 3:
                return resources.getString(C0459R.string.common_google_play_services_enable_text, new Object[]{zzcb});
            case 5:
                return zzg(context, "common_google_play_services_invalid_account_text", zzcb);
            case 7:
                return zzg(context, "common_google_play_services_network_error_text", zzcb);
            case 9:
                return resources.getString(C0459R.string.common_google_play_services_unsupported_text, new Object[]{zzcb});
            case 16:
                return zzg(context, "common_google_play_services_api_unavailable_text", zzcb);
            case 17:
                return zzg(context, "common_google_play_services_sign_in_failed_text", zzcb);
            case 18:
                return resources.getString(C0459R.string.common_google_play_services_updating_text, new Object[]{zzcb});
            case 20:
                return zzg(context, "common_google_play_services_restricted_profile_text", zzcb);
            default:
                return resources.getString(C0459R.string.common_google_play_services_unknown_issue, new Object[]{zzcb});
        }
    }

    @NonNull
    public static String zzj(Context context, int i) {
        return i == 6 ? zzg(context, "common_google_play_services_resolution_required_text", zzcb(context)) : zzi(context, i);
    }

    @NonNull
    public static String zzk(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(C0459R.string.common_google_play_services_install_button);
            case 2:
                return resources.getString(C0459R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(C0459R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    @Nullable
    private static String zzu(Context context, String str) {
        synchronized (f320DO) {
            String str2 = (String) f320DO.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String str3 = "GoogleApiAvailability";
                String str4 = "Missing resource: ";
                String valueOf = String.valueOf(str);
                Log.w(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String str5 = "GoogleApiAvailability";
                String str6 = "Got empty resource: ";
                String valueOf2 = String.valueOf(str);
                Log.w(str5, valueOf2.length() != 0 ? str6.concat(valueOf2) : new String(str6));
                return null;
            }
            f320DO.put(str, string);
            return string;
        }
    }
}
