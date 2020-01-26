package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.zzc.zza;

@SuppressLint({"NewApi"})
public final class zzb extends zza {

    /* renamed from: Qd */
    private Fragment f487Qd;

    private zzb(Fragment fragment) {
        this.f487Qd = fragment;
    }

    public static zzb zza(Fragment fragment) {
        if (fragment != null) {
            return new zzb(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.f487Qd.getArguments();
    }

    public int getId() {
        return this.f487Qd.getId();
    }

    public boolean getRetainInstance() {
        return this.f487Qd.getRetainInstance();
    }

    public String getTag() {
        return this.f487Qd.getTag();
    }

    public int getTargetRequestCode() {
        return this.f487Qd.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.f487Qd.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzac(this.f487Qd.getView());
    }

    public boolean isAdded() {
        return this.f487Qd.isAdded();
    }

    public boolean isDetached() {
        return this.f487Qd.isDetached();
    }

    public boolean isHidden() {
        return this.f487Qd.isHidden();
    }

    public boolean isInLayout() {
        return this.f487Qd.isInLayout();
    }

    public boolean isRemoving() {
        return this.f487Qd.isRemoving();
    }

    public boolean isResumed() {
        return this.f487Qd.isResumed();
    }

    public boolean isVisible() {
        return this.f487Qd.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.f487Qd.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.f487Qd.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.f487Qd.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.f487Qd.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.f487Qd.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.f487Qd.startActivityForResult(intent, i);
    }

    public void zzac(zzd zzd) {
        this.f487Qd.registerForContextMenu((View) zze.zzae(zzd));
    }

    public void zzad(zzd zzd) {
        this.f487Qd.unregisterForContextMenu((View) zze.zzae(zzd));
    }

    public zzd zzbdp() {
        return zze.zzac(this.f487Qd.getActivity());
    }

    public zzc zzbdq() {
        return zza(this.f487Qd.getParentFragment());
    }

    public zzd zzbdr() {
        return zze.zzac(this.f487Qd.getResources());
    }

    public zzc zzbds() {
        return zza(this.f487Qd.getTargetFragment());
    }
}
