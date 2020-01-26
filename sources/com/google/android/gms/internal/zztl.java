package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public final class zztl {

    /* renamed from: Qh */
    private static zztm f809Qh;

    /* renamed from: Qi */
    private static final HashMap<String, byte[]> f810Qi = new HashMap<>();

    /* renamed from: Qj */
    private static String f811Qj;

    /* renamed from: Qk */
    private static final zza f812Qk = new zza() {
        public zztl zza(Context context, String str, int i) throws zza {
            return zztl.zza(context, str, i);
        }

        public int zzaa(Context context, String str) {
            return zztl.zzaa(context, str);
        }

        public int zzb(Context context, String str, boolean z) throws zza {
            return zztl.zzb(context, str, z);
        }
    };

    /* renamed from: Ql */
    private static final zza f813Ql = new zza() {
        public zztl zza(Context context, String str, int i) throws zza {
            return zztl.zzb(context, str, i);
        }

        public int zzaa(Context context, String str) {
            return zztl.zzaa(context, str);
        }

        public int zzb(Context context, String str, boolean z) throws zza {
            return zztl.zzc(context, str, z);
        }
    };

    /* renamed from: Qm */
    public static final zzb f814Qm = new zzb() {
        public C0759zzb zza(Context context, String str, zza zza) throws zza {
            C0759zzb zzb = new C0759zzb();
            zzb.f822Qu = zza.zzb(context, str, true);
            if (zzb.f822Qu != 0) {
                zzb.f823Qv = 1;
            } else {
                zzb.f821Qt = zza.zzaa(context, str);
                if (zzb.f821Qt != 0) {
                    zzb.f823Qv = -1;
                }
            }
            return zzb;
        }
    };

    /* renamed from: Qn */
    public static final zzb f815Qn = new zzb() {
        public C0759zzb zza(Context context, String str, zza zza) throws zza {
            C0759zzb zzb = new C0759zzb();
            zzb.f821Qt = zza.zzaa(context, str);
            if (zzb.f821Qt != 0) {
                zzb.f823Qv = -1;
            } else {
                zzb.f822Qu = zza.zzb(context, str, true);
                if (zzb.f822Qu != 0) {
                    zzb.f823Qv = 1;
                }
            }
            return zzb;
        }
    };

    /* renamed from: Qo */
    public static final zzb f816Qo = new zzb() {
        public C0759zzb zza(Context context, String str, zza zza) throws zza {
            C0759zzb zzb = new C0759zzb();
            zzb.f821Qt = zza.zzaa(context, str);
            zzb.f822Qu = zza.zzb(context, str, true);
            if (zzb.f821Qt == 0 && zzb.f822Qu == 0) {
                zzb.f823Qv = 0;
            } else if (zzb.f821Qt >= zzb.f822Qu) {
                zzb.f823Qv = -1;
            } else {
                zzb.f823Qv = 1;
            }
            return zzb;
        }
    };

    /* renamed from: Qp */
    public static final zzb f817Qp = new zzb() {
        public C0759zzb zza(Context context, String str, zza zza) throws zza {
            C0759zzb zzb = new C0759zzb();
            zzb.f821Qt = zza.zzaa(context, str);
            zzb.f822Qu = zza.zzb(context, str, true);
            if (zzb.f821Qt == 0 && zzb.f822Qu == 0) {
                zzb.f823Qv = 0;
            } else if (zzb.f822Qu >= zzb.f821Qt) {
                zzb.f823Qv = 1;
            } else {
                zzb.f823Qv = -1;
            }
            return zzb;
        }
    };

    /* renamed from: Qq */
    public static final zzb f818Qq = new zzb() {
        public C0759zzb zza(Context context, String str, zza zza) throws zza {
            C0759zzb zzb = new C0759zzb();
            zzb.f821Qt = zza.zzaa(context, str);
            if (zzb.f821Qt != 0) {
                zzb.f822Qu = zza.zzb(context, str, false);
            } else {
                zzb.f822Qu = zza.zzb(context, str, true);
            }
            if (zzb.f821Qt == 0 && zzb.f822Qu == 0) {
                zzb.f823Qv = 0;
            } else if (zzb.f822Qu >= zzb.f821Qt) {
                zzb.f823Qv = 1;
            } else {
                zzb.f823Qv = -1;
            }
            return zzb;
        }
    };

    /* renamed from: Qr */
    private final Context f819Qr;

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface zzb {

        public interface zza {
            zztl zza(Context context, String str, int i) throws zza;

            int zzaa(Context context, String str);

            int zzb(Context context, String str, boolean z) throws zza;
        }

        /* renamed from: com.google.android.gms.internal.zztl$zzb$zzb reason: collision with other inner class name */
        public static class C0759zzb {

            /* renamed from: Qt */
            public int f821Qt = 0;

            /* renamed from: Qu */
            public int f822Qu = 0;

            /* renamed from: Qv */
            public int f823Qv = 0;
        }

        C0759zzb zza(Context context, String str, zza zza2) throws zza;
    }

    private zztl(Context context) {
        this.f819Qr = (Context) zzaa.zzy(context);
    }

    private static Context zza(Context context, String str, byte[] bArr, String str2) {
        if (str2 == null || str2.isEmpty()) {
            Log.e("DynamiteModule", "No valid DynamiteLoader APK path");
            return null;
        }
        try {
            return (Context) zze.zzae(com.google.android.gms.internal.zztn.zza.zzff((IBinder) new PathClassLoader(str2, context.getClassLoader()) {
                /* access modifiers changed from: protected */
                public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
                    if (!str.startsWith("java.") && !str.startsWith("android.")) {
                        try {
                            return findClass(str);
                        } catch (ClassNotFoundException e) {
                        }
                    }
                    return super.loadClass(str, z);
                }
            }.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0])).zza(zze.zzac(context), str, bArr));
        } catch (RemoteException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            String str3 = "DynamiteModule";
            String str4 = "Failed to load DynamiteLoader: ";
            String valueOf = String.valueOf(e.toString());
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            return null;
        }
    }

    public static zztl zza(Context context, zzb zzb2, String str) throws zza {
        return zza(context, zzb2, str, f812Qk);
    }

    public static zztl zza(Context context, zzb zzb2, String str, zza zza2) throws zza {
        C0759zzb zza3 = zzb2.zza(context, str, zza2);
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza3.f821Qt).append(" and remote module ").append(str).append(":").append(zza3.f822Qu).toString());
        if (zza3.f823Qv == 0 || ((zza3.f823Qv == -1 && zza3.f821Qt == 0) || (zza3.f823Qv == 1 && zza3.f822Qu == 0))) {
            throw new zza("No acceptable module found. Local version is " + zza3.f821Qt + " and remote version is " + zza3.f822Qu + ".");
        } else if (zza3.f823Qv == -1) {
            return zzac(context, str);
        } else {
            if (zza3.f823Qv == 1) {
                try {
                    return zza2.zza(context, str, zza3.f822Qu);
                } catch (zza e) {
                    zza zza4 = e;
                    String str2 = "DynamiteModule";
                    String str3 = "Failed to load remote module: ";
                    String valueOf = String.valueOf(zza4.getMessage());
                    Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    if (zza3.f821Qt != 0) {
                        final int i = zza3.f821Qt;
                        if (zzb2.zza(context, str, new zza() {
                            public zztl zza(Context context, String str, int i) throws zza {
                                throw new zza("local only VersionPolicy should not load from remote");
                            }

                            public int zzaa(Context context, String str) {
                                return i;
                            }

                            public int zzb(Context context, String str, boolean z) {
                                return 0;
                            }
                        }).f823Qv == -1) {
                            return zzac(context, str);
                        }
                    }
                    throw new zza("Remote load failed. No local fallback found.", zza4);
                }
            } else {
                throw new zza("VersionPolicy returned invalid code:" + zza3.f823Qv);
            }
        }
    }

    /* access modifiers changed from: private */
    public static zztl zza(Context context, String str, int i) throws zza {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zztm zzcs = zzcs(context);
        if (zzcs == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            zzd zza2 = zzcs.zza(zze.zzac(context), str, i);
            if (zze.zzae(zza2) != null) {
                return new zztl((Context) zze.zzae(zza2));
            }
            throw new zza("Failed to load remote module.");
        } catch (RemoteException e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    public static int zzaa(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            String valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            String valueOf2 = String.valueOf("ModuleDescriptor");
            Class loadClass = classLoader.loadClass(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length() + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(".").append(valueOf2).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf3 = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder(String.valueOf(valueOf3).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf3).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load module descriptor class: ";
            String valueOf4 = String.valueOf(e2.getMessage());
            Log.e(str2, valueOf4.length() != 0 ? str3.concat(valueOf4) : new String(str3));
            return 0;
        }
    }

    public static int zzab(Context context, String str) {
        return zzb(context, str, false);
    }

    private static zztl zzac(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new zztl(context.getApplicationContext());
    }

    public static int zzb(Context context, String str, boolean z) {
        zztm zzcs = zzcs(context);
        if (zzcs == null) {
            return 0;
        }
        try {
            return zzcs.zza(zze.zzac(context), str, z);
        } catch (RemoteException e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static zztl zzb(Context context, String str, int i) throws zza {
        byte[] bArr;
        String str2;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (zztl.class) {
            bArr = (byte[]) f810Qi.get(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString());
            str2 = f811Qj;
        }
        if (bArr == null) {
            throw new zza("Module implementation could not be found.");
        }
        Context zza2 = zza(context.getApplicationContext(), str, bArr, str2);
        if (zza2 != null) {
            return new zztl(zza2);
        }
        throw new zza("Failed to get module context");
    }

    public static int zzc(Context context, String str, boolean z) throws zza {
        String str2 = z ? "api_force_staging" : "api";
        String valueOf = String.valueOf("content://com.google.android.gms.chimera/");
        Uri parse = Uri.parse(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str2).length() + String.valueOf(str).length()).append(valueOf).append(str2).append("/").append(str).toString());
        if (context != null) {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver != null) {
                Cursor query = contentResolver.query(parse, null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int i = query.getInt(0);
                            if (i > 0) {
                                synchronized (zztl.class) {
                                    f810Qi.put(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString(), query.getBlob(1));
                                    f811Qj = query.getString(2);
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                            return i;
                        }
                    } catch (Throwable th) {
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new zza("Failed to connect to dynamite module ContentResolver.");
            }
        }
        throw new zza("Failed to get dynamite module ContentResolver.");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.zztm zzcs(android.content.Context r6) {
        /*
            r1 = 0
            java.lang.Class<com.google.android.gms.internal.zztl> r2 = com.google.android.gms.internal.zztl.class
            monitor-enter(r2)
            com.google.android.gms.internal.zztm r0 = f809Qh     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x000c
            com.google.android.gms.internal.zztm r0 = f809Qh     // Catch:{ all -> 0x003a }
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
        L_0x000b:
            return r0
        L_0x000c:
            com.google.android.gms.common.zzc r0 = com.google.android.gms.common.zzc.zzaql()     // Catch:{ all -> 0x003a }
            int r0 = r0.isGooglePlayServicesAvailable(r6)     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0019
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            r0 = r1
            goto L_0x000b
        L_0x0019:
            java.lang.String r0 = "com.google.android.gms"
            r3 = 3
            android.content.Context r0 = r6.createPackageContext(r0, r3)     // Catch:{ Exception -> 0x003d }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ Exception -> 0x003d }
            java.lang.String r3 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r0 = r0.loadClass(r3)     // Catch:{ Exception -> 0x003d }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x003d }
            android.os.IBinder r0 = (android.os.IBinder) r0     // Catch:{ Exception -> 0x003d }
            com.google.android.gms.internal.zztm r0 = com.google.android.gms.internal.zztm.zza.zzfe(r0)     // Catch:{ Exception -> 0x003d }
            if (r0 == 0) goto L_0x0057
            f809Qh = r0     // Catch:{ Exception -> 0x003d }
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            goto L_0x000b
        L_0x003a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            throw r0
        L_0x003d:
            r0 = move-exception
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r4 = "Failed to load IDynamiteLoader from GmsCore: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x003a }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x003a }
            int r5 = r0.length()     // Catch:{ all -> 0x003a }
            if (r5 == 0) goto L_0x005a
            java.lang.String r0 = r4.concat(r0)     // Catch:{ all -> 0x003a }
        L_0x0054:
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x003a }
        L_0x0057:
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            r0 = r1
            goto L_0x000b
        L_0x005a:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x003a }
            r0.<init>(r4)     // Catch:{ all -> 0x003a }
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztl.zzcs(android.content.Context):com.google.android.gms.internal.zztm");
    }

    public Context zzbdt() {
        return this.f819Qr;
    }

    public IBinder zzjd(String str) throws zza {
        try {
            return (IBinder) this.f819Qr.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String str2 = "Failed to instantiate module class: ";
            String valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
    }
}
