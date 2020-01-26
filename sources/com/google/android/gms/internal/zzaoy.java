package com.google.android.gms.internal;

import java.io.IOException;
import java.io.StringWriter;

public abstract class zzaoy {
    /* renamed from: aT */
    public Number mo10492aT() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: aU */
    public String mo10493aU() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: aV */
    public boolean mo10504aV() {
        return this instanceof zzaov;
    }

    /* renamed from: aW */
    public boolean mo10505aW() {
        return this instanceof zzapb;
    }

    /* renamed from: aX */
    public boolean mo10506aX() {
        return this instanceof zzape;
    }

    /* renamed from: aY */
    public boolean mo10507aY() {
        return this instanceof zzapa;
    }

    /* renamed from: aZ */
    public zzapb mo10508aZ() {
        if (mo10505aW()) {
            return (zzapb) this;
        }
        String valueOf = String.valueOf(this);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("Not a JSON Object: ").append(valueOf).toString());
    }

    /* renamed from: ba */
    public zzaov mo10509ba() {
        if (mo10504aV()) {
            return (zzaov) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    /* renamed from: bb */
    public zzape mo10510bb() {
        if (mo10506aX()) {
            return (zzape) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: bc */
    public Boolean mo10511bc() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double getAsDouble() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int getAsInt() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long getAsLong() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            zzaqr zzaqr = new zzaqr(stringWriter);
            zzaqr.setLenient(true);
            zzapz.zzb(this, zzaqr);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
