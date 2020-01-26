package com.googlecode.tesseract.android;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.googlecode.leptonica.android.Pix;
import com.googlecode.leptonica.android.Pixa;
import com.googlecode.leptonica.android.ReadFile;
import java.io.File;

public class TessBaseAPI {
    @Deprecated
    public static final int OEM_CUBE_ONLY = 1;
    public static final int OEM_DEFAULT = 3;
    @Deprecated
    public static final int OEM_TESSERACT_CUBE_COMBINED = 2;
    public static final int OEM_TESSERACT_ONLY = 0;
    public static final String VAR_CHAR_BLACKLIST = "tessedit_char_blacklist";
    public static final String VAR_CHAR_WHITELIST = "tessedit_char_whitelist";
    public static final String VAR_FALSE = "F";
    public static final String VAR_SAVE_BLOB_CHOICES = "save_blob_choices";
    public static final String VAR_TRUE = "T";
    private long mNativeData;
    private boolean mRecycled = false;
    private ProgressNotifier progressNotifier;

    public static final class PageIteratorLevel {
        public static final int RIL_BLOCK = 0;
        public static final int RIL_PARA = 1;
        public static final int RIL_SYMBOL = 4;
        public static final int RIL_TEXTLINE = 2;
        public static final int RIL_WORD = 3;
    }

    public static final class PageSegMode {
        public static final int PSM_AUTO = 3;
        public static final int PSM_AUTO_ONLY = 2;
        public static final int PSM_AUTO_OSD = 1;
        public static final int PSM_CIRCLE_WORD = 9;
        public static final int PSM_COUNT = 13;
        public static final int PSM_OSD_ONLY = 0;
        public static final int PSM_SINGLE_BLOCK = 6;
        public static final int PSM_SINGLE_BLOCK_VERT_TEXT = 5;
        public static final int PSM_SINGLE_CHAR = 10;
        public static final int PSM_SINGLE_COLUMN = 4;
        public static final int PSM_SINGLE_LINE = 7;
        public static final int PSM_SINGLE_WORD = 8;
        public static final int PSM_SPARSE_TEXT = 11;
        public static final int PSM_SPARSE_TEXT_OSD = 12;
    }

    public interface ProgressNotifier {
        void onProgressValues(ProgressValues progressValues);
    }

    public class ProgressValues {
        private final int percent;
        private final Rect textRect;
        private final Rect wordRect;

        public ProgressValues(int percent2, Rect wordRect2, Rect textRect2) {
            this.percent = percent2;
            this.wordRect = wordRect2;
            this.textRect = textRect2;
        }

        public int getPercent() {
            return this.percent;
        }

        public Rect getCurrentWordRect() {
            return this.wordRect;
        }

        @Deprecated
        public int getBoundingBoxLeft() {
            return this.wordRect.left;
        }

        @Deprecated
        public int getBoundingBoxRight() {
            return this.wordRect.right;
        }

        @Deprecated
        public int getBoundingBoxTop() {
            return this.textRect.bottom - this.wordRect.top;
        }

        @Deprecated
        public int getBoundingBoxBottom() {
            return this.textRect.bottom - this.wordRect.bottom;
        }

        public Rect getCurrentRect() {
            return this.textRect;
        }
    }

    private native boolean nativeAddPageToDocument(long j, String str, long j2);

    private native boolean nativeBeginDocument(long j, String str);

    private static native void nativeClassInit();

    private native void nativeClear();

    private native void nativeConstruct();

    private native void nativeEnd();

    private native boolean nativeEndDocument(long j);

    private native String nativeGetBoxText(int i);

    private native long nativeGetConnectedComponents();

    private native String nativeGetHOCRText(int i);

    private native String nativeGetInitLanguagesAsString();

    private native int nativeGetPageSegMode();

    private native long nativeGetRegions();

    private native long nativeGetResultIterator();

    private native long nativeGetStrips();

