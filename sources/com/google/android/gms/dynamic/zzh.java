package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.zzc.zza;

public final class zzh extends zza {

    /* renamed from: Qg */
    private Fragment f490Qg;

    private zzh(Fragment fragment) {
        this.f490Qg = fragment;
    }

    public static zzh zza(Fragment fragment) {
        if (fragment != null) {
            return new zzh(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.f490Qg.getArguments();
    }

    public int getId() {
        return this.f490Qg.getId();
    }

    public boolean getRetainInstance() {
        return this.f490Qg.getRetainInstance();
    }

    public String getTag() {
        return this.f490Qg.getTag();
    }

    public int getTargetRequestCode() {
        return this.f490Qg.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.f490Qg.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzac(this.f490Qg.getView());
    }

    public boolean isAdded() {
        return this.f490Qg.isAdded();
    }

    public boolean isDetached() {
        return this.f490Qg.isDetached();
    }

    public boolean isHidden() {
        return this.f490Qg.isHidden();
    }

    public boolean isInLayout() {
        return this.f490Qg.isInLayout();
    }

    public boolean isRemoving() {
        return this.f490Qg.isRemoving();
    }

    public boolean isResumed() {
        return this.f490Qg.isResumed();
    }

    public boolean isVisible() {
        return this.f490Qg.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.f490Qg.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.f490Qg.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.f490Qg.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.f490Qg.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.f490Qg.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.f490Qg.startActivityForResult(intent, i);
    }

    public void zzac(zzd zzd) {
        this.f490Qg.registerForContextMenu((View) zze.zzae(zzd));
    }

    public void zzad(zzd zzd) {
        this.f490Qg.unregisterForContextMenu((View) zze.zzae(zzd));
    }

    public zzd zzbdp() {
        return zze.zzac(this.f490Qg.getActivity());
    }

    public zzc zzbdq() {
        return zza(this.f490Qg.getParentFragment());
    }

    public zzd zzbdr() {
        return zze.zzac(this.f490Qg.getResources());
    }

    public zzc zzbds() {
        return zza(this.f490Qg.getTargetFragment());
    }
}
