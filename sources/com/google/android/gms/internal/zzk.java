package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class zzk<T> implements Comparable<zzk<T>> {
    /* access modifiers changed from: private */
    public final zza zzac;
    private final int zzad;
    private final String zzae;
    private final int zzaf;
    private final com.google.android.gms.internal.zzm.zza zzag;
    private Integer zzah;
    private zzl zzai;
    private boolean zzaj;
    private boolean zzak;
    private boolean zzal;
    private long zzam;
    private zzo zzan;
    private com.google.android.gms.internal.zzb.zza zzao;

    public enum zza {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public zzk(int i, String str, com.google.android.gms.internal.zzm.zza zza2) {
        this.zzac = zza.zzbj ? new zza() : null;
        this.zzaj = true;
        this.zzak = false;
        this.zzal = false;
        this.zzam = 0;
        this.zzao = null;
        this.zzad = i;
        this.zzae = str;
        this.zzag = zza2;
        zza((zzo) new zzd());
        this.zzaf = zzb(str);
    }

    private byte[] zza(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                sb.append(URLEncoder.encode((String) entry.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode((String) entry.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            String str2 = "Encoding not supported: ";
            String valueOf = String.valueOf(str);
            throw new RuntimeException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), unsupportedEncodingException);
        }
    }

    private static int zzb(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public int getMethod() {
        return this.zzad;
    }

    public String getUrl() {
        return this.zzae;
    }

    public boolean isCanceled() {
        return false;
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(zzf()));
        String str2 = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        String str3 = "[ ] ";
        String valueOf2 = String.valueOf(getUrl());
        String valueOf3 = String.valueOf(zzq());
        String valueOf4 = String.valueOf(this.zzah);
        return new StringBuilder(String.valueOf(str3).length() + 3 + String.valueOf(valueOf2).length() + String.valueOf(str2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length()).append(str3).append(valueOf2).append(" ").append(str2).append(" ").append(valueOf3).append(" ").append(valueOf4).toString();
    }

    public final zzk<?> zza(int i) {
        this.zzah = Integer.valueOf(i);
        return this;
    }

    public zzk<?> zza(com.google.android.gms.internal.zzb.zza zza2) {
        this.zzao = zza2;
        return this;
    }

    public zzk<?> zza(zzl zzl) {
        this.zzai = zzl;
        return this;
    }

    public zzk<?> zza(zzo zzo) {
        this.zzan = zzo;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract zzm<T> zza(zzi zzi);

    /* access modifiers changed from: protected */
    public abstract void zza(T t);

    /* access modifiers changed from: protected */
    public zzr zzb(zzr zzr) {
        return zzr;
    }

    /* renamed from: zzc */
    public int compareTo(zzk<T> zzk) {
        zza zzq = zzq();
        zza zzq2 = zzk.zzq();
        return zzq == zzq2 ? this.zzah.intValue() - zzk.zzah.intValue() : zzq2.ordinal() - zzq.ordinal();
    }

    public void zzc(zzr zzr) {
        if (this.zzag != null) {
            this.zzag.zze(zzr);
        }
    }

    public void zzc(String str) {
        if (zza.zzbj) {
            this.zzac.zza(str, Thread.currentThread().getId());
        } else if (this.zzam == 0) {
            this.zzam = SystemClock.elapsedRealtime();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzd(final String str) {
        if (this.zzai != null) {
            this.zzai.zzf(this);
        }
        if (zza.zzbj) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        zzk.this.zzac.zza(str, id);
                        zzk.this.zzac.zzd(toString());
                    }
                });
                return;
            }
            this.zzac.zza(str, id);
            this.zzac.zzd(toString());
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzam;
        if (elapsedRealtime >= 3000) {
            zzs.zzb("%d ms: %s", Long.valueOf(elapsedRealtime), toString());
        }
    }

    public int zzf() {
        return this.zzaf;
    }

    public String zzg() {
        return getUrl();
    }

    public com.google.android.gms.internal.zzb.zza zzh() {
        return this.zzao;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Map<String, String> zzi() throws zza {
        return null;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public String zzj() {
        return zzm();
    }

    @Deprecated
    public String zzk() {
        return zzn();
    }

    @Deprecated
    public byte[] zzl() throws zza {
        Map zzi = zzi();
        if (zzi == null || zzi.size() <= 0) {
            return null;
        }
        return zza(zzi, zzj());
    }

    /* access modifiers changed from: protected */
    public String zzm() {
        return "UTF-8";
    }

    public String zzn() {
        String str = "application/x-www-form-urlencoded; charset=";
        String valueOf = String.valueOf(zzm());
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public byte[] zzo() throws zza {
        return null;
    }

    public final boolean zzp() {
        return this.zzaj;
    }

    public zza zzq() {
        return zza.NORMAL;
    }

    public final int zzr() {
        return this.zzan.zzc();
    }

    public zzo zzs() {
        return this.zzan;
    }

    public void zzt() {
        this.zzal = true;
    }

    public boolean zzu() {
        return this.zzal;
    }
}
