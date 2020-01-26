package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class zzaql extends zzapk<Time> {
    public static final zzapl bpG = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            if (zzaqo.mo10723bB() == Time.class) {
                return new zzaql();
            }
            return null;
        }
    };
    private final DateFormat bqg = new SimpleDateFormat("hh:mm:ss a");

    public synchronized void zza(zzaqr zzaqr, Time time) throws IOException {
        zzaqr.zzut(time == null ? null : this.bqg.format(time));
    }

    /* renamed from: zzn */
    public synchronized Time zzb(zzaqp zzaqp) throws IOException {
        Time time;
        if (zzaqp.mo10624bq() == zzaqq.NULL) {
            zzaqp.nextNull();
            time = null;
        } else {
            try {
                time = new Time(this.bqg.parse(zzaqp.nextString()).getTime());
            } catch (ParseException e) {
                throw new zzaph((Throwable) e);
            }
        }
        return time;
    }
}
