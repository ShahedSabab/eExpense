package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class zzaqp implements Closeable {
    private static final char[] bro = ")]}'\n".toCharArray();
    private int[] brA;
    private boolean brp = false;
    private final char[] brq = new char[1024];
    private int brr = 0;
    private int brs = 0;
    /* access modifiers changed from: private */
    public int brt = 0;
    private long bru;
    private int brv;
    private String brw;
    private int[] brx = new int[32];
    private int bry = 0;
    private String[] brz;

    /* renamed from: in */
    private final Reader f504in;
    private int limit = 0;
    private int pos = 0;

    static {
        zzapu.bph = new zzapu() {
            public void zzi(zzaqp zzaqp) throws IOException {
                if (zzaqp instanceof zzaqf) {
                    ((zzaqf) zzaqp).mo10625bt();
                    return;
                }
                int zzag = zzaqp.brt;
                if (zzag == 0) {
                    zzag = zzaqp.m87bD();
                }
                if (zzag == 13) {
                    zzaqp.brt = 9;
                } else if (zzag == 12) {
                    zzaqp.brt = 8;
                } else if (zzag == 14) {
                    zzaqp.brt = 10;
                } else {
                    String valueOf = String.valueOf(zzaqp.mo10624bq());
                    int zzai = zzaqp.getLineNumber();
                    int zzaj = zzaqp.getColumnNumber();
                    String path = zzaqp.getPath();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 70 + String.valueOf(path).length()).append("Expected a name but was ").append(valueOf).append(" ").append(" at line ").append(zzai).append(" column ").append(zzaj).append(" path ").append(path).toString());
                }
            }
        };
    }

    public zzaqp(Reader reader) {
        int[] iArr = this.brx;
        int i = this.bry;
        this.bry = i + 1;
        iArr[i] = 6;
        this.brz = new String[32];
        this.brA = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f504in = reader;
    }

    /* access modifiers changed from: private */
    /* renamed from: bD */
    public int m87bD() throws IOException {
        int i = this.brx[this.bry - 1];
        if (i == 1) {
            this.brx[this.bry - 1] = 2;
        } else if (i == 2) {
            switch (zzdi(true)) {
                case 44:
                    break;
                case 59:
                    m92bI();
                    break;
                case 93:
                    this.brt = 4;
                    return 4;
                default:
                    throw zzuv("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.brx[this.bry - 1] = 4;
            if (i == 5) {
                switch (zzdi(true)) {
                    case 44:
                        break;
                    case 59:
                        m92bI();
                        break;
                    case 125:
                        this.brt = 2;
                        return 2;
                    default:
                        throw zzuv("Unterminated object");
                }
            }
            int zzdi = zzdi(true);
            switch (zzdi) {
                case 34:
                    this.brt = 13;
                    return 13;
                case 39:
                    m92bI();
                    this.brt = 12;
                    return 12;
                case 125:
                    if (i != 5) {
                        this.brt = 2;
                        return 2;
                    }
                    throw zzuv("Expected name");
                default:
                    m92bI();
                    this.pos--;
                    if (zzc((char) zzdi)) {
                        this.brt = 14;
                        return 14;
                    }
                    throw zzuv("Expected name");
            }
        } else if (i == 4) {
            this.brx[this.bry - 1] = 5;
            switch (zzdi(true)) {
                case 58:
                    break;
                case 61:
                    m92bI();
                    if ((this.pos < this.limit || zzago(1)) && this.brq[this.pos] == '>') {
                        this.pos++;
                        break;
                    }
                default:
                    throw zzuv("Expected ':'");
            }
        } else if (i == 6) {
            if (this.brp) {
                m95bL();
            }
            this.brx[this.bry - 1] = 7;
        } else if (i == 7) {
            if (zzdi(false) == -1) {
                this.brt = 17;
                return 17;
            }
            m92bI();
            this.pos--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (zzdi(true)) {
            case 34:
                if (this.bry == 1) {
                    m92bI();
                }
                this.brt = 9;
                return 9;
            case 39:
                m92bI();
                this.brt = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.brt = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.brt = 4;
                    return 4;
                }
                break;
            case 123:
                this.brt = 1;
                return 1;
            default:
                this.pos--;
                if (this.bry == 1) {
                    m92bI();
                }
                int bE = m88bE();
                if (bE != 0) {
                    return bE;
                }
                int bF = m89bF();
                if (bF != 0) {
                    return bF;
                }
                if (!zzc(this.brq[this.pos])) {
                    throw zzuv("Expected value");
                }
                m92bI();
                this.brt = 10;
                return 10;
        }
        if (i == 1 || i == 2) {
            m92bI();
            this.pos--;
            this.brt = 7;
            return 7;
        }
        throw zzuv("Unexpected value");
    }

    /* renamed from: bE */
    private int m88bE() throws IOException {
        String str;
        String str2;
        int i;
        char c = this.brq[this.pos];
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.pos + i2 >= this.limit && !zzago(i2 + 1)) {
                return 0;
            }
            char c2 = this.brq[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || zzago(length + 1)) && zzc(this.brq[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.brt = i;
        return i;
    }

    /* renamed from: bF */
    private int m89bF() throws IOException {
        char c;
        char c2;
        boolean z;
        boolean z2;
        char[] cArr = this.brq;
        int i = this.pos;
        long j = 0;
        boolean z3 = false;
        boolean z4 = true;
        char c3 = 0;
        int i2 = 0;
        int i3 = this.limit;
        int i4 = i;
        while (true) {
            if (i4 + i2 == i3) {
                if (i2 == cArr.length) {
                    return 0;
                }
                if (zzago(i2 + 1)) {
                    i4 = this.pos;
                    i3 = this.limit;
                }
            }
            c = cArr[i4 + i2];
            switch (c) {
                case '+':
                    if (c3 != 5) {
                        return 0;
                    }
                    c2 = 6;
                    z = z4;
                    z2 = z3;
                    continue;
                case '-':
                    if (c3 == 0) {
                        c2 = 1;
                        boolean z5 = z4;
                        z2 = true;
                        z = z5;
                        continue;
                    } else if (c3 == 5) {
                        c2 = 6;
                        z = z4;
                        z2 = z3;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (c3 != 2) {
                        return 0;
                    }
                    c2 = 3;
                    z = z4;
                    z2 = z3;
                    continue;
                case 'E':
                case 'e':
                    if (c3 != 2 && c3 != 4) {
                        return 0;
                    }
                    c2 = 5;
                    z = z4;
                    z2 = z3;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (c3 != 1 && c3 != 0) {
                            if (c3 != 2) {
                                if (c3 != 3) {
                                    if (c3 != 5 && c3 != 6) {
                                        c2 = c3;
                                        z = z4;
                                        z2 = z3;
                                        break;
                                    } else {
                                        c2 = 7;
                                        z = z4;
                                        z2 = z3;
                                        break;
                                    }
                                } else {
                                    c2 = 4;
                                    z = z4;
                                    z2 = z3;
                                    break;
                                }
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - '0'));
                                boolean z6 = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) & z4;
                                z2 = z3;
                                j = j2;
                                char c4 = c3;
                                z = z6;
                                c2 = c4;
                                break;
                            } else {
                                return 0;
                            }
                        } else {
                            j = (long) (-(c - '0'));
                            c2 = 2;
                            z = z4;
                            z2 = z3;
                            continue;
                        }
                    } else {
                        break;
                    }
                    break;
            }
            i2++;
            z3 = z2;
            z4 = z;
            c3 = c2;
        }
        if (zzc(c)) {
            return 0;
        }
        if (c3 == 2 && z4 && (j != Long.MIN_VALUE || z3)) {
            if (!z3) {
                j = -j;
            }
            this.bru = j;
            this.pos += i2;
            this.brt = 15;
            return 15;
        } else if (c3 != 2 && c3 != 4 && c3 != 7) {
            return 0;
        } else {
            this.brv = i2;
            this.brt = 16;
            return 16;
        }
    }

    /* renamed from: bG */
    private String m90bG() throws IOException {
        String sb;
        StringBuilder sb2 = null;
        int i = 0;
        while (true) {
            if (this.pos + i < this.limit) {
                switch (this.brq[this.pos + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m92bI();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.brq.length) {
                if (sb2 == null) {
                    sb2 = new StringBuilder();
                }
                sb2.append(this.brq, this.pos, i);
                this.pos = i + this.pos;
                if (!zzago(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (zzago(i + 1)) {
            }
        }
        if (sb2 == null) {
            sb = new String(this.brq, this.pos, i);
        } else {
            sb2.append(this.brq, this.pos, i);
            sb = sb2.toString();
        }
        this.pos = i + this.pos;
        return sb;
    }

    /* renamed from: bH */
    private void m91bH() throws IOException {
        do {
            int i = 0;
            while (this.pos + i < this.limit) {
                switch (this.brq[this.pos + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m92bI();
                        break;
                    default:
                        i++;
                }
                this.pos = i + this.pos;
                return;
            }
            this.pos = i + this.pos;
        } while (zzago(1));
    }

    /* renamed from: bI */
    private void m92bI() throws IOException {
        if (!this.brp) {
            throw zzuv("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* renamed from: bJ */
    private void m93bJ() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || zzago(1)) {
                char[] cArr = this.brq;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.brr++;
                    this.brs = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    /* renamed from: bK */
    private char m94bK() throws IOException {
        int i;
        if (this.pos != this.limit || zzago(1)) {
            char[] cArr = this.brq;
            int i2 = this.pos;
            this.pos = i2 + 1;
            char c = cArr[i2];
            switch (c) {
                case 10:
                    this.brr++;
                    this.brs = this.pos;
                    return c;
                case 'b':
                    return 8;
                case 'f':
                    return 12;
                case 'n':
                    return 10;
                case 'r':
                    return 13;
                case 't':
                    return 9;
                case 'u':
                    if (this.pos + 4 <= this.limit || zzago(4)) {
                        int i3 = this.pos;
                        int i4 = i3 + 4;
                        int i5 = i3;
                        char c2 = 0;
                        for (int i6 = i5; i6 < i4; i6++) {
                            char c3 = this.brq[i6];
                            char c4 = (char) (c2 << 4);
                            if (c3 >= '0' && c3 <= '9') {
                                i = c3 - '0';
                            } else if (c3 >= 'a' && c3 <= 'f') {
                                i = (c3 - 'a') + 10;
                            } else if (c3 < 'A' || c3 > 'F') {
                                String str = "\\u";
                                String valueOf = String.valueOf(new String(this.brq, this.pos, 4));
                                throw new NumberFormatException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                            } else {
                                i = (c3 - 'A') + 10;
                            }
                            c2 = (char) (c4 + i);
                        }
                        this.pos += 4;
                        return c2;
                    }
                    throw zzuv("Unterminated escape sequence");
                default:
                    return c;
            }
        } else {
            throw zzuv("Unterminated escape sequence");
        }
    }

    /* renamed from: bL */
    private void m95bL() throws IOException {
        zzdi(true);
        this.pos--;
        if (this.pos + bro.length <= this.limit || zzago(bro.length)) {
            int i = 0;
            while (i < bro.length) {
                if (this.brq[this.pos + i] == bro[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.pos += bro.length;
        }
    }

    /* access modifiers changed from: private */
    public int getColumnNumber() {
        return (this.pos - this.brs) + 1;
    }

    /* access modifiers changed from: private */
    public int getLineNumber() {
        return this.brr + 1;
    }

    private void zzagn(int i) {
        if (this.bry == this.brx.length) {
            int[] iArr = new int[(this.bry * 2)];
            int[] iArr2 = new int[(this.bry * 2)];
            String[] strArr = new String[(this.bry * 2)];
            System.arraycopy(this.brx, 0, iArr, 0, this.bry);
            System.arraycopy(this.brA, 0, iArr2, 0, this.bry);
            System.arraycopy(this.brz, 0, strArr, 0, this.bry);
            this.brx = iArr;
            this.brA = iArr2;
            this.brz = strArr;
        }
        int[] iArr3 = this.brx;
        int i2 = this.bry;
        this.bry = i2 + 1;
        iArr3[i2] = i;
    }

    private boolean zzago(int i) throws IOException {
        char[] cArr = this.brq;
        this.brs -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(cArr, this.pos, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int read = this.f504in.read(cArr, this.limit, cArr.length - this.limit);
            if (read == -1) {
                return false;
            }
            this.limit = read + this.limit;
            if (this.brr == 0 && this.brs == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.brs++;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private boolean zzc(char c) throws IOException {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                m92bI();
                break;
            default:
                return true;
        }
        return false;
    }

    private String zzd(char c) throws IOException {
        char[] cArr = this.brq;
        StringBuilder sb = new StringBuilder();
        do {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i4;
                    sb.append(cArr, i, (i4 - i) - 1);
                    return sb.toString();
                }
                if (c2 == '\\') {
                    this.pos = i4;
                    sb.append(cArr, i, (i4 - i) - 1);
                    sb.append(m94bK());
                    i = this.pos;
                    i2 = this.limit;
                    i4 = i;
                } else if (c2 == 10) {
                    this.brr++;
                    this.brs = i4;
                }
                i3 = i4;
            }
            sb.append(cArr, i, i3 - i);
            this.pos = i3;
        } while (zzago(1));
        throw zzuv("Unterminated string");
    }

    private int zzdi(boolean z) throws IOException {
        char[] cArr = this.brq;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (zzago(1)) {
                    i = this.pos;
                    i2 = this.limit;
                } else if (!z) {
                    return -1;
                } else {
                    String valueOf = String.valueOf("End of input at line ");
                    int lineNumber = getLineNumber();
                    throw new EOFException(new StringBuilder(String.valueOf(valueOf).length() + 30).append(valueOf).append(lineNumber).append(" column ").append(getColumnNumber()).toString());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.brr++;
                this.brs = i3;
                i = i3;
            } else if (c == ' ' || c == 13) {
                i = i3;
            } else if (c == 9) {
                i = i3;
            } else if (c == '/') {
                this.pos = i3;
                if (i3 == i2) {
                    this.pos--;
                    boolean zzago = zzago(2);
                    this.pos++;
                    if (!zzago) {
                        return c;
                    }
                }
                m92bI();
                switch (cArr[this.pos]) {
                    case '*':
                        this.pos++;
                        if (zzuu("*/")) {
                            i = this.pos + 2;
                            i2 = this.limit;
                            break;
                        } else {
                            throw zzuv("Unterminated comment");
                        }
                    case '/':
                        this.pos++;
                        m93bJ();
                        i = this.pos;
                        i2 = this.limit;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.pos = i3;
                m92bI();
                m93bJ();
                i = this.pos;
                i2 = this.limit;
            } else {
                this.pos = i3;
                return c;
            }
        }
    }

    private void zze(char c) throws IOException {
        char[] cArr = this.brq;
        do {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i4;
                    return;
                }
                if (c2 == '\\') {
                    this.pos = i4;
                    m94bK();
                    i4 = this.pos;
                    i2 = this.limit;
                } else if (c2 == 10) {
                    this.brr++;
                    this.brs = i4;
                }
                i3 = i4;
            }
            this.pos = i3;
        } while (zzago(1));
        throw zzuv("Unterminated string");
    }

    private boolean zzuu(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !zzago(str.length())) {
                return false;
            }
            if (this.brq[this.pos] == 10) {
                this.brr++;
                this.brs = this.pos + 1;
            } else {
                int i = 0;
                while (i < str.length()) {
                    if (this.brq[this.pos + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private IOException zzuv(String str) throws IOException {
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new zzaqs(new StringBuilder(String.valueOf(str).length() + 45 + String.valueOf(path).length()).append(str).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void beginArray() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 3) {
            zzagn(1);
            this.brA[this.bry - 1] = 0;
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(mo10624bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 74 + String.valueOf(path).length()).append("Expected BEGIN_ARRAY but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void beginObject() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 1) {
            zzagn(3);
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(mo10624bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 75 + String.valueOf(path).length()).append("Expected BEGIN_OBJECT but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    /* renamed from: bq */
    public zzaqq mo10624bq() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        switch (i) {
            case 1:
                return zzaqq.BEGIN_OBJECT;
            case 2:
                return zzaqq.END_OBJECT;
            case 3:
                return zzaqq.BEGIN_ARRAY;
            case 4:
                return zzaqq.END_ARRAY;
            case 5:
            case 6:
                return zzaqq.BOOLEAN;
            case 7:
                return zzaqq.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return zzaqq.STRING;
            case 12:
            case 13:
            case 14:
                return zzaqq.NAME;
            case 15:
            case 16:
                return zzaqq.NUMBER;
            case 17:
                return zzaqq.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public void close() throws IOException {
        this.brt = 0;
        this.brx[0] = 8;
        this.bry = 1;
        this.f504in.close();
    }

    public void endArray() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 4) {
            this.bry--;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(mo10624bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 72 + String.valueOf(path).length()).append("Expected END_ARRAY but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void endObject() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 2) {
            this.bry--;
            this.brz[this.bry] = null;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(mo10624bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 73 + String.valueOf(path).length()).append("Expected END_OBJECT but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public String getPath() {
        StringBuilder append = new StringBuilder().append('$');
        int i = this.bry;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.brx[i2]) {
                case 1:
                case 2:
                    append.append('[').append(this.brA[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    append.append('.');
                    if (this.brz[i2] == null) {
                        break;
                    } else {
                        append.append(this.brz[i2]);
                        break;
                    }
            }
        }
        return append.toString();
    }

    public boolean hasNext() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.brp;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 5) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.brt = 0;
            int[] iArr2 = this.brA;
            int i3 = this.bry - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            String valueOf = String.valueOf(mo10624bq());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 72 + String.valueOf(path).length()).append("Expected a boolean but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
    }

    public double nextDouble() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 15) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.bru;
        }
        if (i == 16) {
            this.brw = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else if (i == 8 || i == 9) {
            this.brw = zzd(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.brw = m90bG();
        } else if (i != 11) {
            String valueOf = String.valueOf(mo10624bq());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 71 + String.valueOf(path).length()).append("Expected a double but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.brt = 11;
        double parseDouble = Double.parseDouble(this.brw);
        if (this.brp || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.brw = null;
            this.brt = 0;
            int[] iArr2 = this.brA;
            int i3 = this.bry - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        int lineNumber2 = getLineNumber();
        int columnNumber2 = getColumnNumber();
        String path2 = getPath();
        throw new zzaqs(new StringBuilder(String.valueOf(path2).length() + 102).append("JSON forbids NaN and infinities: ").append(parseDouble).append(" at line ").append(lineNumber2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
    }

    public int nextInt() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 15) {
            int i2 = (int) this.bru;
            if (this.bru != ((long) i2)) {
                long j = this.bru;
                int lineNumber = getLineNumber();
                int columnNumber = getColumnNumber();
                String path = getPath();
                throw new NumberFormatException(new StringBuilder(String.valueOf(path).length() + 89).append("Expected an int but was ").append(j).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            this.brt = 0;
            int[] iArr = this.brA;
            int i3 = this.bry - 1;
            iArr[i3] = iArr[i3] + 1;
            return i2;
        }
        if (i == 16) {
            this.brw = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else if (i == 8 || i == 9) {
            this.brw = zzd(i == 8 ? '\'' : '\"');
            try {
                int parseInt = Integer.parseInt(this.brw);
                this.brt = 0;
                int[] iArr2 = this.brA;
                int i4 = this.bry - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException e) {
            }
        } else {
            String valueOf = String.valueOf(mo10624bq());
            int lineNumber2 = getLineNumber();
            int columnNumber2 = getColumnNumber();
            String path2 = getPath();
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 69 + String.valueOf(path2).length()).append("Expected an int but was ").append(valueOf).append(" at line ").append(lineNumber2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
        }
        this.brt = 11;
        double parseDouble = Double.parseDouble(this.brw);
        int i5 = (int) parseDouble;
        if (((double) i5) != parseDouble) {
            String str = this.brw;
            int lineNumber3 = getLineNumber();
            int columnNumber3 = getColumnNumber();
            String path3 = getPath();
            throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 69 + String.valueOf(path3).length()).append("Expected an int but was ").append(str).append(" at line ").append(lineNumber3).append(" column ").append(columnNumber3).append(" path ").append(path3).toString());
        }
        this.brw = null;
        this.brt = 0;
        int[] iArr3 = this.brA;
        int i6 = this.bry - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long nextLong() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 15) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.bru;
        }
        if (i == 16) {
            this.brw = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else if (i == 8 || i == 9) {
            this.brw = zzd(i == 8 ? '\'' : '\"');
            try {
                long parseLong = Long.parseLong(this.brw);
                this.brt = 0;
                int[] iArr2 = this.brA;
                int i3 = this.bry - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            String valueOf = String.valueOf(mo10624bq());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 69 + String.valueOf(path).length()).append("Expected a long but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.brt = 11;
        double parseDouble = Double.parseDouble(this.brw);
        long j = (long) parseDouble;
        if (((double) j) != parseDouble) {
            String str = this.brw;
            int lineNumber2 = getLineNumber();
            int columnNumber2 = getColumnNumber();
            String path2 = getPath();
            throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 69 + String.valueOf(path2).length()).append("Expected a long but was ").append(str).append(" at line ").append(lineNumber2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
        }
        this.brw = null;
        this.brt = 0;
        int[] iArr3 = this.brA;
        int i4 = this.bry - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j;
    }

    public String nextName() throws IOException {
        String zzd;
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 14) {
            zzd = m90bG();
        } else if (i == 12) {
            zzd = zzd('\'');
        } else if (i == 13) {
            zzd = zzd('\"');
        } else {
            String valueOf = String.valueOf(mo10624bq());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 69 + String.valueOf(path).length()).append("Expected a name but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.brt = 0;
        this.brz[this.bry - 1] = zzd;
        return zzd;
    }

    public void nextNull() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 7) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        String valueOf = String.valueOf(mo10624bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 67 + String.valueOf(path).length()).append("Expected null but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public String nextString() throws IOException {
        String str;
        int i = this.brt;
        if (i == 0) {
            i = m87bD();
        }
        if (i == 10) {
            str = m90bG();
        } else if (i == 8) {
            str = zzd('\'');
        } else if (i == 9) {
            str = zzd('\"');
        } else if (i == 11) {
            str = this.brw;
            this.brw = null;
        } else if (i == 15) {
            str = Long.toString(this.bru);
        } else if (i == 16) {
            str = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else {
            String valueOf = String.valueOf(mo10624bq());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 71 + String.valueOf(path).length()).append("Expected a string but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.brt = 0;
        int[] iArr = this.brA;
        int i2 = this.bry - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void setLenient(boolean z) {
        this.brp = z;
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.brt;
            if (i2 == 0) {
                i2 = m87bD();
            }
            if (i2 == 3) {
                zzagn(1);
                i++;
            } else if (i2 == 1) {
                zzagn(3);
                i++;
            } else if (i2 == 4) {
                this.bry--;
                i--;
            } else if (i2 == 2) {
                this.bry--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                m91bH();
            } else if (i2 == 8 || i2 == 12) {
                zze('\'');
            } else if (i2 == 9 || i2 == 13) {
                zze('\"');
            } else if (i2 == 16) {
                this.pos += this.brv;
            }
            this.brt = 0;
        } while (i != 0);
        int[] iArr = this.brA;
        int i3 = this.bry - 1;
        iArr[i3] = iArr[i3] + 1;
        this.brz[this.bry - 1] = "null";
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getSimpleName());
        int lineNumber = getLineNumber();
        return new StringBuilder(String.valueOf(valueOf).length() + 39).append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(getColumnNumber()).toString();
    }
}
