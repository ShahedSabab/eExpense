package com.google.android.gms.ads.identifier;

public class zza {
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzv(java.lang.String r7) {
        /*
            r6 = this;
            java.net.URL r0 = new java.net.URL     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
            r0.<init>(r7)     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
            int r1 = r0.getResponseCode()     // Catch:{ all -> 0x0047 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 < r2) goto L_0x0017
            r2 = 300(0x12c, float:4.2E-43)
            if (r1 < r2) goto L_0x0043
        L_0x0017:
            java.lang.String r2 = "HttpUrlPinger"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0047 }
            int r4 = r4.length()     // Catch:{ all -> 0x0047 }
            int r4 = r4 + 65
            r3.<init>(r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = "Received non-success response code "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = " from pinging URL: "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ all -> 0x0047 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0047 }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x0047 }
        L_0x0043:
            r0.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
        L_0x0046:
            return
        L_0x0047:
            r1 = move-exception
            r0.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
            throw r1     // Catch:{ IndexOutOfBoundsException -> 0x004c, IOException -> 0x00cb, RuntimeException -> 0x008b }
        L_0x004c:
            r0 = move-exception
            java.lang.String r1 = "HttpUrlPinger"
            java.lang.String r2 = r0.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r7)
            int r4 = r4.length()
            int r4 = r4 + 32
            java.lang.String r5 = java.lang.String.valueOf(r2)
            int r5 = r5.length()
            int r4 = r4 + r5
            r3.<init>(r4)
            java.lang.String r4 = "Error while parsing ping URL: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r4 = ". "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2, r0)
            goto L_0x0046
        L_0x008b:
            r0 = move-exception
        L_0x008c:
            java.lang.String r1 = "HttpUrlPinger"
            java.lang.String r2 = r0.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r7)
            int r4 = r4.length()
            int r4 = r4 + 27
            java.lang.String r5 = java.lang.String.valueOf(r2)
            int r5 = r5.length()
            int r4 = r4 + r5
            r3.<init>(r4)
            java.lang.String r4 = "Error while pinging URL: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r4 = ". "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2, r0)
            goto L_0x0046
        L_0x00cb:
            r0 = move-exception
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.zza.zzv(java.lang.String):void");
    }
}
