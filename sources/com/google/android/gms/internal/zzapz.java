package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class zzapz {

    private static final class zza extends Writer {
        private final C0752zza bpA;
        private final Appendable bpz;

        /* renamed from: com.google.android.gms.internal.zzapz$zza$zza reason: collision with other inner class name */
        static class C0752zza implements CharSequence {
            char[] bpB;

            C0752zza() {
            }

            public char charAt(int i) {
                return this.bpB[i];
            }

            public int length() {
                return this.bpB.length;
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.bpB, i, i2 - i);
            }
        }

        private zza(Appendable appendable) {
            this.bpA = new C0752zza();
            this.bpz = appendable;
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(int i) throws IOException {
            this.bpz.append((char) i);
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.bpA.bpB = cArr;
            this.bpz.append(this.bpA, i, i + i2);
        }
    }

    public static Writer zza(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new zza(appendable);
    }

    public static void zzb(zzaoy zzaoy, zzaqr zzaqr) throws IOException {
        zzaqn.bqY.zza(zzaqr, zzaoy);
    }

    public static zzaoy zzh(zzaqp zzaqp) throws zzapc {
        boolean z = true;
        try {
            zzaqp.mo10624bq();
            z = false;
            return (zzaoy) zzaqn.bqY.zzb(zzaqp);
        } catch (EOFException e) {
            if (z) {
                return zzapa.bou;
            }
            throw new zzaph((Throwable) e);
        } catch (zzaqs e2) {
            throw new zzaph((Throwable) e2);
        } catch (IOException e3) {
            throw new zzaoz((Throwable) e3);
        } catch (NumberFormatException e4) {
            throw new zzaph((Throwable) e4);
        }
    }
}
