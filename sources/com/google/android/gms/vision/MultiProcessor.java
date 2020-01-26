package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import java.util.HashSet;

public class MultiProcessor<T> implements Processor<T> {
    /* access modifiers changed from: private */
    public int aNH;
    /* access modifiers changed from: private */
    public Factory<T> aNT;
    private SparseArray<zza> aNU;

    public static class Builder<T> {
        private MultiProcessor<T> aNV = new MultiProcessor<>();

        public Builder(Factory<T> factory) {
            if (factory == null) {
                throw new IllegalArgumentException("No factory supplied.");
            }
            this.aNV.aNT = factory;
        }

        public MultiProcessor<T> build() {
            return this.aNV;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Invalid max gap: " + i);
            }
            this.aNV.aNH = i;
            return this;
        }
    }

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    private class zza {
        /* access modifiers changed from: private */
        public Tracker<T> aNG;
        /* access modifiers changed from: private */
        public int aNK;

        private zza() {
            this.aNK = 0;
        }
    }

    private MultiProcessor() {
        this.aNU = new SparseArray<>();
        this.aNH = 3;
    }

    private void zza(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            if (this.aNU.get(keyAt) == null) {
                zza zza2 = new zza();
                zza2.aNG = this.aNT.create(valueAt);
                zza2.aNG.onNewItem(keyAt, valueAt);
                this.aNU.append(keyAt, zza2);
            }
        }
    }

    private void zzb(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet<>();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.aNU.size()) {
                break;
            }
            int keyAt = this.aNU.keyAt(i2);
            if (detectedItems.get(keyAt) == null) {
                zza zza2 = (zza) this.aNU.valueAt(i2);
                zza2.aNK = zza2.aNK + 1;
                if (zza2.aNK >= this.aNH) {
                    zza2.aNG.onDone();
                    hashSet.add(Integer.valueOf(keyAt));
                } else {
                    zza2.aNG.onMissing(detections);
                }
            }
            i = i2 + 1;
        }
        for (Integer intValue : hashSet) {
            this.aNU.delete(intValue.intValue());
        }
    }

    private void zzc(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            zza zza2 = (zza) this.aNU.get(keyAt);
            zza2.aNK = 0;
            zza2.aNG.onUpdate(detections, valueAt);
        }
    }

    public void receiveDetections(Detections<T> detections) {
        zza(detections);
        zzb(detections);
        zzc(detections);
    }

    public void release() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.aNU.size()) {
                ((zza) this.aNU.valueAt(i2)).aNG.onDone();
                i = i2 + 1;
            } else {
                this.aNU.clear();
                return;
            }
        }
    }
}
