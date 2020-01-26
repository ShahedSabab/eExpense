package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<BitmapTeleporter> CREATOR = new zza();

    /* renamed from: BO */
    private Bitmap f179BO;

    /* renamed from: BP */
    private boolean f180BP;

    /* renamed from: BQ */
    private File f181BQ;
    final int mVersionCode;

    /* renamed from: nV */
    final int f182nV;
    ParcelFileDescriptor zzcme;

    BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.mVersionCode = i;
        this.zzcme = parcelFileDescriptor;
        this.f182nV = i2;
        this.f179BO = null;
        this.f180BP = false;
    }

    public BitmapTeleporter(Bitmap bitmap) {
        this.mVersionCode = 1;
        this.zzcme = null;
        this.f182nV = 0;
        this.f179BO = bitmap;
        this.f180BP = true;
    }

    private void zza(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    private FileOutputStream zzauk() {
        if (this.f181BQ == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.f181BQ);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.zzcme = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public void release() {
        if (!this.f180BP) {
            try {
                this.zzcme.close();
            } catch (IOException e) {
                Log.w("BitmapTeleporter", "Could not close PFD", e);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzcme == null) {
            Bitmap bitmap = this.f179BO;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(zzauk());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                zza(dataOutputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                zza(dataOutputStream);
                throw th;
            }
        }
        zza.zza(this, parcel, i | 1);
        this.zzcme = null;
    }

    public Bitmap zzauj() {
        if (!this.f180BP) {
            DataInputStream dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzcme));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                Config valueOf = Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(bArr);
                zza(dataInputStream);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.f179BO = createBitmap;
                this.f180BP = true;
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zza(dataInputStream);
                throw th;
            }
        }
        return this.f179BO;
    }

    public void zzd(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.f181BQ = file;
    }
}
