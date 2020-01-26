package com.google.android.gms.internal;

import java.math.BigInteger;

public final class zzape extends zzaoy {
    private static final Class<?>[] bow = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object value;

    public zzape(Boolean bool) {
        setValue(bool);
    }

    public zzape(Number number) {
        setValue(number);
    }

    zzape(Object obj) {
        setValue(obj);
    }

    public zzape(String str) {
        setValue(str);
    }

    private static boolean zza(zzape zzape) {
        if (!(zzape.value instanceof Number)) {
            return false;
        }
        Number number = (Number) zzape.value;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    private static boolean zzcm(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class<?> isAssignableFrom : bow) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: aT */
    public Number mo10492aT() {
        return this.value instanceof String ? new zzapv((String) this.value) : (Number) this.value;
    }

    /* renamed from: aU */
    public String mo10493aU() {
        return mo10528be() ? mo10492aT().toString() : mo10527bd() ? mo10511bc().toString() : (String) this.value;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: bc */
    public Boolean mo10511bc() {
        return (Boolean) this.value;
    }

    /* renamed from: bd */
    public boolean mo10527bd() {
        return this.value instanceof Boolean;
    }

    /* renamed from: be */
    public boolean mo10528be() {
        return this.value instanceof Number;
    }

    /* renamed from: bf */
    public boolean mo10529bf() {
        return this.value instanceof String;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzape zzape = (zzape) obj;
        if (this.value == null) {
            return zzape.value == null;
        }
        if (zza(this) && zza(zzape)) {
            return mo10492aT().longValue() == zzape.mo10492aT().longValue();
        }
        if (!(this.value instanceof Number) || !(zzape.value instanceof Number)) {
            return this.value.equals(zzape.value);
        }
        double doubleValue = mo10492aT().doubleValue();
        double doubleValue2 = zzape.mo10492aT().doubleValue();
        if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
            z = true;
        }
        return z;
    }

    public boolean getAsBoolean() {
        return mo10527bd() ? mo10511bc().booleanValue() : Boolean.parseBoolean(mo10493aU());
    }

    public double getAsDouble() {
        return mo10528be() ? mo10492aT().doubleValue() : Double.parseDouble(mo10493aU());
    }

    public int getAsInt() {
        return mo10528be() ? mo10492aT().intValue() : Integer.parseInt(mo10493aU());
    }

    public long getAsLong() {
        return mo10528be() ? mo10492aT().longValue() : Long.parseLong(mo10493aU());
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (zza(this)) {
            long longValue = mo10492aT().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.value instanceof Number)) {
            return this.value.hashCode();
        } else {
            long doubleToLongBits = Double.doubleToLongBits(mo10492aT().doubleValue());
            return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        }
    }

    /* access modifiers changed from: 0000 */
    public void setValue(Object obj) {
        if (obj instanceof Character) {
            this.value = String.valueOf(((Character) obj).charValue());
            return;
        }
        zzapq.zzbt((obj instanceof Number) || zzcm(obj));
        this.value = obj;
    }
}
