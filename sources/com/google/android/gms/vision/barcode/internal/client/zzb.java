package com.google.android.gms.vision.barcode.internal.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zztl;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import com.google.android.gms.vision.internal.client.zza;
import java.nio.ByteBuffer;

public class zzb extends zza<zzc> {
    private final BarcodeDetectorOptions aNY;

    public zzb(Context context, BarcodeDetectorOptions barcodeDetectorOptions) {
        super(context, "BarcodeNativeHandle");
        this.aNY = barcodeDetectorOptions;
        zzcls();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public zzc zzb(zztl zztl, Context context) throws RemoteException, zztl.zza {
        return zzd.zza.zzlf(zztl.zzjd("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator")).zza(zze.zzac(context), this.aNY);
    }

    public Barcode[] zza(Bitmap bitmap, FrameMetadataParcel frameMetadataParcel) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzc) zzcls()).zzb(zze.zzac(bitmap), frameMetadataParcel);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public Barcode[] zza(ByteBuffer byteBuffer, FrameMetadataParcel frameMetadataParcel) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzc) zzcls()).zza(zze.zzac(byteBuffer), frameMetadataParcel);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    /* access modifiers changed from: protected */
    public void zzclp() throws RemoteException {
        ((zzc) zzcls()).zzclq();
    }
}
