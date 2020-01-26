package backbencers.nub.dailycostcalc.constant;

import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileCreator {
    public static File createFile() {
        File image_file = getDirc();
        if (!image_file.exists() && !image_file.mkdirs()) {
            return null;
        }
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String yearFolder = image_file.getAbsolutePath() + "/" + new SimpleDateFormat("yyyy").format(new Date());
        createFolder(yearFolder);
        String tmf = yearFolder + "/" + new SimpleDateFormat("MMM").format(new Date()).toUpperCase();
        createFolder(tmf);
        return new File(tmf + "/" + ("eE" + date + ".jpeg"));
    }

    private static void createFolder(String path) {
        File yFile = new File(path);
        if (yFile.exists() || yFile.mkdir()) {
        }
    }

    private static File getDirc() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "eExpense");
    }
}
