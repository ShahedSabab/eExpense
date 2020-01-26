package com.google.android.gms.internal;

import com.theartofdev.edmodo.cropper.CropImage;
import java.io.IOException;

public interface zzaf {

    public static final class zza extends zzaru<zza> {
        public String zzcn;
        public String zzcp;
        public String zzcq;
        public String zzcr;
        public String zzda;
        public String zzdb;
        public Long zzdc;
        public Long zzdd;
        public Long zzde;
        public Long zzdf;
        public Long zzdg;
        public Long zzdh;
        public Long zzdi;
        public Long zzdj;
        public Long zzdk;
        public Long zzdl;
        public String zzdm;
        public Long zzdn;
        public Long zzdo;
        public Long zzdp;
        public Long zzdq;
        public Long zzdr;
        public Long zzds;
        public Long zzdt;
        public Long zzdu;
        public Long zzdv;
        public String zzdw;
        public Long zzdx;
        public Long zzdy;
        public Long zzdz;
        public Long zzea;
        public Long zzeb;
        public Long zzec;
        public zzb zzed;
        public Long zzee;
        public Long zzef;
        public Long zzeg;
        public Long zzeh;
        public Long zzei;
        public Long zzej;
        public Integer zzek;
        public Integer zzel;
        public Long zzem;
        public Long zzen;
        public Long zzeo;
        public Long zzep;
        public Long zzeq;
        public Integer zzer;
        public C0751zza zzes;
        public C0751zza[] zzet;
        public zzb zzeu;
        public Long zzev;
        public String zzew;
        public Integer zzex;
        public Boolean zzey;
        public String zzez;
        public Long zzfa;
        public zze zzfb;

        /* renamed from: com.google.android.gms.internal.zzaf$zza$zza reason: collision with other inner class name */
        public static final class C0751zza extends zzaru<C0751zza> {
            private static volatile C0751zza[] zzfc;
            public Long zzdn;
            public Long zzdo;
            public Long zzfd;
            public Long zzfe;
            public Long zzff;
            public Long zzfg;
            public Integer zzfh;
            public Long zzfi;
            public Long zzfj;
            public Long zzfk;
            public Integer zzfl;
            public Long zzfm;

            public C0751zza() {
                this.zzdn = null;
                this.zzdo = null;
                this.zzfd = null;
                this.zzfe = null;
                this.zzff = null;
                this.zzfg = null;
                this.zzfh = null;
                this.zzfi = null;
                this.zzfj = null;
                this.zzfk = null;
                this.zzfl = null;
                this.zzfm = null;
                this.btP = -1;
            }

            public static C0751zza[] zzaa() {
                if (zzfc == null) {
                    synchronized (zzary.btO) {
                        if (zzfc == null) {
                            zzfc = new C0751zza[0];
                        }
                    }
                }
                return zzfc;
            }

            public void zza(zzart zzart) throws IOException {
                if (this.zzdn != null) {
                    zzart.zzb(1, this.zzdn.longValue());
                }
                if (this.zzdo != null) {
                    zzart.zzb(2, this.zzdo.longValue());
                }
                if (this.zzfd != null) {
                    zzart.zzb(3, this.zzfd.longValue());
                }
                if (this.zzfe != null) {
                    zzart.zzb(4, this.zzfe.longValue());
                }
                if (this.zzff != null) {
                    zzart.zzb(5, this.zzff.longValue());
                }
                if (this.zzfg != null) {
                    zzart.zzb(6, this.zzfg.longValue());
                }
                if (this.zzfh != null) {
                    zzart.zzaf(7, this.zzfh.intValue());
                }
                if (this.zzfi != null) {
                    zzart.zzb(8, this.zzfi.longValue());
                }
                if (this.zzfj != null) {
                    zzart.zzb(9, this.zzfj.longValue());
                }
                if (this.zzfk != null) {
                    zzart.zzb(10, this.zzfk.longValue());
                }
                if (this.zzfl != null) {
                    zzart.zzaf(11, this.zzfl.intValue());
                }
                if (this.zzfm != null) {
                    zzart.zzb(12, this.zzfm.longValue());
                }
                super.zza(zzart);
            }

