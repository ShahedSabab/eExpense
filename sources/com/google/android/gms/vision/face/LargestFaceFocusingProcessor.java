package com.google.android.gms.vision.face;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.FocusingProcessor;
import com.google.android.gms.vision.Tracker;

public class LargestFaceFocusingProcessor extends FocusingProcessor<Face> {

    public static class Builder {
        private LargestFaceFocusingProcessor aOp;

        public Builder(Detector<Face> detector, Tracker<Face> tracker) {
            this.aOp = new LargestFaceFocusingProcessor(detector, tracker);
        }

        public LargestFaceFocusingProcessor build() {
            return this.aOp;
        }

        public Builder setMaxGapFrames(int i) {
            this.aOp.zzaaq(i);
            return this;
        }
    }

    public LargestFaceFocusingProcessor(Detector<Face> detector, Tracker<Face> tracker) {
        super(detector, tracker);
    }

    public int selectFocus(Detections<Face> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            throw new IllegalArgumentException("No faces for selectFocus.");
        }
        int keyAt = detectedItems.keyAt(0);
        int i = 1;
        int i2 = keyAt;
        float width = ((Face) detectedItems.valueAt(0)).getWidth();
        while (true) {
            int i3 = i;
            if (i3 >= detectedItems.size()) {
                return i2;
            }
            int keyAt2 = detectedItems.keyAt(i3);
            float width2 = ((Face) detectedItems.valueAt(i3)).getWidth();
            if (width2 > width) {
                width = width2;
                i2 = keyAt2;
            }
            i = i3 + 1;
        }
    }
}
