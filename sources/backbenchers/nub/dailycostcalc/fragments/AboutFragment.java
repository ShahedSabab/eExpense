package backbencers.nub.dailycostcalc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import backbencers.nub.dailycostcalc.C0374R;

public class AboutFragment extends Fragment {
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getActivity().getApplicationContext();
        getActivity().setTitle("About");
        return inflater.inflate(C0374R.layout.fragment_about, container, false);
    }
}
