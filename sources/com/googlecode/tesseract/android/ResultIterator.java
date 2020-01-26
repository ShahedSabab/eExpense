package com.googlecode.tesseract.android;

import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class ResultIterator extends PageIterator {
    private final long mNativeResultIterator;

    private static native float nativeConfidence(long j, int i);

    private static native void nativeDelete(long j);

    private static native String[] nativeGetChoices(long j, int i);

    private static native String nativeGetUTF8Text(long j, int i);

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    ResultIterator(long nativeResultIterator) {
        super(nativeResultIterator);
        this.mNativeResultIterator = nativeResultIterator;
    }

    public String getUTF8Text(int level) {
        return nativeGetUTF8Text(this.mNativeResultIterator, level);
    }

    public float confidence(int level) {
        return nativeConfidence(this.mNativeResultIterator, level);
    }

    public List<Pair<String, Double>> getChoicesAndConfidence(int level) {
        String utfString;
        String[] nativeChoices = nativeGetChoices(this.mNativeResultIterator, level);
        ArrayList<Pair<String, Double>> pairedResults = new ArrayList<>();
        for (String nativeChoice : nativeChoices) {
            int separatorPosition = nativeChoice.lastIndexOf(124);
            String str = "";
            Double confidenceLevel = Double.valueOf(0.0d);
            if (separatorPosition > 0) {
                utfString = nativeChoice.substring(0, separatorPosition);
                try {
                    confidenceLevel = Double.valueOf(Double.parseDouble(nativeChoice.substring(separatorPosition + 1)));
                } catch (NumberFormatException e) {
                    Log.e("ResultIterator", "Invalid confidence level for " + nativeChoice);
                }
            } else {
                utfString = nativeChoice;
            }
            pairedResults.add(new Pair(utfString, confidenceLevel));
        }
        return pairedResults;
    }

    public void delete() {
        nativeDelete(this.mNativeResultIterator);
    }
}
