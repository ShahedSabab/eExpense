package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */

    /* renamed from: PT */
    public T f472PT;
    /* access modifiers changed from: private */

    /* renamed from: PU */
    public Bundle f473PU;
    /* access modifiers changed from: private */

    /* renamed from: PV */
    public LinkedList<C0746zza> f474PV;

    /* renamed from: PW */
    private final zzf<T> f475PW = new zzf<T>() {
        public void zza(T t) {
            zza.this.f472PT = t;
            Iterator it = zza.this.f474PV.iterator();
            while (it.hasNext()) {
                ((C0746zza) it.next()).zzb(zza.this.f472PT);
            }
            zza.this.f474PV.clear();
            zza.this.f473PU = null;
        }
    };

    /* renamed from: com.google.android.gms.dynamic.zza$zza reason: collision with other inner class name */
    private interface C0746zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    private void zza(Bundle bundle, C0746zza zza) {
        if (this.f472PT != null) {
            zza.zzb(this.f472PT);
            return;
        }
        if (this.f474PV == null) {
            this.f474PV = new LinkedList<>();
        }
        this.f474PV.add(zza);
        if (bundle != null) {
            if (this.f473PU == null) {
                this.f473PU = (Bundle) bundle.clone();
            } else {
                this.f473PU.putAll(bundle);
            }
        }
        zza(this.f475PW);
    }

    @VisibleForTesting
    static void zza(FrameLayout frameLayout, GoogleApiAvailability googleApiAvailability) {
        final Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String zzi = zzg.zzi(context, isGooglePlayServicesAvailable);
        String zzk = zzg.zzk(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzi);
        linearLayout.addView(textView);
        final Intent zzb = googleApiAvailability.zzb(context, isGooglePlayServicesAvailable, null);
        if (zzb != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zzk);
            linearLayout.addView(button);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    try {
                        context.startActivity(zzb);
                    } catch (ActivityNotFoundException e) {
                        Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
                    }
                }
            });
        }
    }

    public static void zzb(FrameLayout frameLayout) {
        zza(frameLayout, GoogleApiAvailability.getInstance());
    }

    private void zznj(int i) {
        while (!this.f474PV.isEmpty() && ((C0746zza) this.f474PV.getLast()).getState() >= i) {
            this.f474PV.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, (C0746zza) new C0746zza() {
            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f472PT.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, (C0746zza) new C0746zza() {
            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.f472PT.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.f472PT == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.f472PT != null) {
            this.f472PT.onDestroy();
        } else {
            zznj(1);
        }
    }

    public void onDestroyView() {
        if (this.f472PT != null) {
            this.f472PT.onDestroyView();
        } else {
            zznj(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, (C0746zza) new C0746zza() {
            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f472PT.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.f472PT != null) {
            this.f472PT.onLowMemory();
        }
    }

    public void onPause() {
        if (this.f472PT != null) {
            this.f472PT.onPause();
        } else {
            zznj(5);
        }
    }

    public void onResume() {
        zza((Bundle) null, (C0746zza) new C0746zza() {
            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f472PT.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.f472PT != null) {
            this.f472PT.onSaveInstanceState(bundle);
        } else if (this.f473PU != null) {
            bundle.putAll(this.f473PU);
        }
    }

    public void onStart() {
        zza((Bundle) null, (C0746zza) new C0746zza() {
            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f472PT.onStart();
            }
        });
    }

    public void onStop() {
        if (this.f472PT != null) {
            this.f472PT.onStop();
        } else {
            zznj(4);
        }
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzf<T> zzf);

    public T zzbdo() {
        return this.f472PT;
    }
}
