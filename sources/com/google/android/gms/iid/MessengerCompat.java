package com.google.android.gms.iid;

import android.annotation.TargetApi;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public class MessengerCompat implements Parcelable {
    public static final Creator<MessengerCompat> CREATOR = new Creator<MessengerCompat>() {
        /* renamed from: zznf */
        public MessengerCompat createFromParcel(Parcel parcel) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                return new MessengerCompat(readStrongBinder);
            }
            return null;
        }

        /* renamed from: zztv */
        public MessengerCompat[] newArray(int i) {
            return new MessengerCompat[i];
        }
    };
    Messenger aiq;
    zzb air;

    private final class zza extends com.google.android.gms.iid.zzb.zza {
        Handler handler;

        zza(Handler handler2) {
            this.handler = handler2;
        }

        public void send(Message message) throws RemoteException {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }
    }

    public MessengerCompat(Handler handler) {
        if (VERSION.SDK_INT >= 21) {
            this.aiq = new Messenger(handler);
        } else {
            this.air = new zza(handler);
        }
    }

    public MessengerCompat(IBinder iBinder) {
        if (VERSION.SDK_INT >= 21) {
            this.aiq = new Messenger(iBinder);
        } else {
            this.air = com.google.android.gms.iid.zzb.zza.zzgx(iBinder);
        }
    }

    public static int zzc(Message message) {
        return VERSION.SDK_INT >= 21 ? zzd(message) : message.arg2;
    }

    @TargetApi(21)
    private static int zzd(Message message) {
        return message.sendingUid;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return z;
        }
        try {
            return getBinder().equals(((MessengerCompat) obj).getBinder());
        } catch (ClassCastException e) {
            return z;
        }
    }

    public IBinder getBinder() {
        return this.aiq != null ? this.aiq.getBinder() : this.air.asBinder();
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public void send(Message message) throws RemoteException {
        if (this.aiq != null) {
            this.aiq.send(message);
        } else {
            this.air.send(message);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.aiq != null) {
            parcel.writeStrongBinder(this.aiq.getBinder());
        } else {
            parcel.writeStrongBinder(this.air.asBinder());
        }
    }
}
