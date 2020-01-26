package com.google.android.gms.internal;

import backbencers.nub.dailycostcalc.constant.Constant;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;

public final class zzaqn {
    public static final zzapl bqA = zza(Number.class, bqz);
    public static final zzapk<Character> bqB = new zzapk<Character>() {
        public void zza(zzaqr zzaqr, Character ch) throws IOException {
            zzaqr.zzut(ch == null ? null : String.valueOf(ch));
        }

        /* renamed from: zzp */
        public Character zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            String nextString = zzaqp.nextString();
            if (nextString.length() == 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            String str = "Expecting character, got: ";
            String valueOf = String.valueOf(nextString);
            throw new zzaph(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    };
    public static final zzapl bqC = zza(Character.TYPE, Character.class, bqB);
    public static final zzapk<String> bqD = new zzapk<String>() {
        public void zza(zzaqr zzaqr, String str) throws IOException {
            zzaqr.zzut(str);
        }

        /* renamed from: zzq */
        public String zzb(zzaqp zzaqp) throws IOException {
            zzaqq bq = zzaqp.mo10624bq();
            if (bq != zzaqq.NULL) {
                return bq == zzaqq.BOOLEAN ? Boolean.toString(zzaqp.nextBoolean()) : zzaqp.nextString();
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapk<BigDecimal> bqE = new zzapk<BigDecimal>() {
        public void zza(zzaqr zzaqr, BigDecimal bigDecimal) throws IOException {
            zzaqr.zza(bigDecimal);
        }

        /* renamed from: zzr */
        public BigDecimal zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                return new BigDecimal(zzaqp.nextString());
            } catch (NumberFormatException e) {
                throw new zzaph((Throwable) e);
            }
        }
    };
    public static final zzapk<BigInteger> bqF = new zzapk<BigInteger>() {
        public void zza(zzaqr zzaqr, BigInteger bigInteger) throws IOException {
            zzaqr.zza(bigInteger);
        }

        /* renamed from: zzs */
        public BigInteger zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                return new BigInteger(zzaqp.nextString());
            } catch (NumberFormatException e) {
                throw new zzaph((Throwable) e);
            }
        }
    };
    public static final zzapl bqG = zza(String.class, bqD);
    public static final zzapk<StringBuilder> bqH = new zzapk<StringBuilder>() {
        public void zza(zzaqr zzaqr, StringBuilder sb) throws IOException {
            zzaqr.zzut(sb == null ? null : sb.toString());
        }

        /* renamed from: zzt */
        public StringBuilder zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return new StringBuilder(zzaqp.nextString());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapl bqI = zza(StringBuilder.class, bqH);
    public static final zzapk<StringBuffer> bqJ = new zzapk<StringBuffer>() {
        public void zza(zzaqr zzaqr, StringBuffer stringBuffer) throws IOException {
            zzaqr.zzut(stringBuffer == null ? null : stringBuffer.toString());
        }

        /* renamed from: zzu */
        public StringBuffer zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return new StringBuffer(zzaqp.nextString());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapl bqK = zza(StringBuffer.class, bqJ);
    public static final zzapk<URL> bqL = new zzapk<URL>() {
        public void zza(zzaqr zzaqr, URL url) throws IOException {
            zzaqr.zzut(url == null ? null : url.toExternalForm());
        }

        /* renamed from: zzv */
        public URL zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            String nextString = zzaqp.nextString();
            if (!"null".equals(nextString)) {
                return new URL(nextString);
            }
            return null;
        }
    };
    public static final zzapl bqM = zza(URL.class, bqL);
    public static final zzapk<URI> bqN = new zzapk<URI>() {
        public void zza(zzaqr zzaqr, URI uri) throws IOException {
            zzaqr.zzut(uri == null ? null : uri.toASCIIString());
        }

        /* renamed from: zzw */
        public URI zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                String nextString = zzaqp.nextString();
                if (!"null".equals(nextString)) {
                    return new URI(nextString);
                }
                return null;
            } catch (URISyntaxException e) {
                throw new zzaoz((Throwable) e);
            }
        }
    };
    public static final zzapl bqO = zza(URI.class, bqN);
    public static final zzapk<InetAddress> bqP = new zzapk<InetAddress>() {
        public void zza(zzaqr zzaqr, InetAddress inetAddress) throws IOException {
            zzaqr.zzut(inetAddress == null ? null : inetAddress.getHostAddress());
        }

        /* renamed from: zzy */
        public InetAddress zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return InetAddress.getByName(zzaqp.nextString());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapl bqQ = zzb(InetAddress.class, bqP);
    public static final zzapk<UUID> bqR = new zzapk<UUID>() {
        public void zza(zzaqr zzaqr, UUID uuid) throws IOException {
            zzaqr.zzut(uuid == null ? null : uuid.toString());
        }

        /* renamed from: zzz */
        public UUID zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return UUID.fromString(zzaqp.nextString());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapl bqS = zza(UUID.class, bqR);
    public static final zzapl bqT = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            if (zzaqo.mo10723bB() != Timestamp.class) {
                return null;
            }
            final zzapk zzk = zzaos.zzk(Date.class);
            return new zzapk<Timestamp>() {
                public void zza(zzaqr zzaqr, Timestamp timestamp) throws IOException {
                    zzk.zza(zzaqr, timestamp);
                }

                /* renamed from: zzaa */
                public Timestamp zzb(zzaqp zzaqp) throws IOException {
                    Date date = (Date) zzk.zzb(zzaqp);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }
            };
        }
    };
    public static final zzapk<Calendar> bqU = new zzapk<Calendar>() {
        public void zza(zzaqr zzaqr, Calendar calendar) throws IOException {
            if (calendar == null) {
                zzaqr.mo10641bA();
                return;
            }
            zzaqr.mo10645by();
            zzaqr.zzus(Constant.COL_YEAR);
            zzaqr.zzcs((long) calendar.get(1));
            zzaqr.zzus(Constant.COL_MONTH);
            zzaqr.zzcs((long) calendar.get(2));
            zzaqr.zzus("dayOfMonth");
            zzaqr.zzcs((long) calendar.get(5));
            zzaqr.zzus("hourOfDay");
            zzaqr.zzcs((long) calendar.get(11));
            zzaqr.zzus("minute");
            zzaqr.zzcs((long) calendar.get(12));
            zzaqr.zzus("second");
            zzaqr.zzcs((long) calendar.get(13));
            zzaqr.mo10646bz();
        }

        /* renamed from: zzab */
        public Calendar zzb(zzaqp zzaqp) throws IOException {
            int i = 0;
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            zzaqp.beginObject();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (zzaqp.mo10624bq() != zzaqq.END_OBJECT) {
                String nextName = zzaqp.nextName();
                int nextInt = zzaqp.nextInt();
                if (Constant.COL_YEAR.equals(nextName)) {
                    i6 = nextInt;
                } else if (Constant.COL_MONTH.equals(nextName)) {
                    i5 = nextInt;
                } else if ("dayOfMonth".equals(nextName)) {
                    i4 = nextInt;
                } else if ("hourOfDay".equals(nextName)) {
                    i3 = nextInt;
                } else if ("minute".equals(nextName)) {
                    i2 = nextInt;
                } else if ("second".equals(nextName)) {
                    i = nextInt;
                }
            }
            zzaqp.endObject();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }
    };
    public static final zzapl bqV = zzb(Calendar.class, GregorianCalendar.class, bqU);
    public static final zzapk<Locale> bqW = new zzapk<Locale>() {
        public void zza(zzaqr zzaqr, Locale locale) throws IOException {
            zzaqr.zzut(locale == null ? null : locale.toString());
        }

        /* renamed from: zzac */
        public Locale zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(zzaqp.nextString(), "_");
            String str = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String str2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String str3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (str2 == null && str3 == null) ? new Locale(str) : str3 == null ? new Locale(str, str2) : new Locale(str, str2, str3);
        }
    };
    public static final zzapl bqX = zza(Locale.class, bqW);
    public static final zzapk<zzaoy> bqY = new zzapk<zzaoy>() {
        public void zza(zzaqr zzaqr, zzaoy zzaoy) throws IOException {
            if (zzaoy == null || zzaoy.mo10507aY()) {
                zzaqr.mo10641bA();
            } else if (zzaoy.mo10506aX()) {
                zzape bb = zzaoy.mo10510bb();
                if (bb.mo10528be()) {
                    zzaqr.zza(bb.mo10492aT());
                } else if (bb.mo10527bd()) {
                    zzaqr.zzdh(bb.getAsBoolean());
                } else {
                    zzaqr.zzut(bb.mo10493aU());
                }
            } else if (zzaoy.mo10504aV()) {
                zzaqr.mo10643bw();
                Iterator it = zzaoy.mo10509ba().iterator();
                while (it.hasNext()) {
                    zza(zzaqr, (zzaoy) it.next());
                }
                zzaqr.mo10644bx();
            } else if (zzaoy.mo10505aW()) {
                zzaqr.mo10645by();
                for (Entry entry : zzaoy.mo10508aZ().entrySet()) {
                    zzaqr.zzus((String) entry.getKey());
                    zza(zzaqr, (zzaoy) entry.getValue());
                }
                zzaqr.mo10646bz();
            } else {
                String valueOf = String.valueOf(zzaoy.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 15).append("Couldn't write ").append(valueOf).toString());
            }
        }

        /* renamed from: zzad */
        public zzaoy zzb(zzaqp zzaqp) throws IOException {
            switch (C055326.bpW[zzaqp.mo10624bq().ordinal()]) {
                case 1:
                    return new zzape((Number) new zzapv(zzaqp.nextString()));
                case 2:
                    return new zzape(Boolean.valueOf(zzaqp.nextBoolean()));
                case 3:
                    return new zzape(zzaqp.nextString());
                case 4:
                    zzaqp.nextNull();
                    return zzapa.bou;
                case 5:
                    zzaov zzaov = new zzaov();
                    zzaqp.beginArray();
                    while (zzaqp.hasNext()) {
                        zzaov.zzc((zzaoy) zzb(zzaqp));
                    }
                    zzaqp.endArray();
                    return zzaov;
                case 6:
                    zzapb zzapb = new zzapb();
                    zzaqp.beginObject();
                    while (zzaqp.hasNext()) {
                        zzapb.zza(zzaqp.nextName(), (zzaoy) zzb(zzaqp));
                    }
                    zzaqp.endObject();
                    return zzapb;
                default:
                    throw new IllegalArgumentException();
            }
        }
    };
    public static final zzapl bqZ = zzb(zzaoy.class, bqY);
    public static final zzapk<Class> bqj = new zzapk<Class>() {
        public void zza(zzaqr zzaqr, Class cls) throws IOException {
            if (cls == null) {
                zzaqr.mo10641bA();
            } else {
                String valueOf = String.valueOf(cls.getName());
                throw new UnsupportedOperationException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Attempted to serialize java.lang.Class: ").append(valueOf).append(". Forgot to register a type adapter?").toString());
            }
        }

        /* renamed from: zzo */
        public Class zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };
    public static final zzapl bqk = zza(Class.class, bqj);
    public static final zzapk<BitSet> bql = new zzapk<BitSet>() {
        public void zza(zzaqr zzaqr, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                zzaqr.mo10641bA();
                return;
            }
            zzaqr.mo10643bw();
            for (int i = 0; i < bitSet.length(); i++) {
                zzaqr.zzcs((long) (bitSet.get(i) ? 1 : 0));
            }
            zzaqr.mo10644bx();
        }

        /* renamed from: zzx */
        public BitSet zzb(zzaqp zzaqp) throws IOException {
            boolean z;
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            BitSet bitSet = new BitSet();
            zzaqp.beginArray();
            zzaqq bq = zzaqp.mo10624bq();
            int i = 0;
            while (bq != zzaqq.END_ARRAY) {
                switch (C055326.bpW[bq.ordinal()]) {
                    case 1:
                        if (zzaqp.nextInt() == 0) {
                            z = false;
                            break;
                        } else {
                            z = true;
                            break;
                        }
                    case 2:
                        z = zzaqp.nextBoolean();
                        break;
                    case 3:
                        String nextString = zzaqp.nextString();
                        try {
                            if (Integer.parseInt(nextString) == 0) {
                                z = false;
                                break;
                            } else {
                                z = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            String str = "Error: Expecting: bitset number value (1, 0), Found: ";
                            String valueOf = String.valueOf(nextString);
                            throw new zzaph(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        }
                    default:
                        String valueOf2 = String.valueOf(bq);
                        throw new zzaph(new StringBuilder(String.valueOf(valueOf2).length() + 27).append("Invalid bitset value type: ").append(valueOf2).toString());
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                bq = zzaqp.mo10624bq();
            }
            zzaqp.endArray();
            return bitSet;
        }
    };
    public static final zzapl bqm = zza(BitSet.class, bql);
    public static final zzapk<Boolean> bqn = new zzapk<Boolean>() {
        public void zza(zzaqr zzaqr, Boolean bool) throws IOException {
            if (bool == null) {
                zzaqr.mo10641bA();
            } else {
                zzaqr.zzdh(bool.booleanValue());
            }
        }

        /* renamed from: zzae */
        public Boolean zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return zzaqp.mo10624bq() == zzaqq.STRING ? Boolean.valueOf(Boolean.parseBoolean(zzaqp.nextString())) : Boolean.valueOf(zzaqp.nextBoolean());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapk<Boolean> bqo = new zzapk<Boolean>() {
        public void zza(zzaqr zzaqr, Boolean bool) throws IOException {
            zzaqr.zzut(bool == null ? "null" : bool.toString());
        }

        /* renamed from: zzae */
        public Boolean zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return Boolean.valueOf(zzaqp.nextString());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapl bqp = zza(Boolean.TYPE, Boolean.class, bqn);
    public static final zzapk<Number> bqq = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) zzaqp.nextInt());
            } catch (NumberFormatException e) {
                throw new zzaph((Throwable) e);
            }
        }
    };
    public static final zzapl bqr = zza(Byte.TYPE, Byte.class, bqq);
    public static final zzapk<Number> bqs = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) zzaqp.nextInt());
            } catch (NumberFormatException e) {
                throw new zzaph((Throwable) e);
            }
        }
    };
    public static final zzapl bqt = zza(Short.TYPE, Short.class, bqs);
    public static final zzapk<Number> bqu = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(zzaqp.nextInt());
            } catch (NumberFormatException e) {
                throw new zzaph((Throwable) e);
            }
        }
    };
    public static final zzapl bqv = zza(Integer.TYPE, Integer.class, bqu);
    public static final zzapk<Number> bqw = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() == zzaqq.NULL) {
                zzaqp.nextNull();
                return null;
            }
            try {
                return Long.valueOf(zzaqp.nextLong());
            } catch (NumberFormatException e) {
                throw new zzaph((Throwable) e);
            }
        }
    };
    public static final zzapk<Number> bqx = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return Float.valueOf((float) zzaqp.nextDouble());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapk<Number> bqy = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return Double.valueOf(zzaqp.nextDouble());
            }
            zzaqp.nextNull();
            return null;
        }
    };
    public static final zzapk<Number> bqz = new zzapk<Number>() {
        public void zza(zzaqr zzaqr, Number number) throws IOException {
            zzaqr.zza(number);
        }

        /* renamed from: zzg */
        public Number zzb(zzaqp zzaqp) throws IOException {
            zzaqq bq = zzaqp.mo10624bq();
            switch (bq) {
                case NUMBER:
                    return new zzapv(zzaqp.nextString());
                case NULL:
                    zzaqp.nextNull();
                    return null;
                default:
                    String valueOf = String.valueOf(bq);
                    throw new zzaph(new StringBuilder(String.valueOf(valueOf).length() + 23).append("Expecting number, got: ").append(valueOf).toString());
            }
        }
    };
    public static final zzapl bra = new zzapl() {
        public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
            Class<Enum> bB = zzaqo.mo10723bB();
            if (!Enum.class.isAssignableFrom(bB) || bB == Enum.class) {
                return null;
            }
            if (!bB.isEnum()) {
                bB = bB.getSuperclass();
            }
            return new zza(bB);
        }
    };

    private static final class zza<T extends Enum<T>> extends zzapk<T> {
        private final Map<String, T> brk = new HashMap();
        private final Map<T, String> brl = new HashMap();

        public zza(Class<T> cls) {
            Enum[] enumArr;
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    zzapn zzapn = (zzapn) cls.getField(name).getAnnotation(zzapn.class);
                    if (zzapn != null) {
                        name = zzapn.value();
                        for (String put : zzapn.mo10536bh()) {
                            this.brk.put(put, enumR);
                        }
                    }
                    String str = name;
                    this.brk.put(str, enumR);
                    this.brl.put(enumR, str);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public void zza(zzaqr zzaqr, T t) throws IOException {
            zzaqr.zzut(t == null ? null : (String) this.brl.get(t));
        }

        /* renamed from: zzaf */
        public T zzb(zzaqp zzaqp) throws IOException {
            if (zzaqp.mo10624bq() != zzaqq.NULL) {
                return (Enum) this.brk.get(zzaqp.nextString());
            }
            zzaqp.nextNull();
            return null;
        }
    }

    public static <TT> zzapl zza(final zzaqo<TT> zzaqo, final zzapk<TT> zzapk) {
        return new zzapl() {
            public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
                if (zzaqo.equals(zzaqo)) {
                    return zzapk;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zza(final Class<TT> cls, final zzapk<TT> zzapk) {
        return new zzapl() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzapk);
                return new StringBuilder(String.valueOf(valueOf).length() + 23 + String.valueOf(valueOf2).length()).append("Factory[type=").append(valueOf).append(",adapter=").append(valueOf2).append("]").toString();
            }

            public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
                if (zzaqo.mo10723bB() == cls) {
                    return zzapk;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zza(final Class<TT> cls, final Class<TT> cls2, final zzapk<? super TT> zzapk) {
        return new zzapl() {
            public String toString() {
                String valueOf = String.valueOf(cls2.getName());
                String valueOf2 = String.valueOf(cls.getName());
                String valueOf3 = String.valueOf(zzapk);
                return new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length()).append("Factory[type=").append(valueOf).append("+").append(valueOf2).append(",adapter=").append(valueOf3).append("]").toString();
            }

            public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
                Class bB = zzaqo.mo10723bB();
                if (bB == cls || bB == cls2) {
                    return zzapk;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zzb(final Class<TT> cls, final zzapk<TT> zzapk) {
        return new zzapl() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzapk);
                return new StringBuilder(String.valueOf(valueOf).length() + 32 + String.valueOf(valueOf2).length()).append("Factory[typeHierarchy=").append(valueOf).append(",adapter=").append(valueOf2).append("]").toString();
            }

            public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
                if (cls.isAssignableFrom(zzaqo.mo10723bB())) {
                    return zzapk;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zzb(final Class<TT> cls, final Class<? extends TT> cls2, final zzapk<? super TT> zzapk) {
        return new zzapl() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(cls2.getName());
                String valueOf3 = String.valueOf(zzapk);
                return new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length()).append("Factory[type=").append(valueOf).append("+").append(valueOf2).append(",adapter=").append(valueOf3).append("]").toString();
            }

            public <T> zzapk<T> zza(zzaos zzaos, zzaqo<T> zzaqo) {
                Class bB = zzaqo.mo10723bB();
                if (bB == cls || bB == cls2) {
                    return zzapk;
                }
                return null;
            }
        };
    }
}
