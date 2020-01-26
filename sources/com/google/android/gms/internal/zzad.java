package com.google.android.gms.internal;

import java.io.IOException;

public interface zzad {

    public static final class zza extends zzasa {
        public zzb zzck;
        public zzc zzcl;

        public zza() {
            zzw();
        }

        public static zza zzc(byte[] bArr) throws zzarz {
            return (zza) zzasa.zza(new zza(), bArr);
        }

        /* renamed from: zza */
        public zza zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        if (this.zzck == null) {
                            this.zzck = new zzb();
                        }
                        zzars.zza(this.zzck);
                        continue;
                    case 18:
                        if (this.zzcl == null) {
                            this.zzcl = new zzc();
                        }
                        zzars.zza(this.zzcl);
                        continue;
                    default:
                        if (!zzasd.zzb(zzars, bU)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzck != null) {
                zzart.zza(1, (zzasa) this.zzck);
            }
            if (this.zzcl != null) {
                zzart.zza(2, (zzasa) this.zzcl);
            }
            super.zza(zzart);
        }

        public zza zzw() {
            this.zzck = null;
            this.zzcl = null;
            this.btP = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzx() {
            int zzx = super.zzx();
            if (this.zzck != null) {
                zzx += zzart.zzc(1, (zzasa) this.zzck);
            }
            return this.zzcl != null ? zzx + zzart.zzc(2, (zzasa) this.zzcl) : zzx;
        }
    }

    public static final class zzb extends zzasa {
        public Integer zzcm;

        public zzb() {
            zzy();
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzcm != null) {
                zzart.zzaf(27, this.zzcm.intValue());
            }
            super.zza(zzart);
        }

        /* renamed from: zzc */
        public zzb zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 216:
                        int bY = zzars.mo10742bY();
                        switch (bY) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzcm = Integer.valueOf(bY);
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!zzasd.zzb(zzars, bU)) {
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
            return this.zzcm != null ? zzx + zzart.zzah(27, this.zzcm.intValue()) : zzx;
        }

        public zzb zzy() {
            this.btP = -1;
            return this;
        }
    }

    public static final class zzc extends zzasa {
        public String zzcn;
        public String zzco;
        public String zzcp;
        public String zzcq;
        public String zzcr;

        public zzc() {
            zzz();
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzcn != null) {
                zzart.zzq(1, this.zzcn);
            }
            if (this.zzco != null) {
                zzart.zzq(2, this.zzco);
            }
            if (this.zzcp != null) {
                zzart.zzq(3, this.zzcp);
            }
            if (this.zzcq != null) {
                zzart.zzq(4, this.zzcq);
            }
            if (this.zzcr != null) {
                zzart.zzq(5, this.zzcr);
            }
            super.zza(zzart);
        }

        /* renamed from: zzd */
        public zzc zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        this.zzcn = zzars.readString();
                        continue;
                    case 18:
                        this.zzco = zzars.readString();
                        continue;
                    case 26:
                        this.zzcp = zzars.readString();
                        continue;
                    case 34:
                        this.zzcq = zzars.readString();
                        continue;
                    case 42:
                        this.zzcr = zzars.readString();
                        continue;
                    default:
                        if (!zzasd.zzb(zzars, bU)) {
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
            if (this.zzcn != null) {
                zzx += zzart.zzr(1, this.zzcn);
            }
            if (this.zzco != null) {
                zzx += zzart.zzr(2, this.zzco);
            }
            if (this.zzcp != null) {
                zzx += zzart.zzr(3, this.zzcp);
            }
            if (this.zzcq != null) {
                zzx += zzart.zzr(4, this.zzcq);
            }
            return this.zzcr != null ? zzx + zzart.zzr(5, this.zzcr) : zzx;
        }

        public zzc zzz() {
            this.zzcn = null;
            this.zzco = null;
            this.zzcp = null;
            this.zzcq = null;
            this.zzcr = null;
            this.btP = -1;
            return this;
        }
    }
}
