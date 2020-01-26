package com.google.android.gms.vision.text.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;

public interface zzc extends IInterface {

    public static abstract class zza extends Binder implements zzc {

        /* renamed from: com.google.android.gms.vision.text.internal.client.zzc$zza$zza reason: collision with other inner class name */
        private static class C0770zza implements zzc {
            private IBinder zzajq;

            C0770zza(IBinder iBinder) {
                this.zzajq = iBinder;
            }

            public IBinder asBinder() {
                return this.zzajq;
            }

            public zzb zza(zzd zzd, TextRecognizerOptions textRecognizerOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (textRecognizerOptions != null) {
                        obtain.writeInt(1);
                        textRecognizerOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzajq.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.vision.text.internal.client.zzb.zza.zzli(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzc zzlj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new C0770zza(iBinder) : (zzc) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
                    zzb zza = zza(com.google.android.gms.dynamic.zzd.zza.zzfd(parcel.readStrongBinder()), parcel.readInt() != 0 ? (TextRecognizerOptions) TextRecognizerOptions.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (zza != null) {
                        iBinder = zza.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzb zza(zzd zzd, TextRecognizerOptions textRecognizerOptions) throws RemoteException;
}