package com.google.android.gms.internal;

import android.content.Context;

public class zzsz {

    /* renamed from: GQ */
    private static zzsz f807GQ = new zzsz();

    /* renamed from: GP */
    private zzsy f808GP = null;

    public static zzsy zzco(Context context) {
        return f807GQ.zzcn(context);
    }

    public synchronized zzsy zzcn(Context context) {
        if (this.f808GP == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.f808GP = new zzsy(context);
        }
        return this.f808GP;
    }
}
