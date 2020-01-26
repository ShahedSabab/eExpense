package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C0459R;

public class zzah {

    /* renamed from: EP */
    private final Resources f273EP;

    /* renamed from: EQ */
    private final String f274EQ = this.f273EP.getResourcePackageName(C0459R.string.common_google_play_services_unknown_issue);

    public zzah(Context context) {
        zzaa.zzy(context);
        this.f273EP = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.f273EP.getIdentifier(str, "string", this.f274EQ);
        if (identifier == 0) {
            return null;
        }
        return this.f273EP.getString(identifier);
    }
}
