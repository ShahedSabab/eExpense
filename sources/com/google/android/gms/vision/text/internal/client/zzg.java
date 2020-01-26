package com.google.android.gms.vision.text.internal.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zztl;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import com.google.android.gms.vision.internal.client.zza;

public class zzg extends zza<zzb> {
    private final TextRecognizerOptions aOO;

    public zzg(Context context, TextRecognizerOptions textRecognizerOptions) {
        super(context, "TextNativeHandle");
        this.aOO = textRecognizerOptions;
        zzcls();
    }

    public LineBoxParcel[] zza(Bitmap bitmap, FrameMetadataParcel frameMetadataParcel, RecognitionOptions recognitionOptions) {
        if (!isOperational()) {
            return new LineBoxParcel[0];
        }
        try {
            return ((zzb) zzcls()).zza(zze.zzac(bitmap), frameMetadataParcel, recognitionOptions);
        } catch (RemoteException e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new LineBoxParcel[0];
        }
    }

    /* access modifiers changed from: protected */
    public void zzclp() throws RemoteException {
        ((zzb) zzcls()).zzclw();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzd */
    public zzb zzb(zztl zztl, Context context) throws RemoteException, zztl.zza {
        return zzc.zza.zzlj(zztl.zzjd("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator")).zza(zze.zzac(context), this.aOO);
    }
}