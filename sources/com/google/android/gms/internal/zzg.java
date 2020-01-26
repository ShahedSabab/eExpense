package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class zzg extends Thread {
    private final zzb zzi;
    private final zzn zzj;
    private volatile boolean zzk = false;
    private final BlockingQueue<zzk<?>> zzx;
    private final zzf zzy;

    public zzg(BlockingQueue<zzk<?>> blockingQueue, zzf zzf, zzb zzb, zzn zzn) {
        super("VolleyNetworkDispatcher");
        this.zzx = blockingQueue;
        this.zzy = zzf;
        this.zzi = zzb;
        this.zzj = zzn;
    }

    @TargetApi(14)
    private void zzb(zzk<?> zzk2) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(zzk2.zzf());
        }
    }

    private void zzb(zzk<?> zzk2, zzr zzr) {
        this.zzj.zza(zzk2, zzk2.zzb(zzr));
    }

    public void quit() {
        this.zzk = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                zzk zzk2 = (zzk) this.zzx.take();
                try {
                    zzk2.zzc("network-queue-take");
                    if (zzk2.isCanceled()) {
                        zzk2.zzd("network-discard-cancelled");
                    } else {
                        zzb(zzk2);
                        zzi zza = this.zzy.zza(zzk2);
                        zzk2.zzc("network-http-complete");
                        if (!zza.zzaa || !zzk2.zzu()) {
                            zzm zza2 = zzk2.zza(zza);
                            zzk2.zzc("network-parse-complete");
                            if (zzk2.zzp() && zza2.zzbf != null) {
                                this.zzi.zza(zzk2.zzg(), zza2.zzbf);
                                zzk2.zzc("network-cache-written");
                            }
                            zzk2.zzt();
                            this.zzj.zza(zzk2, zza2);
                        } else {
                            zzk2.zzd("not-modified");
                        }
                    }
                } catch (zzr e) {
                    e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    zzb(zzk2, e);
                } catch (Exception e2) {
                    zzs.zza(e2, "Unhandled exception %s", e2.toString());
                    zzr zzr = new zzr((Throwable) e2);
                    zzr.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza(zzk2, zzr);
                }
            } catch (InterruptedException e3) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