    private native long nativeGetTextlines();

    private native long nativeGetThresholdedImage();

    private native String nativeGetUTF8Text();

    private native long nativeGetWords();

    private native boolean nativeInit(String str, String str2);

    private native boolean nativeInitOem(String str, String str2, int i);

    private native int nativeMeanConfidence();

    private native void nativeReadConfigFile(String str);

    private native void nativeSetDebug(boolean z);

    private native void nativeSetImageBytes(byte[] bArr, int i, int i2, int i3, int i4);

    private native void nativeSetImagePix(long j);

    private native void nativeSetInputName(String str);

    private native void nativeSetOutputName(String str);

    private native void nativeSetPageSegMode(int i);

    private native void nativeSetRectangle(int i, int i2, int i3, int i4);

    private native boolean nativeSetVariable(String str, String str2);

    private native void nativeStop();

    private native int[] nativeWordConfidences();

    static {
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
        nativeClassInit();
    }

    public TessBaseAPI() {
        nativeConstruct();
    }

    public TessBaseAPI(ProgressNotifier progressNotifier2) {
        this.progressNotifier = progressNotifier2;
        nativeConstruct();
    }

    public boolean init(String datapath, String language) {
        return init(datapath, language, 3);
    }

    public boolean init(String datapath, String language, int ocrEngineMode) {
        String[] split;
        if (datapath == null) {
            throw new IllegalArgumentException("Data path must not be null!");
        }
        if (!datapath.endsWith(File.separator)) {
            datapath = datapath + File.separator;
        }
        if (!new File(datapath).exists()) {
            throw new IllegalArgumentException("Data path does not exist!");
        }
        File tessdata = new File(datapath + "tessdata");
        if (!tessdata.exists() || !tessdata.isDirectory()) {
            throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
        }
        if (ocrEngineMode != 1) {
            for (String languageCode : language.split("\\+")) {
                if (!languageCode.startsWith("~")) {
                    File datafile = new File(tessdata + File.separator + languageCode + ".traineddata");
                    if (!datafile.exists()) {
                        throw new IllegalArgumentException("Data file not found at " + datafile);
                    }
                }
            }
        }
        boolean success = nativeInitOem(datapath, language, ocrEngineMode);
        if (success) {
            this.mRecycled = false;
        }
        return success;
    }

    public String getInitLanguagesAsString() {
        if (!this.mRecycled) {
            return nativeGetInitLanguagesAsString();
        }
        throw new IllegalStateException();
    }

