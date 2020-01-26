package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR = new Creator<BinderWrapper>() {
        /* renamed from: zzck */
        public BinderWrapper createFromParcel(Parcel parcel) {
            return new BinderWrapper(parcel);
        }

        /* renamed from: zzgl */
        public BinderWrapper[] newArray(int i) {
            return new BinderWrapper[i];
        }
    };

    /* renamed from: DI */
    private IBinder f242DI;

    public BinderWrapper() {
        this.f242DI = null;
    }

    public BinderWrapper(IBinder iBinder) {
        this.f242DI = null;
        this.f242DI = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.f242DI = null;
        this.f242DI = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f242DI);
    }
}
