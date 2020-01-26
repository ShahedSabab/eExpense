package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzsi;

public final class zzb {

    /* renamed from: FH */
    public static zzsi<Integer> f409FH = zzsi.zza("gms:common:stats:max_num_of_events", Integer.valueOf(100));

    /* renamed from: FI */
    public static zzsi<Integer> f410FI = zzsi.zza("gms:common:stats:max_chunk_size", Integer.valueOf(100));

    public static final class zza {

        /* renamed from: FJ */
        public static zzsi<Integer> f411FJ = zzsi.zza("gms:common:stats:connections:level", Integer.valueOf(zzc.LOG_LEVEL_OFF));

        /* renamed from: FK */
        public static zzsi<String> f412FK = zzsi.zzaa("gms:common:stats:connections:ignored_calling_processes", "");

        /* renamed from: FL */
        public static zzsi<String> f413FL = zzsi.zzaa("gms:common:stats:connections:ignored_calling_services", "");

        /* renamed from: FM */
        public static zzsi<String> f414FM = zzsi.zzaa("gms:common:stats:connections:ignored_target_processes", "");

        /* renamed from: FN */
        public static zzsi<String> f415FN = zzsi.zzaa("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");

        /* renamed from: FO */
        public static zzsi<Long> f416FO = zzsi.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000));
    }

    /* renamed from: com.google.android.gms.common.stats.zzb$zzb reason: collision with other inner class name */
    public static final class C0744zzb {

        /* renamed from: FJ */
        public static zzsi<Integer> f417FJ = zzsi.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzc.LOG_LEVEL_OFF));

        /* renamed from: FO */
        public static zzsi<Long> f418FO = zzsi.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000));
    }
}