            /* renamed from: zzg */
            public C0751zza zzb(zzars zzars) throws IOException {
                while (true) {
                    int bU = zzars.mo10738bU();
                    switch (bU) {
                        case 0:
                            break;
                        case 8:
                            this.zzdn = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 16:
                            this.zzdo = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 24:
                            this.zzfd = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 32:
                            this.zzfe = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 40:
                            this.zzff = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 48:
                            this.zzfg = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 56:
                            int bY = zzars.mo10742bY();
                            switch (bY) {
                                case 0:
                                case 1:
                                case 2:
                                case 1000:
                                    this.zzfh = Integer.valueOf(bY);
                                    break;
                                default:
                                    continue;
                            }
                        case 64:
                            this.zzfi = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 72:
                            this.zzfj = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 80:
                            this.zzfk = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 88:
                            int bY2 = zzars.mo10742bY();
                            switch (bY2) {
                                case 0:
                                case 1:
                                case 2:
                                case 1000:
                                    this.zzfl = Integer.valueOf(bY2);
                                    break;
                                default:
                                    continue;
                            }
                        case 96:
                            this.zzfm = Long.valueOf(zzars.mo10741bX());
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
                if (this.zzdn != null) {
                    zzx += zzart.zzf(1, this.zzdn.longValue());
                }
                if (this.zzdo != null) {
                    zzx += zzart.zzf(2, this.zzdo.longValue());
                }
                if (this.zzfd != null) {
                    zzx += zzart.zzf(3, this.zzfd.longValue());
                }
                if (this.zzfe != null) {
                    zzx += zzart.zzf(4, this.zzfe.longValue());
                }
                if (this.zzff != null) {
                    zzx += zzart.zzf(5, this.zzff.longValue());
                }
                if (this.zzfg != null) {
                    zzx += zzart.zzf(6, this.zzfg.longValue());
                }
                if (this.zzfh != null) {
                    zzx += zzart.zzah(7, this.zzfh.intValue());
                }
                if (this.zzfi != null) {
                    zzx += zzart.zzf(8, this.zzfi.longValue());
                }
                if (this.zzfj != null) {
                    zzx += zzart.zzf(9, this.zzfj.longValue());
                }
                if (this.zzfk != null) {
                    zzx += zzart.zzf(10, this.zzfk.longValue());
                }
                if (this.zzfl != null) {
                    zzx += zzart.zzah(11, this.zzfl.intValue());
                }
                return this.zzfm != null ? zzx + zzart.zzf(12, this.zzfm.longValue()) : zzx;
            }
        }

        public static final class zzb extends zzaru<zzb> {
            public Long zzep;
            public Long zzeq;
            public Long zzfn;

            public zzb() {
                this.zzep = null;
                this.zzeq = null;
                this.zzfn = null;
                this.btP = -1;
            }

            public void zza(zzart zzart) throws IOException {
                if (this.zzep != null) {
                    zzart.zzb(1, this.zzep.longValue());
                }
                if (this.zzeq != null) {
                    zzart.zzb(2, this.zzeq.longValue());
                }
                if (this.zzfn != null) {
                    zzart.zzb(3, this.zzfn.longValue());
                }
                super.zza(zzart);
            }

            /* renamed from: zzh */
            public zzb zzb(zzars zzars) throws IOException {
                while (true) {
                    int bU = zzars.mo10738bU();
                    switch (bU) {
                        case 0:
                            break;
                        case 8:
                            this.zzep = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 16:
                            this.zzeq = Long.valueOf(zzars.mo10741bX());
                            continue;
                        case 24:
                            this.zzfn = Long.valueOf(zzars.mo10741bX());
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
                if (this.zzep != null) {
                    zzx += zzart.zzf(1, this.zzep.longValue());
                }
                if (this.zzeq != null) {
                    zzx += zzart.zzf(2, this.zzeq.longValue());
                }
                return this.zzfn != null ? zzx + zzart.zzf(3, this.zzfn.longValue()) : zzx;
            }
        }

        public zza() {
            this.zzdb = null;
            this.zzda = null;
            this.zzdc = null;
            this.zzdd = null;
            this.zzde = null;
            this.zzdf = null;
            this.zzdg = null;
            this.zzdh = null;
            this.zzdi = null;
            this.zzdj = null;
            this.zzdk = null;
            this.zzdl = null;
            this.zzdm = null;
            this.zzdn = null;
            this.zzdo = null;
            this.zzdp = null;
            this.zzdq = null;
            this.zzdr = null;
            this.zzds = null;
            this.zzdt = null;
            this.zzdu = null;
            this.zzdv = null;
            this.zzcn = null;
            this.zzdw = null;
            this.zzdx = null;
            this.zzdy = null;
            this.zzdz = null;
            this.zzcp = null;
            this.zzea = null;
            this.zzeb = null;
            this.zzec = null;
            this.zzee = null;
            this.zzef = null;
            this.zzeg = null;
            this.zzeh = null;
            this.zzei = null;
            this.zzej = null;
            this.zzcq = null;
            this.zzcr = null;
            this.zzek = null;
            this.zzel = null;
            this.zzem = null;
            this.zzen = null;
            this.zzeo = null;
            this.zzep = null;
            this.zzeq = null;
            this.zzer = null;
            this.zzet = C0751zza.zzaa();
            this.zzev = null;
            this.zzew = null;
            this.zzex = null;
            this.zzey = null;
            this.zzez = null;
            this.zzfa = null;
            this.btP = -1;
        }

        public static zza zzd(byte[] bArr) throws zzarz {
            return (zza) zzasa.zza(new zza(), bArr);
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzdb != null) {
                zzart.zzq(1, this.zzdb);
            }
            if (this.zzda != null) {
                zzart.zzq(2, this.zzda);
            }
            if (this.zzdc != null) {
                zzart.zzb(3, this.zzdc.longValue());
            }
            if (this.zzdd != null) {
                zzart.zzb(4, this.zzdd.longValue());
            }
            if (this.zzde != null) {
                zzart.zzb(5, this.zzde.longValue());
            }
            if (this.zzdf != null) {
                zzart.zzb(6, this.zzdf.longValue());
            }
            if (this.zzdg != null) {
                zzart.zzb(7, this.zzdg.longValue());
            }
            if (this.zzdh != null) {
                zzart.zzb(8, this.zzdh.longValue());
            }
            if (this.zzdi != null) {
                zzart.zzb(9, this.zzdi.longValue());
            }
            if (this.zzdj != null) {
                zzart.zzb(10, this.zzdj.longValue());
            }
            if (this.zzdk != null) {
                zzart.zzb(11, this.zzdk.longValue());
            }
            if (this.zzdl != null) {
                zzart.zzb(12, this.zzdl.longValue());
            }
            if (this.zzdm != null) {
                zzart.zzq(13, this.zzdm);
            }
            if (this.zzdn != null) {
                zzart.zzb(14, this.zzdn.longValue());
            }
            if (this.zzdo != null) {
                zzart.zzb(15, this.zzdo.longValue());
            }
            if (this.zzdp != null) {
                zzart.zzb(16, this.zzdp.longValue());
            }
            if (this.zzdq != null) {
                zzart.zzb(17, this.zzdq.longValue());
            }
            if (this.zzdr != null) {
                zzart.zzb(18, this.zzdr.longValue());
            }
            if (this.zzds != null) {
                zzart.zzb(19, this.zzds.longValue());
            }
            if (this.zzdt != null) {
                zzart.zzb(20, this.zzdt.longValue());
            }
            if (this.zzev != null) {
                zzart.zzb(21, this.zzev.longValue());
            }
            if (this.zzdu != null) {
                zzart.zzb(22, this.zzdu.longValue());
            }
            if (this.zzdv != null) {
                zzart.zzb(23, this.zzdv.longValue());
            }
            if (this.zzew != null) {
                zzart.zzq(24, this.zzew);
            }
            if (this.zzfa != null) {
                zzart.zzb(25, this.zzfa.longValue());
            }
            if (this.zzex != null) {
                zzart.zzaf(26, this.zzex.intValue());
            }
            if (this.zzcn != null) {
                zzart.zzq(27, this.zzcn);
            }
            if (this.zzey != null) {
                zzart.zzg(28, this.zzey.booleanValue());
            }
            if (this.zzdw != null) {
                zzart.zzq(29, this.zzdw);
            }
            if (this.zzez != null) {
                zzart.zzq(30, this.zzez);
            }
            if (this.zzdx != null) {
                zzart.zzb(31, this.zzdx.longValue());
            }
            if (this.zzdy != null) {
                zzart.zzb(32, this.zzdy.longValue());
            }
            if (this.zzdz != null) {
                zzart.zzb(33, this.zzdz.longValue());
            }
            if (this.zzcp != null) {
                zzart.zzq(34, this.zzcp);
            }
            if (this.zzea != null) {
                zzart.zzb(35, this.zzea.longValue());
            }
            if (this.zzeb != null) {
                zzart.zzb(36, this.zzeb.longValue());
            }
            if (this.zzec != null) {
                zzart.zzb(37, this.zzec.longValue());
            }
            if (this.zzed != null) {
                zzart.zza(38, (zzasa) this.zzed);
            }
            if (this.zzee != null) {
                zzart.zzb(39, this.zzee.longValue());
            }
            if (this.zzef != null) {
                zzart.zzb(40, this.zzef.longValue());
            }
            if (this.zzeg != null) {
                zzart.zzb(41, this.zzeg.longValue());
            }
            if (this.zzeh != null) {
                zzart.zzb(42, this.zzeh.longValue());
            }
            if (this.zzet != null && this.zzet.length > 0) {
                for (C0751zza zza : this.zzet) {
                    if (zza != null) {
                        zzart.zza(43, (zzasa) zza);
                    }
                }
            }
            if (this.zzei != null) {
                zzart.zzb(44, this.zzei.longValue());
            }
            if (this.zzej != null) {
                zzart.zzb(45, this.zzej.longValue());
            }
            if (this.zzcq != null) {
                zzart.zzq(46, this.zzcq);
            }
            if (this.zzcr != null) {
                zzart.zzq(47, this.zzcr);
            }
            if (this.zzek != null) {
                zzart.zzaf(48, this.zzek.intValue());
            }
            if (this.zzel != null) {
                zzart.zzaf(49, this.zzel.intValue());
            }
            if (this.zzes != null) {
                zzart.zza(50, (zzasa) this.zzes);
            }
            if (this.zzem != null) {
                zzart.zzb(51, this.zzem.longValue());
            }
            if (this.zzen != null) {
                zzart.zzb(52, this.zzen.longValue());
            }
            if (this.zzeo != null) {
                zzart.zzb(53, this.zzeo.longValue());
            }
            if (this.zzep != null) {
                zzart.zzb(54, this.zzep.longValue());
            }
            if (this.zzeq != null) {
                zzart.zzb(55, this.zzeq.longValue());
            }
            if (this.zzer != null) {
                zzart.zzaf(56, this.zzer.intValue());
            }
            if (this.zzeu != null) {
                zzart.zza(57, (zzasa) this.zzeu);
            }
            if (this.zzfb != null) {
                zzart.zza((int) CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, (zzasa) this.zzfb);
            }
            super.zza(zzart);
        }

        /* renamed from: zzf */
        public zza zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        this.zzdb = zzars.readString();
                        continue;
                    case 18:
                        this.zzda = zzars.readString();
                        continue;
                    case 24:
                        this.zzdc = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 32:
                        this.zzdd = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 40:
                        this.zzde = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 48:
                        this.zzdf = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 56:
                        this.zzdg = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 64:
                        this.zzdh = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 72:
                        this.zzdi = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 80:
                        this.zzdj = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 88:
                        this.zzdk = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 96:
                        this.zzdl = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 106:
                        this.zzdm = zzars.readString();
                        continue;
                    case 112:
                        this.zzdn = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 120:
                        this.zzdo = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 128:
                        this.zzdp = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 136:
                        this.zzdq = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 144:
                        this.zzdr = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 152:
                        this.zzds = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 160:
                        this.zzdt = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 168:
                        this.zzev = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 176:
                        this.zzdu = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 184:
                        this.zzdv = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 194:
                        this.zzew = zzars.readString();
                        continue;
                    case 200:
                        this.zzfa = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 208:
                        int bY = zzars.mo10742bY();
                        switch (bY) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.zzex = Integer.valueOf(bY);
                                break;
                            default:
                                continue;
                        }
                    case 218:
                        this.zzcn = zzars.readString();
                        continue;
                    case 224:
                        this.zzey = Boolean.valueOf(zzars.mo10744ca());
                        continue;
                    case 234:
                        this.zzdw = zzars.readString();
                        continue;
                    case 242:
                        this.zzez = zzars.readString();
                        continue;
                    case 248:
                        this.zzdx = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 256:
                        this.zzdy = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 264:
                        this.zzdz = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 274:
                        this.zzcp = zzars.readString();
                        continue;
                    case 280:
                        this.zzea = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 288:
                        this.zzeb = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 296:
                        this.zzec = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 306:
                        if (this.zzed == null) {
                            this.zzed = new zzb();
                        }
                        zzars.zza(this.zzed);
                        continue;
                    case 312:
                        this.zzee = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 320:
                        this.zzef = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 328:
                        this.zzeg = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 336:
                        this.zzeh = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 346:
                        int zzc = zzasd.zzc(zzars, 346);
                        int length = this.zzet == null ? 0 : this.zzet.length;
                        C0751zza[] zzaArr = new C0751zza[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzet, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new C0751zza();
                            zzars.zza(zzaArr[length]);
                            zzars.mo10738bU();
                            length++;
                        }
                        zzaArr[length] = new C0751zza();
                        zzars.zza(zzaArr[length]);
                        this.zzet = zzaArr;
                        continue;
                    case 352:
                        this.zzei = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 360:
                        this.zzej = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 370:
                        this.zzcq = zzars.readString();
                        continue;
                    case 378:
                        this.zzcr = zzars.readString();
                        continue;
                    case 384:
                        int bY2 = zzars.mo10742bY();
                        switch (bY2) {
                            case 0:
                            case 1:
                            case 2:
                            case 1000:
                                this.zzek = Integer.valueOf(bY2);
                                break;
                            default:
                                continue;
                        }
                    case 392:
                        int bY3 = zzars.mo10742bY();
                        switch (bY3) {
                            case 0:
                            case 1:
                            case 2:
                            case 1000:
                                this.zzel = Integer.valueOf(bY3);
                                break;
                            default:
                                continue;
                        }
                    case 402:
                        if (this.zzes == null) {
                            this.zzes = new C0751zza();
                        }
                        zzars.zza(this.zzes);
                        continue;
                    case 408:
                        this.zzem = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 416:
                        this.zzen = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 424:
                        this.zzeo = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 432:
                        this.zzep = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 440:
                        this.zzeq = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 448:
                        int bY4 = zzars.mo10742bY();
                        switch (bY4) {
                            case 0:
                            case 1:
                            case 2:
                            case 1000:
                                this.zzer = Integer.valueOf(bY4);
                                break;
                            default:
                                continue;
                        }
                    case 458:
                        if (this.zzeu == null) {
                            this.zzeu = new zzb();
                        }
                        zzars.zza(this.zzeu);
                        continue;
                    case 1610:
                        if (this.zzfb == null) {
                            this.zzfb = new zze();
                        }
                        zzars.zza(this.zzfb);
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
            if (this.zzdb != null) {
                zzx += zzart.zzr(1, this.zzdb);
            }
            if (this.zzda != null) {
                zzx += zzart.zzr(2, this.zzda);
            }
            if (this.zzdc != null) {
                zzx += zzart.zzf(3, this.zzdc.longValue());
            }
            if (this.zzdd != null) {
                zzx += zzart.zzf(4, this.zzdd.longValue());
            }
            if (this.zzde != null) {
                zzx += zzart.zzf(5, this.zzde.longValue());
            }
            if (this.zzdf != null) {
                zzx += zzart.zzf(6, this.zzdf.longValue());
            }
            if (this.zzdg != null) {
                zzx += zzart.zzf(7, this.zzdg.longValue());
            }
            if (this.zzdh != null) {
                zzx += zzart.zzf(8, this.zzdh.longValue());
            }
            if (this.zzdi != null) {
                zzx += zzart.zzf(9, this.zzdi.longValue());
            }
            if (this.zzdj != null) {
                zzx += zzart.zzf(10, this.zzdj.longValue());
            }
            if (this.zzdk != null) {
                zzx += zzart.zzf(11, this.zzdk.longValue());
            }
            if (this.zzdl != null) {
                zzx += zzart.zzf(12, this.zzdl.longValue());
            }
            if (this.zzdm != null) {
                zzx += zzart.zzr(13, this.zzdm);
            }
            if (this.zzdn != null) {
                zzx += zzart.zzf(14, this.zzdn.longValue());
            }
            if (this.zzdo != null) {
                zzx += zzart.zzf(15, this.zzdo.longValue());
            }
            if (this.zzdp != null) {
                zzx += zzart.zzf(16, this.zzdp.longValue());
            }
            if (this.zzdq != null) {
                zzx += zzart.zzf(17, this.zzdq.longValue());
            }
            if (this.zzdr != null) {
                zzx += zzart.zzf(18, this.zzdr.longValue());
            }
            if (this.zzds != null) {
                zzx += zzart.zzf(19, this.zzds.longValue());
            }
            if (this.zzdt != null) {
                zzx += zzart.zzf(20, this.zzdt.longValue());
            }
            if (this.zzev != null) {
                zzx += zzart.zzf(21, this.zzev.longValue());
            }
            if (this.zzdu != null) {
                zzx += zzart.zzf(22, this.zzdu.longValue());
            }
            if (this.zzdv != null) {
                zzx += zzart.zzf(23, this.zzdv.longValue());
            }
            if (this.zzew != null) {
                zzx += zzart.zzr(24, this.zzew);
            }
            if (this.zzfa != null) {
                zzx += zzart.zzf(25, this.zzfa.longValue());
            }
            if (this.zzex != null) {
                zzx += zzart.zzah(26, this.zzex.intValue());
            }
            if (this.zzcn != null) {
                zzx += zzart.zzr(27, this.zzcn);
            }
            if (this.zzey != null) {
                zzx += zzart.zzh(28, this.zzey.booleanValue());
            }
            if (this.zzdw != null) {
                zzx += zzart.zzr(29, this.zzdw);
            }
            if (this.zzez != null) {
                zzx += zzart.zzr(30, this.zzez);
            }
            if (this.zzdx != null) {
                zzx += zzart.zzf(31, this.zzdx.longValue());
            }
            if (this.zzdy != null) {
                zzx += zzart.zzf(32, this.zzdy.longValue());
            }
            if (this.zzdz != null) {
                zzx += zzart.zzf(33, this.zzdz.longValue());
            }
            if (this.zzcp != null) {
                zzx += zzart.zzr(34, this.zzcp);
            }
            if (this.zzea != null) {
                zzx += zzart.zzf(35, this.zzea.longValue());
            }
            if (this.zzeb != null) {
                zzx += zzart.zzf(36, this.zzeb.longValue());
            }
            if (this.zzec != null) {
                zzx += zzart.zzf(37, this.zzec.longValue());
            }
            if (this.zzed != null) {
                zzx += zzart.zzc(38, (zzasa) this.zzed);
            }
            if (this.zzee != null) {
                zzx += zzart.zzf(39, this.zzee.longValue());
            }
            if (this.zzef != null) {
                zzx += zzart.zzf(40, this.zzef.longValue());
            }
            if (this.zzeg != null) {
                zzx += zzart.zzf(41, this.zzeg.longValue());
            }
            if (this.zzeh != null) {
                zzx += zzart.zzf(42, this.zzeh.longValue());
            }
            if (this.zzet != null && this.zzet.length > 0) {
                int i = zzx;
                for (C0751zza zza : this.zzet) {
                    if (zza != null) {
                        i += zzart.zzc(43, (zzasa) zza);
                    }
                }
                zzx = i;
            }
            if (this.zzei != null) {
                zzx += zzart.zzf(44, this.zzei.longValue());
            }
            if (this.zzej != null) {
                zzx += zzart.zzf(45, this.zzej.longValue());
            }
            if (this.zzcq != null) {
                zzx += zzart.zzr(46, this.zzcq);
            }
            if (this.zzcr != null) {
                zzx += zzart.zzr(47, this.zzcr);
            }
            if (this.zzek != null) {
                zzx += zzart.zzah(48, this.zzek.intValue());
            }
            if (this.zzel != null) {
                zzx += zzart.zzah(49, this.zzel.intValue());
            }
            if (this.zzes != null) {
                zzx += zzart.zzc(50, (zzasa) this.zzes);
            }
            if (this.zzem != null) {
                zzx += zzart.zzf(51, this.zzem.longValue());
            }
            if (this.zzen != null) {
                zzx += zzart.zzf(52, this.zzen.longValue());
            }
            if (this.zzeo != null) {
                zzx += zzart.zzf(53, this.zzeo.longValue());
            }
            if (this.zzep != null) {
                zzx += zzart.zzf(54, this.zzep.longValue());
            }
            if (this.zzeq != null) {
                zzx += zzart.zzf(55, this.zzeq.longValue());
            }
            if (this.zzer != null) {
                zzx += zzart.zzah(56, this.zzer.intValue());
            }
            if (this.zzeu != null) {
                zzx += zzart.zzc(57, (zzasa) this.zzeu);
            }
            return this.zzfb != null ? zzx + zzart.zzc((int) CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, (zzasa) this.zzfb) : zzx;
        }
    }

