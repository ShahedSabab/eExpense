package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zztl;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import java.nio.ByteBuffer;

public class zza extends com.google.android.gms.vision.internal.client.zza<zzd> {
    private final FaceSettingsParcel aOq;

    public zza(Context context, FaceSettingsParcel faceSettingsParcel) {
        super(context, "FaceNativeHandle");
        this.aOq = faceSettingsParcel;
        zzcls();
    }

    private Face zza(FaceParcel faceParcel) {
        return new Face(faceParcel.f849id, new PointF(faceParcel.centerX, faceParcel.centerY), faceParcel.width, faceParcel.height, faceParcel.aOr, faceParcel.aOs, zzb(faceParcel), faceParcel.aOu, faceParcel.aOv, faceParcel.aOw);
    }

    private Landmark zza(LandmarkParcel landmarkParcel) {
        return new Landmark(new PointF(landmarkParcel.f850x, landmarkParcel.f851y), landmarkParcel.type);
    }

    private Landmark[] zzb(FaceParcel faceParcel) {
        LandmarkParcel[] landmarkParcelArr = faceParcel.aOt;
        if (landmarkParcelArr == null) {
            return new Landmark[0];
        }
        Landmark[] landmarkArr = new Landmark[landmarkParcelArr.length];
        for (int i = 0; i < landmarkParcelArr.length; i++) {
            landmarkArr[i] = zza(landmarkParcelArr[i]);
        }
        return landmarkArr;
    }

    public boolean zzabh(int i) {
        if (!isOperational()) {
            return false;
        }
        try {
            return ((zzd) zzcls()).zzabh(i);
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return false;
        }
    }

    public Face[] zzb(ByteBuffer byteBuffer, FrameMetadataParcel frameMetadataParcel) {
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            FaceParcel[] zzc = ((zzd) zzcls()).zzc(zze.zzac(byteBuffer), frameMetadataParcel);
            Face[] faceArr = new Face[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                faceArr[i] = zza(zzc[i]);
            }
            return faceArr;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzc */
    public zzd zzb(zztl zztl, Context context) throws RemoteException, com.google.android.gms.internal.zztl.zza {
        return com.google.android.gms.vision.face.internal.client.zze.zza.zzlh(zztl.zzjd("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator")).zza(zze.zzac(context), this.aOq);
    }

    /* access modifiers changed from: protected */
    public void zzclp() throws RemoteException {
        ((zzd) zzcls()).zzclq();
    }
}