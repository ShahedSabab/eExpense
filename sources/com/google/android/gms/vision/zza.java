package com.google.android.gms.vision;

import android.util.SparseArray;

public class zza {
    private static int aNO = 0;
    private static final Object zzaox = new Object();
    private SparseArray<Integer> aNP = new SparseArray<>();
    private SparseArray<Integer> aNQ = new SparseArray<>();

    public int zzaar(int i) {
        int i2;
        synchronized (zzaox) {
            Integer num = (Integer) this.aNP.get(i);
            if (num != null) {
                i2 = num.intValue();
            } else {
                i2 = aNO;
                aNO++;
                this.aNP.append(i, Integer.valueOf(i2));
                this.aNQ.append(i2, Integer.valueOf(i));
            }
        }
        return i2;
    }

    public int zzaas(int i) {
        int intValue;
        synchronized (zzaox) {
            intValue = ((Integer) this.aNQ.get(i)).intValue();
        }
        return intValue;
    }
}
