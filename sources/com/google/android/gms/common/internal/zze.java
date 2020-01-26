package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zze<T extends IInterface> {

    /* renamed from: DB */
    public static final String[] f275DB = {"service_esmobile", "service_googleme"};

    /* renamed from: DA */
    protected AtomicInteger f276DA;

    /* renamed from: Dj */
    private int f277Dj;

    /* renamed from: Dk */
    private long f278Dk;

    /* renamed from: Dl */
    private long f279Dl;

    /* renamed from: Dm */
    private int f280Dm;

    /* renamed from: Dn */
    private long f281Dn;

    /* renamed from: Do */
    private final zzl f282Do;
    /* access modifiers changed from: private */

    /* renamed from: Dp */
    public final Object f283Dp;
    /* access modifiers changed from: private */

    /* renamed from: Dq */
    public zzt f284Dq;

    /* renamed from: Dr */
    protected zzf f285Dr;

    /* renamed from: Ds */
    private T f286Ds;
    /* access modifiers changed from: private */

    /* renamed from: Dt */
    public final ArrayList<C0736zze<?>> f287Dt;

    /* renamed from: Du */
    private zzh f288Du;

    /* renamed from: Dv */
    private int f289Dv;
    /* access modifiers changed from: private */

    /* renamed from: Dw */
    public final zzb f290Dw;
    /* access modifiers changed from: private */

    /* renamed from: Dx */
    public final zzc f291Dx;

    /* renamed from: Dy */
    private final int f292Dy;

    /* renamed from: Dz */
    private final String f293Dz;
    private final Context mContext;
    final Handler mHandler;

    /* renamed from: zm */
    private final com.google.android.gms.common.zzc f294zm;
    private final Looper zzajy;
    private final Object zzako;

    private abstract class zza extends C0736zze<Boolean> {

        /* renamed from: DC */
        public final Bundle f295DC;
        public final int statusCode;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(Boolean.valueOf(true));
            this.statusCode = i;
            this.f295DC = bundle;
        }

        /* access modifiers changed from: protected */
        public abstract boolean zzavj();

        /* access modifiers changed from: protected */
        public void zzavk() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzc */
        public void zzv(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                zze.this.zzb(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzavj()) {
                        zze.this.zzb(1, null);
                        zzm(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                case 10:
                    zze.this.zzb(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    zze.this.zzb(1, null);
                    if (this.f295DC != null) {
                        pendingIntent = (PendingIntent) this.f295DC.getParcelable("pendingIntent");
                    }
                    zzm(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzm(ConnectionResult connectionResult);
    }

    public interface zzb {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface zzc {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    final class zzd extends Handler {
        public zzd(Looper looper) {
            super(looper);
        }

        private void zza(Message message) {
            C0736zze zze = (C0736zze) message.obj;
            zze.zzavk();
            zze.unregister();
        }

        private boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }

        public void handleMessage(Message message) {
            PendingIntent pendingIntent = null;
            if (zze.this.f276DA.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 5) && !zze.this.isConnecting()) {
                zza(message);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                zze.this.f285Dr.zzg(connectionResult);
                zze.this.onConnectionFailed(connectionResult);
            } else if (message.what == 4) {
                zze.this.zzb(4, null);
                if (zze.this.f290Dw != null) {
                    zze.this.f290Dw.onConnectionSuspended(message.arg2);
                }
                zze.this.onConnectionSuspended(message.arg2);
                zze.this.zza(4, 1, null);
            } else if (message.what == 2 && !zze.this.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((C0736zze) message.obj).zzavl();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zze$zze reason: collision with other inner class name */
    protected abstract class C0736zze<TListener> {

        /* renamed from: DE */
        private boolean f299DE = false;
        private TListener mListener;

        public C0736zze(TListener tlistener) {
            this.mListener = tlistener;
        }

        public void unregister() {
            zzavm();
            synchronized (zze.this.f287Dt) {
                zze.this.f287Dt.remove(this);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzavk();

        public void zzavl() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.f299DE) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (tlistener != null) {
                try {
                    zzv(tlistener);
                } catch (RuntimeException e) {
                    zzavk();
                    throw e;
                }
            } else {
                zzavk();
            }
            synchronized (this) {
                this.f299DE = true;
            }
            unregister();
        }

        public void zzavm() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzv(TListener tlistener);
    }

    public interface zzf {
        void zzg(@NonNull ConnectionResult connectionResult);
    }

    public static final class zzg extends com.google.android.gms.common.internal.zzs.zza {

        /* renamed from: DF */
        private zze f300DF;

        /* renamed from: DG */
        private final int f301DG;

        public zzg(@NonNull zze zze, int i) {
            this.f300DF = zze;
            this.f301DG = i;
        }

        private void zzavn() {
            this.f300DF = null;
        }

        @BinderThread
        public void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            zzaa.zzb(this.f300DF, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.f300DF.zza(i, iBinder, bundle, this.f301DG);
            zzavn();
        }

        @BinderThread
        public void zzb(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }
    }

    public final class zzh implements ServiceConnection {

        /* renamed from: DG */
        private final int f303DG;

        public zzh(int i) {
            this.f303DG = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                zze.this.zzl(new ConnectionResult(8, null, "ServiceBroker IBinder is null"));
                return;
            }
            synchronized (zze.this.f283Dp) {
                zze.this.f284Dq = com.google.android.gms.common.internal.zzt.zza.zzdu(iBinder);
            }
            zze.this.zza(0, (Bundle) null, this.f303DG);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (zze.this.f283Dp) {
                zze.this.f284Dq = null;
            }
            zze.this.mHandler.sendMessage(zze.this.mHandler.obtainMessage(4, this.f303DG, 1));
        }
    }

    protected class zzi implements zzf {
        public zzi() {
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zze.this.zza((zzp) null, zze.this.zzavi());
            } else if (zze.this.f291Dx != null) {
                zze.this.f291Dx.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final class zzj extends zza {

        /* renamed from: DH */
        public final IBinder f306DH;

        @BinderThread
        public zzj(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.f306DH = iBinder;
        }

        /* access modifiers changed from: protected */
        public boolean zzavj() {
            try {
                String interfaceDescriptor = this.f306DH.getInterfaceDescriptor();
                if (!zze.this.zzjy().equals(interfaceDescriptor)) {
                    String valueOf = String.valueOf(zze.this.zzjy());
                    Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(valueOf).append(" vs. ").append(interfaceDescriptor).toString());
                    return false;
                }
                IInterface zzh = zze.this.zzh(this.f306DH);
                if (zzh == null || !zze.this.zza(2, 3, zzh)) {
                    return false;
                }
                Bundle zzapn = zze.this.zzapn();
                if (zze.this.f290Dw != null) {
                    zze.this.f290Dw.onConnected(zzapn);
                }
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void zzm(ConnectionResult connectionResult) {
            if (zze.this.f291Dx != null) {
                zze.this.f291Dx.onConnectionFailed(connectionResult);
            }
            zze.this.onConnectionFailed(connectionResult);
        }
    }

    protected final class zzk extends zza {
        @BinderThread
        public zzk(int i, Bundle bundle) {
            super(i, bundle);
        }

        /* access modifiers changed from: protected */
        public boolean zzavj() {
            zze.this.f285Dr.zzg(ConnectionResult.f110wO);
            return true;
        }

        /* access modifiers changed from: protected */
        public void zzm(ConnectionResult connectionResult) {
            zze.this.f285Dr.zzg(connectionResult);
            zze.this.onConnectionFailed(connectionResult);
        }
    }

    protected zze(Context context, Looper looper, int i, zzb zzb2, zzc zzc2, String str) {
        this(context, looper, zzl.zzcc(context), com.google.android.gms.common.zzc.zzaql(), i, (zzb) zzaa.zzy(zzb2), (zzc) zzaa.zzy(zzc2), str);
    }

    protected zze(Context context, Looper looper, zzl zzl, com.google.android.gms.common.zzc zzc2, int i, zzb zzb2, zzc zzc3, String str) {
        this.zzako = new Object();
        this.f283Dp = new Object();
        this.f287Dt = new ArrayList<>();
        this.f289Dv = 1;
        this.f276DA = new AtomicInteger(0);
        this.mContext = (Context) zzaa.zzb(context, (Object) "Context must not be null");
        this.zzajy = (Looper) zzaa.zzb(looper, (Object) "Looper must not be null");
        this.f282Do = (zzl) zzaa.zzb(zzl, (Object) "Supervisor must not be null");
        this.f294zm = (com.google.android.gms.common.zzc) zzaa.zzb(zzc2, (Object) "API availability must not be null");
        this.mHandler = new zzd(looper);
        this.f292Dy = i;
        this.f290Dw = zzb2;
        this.f291Dx = zzc3;
        this.f293Dz = str;
    }

    /* access modifiers changed from: private */
    public boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzako) {
            if (this.f289Dv != i) {
                z = false;
            } else {
                zzb(i2, t);
                z = true;
            }
        }
        return z;
    }

    private void zzavb() {
        if (this.f288Du != null) {
            String valueOf = String.valueOf(zzjx());
            String valueOf2 = String.valueOf(zzauz());
            Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 70 + String.valueOf(valueOf2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(valueOf).append(" on ").append(valueOf2).toString());
            this.f282Do.zzb(zzjx(), zzauz(), this.f288Du, zzava());
            this.f276DA.incrementAndGet();
        }
        this.f288Du = new zzh(this.f276DA.get());
        if (!this.f282Do.zza(zzjx(), zzauz(), this.f288Du, zzava())) {
            String valueOf3 = String.valueOf(zzjx());
            String valueOf4 = String.valueOf(zzauz());
            Log.e("GmsClient", new StringBuilder(String.valueOf(valueOf3).length() + 34 + String.valueOf(valueOf4).length()).append("unable to connect to service: ").append(valueOf3).append(" on ").append(valueOf4).toString());
            zza(16, (Bundle) null, this.f276DA.get());
        }
    }

    private void zzavc() {
        if (this.f288Du != null) {
            this.f282Do.zzb(zzjx(), zzauz(), this.f288Du, zzava());
            this.f288Du = null;
        }
    }

    /* access modifiers changed from: private */
    public void zzb(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        zzaa.zzbt(z);
        synchronized (this.zzako) {
            this.f289Dv = i;
            this.f286Ds = t;
            zzc(i, t);
            switch (i) {
                case 1:
                    zzavc();
                    break;
                case 2:
                    zzavb();
                    break;
                case 3:
                    zza(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzl(ConnectionResult connectionResult) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.f276DA.get(), connectionResult.getErrorCode(), connectionResult.getResolution()));
    }

    public void disconnect() {
        this.f276DA.incrementAndGet();
        synchronized (this.f287Dt) {
            int size = this.f287Dt.size();
            for (int i = 0; i < size; i++) {
                ((C0736zze) this.f287Dt.get(i)).zzavm();
            }
            this.f287Dt.clear();
        }
        synchronized (this.f283Dp) {
            this.f284Dq = null;
        }
        zzb(1, null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        synchronized (this.zzako) {
            i = this.f289Dv;
            t = this.f286Ds;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("CONNECTING");
                break;
            case 3:
                printWriter.print("CONNECTED");
                break;
            case 4:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.println("null");
        } else {
            printWriter.append(zzjy()).append("@").println(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.f279Dl > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.f279Dl;
            String valueOf = String.valueOf(simpleDateFormat.format(new Date(this.f279Dl)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.f278Dk > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.f277Dj) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.f277Dj));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.f278Dk;
            String valueOf2 = String.valueOf(simpleDateFormat.format(new Date(this.f278Dk)));
            append2.println(new StringBuilder(String.valueOf(valueOf2).length() + 21).append(j2).append(" ").append(valueOf2).toString());
        }
        if (this.f281Dn > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.f280Dm));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.f281Dn;
            String valueOf3 = String.valueOf(simpleDateFormat.format(new Date(this.f281Dn)));
            append3.println(new StringBuilder(String.valueOf(valueOf3).length() + 21).append(j3).append(" ").append(valueOf3).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzajy;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzako) {
            z = this.f289Dv == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzako) {
            z = this.f289Dv == 2;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.f280Dm = connectionResult.getErrorCode();
        this.f281Dn = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.f277Dj = i;
        this.f278Dk = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i2, -1, new zzk(i, bundle)));
    }

    /* access modifiers changed from: protected */
    @BinderThread
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzj(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(@NonNull T t) {
        this.f279Dl = System.currentTimeMillis();
    }

    public void zza(@NonNull zzf zzf2) {
        this.f285Dr = (zzf) zzaa.zzb(zzf2, (Object) "Connection progress callbacks cannot be null.");
        zzb(2, null);
    }

    public void zza(zzf zzf2, ConnectionResult connectionResult) {
        this.f285Dr = (zzf) zzaa.zzb(zzf2, (Object) "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.f276DA.get(), connectionResult.getErrorCode(), connectionResult.getResolution()));
    }

    @WorkerThread
    public void zza(zzp zzp, Set<Scope> set) {
        GetServiceRequest zzo = new GetServiceRequest(this.f292Dy).zzhv(this.mContext.getPackageName()).zzo(zzahv());
        if (set != null) {
            zzo.zzf(set);
        }
        if (zzain()) {
            zzo.zze(zzave()).zzb(zzp);
        } else if (zzavh()) {
            zzo.zze(getAccount());
        }
        try {
            synchronized (this.f283Dp) {
                if (this.f284Dq != null) {
                    this.f284Dq.zza((zzs) new zzg(this, this.f276DA.get()), zzo);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            zzgk(1);
        } catch (RemoteException e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e4);
            zzl(new ConnectionResult(8, null, "IGmsServiceBroker.getService failed."));
        }
    }

    /* access modifiers changed from: protected */
    public Bundle zzahv() {
        return new Bundle();
    }

    public boolean zzain() {
        return false;
    }

    public boolean zzajc() {
        return false;
    }

    public Intent zzajd() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzapn() {
        return null;
    }

    public boolean zzaqx() {
        return true;
    }

    @Nullable
    public IBinder zzaqy() {
        IBinder asBinder;
        synchronized (this.f283Dp) {
            asBinder = this.f284Dq == null ? null : this.f284Dq.asBinder();
        }
        return asBinder;
    }

    /* access modifiers changed from: protected */
    public String zzauz() {
        return "com.google.android.gms";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzava() {
        return this.f293Dz == null ? this.mContext.getClass().getName() : this.f293Dz;
    }

    public void zzavd() {
        int isGooglePlayServicesAvailable = this.f294zm.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zzb(1, null);
            this.f285Dr = new zzi();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.f276DA.get(), isGooglePlayServicesAvailable));
            return;
        }
        zza((zzf) new zzi());
    }

    public final Account zzave() {
        return getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
    }

    /* access modifiers changed from: protected */
    public final void zzavf() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzavg() throws DeadObjectException {
        T t;
        synchronized (this.zzako) {
            if (this.f289Dv == 4) {
                throw new DeadObjectException();
            }
            zzavf();
            zzaa.zza(this.f286Ds != null, (Object) "Client is connected but service is null");
            t = this.f286Ds;
        }
        return t;
    }

    public boolean zzavh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Set<Scope> zzavi() {
        return Collections.EMPTY_SET;
    }

    /* access modifiers changed from: 0000 */
    public void zzc(int i, T t) {
    }

    public void zzgk(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.f276DA.get(), i));
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T zzh(IBinder iBinder);

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzjx();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzjy();
}
