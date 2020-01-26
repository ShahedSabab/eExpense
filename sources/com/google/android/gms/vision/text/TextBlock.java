package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.vision.text.internal.client.BoundingBoxParcel;
import com.google.android.gms.vision.text.internal.client.LineBoxParcel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TextBlock implements Text {
    private LineBoxParcel[] aOI;
    private List<Line> aOJ;
    private String aOK;
    private Rect aOL;
    private Point[] cornerPoints;

    TextBlock(SparseArray<LineBoxParcel> sparseArray) {
        this.aOI = new LineBoxParcel[sparseArray.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.aOI.length) {
                this.aOI[i2] = (LineBoxParcel) sparseArray.valueAt(i2);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static Point[] zza(int i, int i2, int i3, int i4, BoundingBoxParcel boundingBoxParcel) {
        int i5 = boundingBoxParcel.left;
        int i6 = boundingBoxParcel.top;
        double sin = Math.sin(Math.toRadians((double) boundingBoxParcel.aOP));
        double cos = Math.cos(Math.toRadians((double) boundingBoxParcel.aOP));
        Point[] pointArr = {new Point(i, i2), new Point(i3, i2), new Point(i3, i4), new Point(i, i4)};
        for (int i7 = 0; i7 < 4; i7++) {
            int i8 = (int) ((((double) pointArr[i7].x) * sin) + (((double) pointArr[i7].y) * cos));
            pointArr[i7].x = (int) ((((double) pointArr[i7].x) * cos) - (((double) pointArr[i7].y) * sin));
            pointArr[i7].y = i8;
            pointArr[i7].offset(i5, i6);
        }
        return pointArr;
    }

    private static Point[] zza(BoundingBoxParcel boundingBoxParcel, BoundingBoxParcel boundingBoxParcel2) {
        int i = -boundingBoxParcel2.left;
        int i2 = -boundingBoxParcel2.top;
        double sin = Math.sin(Math.toRadians((double) boundingBoxParcel2.aOP));
        double cos = Math.cos(Math.toRadians((double) boundingBoxParcel2.aOP));
        Point[] pointArr = new Point[4];
        pointArr[0] = new Point(boundingBoxParcel.left, boundingBoxParcel.top);
        pointArr[0].offset(i, i2);
        int i3 = (int) ((((double) pointArr[0].x) * cos) + (((double) pointArr[0].y) * sin));
        int i4 = (int) ((sin * ((double) (-pointArr[0].x))) + (cos * ((double) pointArr[0].y)));
        pointArr[0].x = i3;
        pointArr[0].y = i4;
        pointArr[1] = new Point(boundingBoxParcel.width + i3, i4);
        pointArr[2] = new Point(boundingBoxParcel.width + i3, boundingBoxParcel.height + i4);
        pointArr[3] = new Point(i3, i4 + boundingBoxParcel.height);
        return pointArr;
    }

    public Rect getBoundingBox() {
        if (this.aOL == null) {
            this.aOL = zza.zza((Text) this);
        }
        return this.aOL;
    }

    public List<? extends Text> getComponents() {
        return zzclv();
    }

    public Point[] getCornerPoints() {
        if (this.cornerPoints == null) {
            zzclu();
        }
        return this.cornerPoints;
    }

    public String getLanguage() {
        LineBoxParcel[] lineBoxParcelArr;
        if (this.aOK != null) {
            return this.aOK;
        }
        HashMap hashMap = new HashMap();
        for (LineBoxParcel lineBoxParcel : this.aOI) {
            hashMap.put(lineBoxParcel.aOK, Integer.valueOf((hashMap.containsKey(lineBoxParcel.aOK) ? ((Integer) hashMap.get(lineBoxParcel.aOK)).intValue() : 0) + 1));
        }
        this.aOK = (String) ((Entry) Collections.max(hashMap.entrySet(), new Comparator<Entry<String, Integer>>() {
            /* renamed from: zza */
            public int compare(Entry<String, Integer> entry, Entry<String, Integer> entry2) {
                return ((Integer) entry.getValue()).compareTo((Integer) entry2.getValue());
            }
        })).getKey();
        if (this.aOK == null || this.aOK.isEmpty()) {
            this.aOK = "und";
        }
        return this.aOK;
    }

    public String getValue() {
        if (this.aOI.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(this.aOI[0].aOU);
        for (int i = 1; i < this.aOI.length; i++) {
            sb.append("\n");
            sb.append(this.aOI[i].aOU);
        }
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public void zzclu() {
        if (this.aOI.length == 0) {
            this.cornerPoints = new Point[0];
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (LineBoxParcel lineBoxParcel : this.aOI) {
            Point[] zza = zza(lineBoxParcel.aOR, this.aOI[0].aOR);
            int i5 = 0;
            while (i5 < 4) {
                Point point = zza[i5];
                int min = Math.min(i3, point.x);
                int max = Math.max(i2, point.x);
                int min2 = Math.min(i, point.y);
                i5++;
                i4 = Math.max(i4, point.y);
                i = min2;
                i2 = max;
                i3 = min;
            }
        }
        this.cornerPoints = zza(i3, i, i2, i4, this.aOI[0].aOR);
    }

    /* access modifiers changed from: 0000 */
    public List<Line> zzclv() {
        if (this.aOI.length == 0) {
            return new ArrayList(0);
        }
        if (this.aOJ == null) {
            this.aOJ = new ArrayList(this.aOI.length);
            for (LineBoxParcel line : this.aOI) {
                this.aOJ.add(new Line(line));
            }
        }
        return this.aOJ;
    }
}
