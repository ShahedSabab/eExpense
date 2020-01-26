package com.google.android.gms.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class zzaqd extends zzapk<Date> {
    public static final zzapl bpG = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            if (zzaqo.mo10723bB() == Date.class) {
                return new zzaqd();
            }
            return null;
        }
    };
    private final DateFormat bnQ = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat bnR = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat bnS = m73bp();

    /* renamed from: bp */
    private static DateFormat m73bp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    private synchronized Date zzur(String str) {
        Date parse;
        try {
            parse = this.bnR.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.bnQ.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.bnS.parse(str);
                } catch (ParseException e3) {
                    throw new zzaph(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void zza(zzaqr zzaqr, Date date) throws IOException {
        if (date == null) {
            zzaqr.mo10641bA();
        } else {
            zzaqr.zzut(this.bnQ.format(date));
        }
    }

    /* renamed from: zzk */
    public Date zzb(zzaqp zzaqp) throws IOException {
        if (zzaqp.mo10624bq() != zzaqq.NULL) {
            return zzur(zzaqp.nextString());
        }
        zzaqp.nextNull();
        return null;
    }
}
