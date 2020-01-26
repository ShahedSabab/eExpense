package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzm extends zzl implements Callback {
    /* access modifiers changed from: private */

    /* renamed from: En */
    public final HashMap<zza, zzb> f336En = new HashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: Eo */
    public final com.google.android.gms.common.stats.zza f337Eo;

    /* renamed from: Ep */
    private final long f338Ep;
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public final Context zzatc;

    private static final class zza {

        /* renamed from: Eq */
        private final String f339Eq;

        /* renamed from: Er */
        private final ComponentName f340Er;

        /* renamed from: cd */
        private final String f341cd;

        public zza(ComponentName componentName) {
            this.f341cd = null;
            this.f339Eq = null;
            this.f340Er = (ComponentName) zzaa.zzy(componentName);
        }

        public zza(String str, String str2) {
            this.f341cd = zzaa.zzib(str);
            this.f339Eq = zzaa.zzib(str2);
            this.f340Er = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzz.equal(this.f341cd, zza.f341cd) && zzz.equal(this.f340Er, zza.f340Er);
        }

        public int hashCode() {
            return zzz.hashCode(this.f341cd, this.f340Er);
        }

        public String toString() {
            return this.f341cd == null ? this.f340Er.flattenToString() : this.f341cd;
        }

        public Intent zzawe() {
            return this.f341cd != null ? new Intent(this.f341cd).setPackage(this.f339Eq) : new Intent().setComponent(this.f340Er);
        }
    }

    private final class zzb {
        /* access modifiers changed from: private */

        /* renamed from: DI */
        public IBinder f342DI;
        /* access modifiers changed from: private */

        /* renamed from: Er */
        public ComponentName f343Er;

        /* renamed from: Es */
        private final zza f344Es = new zza();
        /* access modifiers changed from: private */

        /* renamed from: Et */
        public final Set<ServiceConnection> f345Et = new HashSet();

        /* renamed from: Eu */
        private boolean f346Eu;

        /* renamed from: Ev */
        private final zza f347Ev;
        /* access modifiers changed from: private */
        public int mState = 2;

        public class zza implements ServiceConnection {
            public zza() {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (zzm.this.f336En) {
                    zzb.this.f342DI = iBinder;
                    zzb.this.f343Er = componentName;
                    for (ServiceConnection onServiceConnected : zzb.this.f345Et) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    zzb.this.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (zzm.this.f336En) {
                    zzb.this.f342DI = null;
                    zzb.this.f343Er = componentName;
                    for (ServiceConnection onServiceDisconnected : zzb.this.f345Et) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    zzb.this.mState = 2;
                }
            }
        }

        public zzb(zza zza2) {
            this.f347Ev = zza2;
        }

        public IBinder getBinder() {
            return this.f342DI;
        }

        public ComponentName getComponentName() {
            return this.f343Er;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.f346Eu;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            zzm.this.f337Eo.zza(zzm.this.zzatc, serviceConnection, str, this.f347Ev.zzawe());
            this.f345Et.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.f345Et.contains(serviceConnection);
        }

        public boolean zzawf() {
            return this.f345Et.isEmpty();
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            zzm.this.f337Eo.zzb(zzm.this.zzatc, serviceConnection);
            this.f345Et.remove(serviceConnection);
        }

        @TargetApi(14)
        public void zzhw(String str) {
            this.mState = 3;
            this.f346Eu = zzm.this.f337Eo.zza(zzm.this.zzatc, str, this.f347Ev.zzawe(), this.f344Es, 129);
            if (!this.f346Eu) {
                this.mState = 2;
                try {
                    zzm.this.f337Eo.zza(zzm.this.zzatc, this.f344Es);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void zzhx(String str) {
            zzm.this.f337Eo.zza(zzm.this.zzatc, this.f344Es);
            this.f346Eu = false;
            this.mState = 2;
        }
    }

    zzm(Context context) {
        this.zzatc = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.f337Eo = com.google.android.gms.common.stats.zza.zzaxr();
        this.f338Ep = 5000;
    }

    private boolean zza(zza zza2, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzaa.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f336En) {
            zzb zzb2 = (zzb) this.f336En.get(zza2);
            if (zzb2 != null) {
                this.mHandler.removeMessages(0, zza2);
                if (!zzb2.zza(serviceConnection)) {
                    zzb2.zza(serviceConnection, str);
                    switch (zzb2.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzb2.getComponentName(), zzb2.getBinder());
                            break;
                        case 2:
                            zzb2.zzhw(str);
                            break;
                    }
                } else {
                    String valueOf = String.valueOf(zza2);
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
                }
            } else {
                zzb2 = new zzb(zza2);
                zzb2.zza(serviceConnection, str);
                zzb2.zzhw(str);
                this.f336En.put(zza2, zzb2);
            }
            isBound = zzb2.isBound();
        }
        return isBound;
    }

    private void zzb(zza zza2, ServiceConnection serviceConnection, String str) {
        zzaa.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f336En) {
            zzb zzb2 = (zzb) this.f336En.get(zza2);
            if (zzb2 == null) {
                String valueOf = String.valueOf(zza2);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (!zzb2.zza(serviceConnection)) {
                String valueOf2 = String.valueOf(zza2);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf2).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf2).toString());
            } else {
                zzb2.zzb(serviceConnection, str);
                if (zzb2.zzawf()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza2), this.f338Ep);
                }
            }
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                zza zza2 = (zza) message.obj;
                synchronized (this.f336En) {
                    zzb zzb2 = (zzb) this.f336En.get(zza2);
                    if (zzb2 != null && zzb2.zzawf()) {
                        if (zzb2.isBound()) {
                            zzb2.zzhx("GmsClientSupervisor");
                        }
                        this.f336En.remove(zza2);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3) {
        return zza(new zza(str, str2), serviceConnection, str3);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, String str2, ServiceConnection serviceConnection, String str3) {
        zzb(new zza(str, str2), serviceConnection, str3);
    }
}
