package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T> extends AbstractDataBuffer<T> {

    /* renamed from: Cl */
    private boolean f206Cl = false;

    /* renamed from: Cm */
    private ArrayList<Integer> f207Cm;

    protected zzf(DataHolder dataHolder) {
        super(dataHolder);
    }

    private void zzaur() {
        synchronized (this) {
            if (!this.f206Cl) {
                int count = this.f178zy.getCount();
                this.f207Cm = new ArrayList<>();
                if (count > 0) {
                    this.f207Cm.add(Integer.valueOf(0));
                    String zzauq = zzauq();
                    String zzd = this.f178zy.zzd(zzauq, 0, this.f178zy.zzga(0));
                    int i = 1;
                    while (i < count) {
                        int zzga = this.f178zy.zzga(i);
                        String zzd2 = this.f178zy.zzd(zzauq, i, zzga);
                        if (zzd2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(zzauq).length() + 78).append("Missing value for markerColumn: ").append(zzauq).append(", at row: ").append(i).append(", for window: ").append(zzga).toString());
                        }
                        if (!zzd2.equals(zzd)) {
                            this.f207Cm.add(Integer.valueOf(i));
                        } else {
                            zzd2 = zzd;
                        }
                        i++;
                        zzd = zzd2;
                    }
                }
                this.f206Cl = true;
            }
        }
    }

    public final T get(int i) {
        zzaur();
        return zzn(zzge(i), zzgf(i));
    }

    public int getCount() {
        zzaur();
        return this.f207Cm.size();
    }

    /* access modifiers changed from: protected */
    public abstract String zzauq();

    /* access modifiers changed from: protected */
    public String zzaus() {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int zzge(int i) {
        if (i >= 0 && i < this.f207Cm.size()) {
            return ((Integer) this.f207Cm.get(i)).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    /* access modifiers changed from: protected */
    public int zzgf(int i) {
        if (i < 0 || i == this.f207Cm.size()) {
            return 0;
        }
        int intValue = i == this.f207Cm.size() + -1 ? this.f178zy.getCount() - ((Integer) this.f207Cm.get(i)).intValue() : ((Integer) this.f207Cm.get(i + 1)).intValue() - ((Integer) this.f207Cm.get(i)).intValue();
        if (intValue != 1) {
            return intValue;
        }
        int zzge = zzge(i);
        int zzga = this.f178zy.zzga(zzge);
        String zzaus = zzaus();
        if (zzaus == null || this.f178zy.zzd(zzaus, zzge, zzga) != null) {
            return intValue;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract T zzn(int i, int i2);
}
