package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class zzaqk extends zzapk<Date> {
    public static final zzapl bpG = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            if (zzaqo.mo10723bB() == Date.class) {
                return new zzaqk();
            }
            return null;
        }
    };
    private final DateFormat bqg = new SimpleDateFormat("MMM d, yyyy");

    public synchronized void zza(zzaqr zzaqr, Date date) throws IOException {
        zzaqr.zzut(date == null ? null : this.bqg.format(date));
    }

    /* renamed from: zzm */
    public synchronized Date zzb(zzaqp zzaqp) throws IOException {
        Date date;
        if (zzaqp.mo10624bq() == zzaqq.NULL) {
            zzaqp.nextNull();
            date = null;
        } else {
            try {
                date = new Date(this.bqg.parse(zzaqp.nextString()).getTime());
            } catch (ParseException e) {
                throw new zzaph((Throwable) e);
            }
        }
        return date;
    }
}
