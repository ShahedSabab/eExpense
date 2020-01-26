package android.support.p003v7.app;

import android.annotation.TargetApi;
import android.app.Notification.BigTextStyle;
import android.support.annotation.RequiresApi;
import android.support.p000v4.app.NotificationBuilderWithBuilderAccessor;

@TargetApi(16)
@RequiresApi(16)
/* renamed from: android.support.v7.app.NotificationCompatImplJellybean */
class NotificationCompatImplJellybean {
    NotificationCompatImplJellybean() {
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor b, CharSequence bigText) {
        new BigTextStyle(b.getBuilder()).bigText(bigText);
    }
}