    public static final class zzb extends zzaru<zzb> {
        public Long zzfo;
        public Integer zzfp;
        public Boolean zzfq;
        public int[] zzfr;
        public Long zzfs;

        public zzb() {
            this.zzfo = null;
            this.zzfp = null;
            this.zzfq = null;
            this.zzfr = zzasd.btR;
            this.zzfs = null;
            this.btP = -1;
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzfo != null) {
                zzart.zzb(1, this.zzfo.longValue());
            }
            if (this.zzfp != null) {
                zzart.zzaf(2, this.zzfp.intValue());
            }
            if (this.zzfq != null) {
                zzart.zzg(3, this.zzfq.booleanValue());
            }
            if (this.zzfr != null && this.zzfr.length > 0) {
                for (int zzaf : this.zzfr) {
                    zzart.zzaf(4, zzaf);
                }
            }
            if (this.zzfs != null) {
                zzart.zza(5, this.zzfs.longValue());
            }
            super.zza(zzart);
        }

        /* renamed from: zzi */
        public zzb zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 8:
                        this.zzfo = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 16:
                        this.zzfp = Integer.valueOf(zzars.mo10742bY());
                        continue;
                    case 24:
                        this.zzfq = Boolean.valueOf(zzars.mo10744ca());
                        continue;
                    case 32:
                        int zzc = zzasd.zzc(zzars, 32);
                        int length = this.zzfr == null ? 0 : this.zzfr.length;
                        int[] iArr = new int[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzfr, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zzars.mo10742bY();
                            zzars.mo10738bU();
                            length++;
                        }
                        iArr[length] = zzars.mo10742bY();
                        this.zzfr = iArr;
                        continue;
                    case 34:
                        int zzagt = zzars.zzagt(zzars.mo10747cd());
                        int position = zzars.getPosition();
                        int i = 0;
                        while (zzars.mo10751ci() > 0) {
                            zzars.mo10742bY();
                            i++;
                        }
                        zzars.zzagv(position);
                        int length2 = this.zzfr == null ? 0 : this.zzfr.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzfr, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zzars.mo10742bY();
                            length2++;
                        }
                        this.zzfr = iArr2;
                        zzars.zzagu(zzagt);
                        continue;
                    case 40:
                        this.zzfs = Long.valueOf(zzars.mo10740bW());
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
            if (this.zzfo != null) {
                zzx += zzart.zzf(1, this.zzfo.longValue());
            }
            if (this.zzfp != null) {
                zzx += zzart.zzah(2, this.zzfp.intValue());
            }
            if (this.zzfq != null) {
                zzx += zzart.zzh(3, this.zzfq.booleanValue());
            }
            if (this.zzfr != null && this.zzfr.length > 0) {
                int i = 0;
                for (int zzagz : this.zzfr) {
                    i += zzart.zzagz(zzagz);
                }
                zzx = zzx + i + (this.zzfr.length * 1);
            }
            return this.zzfs != null ? zzx + zzart.zze(5, this.zzfs.longValue()) : zzx;
        }
    }

    public static final class zzc extends zzaru<zzc> {
        public byte[] zzft;
        public byte[] zzfu;

        public zzc() {
            this.zzft = null;
            this.zzfu = null;
            this.btP = -1;
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzft != null) {
                zzart.zzb(1, this.zzft);
            }
            if (this.zzfu != null) {
                zzart.zzb(2, this.zzfu);
            }
            super.zza(zzart);
        }

        /* renamed from: zzj */
        public zzc zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        this.zzft = zzars.readBytes();
                        continue;
                    case 18:
                        this.zzfu = zzars.readBytes();
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
            if (this.zzft != null) {
                zzx += zzart.zzc(1, this.zzft);
            }
            return this.zzfu != null ? zzx + zzart.zzc(2, this.zzfu) : zzx;
        }
    }

    public static final class zzd extends zzaru<zzd> {
        public byte[] data;
        public byte[] zzfv;
        public byte[] zzfw;
        public byte[] zzfx;

        public zzd() {
            this.data = null;
            this.zzfv = null;
            this.zzfw = null;
            this.zzfx = null;
            this.btP = -1;
        }

        public static zzd zze(byte[] bArr) throws zzarz {
            return (zzd) zzasa.zza(new zzd(), bArr);
        }

        public void zza(zzart zzart) throws IOException {
            if (this.data != null) {
                zzart.zzb(1, this.data);
            }
            if (this.zzfv != null) {
                zzart.zzb(2, this.zzfv);
            }
            if (this.zzfw != null) {
                zzart.zzb(3, this.zzfw);
            }
            if (this.zzfx != null) {
                zzart.zzb(4, this.zzfx);
            }
            super.zza(zzart);
        }

        /* renamed from: zzk */
        public zzd zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        this.data = zzars.readBytes();
                        continue;
                    case 18:
                        this.zzfv = zzars.readBytes();
                        continue;
                    case 26:
                        this.zzfw = zzars.readBytes();
                        continue;
                    case 34:
                        this.zzfx = zzars.readBytes();
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
            if (this.data != null) {
                zzx += zzart.zzc(1, this.data);
            }
            if (this.zzfv != null) {
                zzx += zzart.zzc(2, this.zzfv);
            }
            if (this.zzfw != null) {
                zzx += zzart.zzc(3, this.zzfw);
            }
            return this.zzfx != null ? zzx + zzart.zzc(4, this.zzfx) : zzx;
        }
    }

    public static final class zze extends zzaru<zze> {
        public Long zzfo;
        public String zzfy;
        public byte[] zzfz;

        public zze() {
            this.zzfo = null;
            this.zzfy = null;
            this.zzfz = null;
            this.btP = -1;
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzfo != null) {
                zzart.zzb(1, this.zzfo.longValue());
            }
            if (this.zzfy != null) {
                zzart.zzq(3, this.zzfy);
            }
            if (this.zzfz != null) {
                zzart.zzb(4, this.zzfz);
            }
            super.zza(zzart);
        }

        /* renamed from: zzl */
        public zze zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 8:
                        this.zzfo = Long.valueOf(zzars.mo10741bX());
                        continue;
                    case 26:
                        this.zzfy = zzars.readString();
                        continue;
                    case 34:
                        this.zzfz = zzars.readBytes();
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
            if (this.zzfo != null) {
                zzx += zzart.zzf(1, this.zzfo.longValue());
            }
            if (this.zzfy != null) {
                zzx += zzart.zzr(3, this.zzfy);
            }
            return this.zzfz != null ? zzx + zzart.zzc(4, this.zzfz) : zzx;
        }
    }

    public static final class zzf extends zzaru<zzf> {
        public byte[] zzfv;
        public byte[][] zzga;
        public Integer zzgb;
        public Integer zzgc;

        public zzf() {
            this.zzga = zzasd.btX;
            this.zzfv = null;
            this.zzgb = null;
            this.zzgc = null;
            this.btP = -1;
        }

        public void zza(zzart zzart) throws IOException {
            if (this.zzga != null && this.zzga.length > 0) {
                for (byte[] bArr : this.zzga) {
                    if (bArr != null) {
                        zzart.zzb(1, bArr);
                    }
                }
            }
            if (this.zzfv != null) {
                zzart.zzb(2, this.zzfv);
            }
            if (this.zzgb != null) {
                zzart.zzaf(3, this.zzgb.intValue());
            }
            if (this.zzgc != null) {
                zzart.zzaf(4, this.zzgc.intValue());
            }
            super.zza(zzart);
        }

        /* renamed from: zzm */
        public zzf zzb(zzars zzars) throws IOException {
            while (true) {
                int bU = zzars.mo10738bU();
                switch (bU) {
                    case 0:
                        break;
                    case 10:
                        int zzc = zzasd.zzc(zzars, 10);
                        int length = this.zzga == null ? 0 : this.zzga.length;
                        byte[][] bArr = new byte[(zzc + length)][];
                        if (length != 0) {
                            System.arraycopy(this.zzga, 0, bArr, 0, length);
                        }
                        while (length < bArr.length - 1) {
                            bArr[length] = zzars.readBytes();
                            zzars.mo10738bU();
                            length++;
                        }
                        bArr[length] = zzars.readBytes();
                        this.zzga = bArr;
                        continue;
                    case 18:
                        this.zzfv = zzars.readBytes();
                        continue;
                    case 24:
                        int bY = zzars.mo10742bY();
                        switch (bY) {
                            case 0:
                            case 1:
                                this.zzgb = Integer.valueOf(bY);
                                break;
                            default:
                                continue;
                        }
                    case 32:
                        int bY2 = zzars.mo10742bY();
                        switch (bY2) {
                            case 0:
                            case 1:
                                this.zzgc = Integer.valueOf(bY2);
                                break;
                            default:
                                continue;
                        }
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
            int i;
            int zzx = super.zzx();
            if (this.zzga == null || this.zzga.length <= 0) {
                i = zzx;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (byte[] bArr : this.zzga) {
                    if (bArr != null) {
                        i3++;
                        i2 += zzart.zzbg(bArr);
                    }
                }
                i = zzx + i2 + (i3 * 1);
            }
            if (this.zzfv != null) {
                i += zzart.zzc(2, this.zzfv);
            }
            if (this.zzgb != null) {
                i += zzart.zzah(3, this.zzgb.intValue());
            }
            return this.zzgc != null ? i + zzart.zzah(4, this.zzgc.intValue()) : i;
        }
    }
}
