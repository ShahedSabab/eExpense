package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.internal.zzb.zza;
import java.util.concurrent.BlockingQueue;

public class zzc extends Thread {
    private static final boolean DEBUG = zzs.DEBUG;
    private final BlockingQueue<zzk<?>> zzg;
    /* access modifiers changed from: private */
    public final BlockingQueue<zzk<?>> zzh;
    private final zzb zzi;
    private final zzn zzj;
    private volatile boolean zzk = false;

    public zzc(BlockingQueue<zzk<?>> blockingQueue, BlockingQueue<zzk<?>> blockingQueue2, zzb zzb, zzn zzn) {
        super("VolleyCacheDispatcher");
        this.zzg = blockingQueue;
        this.zzh = blockingQueue2;
        this.zzi = zzb;
        this.zzj = zzn;
    }

    public void quit() {
        this.zzk = true;
        interrupt();
    }

    public void run() {
        if (DEBUG) {
            zzs.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzi.initialize();
        while (true) {
            try {
                final zzk zzk2 = (zzk) this.zzg.take();
                zzk2.zzc("cache-queue-take");
                if (zzk2.isCanceled()) {
                    zzk2.zzd("cache-discard-canceled");
                } else {
                    zza zza = this.zzi.zza(zzk2.zzg());
                    if (zza == null) {
                        zzk2.zzc("cache-miss");
                        this.zzh.put(zzk2);
                    } else if (zza.zza()) {
                        zzk2.zzc("cache-hit-expired");
                        zzk2.zza(zza);
                        this.zzh.put(zzk2);
                    } else {
                        zzk2.zzc("cache-hit");
                        zzm zza2 = zzk2.zza(new zzi(zza.data, zza.zzf));
                        zzk2.zzc("cache-hit-parsed");
                        if (!zza.zzb()) {
                            this.zzj.zza(zzk2, zza2);
                        } else {
                            zzk2.zzc("cache-hit-refresh-needed");
                            zzk2.zza(zza);
                            zza2.zzbh = true;
                            this.zzj.zza(zzk2, zza2, new Runnable() {
                                public void run() {
                                    try {
                                        zzc.this.zzh.put(zzk2);
                                    } catch (InterruptedException e) {
                                    }
                                }
                            });
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