    public void clear() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeClear();
    }

    public void end() {
        if (!this.mRecycled) {
            nativeEnd();
            this.mRecycled = true;
        }
    }

    public boolean setVariable(String var, String value) {
        if (!this.mRecycled) {
            return nativeSetVariable(var, value);
        }
        throw new IllegalStateException();
    }

    public int getPageSegMode() {
        if (!this.mRecycled) {
            return nativeGetPageSegMode();
        }
        throw new IllegalStateException();
    }

    public void setPageSegMode(int mode) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetPageSegMode(mode);
    }

    public void setDebug(boolean enabled) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetDebug(enabled);
    }

    public void setRectangle(Rect rect) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        setRectangle(rect.left, rect.top, rect.width(), rect.height());
    }

    public void setRectangle(int left, int top, int width, int height) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetRectangle(left, top, width, height);
    }

    public void setImage(File file) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        Pix image = ReadFile.readFile(file);
        if (image == null) {
            throw new RuntimeException("Failed to read image file");
        }
        nativeSetImagePix(image.getNativePix());
    }

    public void setImage(Bitmap bmp) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        Pix image = ReadFile.readBitmap(bmp);
        if (image == null) {
            throw new RuntimeException("Failed to read bitmap");
        }
        nativeSetImagePix(image.getNativePix());
    }

    public void setImage(Pix image) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetImagePix(image.getNativePix());
    }

    public void setImage(byte[] imagedata, int width, int height, int bpp, int bpl) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetImageBytes(imagedata, width, height, bpp, bpl);
    }

    public String getUTF8Text() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        String text = nativeGetUTF8Text();
        if (text != null) {
            return text.trim();
        }
        return null;
    }

    public int meanConfidence() {
        if (!this.mRecycled) {
            return nativeMeanConfidence();
        }
        throw new IllegalStateException();
    }

    public int[] wordConfidences() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        int[] conf = nativeWordConfidences();
        if (conf == null) {
            return new int[0];
        }
        return conf;
    }

    public Pix getThresholdedImage() {
        if (!this.mRecycled) {
            return new Pix(nativeGetThresholdedImage());
        }
        throw new IllegalStateException();
    }

    public Pixa getRegions() {
        if (!this.mRecycled) {
            return new Pixa(nativeGetRegions(), 0, 0);
        }
        throw new IllegalStateException();
    }

    public Pixa getTextlines() {
        if (!this.mRecycled) {
            return new Pixa(nativeGetTextlines(), 0, 0);
        }
        throw new IllegalStateException();
    }

    public Pixa getStrips() {
        if (!this.mRecycled) {
            return new Pixa(nativeGetStrips(), 0, 0);
        }
        throw new IllegalStateException();
    }

    public Pixa getWords() {
        if (!this.mRecycled) {
            return new Pixa(nativeGetWords(), 0, 0);
        }
        throw new IllegalStateException();
    }

    public Pixa getConnectedComponents() {
        if (!this.mRecycled) {
            return new Pixa(nativeGetConnectedComponents(), 0, 0);
        }
        throw new IllegalStateException();
    }

    public ResultIterator getResultIterator() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        long nativeResultIterator = nativeGetResultIterator();
        if (nativeResultIterator == 0) {
            return null;
        }
        return new ResultIterator(nativeResultIterator);
    }

    public String getHOCRText(int page) {
        if (!this.mRecycled) {
            return nativeGetHOCRText(page);
        }
        throw new IllegalStateException();
    }

    public void setInputName(String name) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetInputName(name);
    }

    public void setOutputName(String name) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeSetOutputName(name);
    }

    public void readConfigFile(String filename) {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeReadConfigFile(filename);
    }

    @Deprecated
    public void ReadConfigFile(String filename) {
        readConfigFile(filename);
    }

    public String getBoxText(int page) {
        if (!this.mRecycled) {
            return nativeGetBoxText(page);
        }
        throw new IllegalStateException();
    }

    public void stop() {
        if (this.mRecycled) {
            throw new IllegalStateException();
        }
        nativeStop();
    }

    /* access modifiers changed from: protected */
    public void onProgressValues(int percent, int left, int right, int top, int bottom, int textLeft, int textRight, int textTop, int textBottom) {
        if (this.progressNotifier != null) {
            this.progressNotifier.onProgressValues(new ProgressValues(percent, new Rect(left, textTop - top, right, textTop - bottom), new Rect(textLeft, textBottom, textRight, textTop)));
        }
    }

    public boolean beginDocument(TessPdfRenderer tessPdfRenderer, String title) {
        return nativeBeginDocument(tessPdfRenderer.getNativePdfRenderer(), title);
    }

    public boolean beginDocument(TessPdfRenderer tessPdfRenderer) {
        return nativeBeginDocument(tessPdfRenderer.getNativePdfRenderer(), "");
    }

    public boolean endDocument(TessPdfRenderer tessPdfRenderer) {
        return nativeEndDocument(tessPdfRenderer.getNativePdfRenderer());
    }

    public boolean addPageToDocument(Pix imageToProcess, String imageToWrite, TessPdfRenderer tessPdfRenderer) {
        return nativeAddPageToDocument(imageToProcess.getNativePix(), imageToWrite, tessPdfRenderer.getNativePdfRenderer());
    }
}
