package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class zzapd {
    public zzaoy zza(Reader reader) throws zzaoz, zzaph {
        try {
            zzaqp zzaqp = new zzaqp(reader);
            zzaoy zzh = zzh(zzaqp);
            if (zzh.mo10507aY() || zzaqp.mo10624bq() == zzaqq.END_DOCUMENT) {
                return zzh;
            }
            throw new zzaph("Did not consume the entire document.");
        } catch (zzaqs e) {
            throw new zzaph((Throwable) e);
        } catch (IOException e2) {
            throw new zzaoz((Throwable) e2);
        } catch (NumberFormatException e3) {
            throw new zzaph((Throwable) e3);
        }
    }

    public zzaoy zzh(zzaqp zzaqp) throws zzaoz, zzaph {
        boolean isLenient = zzaqp.isLenient();
        zzaqp.setLenient(true);
        try {
            zzaoy zzh = zzapz.zzh(zzaqp);
            zzaqp.setLenient(isLenient);
            return zzh;
        } catch (StackOverflowError e) {
            String valueOf = String.valueOf(zzaqp);
            throw new zzapc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed parsing JSON source: ").append(valueOf).append(" to Json").toString(), e);
        } catch (OutOfMemoryError e2) {
            String valueOf2 = String.valueOf(zzaqp);
            throw new zzapc(new StringBuilder(String.valueOf(valueOf2).length() + 36).append("Failed parsing JSON source: ").append(valueOf2).append(" to Json").toString(), e2);
        } catch (Throwable th) {
            zzaqp.setLenient(isLenient);
            throw th;
        }
    }

    public zzaoy zzuq(String str) throws zzaph {
        return zza(new StringReader(str));
    }
}
