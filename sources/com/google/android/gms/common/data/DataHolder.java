package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzc;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@KeepName
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Creator<DataHolder> CREATOR = new zze();

    /* renamed from: Cf */
    private static final zza f184Cf = new zza(new String[0], null) {
        public zza zza(ContentValues contentValues) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    };

    /* renamed from: BY */
    private final String[] f185BY;

    /* renamed from: BZ */
    Bundle f186BZ;

    /* renamed from: Ca */
    private final CursorWindow[] f187Ca;

    /* renamed from: Cb */
    private final Bundle f188Cb;

    /* renamed from: Cc */
    int[] f189Cc;

    /* renamed from: Cd */
    int f190Cd;

    /* renamed from: Ce */
    private boolean f191Ce;
    boolean mClosed;
    final int mVersionCode;

    /* renamed from: uo */
    private final int f192uo;

    public static class zza {
        /* access modifiers changed from: private */

        /* renamed from: BY */
        public final String[] f193BY;
        /* access modifiers changed from: private */

        /* renamed from: Cg */
        public final ArrayList<HashMap<String, Object>> f194Cg;

        /* renamed from: Ch */
        private final String f195Ch;

        /* renamed from: Ci */
        private final HashMap<Object, Integer> f196Ci;

        /* renamed from: Cj */
        private boolean f197Cj;

        /* renamed from: Ck */
        private String f198Ck;

        private zza(String[] strArr, String str) {
            this.f193BY = (String[]) zzaa.zzy(strArr);
            this.f194Cg = new ArrayList<>();
            this.f195Ch = str;
            this.f196Ci = new HashMap<>();
            this.f197Cj = false;
            this.f198Ck = null;
        }

        private int zzc(HashMap<String, Object> hashMap) {
            if (this.f195Ch == null) {
                return -1;
            }
            Object obj = hashMap.get(this.f195Ch);
            if (obj == null) {
                return -1;
            }
            Integer num = (Integer) this.f196Ci.get(obj);
            if (num != null) {
                return num.intValue();
            }
            this.f196Ci.put(obj, Integer.valueOf(this.f194Cg.size()));
            return -1;
        }

        public zza zza(ContentValues contentValues) {
            zzc.zzu(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Entry entry : contentValues.valueSet()) {
                hashMap.put((String) entry.getKey(), entry.getValue());
            }
            return zzb(hashMap);
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            zzc.zzu(hashMap);
            int zzc = zzc(hashMap);
            if (zzc == -1) {
                this.f194Cg.add(hashMap);
            } else {
                this.f194Cg.remove(zzc);
                this.f194Cg.add(zzc, hashMap);
            }
            this.f197Cj = false;
            return this;
        }

        public DataHolder zzgc(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.f191Ce = true;
        this.mVersionCode = i;
        this.f185BY = strArr;
        this.f187Ca = cursorWindowArr;
        this.f192uo = i2;
        this.f188Cb = bundle;
    }

    private DataHolder(zza zza2, int i, Bundle bundle) {
        this(zza2.f193BY, zza(zza2, -1), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.f191Ce = true;
        this.mVersionCode = 1;
        this.f185BY = (String[]) zzaa.zzy(strArr);
        this.f187Ca = (CursorWindow[]) zzaa.zzy(cursorWindowArr);
        this.f192uo = i;
        this.f188Cb = bundle;
        zzaun();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(f184Cf, i, bundle);
    }

    private static CursorWindow[] zza(zza zza2, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (zza2.f193BY.length == 0) {
            return new CursorWindow[0];
        }
        List zzb2 = (i < 0 || i >= zza2.f194Cg.size()) ? zza2.f194Cg : zza2.f194Cg.subList(0, i);
        int size = zzb2.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(zza2.f193BY.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(zza2.f193BY.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb2.get(i3);
                boolean z3 = true;
                for (int i4 = 0; i4 < zza2.f193BY.length && z3; i4++) {
                    String str = zza2.f193BY[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z3 = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        z3 = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        z3 = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        z3 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        z3 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i4);
                    } else if (obj instanceof byte[]) {
                        z3 = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        z3 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else if (obj instanceof Float) {
                        z3 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i3, i4);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z3) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else if (z2) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza2.f193BY.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static zza zzc(String[] strArr) {
        return new zza(strArr, null);
    }

    public static DataHolder zzgb(int i) {
        return zza(i, (Bundle) null);
    }

    private void zzi(String str, int i) {
        if (this.f186BZ == null || !this.f186BZ.containsKey(str)) {
            String str2 = "No such column: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.f190Cd) {
            throw new CursorIndexOutOfBoundsException(i, this.f190Cd);
        }
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.f187Ca) {
                    close.close();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.f191Ce && this.f187Ca.length > 0 && !isClosed()) {
                close();
                String valueOf = String.valueOf(toString());
                Log.e("DataBuffer", new StringBuilder(String.valueOf(valueOf).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(valueOf).append(")").toString());
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.f190Cd;
    }

    public int getStatusCode() {
        return this.f192uo;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzi(str, i);
        this.f187Ca[i2].copyStringToBuffer(i, this.f186BZ.getInt(str), charArrayBuffer);
    }

    public Bundle zzaui() {
        return this.f188Cb;
    }

    public void zzaun() {
        this.f186BZ = new Bundle();
        for (int i = 0; i < this.f185BY.length; i++) {
            this.f186BZ.putInt(this.f185BY[i], i);
        }
        this.f189Cc = new int[this.f187Ca.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.f187Ca.length; i3++) {
            this.f189Cc[i3] = i2;
            i2 += this.f187Ca[i3].getNumRows() - (i2 - this.f187Ca[i3].getStartPosition());
        }
        this.f190Cd = i2;
    }

    /* access modifiers changed from: 0000 */
    public String[] zzauo() {
        return this.f185BY;
    }

    /* access modifiers changed from: 0000 */
    public CursorWindow[] zzaup() {
        return this.f187Ca;
    }

    public long zzb(String str, int i, int i2) {
        zzi(str, i);
        return this.f187Ca[i2].getLong(i, this.f186BZ.getInt(str));
    }

    public int zzc(String str, int i, int i2) {
        zzi(str, i);
        return this.f187Ca[i2].getInt(i, this.f186BZ.getInt(str));
    }

    public String zzd(String str, int i, int i2) {
        zzi(str, i);
        return this.f187Ca[i2].getString(i, this.f186BZ.getInt(str));
    }

    public boolean zze(String str, int i, int i2) {
        zzi(str, i);
        return Long.valueOf(this.f187Ca[i2].getLong(i, this.f186BZ.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzi(str, i);
        return this.f187Ca[i2].getFloat(i, this.f186BZ.getInt(str));
    }

    public byte[] zzg(String str, int i, int i2) {
        zzi(str, i);
        return this.f187Ca[i2].getBlob(i, this.f186BZ.getInt(str));
    }

    public int zzga(int i) {
        int i2 = 0;
        zzaa.zzbs(i >= 0 && i < this.f190Cd);
        while (true) {
            if (i2 >= this.f189Cc.length) {
                break;
            } else if (i < this.f189Cc[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.f189Cc.length ? i2 - 1 : i2;
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }

    public boolean zzho(String str) {
        return this.f186BZ.containsKey(str);
    }

    public boolean zzi(String str, int i, int i2) {
        zzi(str, i);
        return this.f187Ca[i2].isNull(i, this.f186BZ.getInt(str));
    }
}
