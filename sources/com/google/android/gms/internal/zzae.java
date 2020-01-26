package com.google.android.gms.internal;

import java.io.IOException;

public interface zzae {

    public static final class zza extends zzaru<zza> {
        public String stackTrace;
        public String zzcs;
        public Long zzct;
        public String zzcu;
        public String zzcv;
        public Long zzcw;
        public Long zzcx;
        public String zzcy;
        public Long zzcz;
        public String zzda;

        public zza() {
            this.zzcs = null;
            this.zzct = null;
            this.stackTrace = null;
            this.zzcu = null;
            this.zzcv = null;
            this.zzcw = null;
            this.zzcx = null;
            this.zzcy = null;
            this.zzcz = null;
            this.zzda = null;
            this.btP = -1;
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzcs != null) {
                zzart.zzq(1, this.zzcs);
            }
            if (this.zzct != null) {
                zzart.zzb(2, this.zzct.longValue());
            }
            if (this.stackTrace != null) {
                zzart.zzq(3, this.stackTrace);
            }
            if (this.zzcu != null) {
                zzart.zzq(4, this.zzcu);
            }
            if (this.zzcv != null) {
                zzart.zzq(5, this.zzcv);
            }
            if (this.zzcw != null) {
                zzart.zzb(6, this.zzcw.longValue());
            }
            if (this.zzcx != null) {
                zzart.zzb(7, this.zzcx.longValue());
            }
            if (this.zzcy != null) {
                zzart.zzq(8, this.zzcy);
            }
            if (this.zzcz != null) {
                zzart.zzb(9, this.zzcz.longValue());
            }
            if (this.zzda != null) {
                zzart.zzq(10, this.zzda);
            }
            super.zza(zzart);
        }

        /* renamed from: zze */
        public zza zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        this.zzcs = zzars.readString();
                        continue;
                    case 16:
                        this.zzct = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 26:
                        this.stackTrace = zzars.readString();
                        continue;
                    case 34:
                        this.zzcu = zzars.readString();
                        continue;
                    case 42:
                        this.zzcv = zzars.readString();
                        continue;
                    case 48:
                        this.zzcw = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 56:
                        this.zzcx = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 66:
                        this.zzcy = zzars.readString();
                        continue;
                    case 72:
                        this.zzcz = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 82:
                        this.zzda = zzars.readString();
                        continue;
                    default:
                        if (!super.zza(zzars, bU)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzx() {
            int zzx = super.zzx();
            if (this.zzcs != null) {
                zzx += zzart.zzr(1, this.zzcs);
            }
            if (this.zzct != null) {
                zzx += zzart.zzf(2, this.zzct.longValue());
            }
            if (this.stackTrace != null) {
                zzx += zzart.zzr(3, this.stackTrace);
            }
            if (this.zzcu != null) {
                zzx += zzart.zzr(4, this.zzcu);
            }
            if (this.zzcv != null) {
                zzx += zzart.zzr(5, this.zzcv);
            }
            if (this.zzcw != null) {
                zzx += zzart.zzf(6, this.zzcw.longValue());
            }
            if (this.zzcx != null) {
                zzx += zzart.zzf(7, this.zzcx.longValue());
            }
            if (this.zzcy != null) {
                zzx += zzart.zzr(8, this.zzcy);
            }
            if (this.zzcz != null) {
                zzx += zzart.zzf(9, this.zzcz.longValue());
            }
            return this.zzda != null ? zzx + zzart.zzr(10, this.zzda) : zzx;
        }
    }
}
