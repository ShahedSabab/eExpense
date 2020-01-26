package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.vision.text.internal.client.BoundingBoxParcel;

final class zza {
    static Rect zza(Text text) {
        Point[] cornerPoints;
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i4 = Math.min(i4, point.x);
            i3 = Math.max(i3, point.x);
            i = Math.min(i, point.y);
            i2 = Math.max(i2, point.y);
        }
        return new Rect(i4, i, i3, i2);
    }

    static Point[] zza(BoundingBoxParcel boundingBoxParcel) {
        Point[] pointArr = new Point[4];
        double sin = Math.sin(Math.toRadians((double) boundingBoxParcel.aOP));
        double cos = Math.cos(Math.toRadians((double) boundingBoxParcel.aOP));
        pointArr[0] = new Point(boundingBoxParcel.left, boundingBoxParcel.top);
        pointArr[1] = new Point((int) (((double) boundingBoxParcel.left) + (((double) boundingBoxParcel.width) * cos)), (int) (((double) boundingBoxParcel.top) + (((double) boundingBoxParcel.width) * sin)));
        pointArr[2] = new Point((int) (((double) pointArr[1].x) - (sin * ((double) boundingBoxParcel.height))), (int) ((cos * ((double) boundingBoxParcel.height)) + ((double) pointArr[1].y)));
        pointArr[3] = new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y));
        return pointArr;
    }
}
