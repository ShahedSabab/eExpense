package com.google.android.gms.internal;

import java.io.IOException;

public class zzarz extends IOException {
    public zzarz(String str) {
        super(str);
    }

    /* renamed from: cr */
    static zzarz m131cr() {
        return new zzarz("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    /* renamed from: cs */
    static zzarz m132cs() {
        return new zzarz("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* renamed from: ct */
    static zzarz m133ct() {
        return new zzarz("CodedInputStream encountered a malformed varint.");
    }

    /* renamed from: cu */
    static zzarz m134cu() {
        return new zzarz("Protocol message contained an invalid tag (zero).");
    }

    /* renamed from: cv */
    static zzarz m135cv() {
        return new zzarz("Protocol message end-group tag did not match expected tag.");
    }

    /* renamed from: cw */
    static zzarz m136cw() {
        return new zzarz("Protocol message tag had invalid wire type.");
    }

    /* renamed from: cx */
    static zzarz m137cx() {
        return new zzarz("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
