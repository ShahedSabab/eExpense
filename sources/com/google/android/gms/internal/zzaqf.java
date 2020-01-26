package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public final class zzaqf extends zzaqp {
    private static final Reader bpL = new Reader() {
        public void close() throws IOException {
            throw new AssertionError();
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object bpM = new Object();
    private final List<Object> bpN = new ArrayList();

    public zzaqf(zzaoy zzaoy) {
        super(bpL);
        this.bpN.add(zzaoy);
    }

    /* renamed from: br */
    private Object m74br() {
        return this.bpN.get(this.bpN.size() - 1);
    }

    /* renamed from: bs */
    private Object m75bs() {
        return this.bpN.remove(this.bpN.size() - 1);
    }

    private void zza(zzaqq zzaqq) throws IOException {
        if (mo10624bq() != zzaqq) {
            String valueOf = String.valueOf(zzaqq);
            String valueOf2 = String.valueOf(mo10624bq());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
        }
    }

    public void beginArray() throws IOException {
        zza(zzaqq.BEGIN_ARRAY);
        this.bpN.add(((zzaov) m74br()).iterator());
    }

    public void beginObject() throws IOException {
        zza(zzaqq.BEGIN_OBJECT);
        this.bpN.add(((zzapb) m74br()).entrySet().iterator());
    }

    /* renamed from: bq */
    public zzaqq mo10624bq() throws IOException {
        if (this.bpN.isEmpty()) {
            return zzaqq.END_DOCUMENT;
        }
        Object br = m74br();
        if (br instanceof Iterator) {
            boolean z = this.bpN.get(this.bpN.size() - 2) instanceof zzapb;
            Iterator it = (Iterator) br;
            if (!it.hasNext()) {
                return z ? zzaqq.END_OBJECT : zzaqq.END_ARRAY;
            }
            if (z) {
                return zzaqq.NAME;
            }
            this.bpN.add(it.next());
            return mo10624bq();
        } else if (br instanceof zzapb) {
            return zzaqq.BEGIN_OBJECT;
        } else {
            if (br instanceof zzaov) {
                return zzaqq.BEGIN_ARRAY;
            }
            if (br instanceof zzape) {
                zzape zzape = (zzape) br;
                if (zzape.mo10529bf()) {
                    return zzaqq.STRING;
                }
                if (zzape.mo10527bd()) {
                    return zzaqq.BOOLEAN;
                }
                if (zzape.mo10528be()) {
                    return zzaqq.NUMBER;
                }
                throw new AssertionError();
            } else if (br instanceof zzapa) {
                return zzaqq.NULL;
            } else {
                if (br == bpM) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    /* renamed from: bt */
    public void mo10625bt() throws IOException {
        zza(zzaqq.NAME);
        Entry entry = (Entry) ((Iterator) m74br()).next();
        this.bpN.add(entry.getValue());
        this.bpN.add(new zzape((String) entry.getKey()));
    }

    public void close() throws IOException {
        this.bpN.clear();
        this.bpN.add(bpM);
    }

    public void endArray() throws IOException {
        zza(zzaqq.END_ARRAY);
        m75bs();
        m75bs();
    }

    public void endObject() throws IOException {
        zza(zzaqq.END_OBJECT);
        m75bs();
        m75bs();
    }

    public boolean hasNext() throws IOException {
        zzaqq bq = mo10624bq();
        return (bq == zzaqq.END_OBJECT || bq == zzaqq.END_ARRAY) ? false : true;
    }

    public boolean nextBoolean() throws IOException {
        zza(zzaqq.BOOLEAN);
        return ((zzape) m75bs()).getAsBoolean();
    }

    public double nextDouble() throws IOException {
        zzaqq bq = mo10624bq();
        if (bq == zzaqq.NUMBER || bq == zzaqq.STRING) {
            double asDouble = ((zzape) m74br()).getAsDouble();
            if (isLenient() || (!Double.isNaN(asDouble) && !Double.isInfinite(asDouble))) {
                m75bs();
                return asDouble;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        String valueOf = String.valueOf(zzaqq.NUMBER);
        String valueOf2 = String.valueOf(bq);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public int nextInt() throws IOException {
        zzaqq bq = mo10624bq();
        if (bq == zzaqq.NUMBER || bq == zzaqq.STRING) {
            int asInt = ((zzape) m74br()).getAsInt();
            m75bs();
            return asInt;
        }
        String valueOf = String.valueOf(zzaqq.NUMBER);
        String valueOf2 = String.valueOf(bq);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public long nextLong() throws IOException {
        zzaqq bq = mo10624bq();
        if (bq == zzaqq.NUMBER || bq == zzaqq.STRING) {
            long asLong = ((zzape) m74br()).getAsLong();
            m75bs();
            return asLong;
        }
        String valueOf = String.valueOf(zzaqq.NUMBER);
        String valueOf2 = String.valueOf(bq);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public String nextName() throws IOException {
        zza(zzaqq.NAME);
        Entry entry = (Entry) ((Iterator) m74br()).next();
        this.bpN.add(entry.getValue());
        return (String) entry.getKey();
    }

    public void nextNull() throws IOException {
        zza(zzaqq.NULL);
        m75bs();
    }

    public String nextString() throws IOException {
        zzaqq bq = mo10624bq();
        if (bq == zzaqq.STRING || bq == zzaqq.NUMBER) {
            return ((zzape) m75bs()).mo10493aU();
        }
        String valueOf = String.valueOf(zzaqq.STRING);
        String valueOf2 = String.valueOf(bq);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public void skipValue() throws IOException {
        if (mo10624bq() == zzaqq.NAME) {
            nextName();
        } else {
            m75bs();
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
