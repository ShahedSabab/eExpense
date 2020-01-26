package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class zzaqg extends zzaqr {
    private static final Writer bpO = new Writer() {
        public void close() throws IOException {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    };
    private static final zzape bpP = new zzape("closed");
    private final List<zzaoy> bpN = new ArrayList();
    private String bpQ;
    private zzaoy bpR = zzapa.bou;

    public zzaqg() {
        super(bpO);
    }

    /* renamed from: bv */
    private zzaoy m78bv() {
        return (zzaoy) this.bpN.get(this.bpN.size() - 1);
    }

    private void zzd(zzaoy zzaoy) {
        if (this.bpQ != null) {
            if (!zzaoy.mo10507aY() || mo10732bN()) {
                ((zzapb) m78bv()).zza(this.bpQ, zzaoy);
            }
            this.bpQ = null;
        } else if (this.bpN.isEmpty()) {
            this.bpR = zzaoy;
        } else {
            zzaoy bv = m78bv();
            if (bv instanceof zzaov) {
                ((zzaov) bv).zzc(zzaoy);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: bA */
    public zzaqr mo10641bA() throws IOException {
        zzd(zzapa.bou);
        return this;
    }

    /* renamed from: bu */
    public zzaoy mo10642bu() {
        if (this.bpN.isEmpty()) {
            return this.bpR;
        }
        String valueOf = String.valueOf(this.bpN);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 34).append("Expected one JSON element but was ").append(valueOf).toString());
    }

    /* renamed from: bw */
    public zzaqr mo10643bw() throws IOException {
        zzaov zzaov = new zzaov();
        zzd(zzaov);
        this.bpN.add(zzaov);
        return this;
    }

    /* renamed from: bx */
    public zzaqr mo10644bx() throws IOException {
        if (this.bpN.isEmpty() || this.bpQ != null) {
            throw new IllegalStateException();
        } else if (m78bv() instanceof zzaov) {
            this.bpN.remove(this.bpN.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: by */
    public zzaqr mo10645by() throws IOException {
        zzapb zzapb = new zzapb();
        zzd(zzapb);
        this.bpN.add(zzapb);
        return this;
    }

    /* renamed from: bz */
    public zzaqr mo10646bz() throws IOException {
        if (this.bpN.isEmpty() || this.bpQ != null) {
            throw new IllegalStateException();
        } else if (m78bv() instanceof zzapb) {
            this.bpN.remove(this.bpN.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public void close() throws IOException {
        if (!this.bpN.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.bpN.add(bpP);
    }

    public void flush() throws IOException {
    }

    public zzaqr zza(Number number) throws IOException {
        if (number == null) {
            return mo10641bA();
        }
        if (!isLenient()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                String valueOf = String.valueOf(number);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("JSON forbids NaN and infinities: ").append(valueOf).toString());
            }
        }
        zzd(new zzape(number));
        return this;
    }

    public zzaqr zzcs(long j) throws IOException {
        zzd(new zzape((Number) Long.valueOf(j)));
        return this;
    }

    public zzaqr zzdh(boolean z) throws IOException {
        zzd(new zzape(Boolean.valueOf(z)));
        return this;
    }

    public zzaqr zzus(String str) throws IOException {
        if (this.bpN.isEmpty() || this.bpQ != null) {
            throw new IllegalStateException();
        } else if (m78bv() instanceof zzapb) {
            this.bpQ = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public zzaqr zzut(String str) throws IOException {
        if (str == null) {
            return mo10641bA();
        }
        zzd(new zzape(str));
        return this;
    }
}
