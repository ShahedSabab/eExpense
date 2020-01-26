package backbencers.nub.dailycostcalc.objects;

import android.graphics.Bitmap;
import android.os.Environment;
import backbencers.nub.dailycostcalc.activities.ScanActivity;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.File;

public class TessOCR {
    private TessBaseAPI mTess = new TessBaseAPI();

    public TessOCR() {
        String datapath = Environment.getExternalStorageDirectory() + "/DemoOCR/";
        String language = ScanActivity.lang;
        File dir = new File(datapath + "/tessdata/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.mTess.init(datapath, language);
    }

    public String getOCRResult(Bitmap bitmap) {
        this.mTess.setImage(bitmap);
        return this.mTess.getUTF8Text();
    }

    public void onDestroy() {
        if (this.mTess != null) {
            this.mTess.end();
        }
    }
}
